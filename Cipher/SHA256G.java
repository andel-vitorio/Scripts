package Cipher;

import java.util.Arrays;

public class SHA256G {

    private static long[] Kzh = {
            0x428A2F98L, 0x71374491L, 0xB5C0FBCFL, 0xE9B5DBA5L,
            0x3956C25BL, 0x59F111F1L, 0x923F82A4L, 0xAB1C5ED5L,
            0xD807AA98L, 0x12835B01L, 0x243185BEL, 0x550C7DC3L,
            0x72BE5D74L, 0x80DEB1FEL, 0x9BDC06A7L, 0xC19BF174L,
            0xE49B69C1L, 0xEFBE4786L, 0x0FC19DC6L, 0x240CA1CCL,
            0x2DE92C6FL, 0x4A7484AAL, 0x5CB0A9DCL, 0x76F988DAL,
            0x983E5152L, 0xA831C66DL, 0xB00327C8L, 0xBF597FC7L,
            0xC6E00BF3L, 0xD5A79147L, 0x06CA6351L, 0x14292967L,
            0x27B70A85L, 0x2E1B2138L, 0x4D2C6DFCL, 0x53380D13L,
            0x650A7354L, 0x766A0ABBL, 0x81C2C92EL, 0x92722C85L,
            0xA2BFE8A1L, 0xA81A664BL, 0xC24B8B70L, 0xC76C51A3L,
            0xD192E819L, 0xD6990624L, 0xF40E3585L, 0x106AA070L,
            0x19A4C116L, 0x1E376C08L, 0x2748774CL, 0x34B0BCB5L,
            0x391C0CB3L, 0x4ED8AA4AL, 0x5B9CCA4FL, 0x682E6FF3L,
            0x748F82EEL, 0x78A5636FL, 0x84C87814L, 0x8CC70208L,
            0x90BEFFFAL, 0xA4506CEBL, 0xBEF9A3F7L, 0xC67178F2L
    };

    private static long[] H0 = {
            0x6A09E667L, 0xBB67AE85L, 0x3C6Ef372L, 0xA54FF53AL,
            0x510E527FL, 0x9B05688CL, 0x1F83D9ABL, 0x5BE0CD19L
    };

    public static int sha_256 (long[] W, long[] Hi0, long[] Hi) {
        long A, B, C, D, E, F, G, H, temp1, temp2;

        for (int t = 16; t < 64; t++)
            W[t] = setRange(S1(W[t - 2]) + W[t - 7] + S0(W[t - 15]) + W[t - 16]);
        
        temp1 = setRange(Hi0[7] + S3(Hi0[4]) + CH(Hi0[4], Hi0[5], Hi0[6]) + Kzh[0] + W[0]);
        temp2 = setRange(S2(Hi0[0]) + MAJ(Hi0[0], Hi0[1], Hi0[2]));

        H = Hi0[6]; G = Hi0[5]; F = Hi0[4];
        E = setRange(Hi0[3] + temp1);
        D = Hi0[2]; C = Hi0[1]; B = Hi0[0];
        A = setRange(temp1 + temp2);

        for (int t = 1; t < 63; t++) {
            temp1 = setRange(H + S3(E) + CH(E, F, G) + Kzh[t] + W[t]);
            temp2 = setRange(S2(A) + MAJ(A,B,C));
            
            H = G; G = F; F = E;
            E = setRange(D + temp1);
            D = C; C = B; B = A;
            A = setRange(temp1 + temp2);
        }
        
        temp1 = setRange(H + S3(E) + CH(E, F, G) + Kzh[63] + W[63]);
        temp2 = setRange(S2(A) + MAJ(A, B, C));
        Hi[0] = setRange((temp1 + temp2) + Hi0[0]);
        Hi[1] = setRange(A + Hi0[1]);
        Hi[2] = setRange(B + Hi0[2]);
        Hi[3] = setRange(C + Hi0[3]);
        Hi[4] = setRange((D + temp1) + Hi0[4]);
        Hi[5] = setRange(E + Hi0[5]);
        Hi[6] = setRange(F + Hi0[6]);
        Hi[7] = setRange(G + Hi0[7]);

        return 0;
    }

