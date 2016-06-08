package com.yunshan.testframe.util.crypt;

import java.security.MessageDigest;

public class MD5Crypter{
    public static String encryptHEX(String pass) {
    	if(pass==null||"".equals(pass)) return "";
        char[] Digit ={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F' };
        char[] ob = new char[2];
        byte[] md5_buf;
        StringBuffer sb = new StringBuffer();
        if (pass == null)   return (null);
        byte buf[] = pass.getBytes();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md5_buf = md.digest(buf);
        } catch (Exception e) {
            return (null);
        }
        for (int i = 0; i < md5_buf.length; i++) {
            ob[0] = Digit[(md5_buf[i] >>> 4) & 0X0F];
            ob[1] = Digit[md5_buf[i] & 0X0F];
            sb.append(new String(ob));
        }
        return sb.toString();
    }
    public static void main(String args[]){
    	String str = "123456a";
    	String s = MD5Crypter.encryptHEX(str);
    	System.out.println(s);
    }
}
