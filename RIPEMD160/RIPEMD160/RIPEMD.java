package RIPEMD160;

import static java.lang.Integer.rotateLeft;
import java.util.Arrays;
import java.util.Objects;

public final class RIPEMD {
    private static int BLOCK_LEN = 64;

    private static final int[] KL = {0x00000000, 0x5A827999, 0x6ED9EBA1, 0x8F1BBCDC, 0xA953FD4E};  // Round constants for left line
	private static final int[] KR = {0x50A28BE6, 0x5C4DD124, 0x6D703EF3, 0x7A6D76E9, 0x00000000};  // Round constants for right line
	
	private static final int[] RL = { 
		 0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15,
		 7,  4, 13,  1, 10,  6, 15,  3, 12,  0,  9,  5,  2, 14, 11,  8,
		 3, 10, 14,  4,  9, 15,  8,  1,  2,  7,  0,  6, 13, 11,  5, 12,
		 1,  9, 11, 10,  0,  8, 12,  4, 13,  3,  7, 15, 14,  5,  6,  2,
		 4,  0,  5,  9,  7, 12,  2, 10, 14,  1,  3,  8, 11,  6, 15, 13};
	
	private static final int[] RR = { 
		 5, 14,  7,  0,  9,  2, 11,  4, 13,  6, 15,  8,  1, 10,  3, 12,
		 6, 11,  3,  7,  0, 13,  5, 10, 14, 15,  8, 12,  4,  9,  1,  2,
		15,  5,  1,  3,  7, 14,  6,  9, 11,  8, 12,  2, 10,  0,  4, 13,
		 8,  6,  4,  1,  3, 11, 15,  0,  5, 12,  2, 13,  9,  7, 10, 14,
		12, 15, 10,  4,  1,  5,  8,  7,  6,  2, 13, 14,  0,  3,  9, 11};
	
	private static final int[] SL = {  
		11, 14, 15, 12,  5,  8,  7,  9, 11, 13, 14, 15,  6,  7,  9,  8,
		 7,  6,  8, 13, 11,  9,  7, 15,  7, 12, 15,  9, 11,  7, 13, 12,
		11, 13,  6,  7, 14,  9, 13, 15, 14,  8, 13,  6,  5, 12,  7,  5,
		11, 12, 14, 15, 14, 15,  9,  8,  9, 14,  5,  6,  8,  6,  5, 12,
		 9, 15,  5, 11,  6,  8, 13, 12,  5, 12, 13, 14, 11,  8,  5,  6};
	
	private static final int[] SR = {
		 8,  9,  9, 11, 13, 15, 15,  5,  7,  7,  8, 11, 14, 14, 12,  6,
		 9, 13, 15,  7, 12,  8,  9, 11,  7,  7, 12,  7,  6, 15, 13, 11,
		 9,  7, 15, 11,  8,  6,  6, 14, 12, 13,  5, 14, 13, 13,  7,  5,
		15,  5,  8, 11, 14, 14,  6, 14,  6,  9, 12,  9, 12,  5, 15,  8,
		 8,  5, 12,  9, 12,  5, 14,  6,  8, 13,  6,  5, 15, 13, 11, 11};
	
    private static int f (int i, int x, int y, int z) {
        assert 0 <= i && i < 80;
        if (i < 16) return x ^ y ^ z;
		if (i < 32) return (x & y) | (~x & z);
		if (i < 48) return (x | ~y) ^ z;
		if (i < 64) return (x & z) | (y & ~z);
		return x ^ (y | ~z);
    }

    private static void compress (int[] state, byte[] blocks, int len) throws IllegalAccessException {
        if (len % BLOCK_LEN != 0) {
            throw new IllegalAccessException();
        }

        for (int idx = 0; idx < len; idx += BLOCK_LEN) {
            int[] schedule = new int[16];

            for (int idy = 0; idy < BLOCK_LEN; idy++) {
                schedule[idy / 4] |= (blocks[idx + idy] & 0xFF) << (idy % 4 * 8);
            }

            int al = state[0], ar = state[0];
            int bl = state[1], br = state[1];
            int cl = state[2], cr = state[2];
            int dl = state[3], dr = state[3];
            int el = state[4], er = state[4];

            for (int idy = 0; idy < 80; idy++) {
                int i = al + f(idy, bl, cl, dl) + schedule[RL[idy]] + KL[idy / 16];
                int tmp = rotateLeft(i, SL[idy]) + el;
                al = el; el = dl;
                dl = rotateLeft(cl, 10);
                cl = bl; bl = tmp;

                i = ar + f(79 - idy, br, cr, dr) + schedule[RR[idy]] + KR[idy / 16];
                tmp = rotateLeft(i, SR[idy]) + er;
                ar = er; er = dr;
                dr = rotateLeft(cr, 10);
                cr = br; br = tmp;
            }

            int tmp = state[1] + cl + dr;
			state[1] = state[2] + dl + er;
			state[2] = state[3] + el + ar;
			state[3] = state[4] + al + br;
			state[4] = state[0] + bl + cr;
			state[0] = tmp;
        }
    }

    public String getHash (String text) throws IllegalAccessException {
        byte[] msg = text.getBytes();
        Objects.requireNonNull(msg);

        int[] state = {0x67452301, 0xEFCDAB89, 0x98BADCFE, 0x10325476, 0xC3D2E1F0};
        int off = msg.length / BLOCK_LEN * BLOCK_LEN;

        compress(state, msg, off);

        byte[] block = new byte[BLOCK_LEN];
		System.arraycopy(msg, off, block, 0, msg.length - off);
		off = msg.length % block.length;
		block[off] = (byte) 0x80;
		off++;

		if (off + 8 > block.length) {
			compress(state, block, block.length);
			Arrays.fill(block, (byte)0);
		}

		long len = (long)msg.length << 3;

		for (int i = 0; i < 8; i++)
			block[block.length - 8 + i] = (byte)(len >>> (i * 8));

		compress(state, block, block.length);
		
		byte[] result = new byte[state.length * 4];

		for (int i = 0; i < result.length; i++)
			result[i] = (byte)(state[i / 4] >>> (i % 4 * 8));

		return byte2string(result);
    }

    private static String byte2string (byte[] hash) {
        String ans = "";
        for (byte b: hash) {
            ans += String.format("%02x", b);
        }
        return ans;
    }
}