    public static long setRange (long val) {
        return (val & 0xFFFFFFFFL);
    }
    // Shift right
    public static long SHR (long x, long n) {
        return ((x & 0xFFFFFFFFL) >> n);
    }

    public static long ROTR (long x, int n) {
        long tmp1 = setRange(x << (32 - n));
        return (SHR(x,n) | tmp1);
    }
    // S0 and S1
    public static long S0 (long x) {
        return (ROTR(x, 7) ^ ROTR(x,18) ^ SHR(x, 3));
    }
    public static long S1 (long x) {
        return (ROTR(x,17) ^ ROTR(x,19) ^ SHR(x,10));
    }
    // Σ0 and Σ1
    public static long S2 (long x) {
        return (ROTR(x, 2) ^ ROTR(x,13) ^ ROTR(x,22));
    }

    public static long S3 (long x) {
        return (ROTR(x, 6) ^ ROTR(x,11) ^ ROTR(x,25));
    }
    // Maj and Ch
    public static long MAJ (long x, long y, long z) {
        return ((x & y) | (z & (x | y)));
    }

    public static long CH (long x, long y, long z) {
        return (z ^ (x & (y ^ z)));
    }

    public static byte[] HashStrToByte (String hashKey) {
        byte[] result  = new byte[hashKey.length()];
        char[] strChar = new char[hashKey.length()];
        strChar = hashKey.toCharArray();

        for (int i = 0; i < strChar.length; i++) {
            int digHex = Character.digit(strChar[i], 16);
            result[i] = (byte) digHex;
        }

        return result;
    }
    //Metodo usado para transforma um HASH SHA256 de String para Bytes
    public static byte[] HashStrToByte2(String hashKey) {
        byte[] result  = new byte[hashKey.length()];
        byte[] result2 = new byte[hashKey.length()/2];
        char[] strChar = new char[hashKey.length()];
        strChar = hashKey.toCharArray();
        
        for (int i = 0; i < strChar.length; i++) {
            int digHex = Character.digit(strChar[i], 16);
            result[i]  = (byte) digHex;
        }

        for (int i = (hashKey.length()/2) - 1; i >= 0; i--)
            result2 [i] = (byte) (result[2 * i + 1] + result[2 * i] * 0x10);

        return  result2;
    }
    //Metodo usado para transforma um HASH SHA256 de String para Bytes
    public static byte[] StrToByte(String hashKey)  {
        byte[] result  = new byte[hashKey.length()];
        char[] strChar = new char[hashKey.length()];
        strChar = hashKey.toCharArray();

        for (int i = 0; i < hashKey.length(); i++)
           result[i] = (byte) strChar[i];

        return result;
    }
    //Metodo usado para transforma um HASH SHA256 de bytes para String
    public static String HashByteToStr(byte[] hashKey) {
        String result = "";

        for (int i = 0; i < hashKey.length; i++)
            result += Integer.toHexString((int) hashKey[i]);

        return result;
    }
    //Metodo usado para construir um Hash SHA256 a partir de uma Stream de Bytes
    public static String SHA256bytes(byte[] txtcomp) {
        long[] W  = new long[64];
        long[] Hi = new long[8];

        Arrays.fill( W, 0x00000000); W[15] = 0x00000008;
        Arrays.fill(Hi, 0x00000000);

        if (txtcomp.length > 0) {
            char[] result = new char[64];

            boolean firstCyle = true;
            int i = 0;

            for (; i < txtcomp.length - (txtcomp.length % 64); i = i + 64) {
                //verificar se será necessário acressentar a metodologia de remoção de sinal
                for (int k = 0; k < 16; k++)
                    W[k] = (0x01000000L * (long) (txtcomp[(i + 0) + 4 * k] & 0x000000FFL)) + (0x00010000L * (long) (txtcomp[(i + 1) + 4 * k] & 0x000000FFL))
                         + (0x00000100L * (long) (txtcomp[(i + 2) + 4 * k] & 0x000000FFL)) + (0x00000001L * (long) (txtcomp[(i + 3) + 4 * k] & 0x000000FFL));

                if (firstCyle) {
                    sha_256 (W, H0, Hi);
                    firstCyle = false;
                }
                else sha_256 (W, Hi, Hi);
            }

            Arrays.fill(W, 0x00000000);
            int j = i, k = 0;

            for(; j < txtcomp.length; j++) {
                W[k] += ((long) 0x01000000L >> ((j % 4) * 8)) * ((long) (txtcomp[j] & 0x000000FFL));
                if (j % 4 == 3) k++;
            }
            
            if ((j - 1) % 4 == 3) W[k] = 0x80000000L;
            else W[k] += (long) 0x00800000L >> (((j - 1) % 4) * 8);

            if(txtcomp.length % 64 < 56) {
                W[14]  = 0x00000000L;
                W[15]  = 0x00000001L * (long) (8 * txtcomp.length);

                if(firstCyle) {
                    sha_256 (W, H0, Hi);
                    firstCyle = false;
                }
                else sha_256 (W, Hi, Hi);
            } else {
                W[15]  = 0x00000000L;

                if (firstCyle) {
                    sha_256 (W, H0, Hi);
                    firstCyle = false;
                }
                else sha_256 (W, Hi, Hi);

                Arrays.fill(W, 0x00000000L);
                W[15] = 0x00000001L * (long) (8 * txtcomp.length);

                sha_256 (W, Hi, Hi);
            }

            for (i = 0; i < 8; i++)
                for (int x = 0; x < 8; x++)
                    result[8 * i + x] = (char) ((Hi[i]/ ((long) 0x10000000L >> (4 * x))) & 0x0000000FL);

            String result2 = "";
            for (i = 0; i < 64; i++)
                result2 += Integer.toHexString((int) result[i]);

            return result2;
        }

        return null;
    }
    //Metodo usado para construir um Hash SHA256 a partir de uma String
    public static String SHA256STR (String strIn) {
        long[] W  = new long[64];
        long[] Hi = new long[8];

        Arrays.fill(W, 0x00000000);
        Arrays.fill(Hi, 0x00000000);
        W[15] = 0x00000008;

        if (strIn.length() > 0) {
            char[] txtcomp = new char[strIn.length()];
            char[] result  = new char[64];
            txtcomp = strIn.toCharArray();

            boolean firstCyle = true;
            int i = 0;

            //verificar se será necessário acressentar a metodologia de remoção de sinal
            //por ser string não teria problemas, pois é de um tipo que não causa problemas
            for(; i < strIn.length() - (strIn.length() % 64); i = i + 64) {
                for (int k = 0; k < 16; k++)
                    W[k] = (0x01000000L * (long) (txtcomp[(i + 0) + 4 * k] & 0x000000FFL)) + (0x00010000L * (long) (txtcomp[(i + 1) + 4 * k] & 0x000000FFL))
                         + (0x00000100L * (long) (txtcomp[(i + 2) + 4 * k] & 0x000000FFL)) + (0x00000001L * (long) (txtcomp[(i + 3) + 4 * k] & 0x000000FFL));

                if(firstCyle) {
                    sha_256 (W, H0, Hi);
                    firstCyle = false;
                }
                else sha_256 (W, Hi, Hi);
            }

            Arrays.fill(W, 0x00000000);
            int j = i, k = 0;

            for (; j < strIn.length(); j++) {
                W[k] += ((long) 0x01000000L >> ((j % 4) * 8)) * ((long) (txtcomp[j] & 0x000000FFL));
                if (j % 4 == 3) k++;
            }

            if ((j - 1) % 4 == 3) W[k] = 0x80000000L;
            else W[k] += (long) 0x00800000L >> (((j - 1) % 4) * 8);

            if (strIn.length() % 64 < 56) {
                W[14]  = 0x00000000L;
                W[15]  = 0x00000001L * (long) (8 * strIn.length());

                if(firstCyle) {
                    sha_256(W, H0, Hi);
                    firstCyle = false;
                }
                else sha_256 (W, Hi, Hi);
            } else {
                W[15]  = 0x00000000L;

                if(firstCyle) {
                    sha_256(W, H0, Hi);
                    firstCyle = false;
                }
                else sha_256 (W, Hi, Hi);

                Arrays.fill(W, 0x00000000L);
                W[15] = 0x00000001L * (long) (8 * strIn.length());

                sha_256 (W, Hi, Hi);
            }

            for (i = 0; i < 8; i++)
                for (int x = 0; x < 8; x++)
                    result[8 * i + x] = (char) ((Hi[i]/ ((long) 0x10000000L >> (4 * x))) & 0x0000000FL);

            String result2 = "";
            for (i = 0; i < 64; i++)
                result2 += Integer.toHexString((int) result[i]);
        
            return result2;
        }

        return null;
    }
    //Metodo usado para construir um Hash com H0 diferente
    //Entradas podem ser mensagem e H0 diferentes
    //Hx precisa ter 32 bytes;
    public static String SHA256MsnHx(String strIn, String Hx) {
        long[] W   = new long[64];
        long[] Hi  = new long[8];
        long[] H0x = new long[8];
        
        Arrays.fill(  W, 0x00000000L); W[15] = 0x00000008L;
        Arrays.fill( Hi, 0x00000000L);
        Arrays.fill(H0x, 0x00000000L);


        if (strIn.length() > 0) {
            char[] txtcomp = new char[strIn.length()];
            char[] result = new char[64];
            char[] H0xStr = new char[32];

            txtcomp = strIn.toCharArray();
            H0xStr = Hx.toCharArray();

            for (int x = 0; x < 8; x++) {
                H0x[x] = (0x01000000L * (long) H0xStr[x    ]) + (0x00010000L * (long) H0xStr[x + 1])
                       + (0x00000100L * (long) H0xStr[x + 2]) + (0x00000001L * (long) H0xStr[x + 3]);
            }

            boolean firstCyle = true;
            int i = 0;

            for (; i < strIn.length() - (strIn.length() % 64); i = i + 64)
            {
                for (int k = 0; k < 16; k++) {
                    W[k] = (0x01000000L * (long) (txtcomp[(i    ) + 4 * k])) + (0x00010000L * (long) (txtcomp[(i + 1) + 4 * k]))
                         + (0x00000100L * (long) (txtcomp[(i + 2) + 4 * k])) + (0x00000001L * (long) (txtcomp[(i + 3) + 4 * k]));
                }

                if (firstCyle) {
                    sha_256(W, H0x, Hi);
                    firstCyle = false;
                }
                else sha_256 (W, Hi, Hi);
            }

            Arrays.fill(W, 0x00000000L);
            int j = i, k = 0;

            for (; j < strIn.length(); j++) {
                W[k] += ((long) 0x01000000L >> ((j % 4) * 8)) * ((long) (txtcomp[j]));
                if (j % 4 == 3) k++;
            }
            
            if ((j - 1) % 4 == 3) W[k] = 0x80000000L;
            else W[k] += (long) 0x00800000L >> (((j - 1) % 4) * 8);

            if (strIn.length() % 64 < 56) {
                W[14]  = 0x00000000L;
                W[15]  = 0x00000001L * (long) (8 * strIn.length());

                if(firstCyle) {
                    sha_256(W, H0x, Hi);
                    firstCyle = false;
                }
                else sha_256 (W, Hi, Hi);
            } else {
                W[15]  = 0x00000000L;

                if(firstCyle) {
                    sha_256(W, H0x, Hi);
                    firstCyle = false;
                }
                else sha_256 (W, Hi, Hi);

                Arrays.fill(W, 0x00000000L);
                W[15] = 0x00000001L * (long) (8 * strIn.length());

                sha_256 (W, Hi, Hi);
            }

            for (i = 0; i < 8; i++)
                for (int x = 0; x < 8; x++)
                    result[8 * i + x] = (char) ((Hi[i]/ ((long) 0x10000000L >> (4 * x))) & 0x0000000FL);

            String result2 = "";
            for (i = 0; i < 64; i++)
                result2 += Integer.toHexString((int) result[i]);
        
            return result2;
        }

        return null;
    }
    //Para realizar testes de convergencia
    public static String H0String ()
    {
        char[] result = new char[64];

        for (int i = 0; i < 8; i++)
                for (int x = 0; x < 8; x++)
                    result[8 * i + x] = (char) ((H0[i]/ ((long) 0x10000000L >> (4 * x))) & 0x0000000FL);

        String result2 = "";
        for (int i = 0; i < 64; i++)
             result2 += Integer.toHexString((int) result[i]);
                
        return result2;
    }
    //Metodo usado para construir um Hash com H0 diferente
    //Entradas podem ser mensagem e H0 diferentes
    //Hx precisa ter 64 bytes;
    public static String SHA256MsnHxHex(byte[] txtcomp, byte[] Hx) {
        long[] W   = new long[64];
        long[] Hi  = new long[8];
        long[] H0x = new long[8];
        
        Arrays.fill(  W, 0x00000000L); W[15] = 0x00000008L;
        Arrays.fill( Hi, 0x00000000L);
        Arrays.fill(H0x, 0x00000000L);

        if ((txtcomp.length > 0) && (Hx.length == 64)) {
            char[] result = new char[64];
            
            for (int i = 0; i < 8; i++)
                for (int x = 0; x < 8; x++)
                    H0x[i] += ((long) (0x10000000L >> x) * (long) Hx[8 * i + x]);

            int i = 0;
            boolean firstCyle = true;

            for (; i < txtcomp.length - (txtcomp.length % 64); i = i + 64) {
                for (int k = 0; k < 16; k++) {
                    W[k] = (0x01000000L * (long) (txtcomp[(i    ) + 4 * k] & 0x000000FFL)) + (0x00010000L * (long) (txtcomp[(i + 1) + 4 * k] & 0x000000FFL))
                         + (0x00000100L * (long) (txtcomp[(i + 2) + 4 * k] & 0x000000FFL)) + (0x00000001L * (long) (txtcomp[(i + 3) + 4 * k] & 0x000000FFL));
                }

                if (firstCyle) {
                    sha_256(W, H0x, Hi);
                    firstCyle = false;
                }
                else sha_256 (W, Hi, Hi);
            }

            Arrays.fill(W, 0x00000000L);
            int j = i, k = 0;

            for (; j < txtcomp.length; j++) {
                W[k] += ((long) 0x01000000L >> ((j % 4) * 8)) * ((long) (txtcomp[j] & 0x000000FFL));
                if (j % 4 == 3) k++;
            }
            
            if ((j - 1) % 4 == 3) W[k] = 0x80000000L;
            else W[k] += (long) 0x00800000L >> (((j - 1) % 4) * 8);

            if (txtcomp.length % 64 < 56) {
                W[14]  = 0x00000000L;
                W[15]  = 0x00000001L * (long) (8 * txtcomp.length);

                if(firstCyle) {
                    sha_256(W, H0x, Hi);
                    firstCyle = false;
                }
                else sha_256 (W, Hi, Hi);
            } else {
                W[15]  = 0x00000000L;

                if(firstCyle) {
                    sha_256(W, H0x, Hi);
                    firstCyle = false;
                }
                else sha_256 (W, Hi, Hi);

                Arrays.fill(W, 0x00000000L);
                W[15] = 0x00000001L * (long) (8 * txtcomp.length);

                sha_256 (W, Hi, Hi);
            }
            
            for (i = 0; i < 8; i++)
                for (int x = 0; x < 8; x++)
                    result[8 * i + x] = (char) ((Hi[i]/ ((long) 0x10000000L >> (4 * x))) & 0x0000000FL);

            String result2 = "";
            for (i = 0; i < 64; i++)
                result2 += Integer.toHexString((int) result[i]);
        
            return result2;
        }

        return null;
    }
    //Metodo usado para construir um Hash com H0 diferente
    //Entradas podem ser mensagem e H0 diferentes
    //Hx precisa ter 32 bytes;
    public static String SHA256MsnHxHex2(byte[] txtcomp, byte[] Hx)
    {
        long[] W   = new long[64];
        long[] Hi  = new long[8];
        long[] H0x = new long[8];
        
        Arrays.fill(  W, 0x00000000L); W[15] = 0x00000008L;
        Arrays.fill( Hi, 0x00000000L);
        Arrays.fill(H0x, 0x00000000L);

        if ((txtcomp.length > 0) && (Hx.length == 32)) {
            char[] result = new char[64];

            for (int x = 0; x < 8; x++) {
                H0x[x] = (0x01000000L * (long) (Hx[4 * x    ] & 0x00000000FFL)) + (0x00010000L * (long) (Hx[4 * x + 1] & 0x00000000FFL))
                       + (0x00000100L * (long) (Hx[4 * x + 2] & 0x00000000FFL)) + (0x00000001L * (long) (Hx[4 * x + 3] & 0x00000000FFL));
            }

            int i = 0;
            boolean firstCyle = true;

            for (; i < txtcomp.length - (txtcomp.length % 64); i = i + 64) {
                for (int k = 0; k < 16; k++)
                    W[k] = (0x01000000L * (long) (txtcomp[(i) + 4 * k] & 0x000000FFL)) + (0x00010000L * (long) (txtcomp[(i + 1) + 4 * k] & 0x000000FFL))
                         + (0x00000100L * (long) (txtcomp[(i + 2) + 4 * k] & 0x000000FFL)) + (0x00000001L * (long) (txtcomp[(i + 3) + 4 * k] & 0x000000FFL));

                if(firstCyle) {
                    sha_256(W, H0x, Hi);
                    firstCyle = false;
                }
                else sha_256 (W, Hi, Hi);
            }
            
            Arrays.fill(W, 0x00000000L);
            int j = i, k = 0;

            for (; j < txtcomp.length; j++) {
                W[k] += ((long) 0x01000000L >> ((j % 4) * 8)) * ((long) (txtcomp[j]));
                if (j % 4 == 3) k++;
            }

            if ((j - 1) % 4 == 3) W[k] = 0x80000000L;
            else W[k] += (long) 0x00800000L >> (((j - 1) % 4) * 8);


            if (txtcomp.length % 64 < 56) {
                W[14]  = 0x00000000L;
                W[15]  = 0x00000001L * (long) (8 * txtcomp.length);

                if(firstCyle) {
                    sha_256(W, H0x, Hi);
                    firstCyle = false;
                }
                else sha_256 (W, Hi, Hi);
            } else {
                W[15]  = 0x00000000L;

                if(firstCyle) {
                    sha_256(W, H0x, Hi);
                    firstCyle = false;
                }
                else sha_256 (W, Hi, Hi);

                Arrays.fill(W, 0x00000000L);
                W[15] = 0x00000001L * (long) (8 * txtcomp.length);

                sha_256 (W, Hi, Hi);
            }
            
            for (i = 0; i < 8; i++)
                for (int x = 0; x < 8; x++)
                    result[8 * i + x] = (char) ((Hi[i]/ ((long) 0x10000000L >> (4 * x))) & 0x0000000FL);

            String result2 = "";
            for (i = 0; i < 64; i++)
                result2 += Integer.toHexString((int) result[i]);
        
            return result2;
        }

        return null;
    }

