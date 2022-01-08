package RIPEMD160;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
//import java.util.Scanner;

public class Main {
    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }

    public static void main(String[] args) throws IOException, IllegalAccessException {
        RIPEMD ripemd = new RIPEMD();
        String msg = readFile("C:/Users/andel/Projetos/Scripts/A.in", Charset.defaultCharset());

        String hash = ripemd.getHash(msg);

        System.out.println(hash);
    }
}