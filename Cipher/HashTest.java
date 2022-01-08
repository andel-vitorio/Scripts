package Cipher;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class HashTest {

    // Notes banco;
    static Boolean state;
    // Encript txtCypher = new Encript();
    static String key;
    static String user;
    static String xpin;
    Keygen genKey = new Keygen();
    private static int codType;

    private char base64[] = { '*', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            '#', 'J', 'K', 'L', 'M', 'N', '%', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c',
            'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', '?', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z', '$', '&' };
    private char base16[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static void main(String[] args) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader("/home/andel/Scripts/input.in"));
        codType = 1;

        ////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////

        //banco = new Notes(this);

        key = buffRead.readLine();
        user = buffRead.readLine();
        xpin = buffRead.readLine();


        //protocoloZero();
        protocoloUm();

        buffRead.close(); 



       // key = getIntent().getExtras().getString("myPass");
        //user = getIntent().getExtras().getString("user");
        //xpin = getIntent().getExtras().getString("PIN");
        //key = getIntent().getExtras().getString("Key");

        //state = ((getIntent().getExtras().getString("Mode")).contains("Deco"));

        //if(state)((TextView)findViewById(R.id.TV_TEXT)).setText("CRIPTOGRAMA:");

        /*state = true;



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //myWebView.loadUrl("file:///android_asset/www/moneyButton.html");
                //myWebView.reload();

                //if(findViewById(R.id.ET_TEXTO)==null) text = "0"

                //if (state) SHA256test(view);
                //if (state) ECDSAtest(view);
                //if (state) BSVwallet(view);
                //if (state) protoocloZero(view);
                if (state) protoocloUm(view);

                //criptografia(view);
            }
        });*/
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //Protocolo para verificação de acesso ao APP
    ///////////////////////////////////////////////////////////////////////////////////
    /*
    private void protocoloZero(View view){

        String text;
        Keygen pubKey = new Keygen();
        TxidDB banco = new TxidDB(HashTest.this);

        state = false;

        text = ((EditText)findViewById(R.id.ET_TEXTO)).getText().toString();
        if (text.length() < 1)    text = "0";

        /////////////////////////////////////////////////////////
        //SENHA - PVTKEY - PROTOCOLO ZERO
        /////////////////////////////////////////////////////////
        String H1, H2, HS, H3, H4, H5;
        H1 = SHA256G.SHA256STR(user+key);
        H2 = SHA256G.SHA256MsnHxHex2(SHA256G.HashStrToByte2(H1),SHA256G.HashStrToByte2(H1));
        HS = SHA256G.SHA256MsnHx(user+key, H2);
        H3 = SHA256G.SHA256MsnHx(user+key, H1);
        H4 = SHA256G.SHA256MsnHxHex2(SHA256G.HashStrToByte2(HS),SHA256G.HashStrToByte2(H3));
        //Utilizado para conferencia de credenciais de acesso ao APP;
        H5 = SHA256G.SHA256MsnHxHex2(SHA256G.HashStrToByte2(H2),SHA256G.HashStrToByte2(H4));
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////

        ((TextView)findViewById(R.id.TV_TEXT)).setText("HASH:");
        //((EditText)findViewById(R.id.ET_TEXTO)).setText("SHA256: "+ SHA256G.SHA256STR(text));
        ((EditText)findViewById(R.id.ET_TEXTO)).setText(
                "TESTES REALIZADOS: "
                        + "\nUser: " + user
                        + "\nKey: " + key
                        + "\nH1: " + H1
                        + "\nH2: " + H2
                        + "\nHS: " + HS
                        + "\nH3: " + H3
                        + "\nH4: " + H4
                        + "\nH5: " + H5
        );
    }*/

    ////////////////////////////////////////////////////////////////////////////////////
    //Protocolo para geração de Chave Privada para Diversas Funções como:
    //  Geração de Chave Pública
    //  Geração de Chaves de Encriptação para dados do Usuário
    //  Geração de Endereços BSV para armazenamento de dados do Usuário
    //  Geração de Endereços BSV para comunicação P2P
    //  ...
    ////////////////////////////////////////////////////////////////////////////////////
    private static void protoocloUm () {
        BufferedReader buffRead = new BufferedReader(new FileReader("/home/andel/Scripts/txt1.txt"));
        String text;
        Keygen pubKey = new Keygen();
        //TxidDB banco = new TxidDB (HashTest.this);

        state = false;

        text = buffRead.toString();
        if (text.length() < 1)    text = "0";

        byte[] testBytes = new byte[text.length()];
        char[] testChar = new char[text.length()];

        //testChar = "Test de conversão de tipo!!!!".toCharArray();
        testChar = text.toCharArray();

        for(int i = 0; i < text.length(); i++)
            testBytes [i] = (byte) testChar [i];

        byte[] resultHash = new byte[64];
        resultHash = SHA256G.HashStrToByte(SHA256G.SHA256bytes(testBytes));

        /////////////////////////////////////////////////////////
        //SENHA - PVTKEY - XPIN - PROTOCOLO UM
        /////////////////////////////////////////////////////////
        String H1, H2, H3, H4;
        String PVTKEY, ENCKUSER;

        H1 = SHA256G.SHA256STR(user+key+xpin);
        H2 = SHA256G.SHA256MsnHxHex2(SHA256G.HashStrToByte2(H1),SHA256G.HashStrToByte2(H1));
        PVTKEY = SHA256G.SHA256MsnHx(user+key+xpin, H2);
        //Chave de Encriptação de DADOS de USUÁRIO:
        ENCKUSER = SHA256G.SHA256MsnHxHex2(SHA256G.HashStrToByte2(H1),SHA256G.HashStrToByte2(H2));
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////

        /////////////////////////////////////////////////////////
        //DUMMY P2P:
        /////////////////////////////////////////////////////////
        String PUBKUSER, PUBKP1, PVTKP1;
        //Chave Publica BASE64 do Usuário
        //  Considerada aqui chave de encriptação P2P
        //  Gera a chave publica a partir de uma string em HEXADECIMAL
        //  A entrada precisa ser o resultado de uma HASH SHA256 - Uma string HEX de 64 elementos;
        PUBKUSER = pubKey.publicKeyHEX(PVTKEY);
        //Chave Privada do Contato para testes
        PVTKP1 = SHA256G.SHA256STR(xpin); //Chave privada DUMMY de contato
        PUBKP1 = pubKey.publicKeyHEX(PVTKP1); //Chave publica DUMMY de contato

        Ecc myEcc = new Ecc();

        BigInteger[] toPubKeyUser = pubKey.pubKeyRev(PUBKUSER);
        BigInteger[] toPubKeyP1 = pubKey.pubKeyRev(PUBKP1);
        //Transoformar a chave privada de HEX para Bigint

        BigInteger secUserKey, secP1Key;
        //Chaves Privadas de Usuário e Contato em BigInteger
        //  Para Geração de Chaves de Encriptação
        secUserKey = pubKey.secKeyHashToBigInt(PVTKEY);
        secP1Key = pubKey.secKeyHashToBigInt(PVTKP1);

        //Geração das chaves de encriptação
        BigInteger []comKey1 = myEcc.eccnP(secUserKey, toPubKeyP1[0], toPubKeyP1[1]);
        BigInteger []comKey2 = myEcc.eccnP(secP1Key, toPubKeyUser[0], toPubKeyUser[1]);

        //UTILIZAR SOMENTA A COORDENADA X OU Y DA CHAVE PUBLICA
        //encKeyToHexSTR() metodo que sua a chave publica completa
        //encKeyHalfToHexSTR metodo que usa a coordenada X ou Y da chave publica
        String ENCKeyUSER = pubKey.encKeyHalfToHexSTR(comKey1[0]);
        String ENCKeyP1 = pubKey.encKeyHalfToHexSTR(comKey2[0]);

        //Chave de Encriptação;
        //Para encriptação de MSN P2P
        String HK1USER = SHA256G.SHA256bytes(SHA256G.HashStrToByte2(ENCKeyUSER));
        String HK1P1 = SHA256G.SHA256bytes(SHA256G.HashStrToByte2(ENCKeyP1));

        //Mensagem Dummy
        String MSN = xpin;

        /////////////////////////////////////////////////////////
        //Primeira chave de Encriptação
        //Testado novamente
        /////////////////////////////////////////////////////////
        String HA1, HA2, HA3, e1, HAX, HAY;
        HA1 = SHA256G.SHA256bytes(SHA256G.HashStrToByte2(PVTKEY));
        HAX = SHA256G.SHA256MsnHxHex2(SHA256G.HashStrToByte2(HA1),SHA256G.HashStrToByte2(HA1));
        HAY = SHA256G.SHA256bytes(SHA256G.HashStrToByte2(HAX));
        HA2 = SHA256G.SHA256MsnHxHex2(SHA256G.HashStrToByte2(HAY),SHA256G.HashStrToByte2(HAY));
        HA3 = SHA256G.SHA256bytes(SHA256G.HashStrToByte2(HA2));
        //A conversão de String to Byte não pode Usar HashStrToByte2, mas StrToByte
        e1 = SHA256G.SHA256MsnHxHex2(SHA256G.StrToByte(MSN),SHA256G.HashStrToByte2(HA3));
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////

        /////////////////////////////////////////////////////////
        //Chave de Encriptação após a Primeira
        /////////////////////////////////////////////////////////
        String e2;
        String lastTXID = banco.LastTXID();
        HA1 = SHA256G.SHA256bytes(SHA256G.HashStrToByte2(PVTKEY));
        HA2 = SHA256G.SHA256MsnHxHex2(SHA256G.HashStrToByte2(lastTXID),SHA256G.HashStrToByte2(HA1));
        HA3 = SHA256G.SHA256bytes(SHA256G.HashStrToByte2(HA2));
        e2 = SHA256G.SHA256MsnHxHex2(SHA256G.StrToByte(MSN),SHA256G.HashStrToByte2(HA3));
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////
        //Nesta Seção é preciso considerar o TXID ou a primeira mensagem enviada
        //Se for a primeira mensagem enviada, pode-se buscar um TXID na rede
        //////////////////////////////////////////////////////////////

        //O receptor não conseguirá decriptar a chave 'e';
        String e = e1;//SHA256G.SHA256MsnHx(MSN, HK1USER);

        //Nestas operações ambos os NIBBLEs serão utilizados
        byte[] eByte = SHA256G.HashStrToByte2(e); //Dummy block;
        byte[] BLK0 = SHA256G.HashStrToByte2(e); //Bloco para Encriptação inicializado;

        //Chave de encriptação entre a dupla:
        byte[] ENCKUSERByte = SHA256G.HashStrToByte2(HK1USER);

        //Java promotes Byte para Int antes de realizar a operação
        //https://stackoverflow.com/questions/28889051/bitwise-operations-in-java-using-byte-and-int
        //Por isso além do cast para (byte) também é importante o limitador 0xFF - QUEBRA DE SINAL
        for(int i = 0; i < e.length()/2 ; i++) BLK0[i]  = (byte)( (eByte[i] & 0xFF)  ^ (ENCKUSERByte[i]& 0xFF));
        //for(int i = 0; i < e.length() ; i++) BLK0[i]  = (byte) ( (eByte[i] & 0xFF)  + (ENCKUSERByte[i]& 0xFF));

        String inputUser = SHA256G.ByteToStrHex(eByte); //Dado a ser encriptado
        String keyUser = SHA256G.ByteToStrHex(ENCKUSERByte); //chave de encriptação
        String BLK0Str = SHA256G.ByteToStrHex(BLK0); //Dado Encriptado

        //DECRIPTACAO

        byte[] ENCKP1Byte = SHA256G.HashStrToByte2(HK1P1);
        byte [] blk0 = new byte[32];

        BLK0 = SHA256G.HashStrToByte2(BLK0Str);

        for(int i = 0; i < BLK0Str.length()/2 ; i++) blk0[i]  = (byte)( (BLK0[i] & 0xFF)  ^ (ENCKP1Byte[i]& 0xFF));

        String inputP1 = SHA256G.ByteToStrHex(BLK0); //Dado a ser decriptaco
        String keyP1 = SHA256G.ByteToStrHex(ENCKP1Byte); //chave de dencriptação
        String blk0Str = SHA256G.ByteToStrHex(blk0); // Dado Decriptado
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////

        ((TextView)findViewById(R.id.TV_TEXT)).setText("HASH:");
        //((EditText)findViewById(R.id.ET_TEXTO)).setText("SHA256: "+ SHA256G.SHA256STR(text));
        ((EditText)findViewById(R.id.ET_TEXTO)).setText(
                "TESTES REALIZADOS: "

                        + "\n\nUsuário: \n"+ user
                        + "\n\nSenha: \n"+ key
                        + "\n\nXPIN == MSN: \n"+ xpin
                        + "\n\nH1: \n"+ H1
                        + "\n\nH2: \n"+ H2

                        + "\n\nChave Privada de Usuário: \n"+ PVTKEY
                        + "\n\nChave de Encriptação do Usuário: \n"+ ENCKUSER
                        + "\n\nChave Pública de Usuário: \n"+ PUBKUSER
                        + "\n\nChave Privada de Contato: \n"+ PVTKP1
                        + "\n\nChave Pública de Contato: \n"+ PUBKP1

                        + "\n\nPVTKEY de Usuário em BigInt: \n"+ secUserKey
                        + "\n\nPVTKEY de Contato em BigInt: \n"+ secP1Key

                        + "\n\nComKey de Usuário em BigInt: \n"+ comKey1[0] + "\n" + comKey1[1]
                        + "\n\nComkey de Contato em BigInt: \n"+ comKey2[0] + "\n" + comKey2[1]

                        + "\n\nHalfComKey de Usuário em BigInt: \n"+ ENCKeyUSER
                        + "\n\nHalfComKey de Contato em BigInt: \n"+ ENCKeyP1

                        + "\n\nEncKey de Usuário em Hex: \n"+ HK1USER
                        + "\n\nEncKey de Contato em Hex: \n"+ HK1P1

                        + "\n\nChave de Encriptação Inicial: \n"+ e1
                        + "\n\nChave de Encriptação Posterior: \n"+ e2

                        + "\n\nDado blk[0] a ser encriptado: \n"+ inputUser
                        + "\n\nChave de Encriptação: \n"+ keyUser
                        + "\n\nDado BLK[0] encriptado: \n"+ BLK0Str

                        + "\n\nDado BLK[0] enviado ao Contato: \n"+ inputP1
                        + "\n\nChave de Decriptação de Contato: \n"+ keyP1
                        + "\n\nDado blk[0] decriptado: \n"+ blk0Str
        );
    }


    ////////////////////////////////////////////////////////////////////////////////////
    //Mestodo para testes de Geração de Carteiras BSV
    ////////////////////////////////////////////////////////////////////////////////////
    private void BSVwallet(View view){

        String text;
        Keygen pubKey = new Keygen();
        //TxidDB banco = new TxidDB(HashTest.this);

        state = false;

        text = ((EditText)findViewById(R.id.ET_TEXTO)).getText().toString();
        if (text.length() < 1)    text = "0";

        String hashPINTEXT = SHA256G.SHA256STR(xpin+text);
        String PUBKEY64 =  pubKey.publicKeyHEX(hashPINTEXT);
        String BSVWallet = pubKey.bsvWallet(PUBKEY64);
        BigInteger [] PUBKEY = pubKey.pubKeyRev(PUBKEY64);


        ((TextView)findViewById(R.id.TV_TEXT)).setText("HASH:");
        //((EditText)findViewById(R.id.ET_TEXTO)).setText("SHA256: "+ SHA256G.SHA256STR(text));
        ((EditText)findViewById(R.id.ET_TEXTO)).setText(
                "TESTES REALIZADOS: "

                        + "\n\nXPIM: \n" + xpin
                        + "\n\nText: \n" + text
                        //+ "\n\nSHA256: \n" + SHA256G.SHA256STR(xpin+text)
                        + "\n\nSHA256: \n" + hashPINTEXT
                        //+ "\n\nPUBK0: \n" + pubKey.publicKeyHEX(SHA256G.SHA256STR(xpin+text))
                        + "\n\nPUBKEY64 (Link P2P Exclusivo): \n" + PUBKEY64
                        + "\n\nPUBKEY: \n" + PUBKEY[0] + "\n" + PUBKEY[1]
                        //+ "\n\nBSV Wallet: \n" + pubKey.bsvWallet(pubKey.publicKeyHEX(SHA256G.SHA256STR(xpin+text)))
                        + "\n\nBSV Wallet: \n" + BSVWallet

                        //+ "\nBSV: " + pubKey.bsvWallet(pubKey.publicKeyHEX("0FB0D86E902CCBDF6F3C66C0614EB18C6A17A78969BD98D60EAD584364512529"))
                        //+ "\nBSV: " + pubKey.bsvWallet(pubKey.publicKeyHEX("0fb0d86e902ccbdf6f3c66c0614eb18c6a17a78969bd98d60ead584364512529"))
                        //+ "\nBSV: " + pubKey.bsvWallet(pubKey.publicKeyHEX("11939ace9177149f26816e66d0db31f4e5089374630c102c63e77715fd3cfd68"))
                        //+ "\nBSV: " + pubKey.bsvWallet(pubKey.publicKeyHEX("0000000000000000000000000000000000000000000000000000000000000000"))
                        //+ "\nBSV: " + pubKey.bsvWallet(pubKey.publicKeyHEX("0000000000000000000000000000000000000000000000000000000000000006"))
                        //+ "\nBSV: " + pubKey.bsvWallet(pubKey.publicKeyHEX("0000000000000000000000000000000000000000000000000000000000000006"))
                        //+ "\nBSV: " + pubKey.publicKeyHEX("0000000000000000000000000000000000000000000000000000000000000006")
        );
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //Metodo para testes de Geração e Verificação de Assinaturas Digitais ECDSA
    ////////////////////////////////////////////////////////////////////////////////////
    private void ECDSAtest(View view){

        String text;
        Keygen pubKey = new Keygen();
        TxidDB banco = new TxidDB(HashTest.this);

        state = false;
        text = ((EditText)findViewById(R.id.ET_TEXTO)).getText().toString();

        if (text.length() < 1)    text = "0";

        /////////////////////////////////////////////////////////
        //SENHA - PVTKEY - XPIN - PROTOCOLO UM
        /////////////////////////////////////////////////////////
        String H1, H2;
        String PVTKEY;

        H1 = SHA256G.SHA256STR(user+key+xpin);
        H2 = SHA256G.SHA256MsnHxHex2(SHA256G.HashStrToByte2(H1),SHA256G.HashStrToByte2(H1));
        PVTKEY = SHA256G.SHA256MsnHx(user+key+xpin, H2);
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////

        /////////////////////////////////////////////////////////
        //DUMMY P2P:
        /////////////////////////////////////////////////////////
        BigInteger[] toPubKeyUser;
        String MSN = xpin; //Mensagem Dummy
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////

        /////////////////////////////////////////////////////////
        //Chave de Encriptação após a Primeira
        //Neste Conjunto de testes somente este conjunto de Chaves é testada
        /////////////////////////////////////////////////////////
        String HA1, HA2, HA3, HA4;
        String lastTXID = banco.LastTXID();

        HA1 = SHA256G.SHA256bytes(SHA256G.HashStrToByte2(PVTKEY));
        HA2 = SHA256G.SHA256MsnHxHex2(SHA256G.HashStrToByte2(lastTXID),SHA256G.HashStrToByte2(HA1));
        HA3 = SHA256G.SHA256bytes(SHA256G.HashStrToByte2(HA2));
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////

        /////////////////////////////////////////////////////////
        //ECDSA
        /////////////////////////////////////////////////////////
        EcdsaSecretus ECDSA = new EcdsaSecretus();

        //HA4 != H4 Elemento "Secreto" utilizado na Geração de H5 e também de K em ECDSA;
        HA4 = SHA256G.SHA256MsnHxHex2(SHA256G.HashStrToByte2(HA3),SHA256G.HashStrToByte2(HA2));

        BigInteger [] sigECD = new BigInteger[3];
        sigECD = ECDSA.ECDSA(SHA256G.StrToByte(MSN), PVTKEY, HA4);

        //GERAÇÃO DA CHAVE PUBLICA DE FORMA CORRETA
        toPubKeyUser = pubKey.pubKeyRev(pubKey.publicKeyHEX(PVTKEY));
        //TESTE DE GERAÇÃO DA FORMA CORRETA
        //Ecc myEcc = new Ecc();
        //toPubKeyUser = myEcc.eccnP(pubKey.HashToBigInt(PVTKEY), myEcc.Gx, myEcc.Gy);

        //VERFICAÇÃO DA ASSINATURA DIGITAL
        //BigInteger [] sigTST = ECDSA.ECDSAVerify(SHA256G.StrToByte(MSN), toPubKeyUser, sigECD);
        BigInteger [] sigTST = ECDSA.ECDSAVerify(SHA256G.StrToByte(text), toPubKeyUser, sigECD);
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////

        ((TextView)findViewById(R.id.TV_TEXT)).setText("HASH:");
        //((EditText)findViewById(R.id.ET_TEXTO)).setText("SHA256: "+ SHA256G.SHA256STR(text));
        ((EditText)findViewById(R.id.ET_TEXTO)).setText(
                "TESTES REALIZADOS: "
                        + "\n\nHASH DA MENSAGEM: \n" + SHA256G.SHA256STR(MSN)
                        + "\n\nHASH DA MENSAGEM em BigInt: \n" + pubKey.HashToBigInt(SHA256G.SHA256STR(MSN))
                        + "\n\nPVTKEY: \n" + pubKey.HashToBigInt(PVTKEY)
                        + "\n\nkNUM: \n" + sigECD[2]
                        + "\n\nPubKey: \n" + toPubKeyUser[0] + "\n" + toPubKeyUser[1]
                        + "\n\nECDSA: \n" + sigECD[0] + "\n" + sigECD[1]
                        + "\n\nECDSA verify: \n" + sigTST[0] + "\n" + sigTST[1]
                        + "\n\nECDSA state: \n" + ECDSA.ECDSAVerify2(SHA256G.StrToByte(text), toPubKeyUser, sigECD)
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_coding, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            String text = ((EditText)findViewById(R.id.ET_TEXTO)).getText().toString();
            //if(text.trim().length()>0){
            if(text.length()>0){
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, text);
                sendIntent.setType("text/plain");

                /////////////////////////////////////////////////
                //MAXSPECIALPAUSETIME é para uso quando o Usuário sair do contexto da aplicação
                //para executar uma atividade relacionada com a aplicação
                //como ele pode não retornar, é bom fechar tudo
                //contador de retorno para o contexto da aplicação
                //Variables.timeCounter = 10;

                //O contador de pausa deve estar presente em OnResume e OnCreate de cada Activity
                //Atenção especial para o uso de MAXSPECIALPAUSETIME
                Variables.timeCounter = Variables.MAXSPECIALPAUSETIME;

                //////////////////////////////////////////////////


                startActivity(Intent.createChooser(sendIntent, "Enviar texto:"));
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //https://stackoverflow.com/questions/4783960/call-method-when-home-button-pressed
    @Override
    public void onPause(){
        super.onPause();
        Variables.activityPause = true;
    }
    //https://stuff.mit.edu/afs/sipb/project/android/docs/training/basics/activity-lifecycle/pausing.html
    @Override
    public void onResume(){
        super.onResume();

        //O verficador de pausa deve ser modificado em OnResume e OnCreate de cada Activity
        Variables.activityPause = false;
        //O contador de pausa deve estar presente em OnResume e OnCreate de cada Activity
        //Atenção especial para o uso de MAXSPECIALPAUSETIME
        Variables.timeCounter = Variables.MAXPAUSETIME;
        //O contador de Interação com Usuário deve também estar presente em OnResume e em OnCreate de cada activity
        //O contador deve ser resetado em OnResume, OnCreate, onUserInteraction de cada Activity
        Variables.userInteractionAct = Variables.MAXNOINTERACTIONTIME;
    }

    //Monitora intecação do usuário com a aplicação
    //O contador deve também estar presente em OnResume e em OnCreate de cada activity
    //Se o contador atingir 0 a aplicação encerra
    //https://stackoverflow.com/questions/4208730/how-to-detect-user-inactivity-in-android
    @Override
    public void onUserInteraction(){
        super.onUserInteraction();
        //O contador de Interação com Usuário deve também estar presente em OnResume e em OnCreate de cada activity
        //O contador deve ser resetado em OnResume, OnCreate, onUserInteraction de cada Activity
        Variables.userInteractionAct = Variables.MAXNOINTERACTIONTIME;
    }

}