    public static String H0000000(byte[] txtcomp, byte[] Hx) {
        long[] W   = new long[64];
        long[] Hi  = new long[8];
        long[] H0x = new long[8];
        
        Arrays.fill(  W, 0x00000000L); W[15] = 0x00000008L;
        Arrays.fill( Hi, 0x00000000L);
        Arrays.fill(H0x, 0x00000000L);


        if ((txtcomp.length > 0) && (Hx.length == 32)) {
            char[] result = new char[64];
            
            for (int i = 0; i < 8; i++) {
                H0x[i] = (0x01000000L * (long) (Hx[4 * i + 0] & 0x00000000FFL)) + (0x00010000L * (long) (Hx[4 * i + 1] & 0x00000000FFL))
                       + (0x00000100L * (long) (Hx[4 * i + 2] & 0x00000000FFL)) + (0x00000001L * (long) (Hx[4 * i + 3] & 0x00000000FFL));
            }

            for (int i = 0; i < 8; i++)
                for (int x = 0; x < 8; x++)
                    result[8 * i + x] = (char) ((H0x[i]/ ((long) 0x10000000L >> (4 * x))) & 0x0000000FL);

            String result2 = "";
            for (int i = 0; i < 64; i++)
                result2 += Integer.toHexString((int) result[i]);
        
            return result2;
        }

        return null;
    }


