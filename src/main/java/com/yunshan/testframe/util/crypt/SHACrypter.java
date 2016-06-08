package com.yunshan.testframe.util.crypt;

import java.security.MessageDigest;

public class SHACrypter{

	private final static String SHA = "SHA1";
	
	private final static char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5','6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	public String encrypt(String clear) {
		
		if(clear==null)
			return(null);
		
		byte buf[]=clear.getBytes();
		try{
  	      MessageDigest messageDigest = MessageDigest.getInstance(SHA);
  	      messageDigest.update(buf);
  	      return getFormattedText(messageDigest.digest());
  	   }catch(Exception ex){
  	   
  		   return "";
  	   }
	}
    
    
    
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {          buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
}


	public String decrypt(String encrypted) {
		return null;
	}

	public boolean validate(String clear, String encrypted) {
		
		if(clear==null||encrypted==null)
			return false;

		return(encrypted.equals(encrypt(clear)));
	}

	public boolean validateWithToken(String encrypted, String encryptedWithToken, String token) {
		return false;
	}

}
