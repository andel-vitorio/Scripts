package Cipher;

public class Main {
    public static void main(String[] args) {
        String xpin = "Andel Vitorio";
        String PBkey;
        //String PVkey = "vAV½¾m&âN8	_.ßgXf0¿xæ²ÉÐfÁ";
        String PVkey_hex = "7EF170B55CAA5FF598DF9B12768270FFB1062869DB92AAA447CDF5AD10094B11".toLowerCase();


        Keygen pubKey = new Keygen();
        PVkey_hex = SHA256G.SHA256STR(xpin); //Gera um HASH SHA256 a partir de uma string de entrada xpin que é usada como sendo uma CHAVE PRIVADA da curva eliptica SECP256K1	
        System.out.println(PVkey_hex.toUpperCase());
        //PBkey = pubKey.publicKey(PVkey); // Gera uma chave publica SECP256K1 a partir de uma chave privada.
        PBkey = pubKey.publicKeyHEX(PVkey_hex);
        
        System.out.println(PBkey);

        String output = pubKey.bsvWallet(PBkey); //Gera um endereço BSV a partir de uma chave publica SECP256K1;
        System.out.println(output);
    }
}