    public static void main_to_test(String[] args) {
        long[] W = new long[64];
        long[] Hi = new long[8];

        Arrays.fill(  W, 0x00000000L); W[15] = 0x00000008L;
        Arrays.fill( Hi, 0x00000000L);

        sha_256 (W, H0, Hi);
        System.out.printf("%x %x %x %x %x %x %x %x \n", Hi[0], Hi[1], Hi[2], Hi[3], Hi[4], Hi[5], Hi[6], Hi[7]);

        // Para String de 448 bits 56 letras A
        // AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        // O PAD deve ser feito na segunda iteracao, pois nao sobrarah 64 bits,
        // necessarios para indicar a quantidade de bits da mensagem

        W[0] = 0x41414141L; W[1] = 0x41414141L; W[2] = 0x41414141L; W[3] = 0x41414141L;
        W[4] = 0x41414141L; W[5] = 0x41414141L; W[6] = 0x41414141L; W[7] = 0x41414141L;
        W[8] = 0x41414141L; W[9] = 0x41414141L; W[10] = 0x41414141L; W[11] = 0x41414141L;
        W[12] = 0x41414141L; W[13] = 0x41414141L; W[14] = 0x80000000L; W[15] = 0x00000000L;

        // Calcula SHA256 de W resposta em Hi
        sha_256 (W, H0, Hi);

        W[0] = 0x00000000; W[1] = 0x00000000; W[2] = 0x00000000; W[3] = 0x00000000;
        W[4] = 0x00000000; W[5] = 0x00000000; W[6] = 0x00000000; W[7] = 0x00000000;
        W[8] = 0x00000000; W[9] = 0x00000000; W[10] = 0x00000000; W[11] = 0x00000000;
        W[12] = 0x00000000; W[13] = 0x00000000; W[14] = 0x00000000; W[15] = 0x000001c0L;

        sha_256 (W, Hi, Hi);
        System.out.printf("%x %x %x %x %x %x %x %x \n", Hi[0], Hi[1], Hi[2], Hi[3], Hi[4], Hi[5], Hi[6], Hi[7]);

        // Para String de 448 bits 56 letras A
        // BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB
        // O PAD deve ser feito na segunda iteracao, pois nao sobrarah 64 bits,
        // necessarios para indicar a quantidade de bits da mensagem

        W[0] = 0x42424242L; W[1] = 0x42424242L; W[2] = 0x42424242L; W[3] = 0x42424242L;
        W[4] = 0x42424242L; W[5] = 0x42424242L; W[6] = 0x42424242L; W[7] = 0x42424242L;
        W[8] = 0x42424242L; W[9] = 0x42424242L; W[10] = 0x42424242L; W[11] = 0x42424242L;
        W[12] = 0x42424242L; W[13] = 0x42424242L; W[14] = 0x80000000L; W[15] = 0x00000000L;

        // Calcula SHA256 de W resposta em Hi
        sha_256 (W, H0, Hi);

        W[0] = 0x00000000; W[1] = 0x00000000; W[2] = 0x00000000; W[3] = 0x00000000;
        W[4] = 0x00000000; W[5] = 0x00000000; W[6] = 0x00000000; W[7] = 0x00000000;
        W[8] = 0x00000000; W[9] = 0x00000000; W[10] = 0x00000000; W[11] = 0x00000000;
        W[12] = 0x00000000; W[13] = 0x00000000; W[14] = 0x00000000; W[15] = 0x000001c0L;

        sha_256 (W, Hi, Hi);
        System.out.printf("%x %x %x %x %x %x %x %x \n", Hi[0], Hi[1], Hi[2], Hi[3], Hi[4], Hi[5], Hi[6], Hi[7]);
    }
}