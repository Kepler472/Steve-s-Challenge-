public class CryptoSc {


    public static String encrypt(String data) {
    	
    	byte[] toBeEncrypted = data.getBytes();
    	
        byte[] encryption = new byte[toBeEncrypted.length];
        
        for (int i = 0; i < toBeEncrypted.length; i++) {
            encryption[i] = (byte) ((i % 2 == 0) ? toBeEncrypted[i] + 1 : toBeEncrypted[i] - 1);
        }
        String encrypt = new String(encryption);
        
        return encrypt;
    }


    public static String decrypt(String data) {
        
    	byte[] toBeDecrypted = data.getBytes();
    	
    	byte[] decryption = new byte[toBeDecrypted.length];
    	
        for (int i = 0; i < toBeDecrypted.length; i++) {
            decryption[i] = (byte) ((i % 2 == 0) ? toBeDecrypted[i] - 1 :toBeDecrypted[i] + 1);
        }
        String decrypted = new String(decryption);
        
        return decrypted;
    }
}
