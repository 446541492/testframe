package com.yunshan.testframe.util.crypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AES128Crypter {
	
	/**
	 *  数字加密 
	 * @param sSrc	要加密的数字
	 * @return
	 * @throws Exception
	 */
	public static String EncryptNumber(int number) throws Exception {
		String str = Integer.toString(number);
		return AES128Crypter.EncryptString(str);
	}
	/**
	 *  字符串加密 
	 * @param sSrc	要加密的字符串
	 * @return
	 * @throws Exception
	 */
	public static String EncryptString(String sSrc) throws Exception {
		String sKey = "01256789ABEFIKLP";
//		if (sKey == null) {
//			System.out.print("Key为空null");
//			return null;
//		}
		// 判断Key是否为16位
		if (sKey.length() != 16) {
			System.out.print("Key长度不是16位");
			return null;
		}
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		// "算法/模式/补码方式"
		IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
		// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec,iv);
		System.out.println(sSrc.getBytes());
		byte[] encrypted = cipher.doFinal(sSrc.getBytes());
		return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
	}
	
	/**
	 *  解密 
	 * @param sSrc	要解密的字符串
	 * @return
	 * @throws Exception
	 */
	public static String DecryptString(String sSrc) throws Exception {

		String sKey = "01256789ABEFIKLP";
		try {
			// 判断Key是否正确
//			if (sKey == null) {
//				System.out.print("Key为空null");
//				return null;
//			}
			// 判断Key是否为16位 
			if (sKey.length() != 16) { 
				System.out.print("Key长度不是16位");
				return null;
			}
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original);
				return originalString;
			} catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}
	

	public static void main(String[] args) throws Exception {
		/**
		 *  加密用的Key 可以用26个字母和数字组成，最好不要用保留字符，
		 *  虽然不会错，至于怎么裁决，个人看情况而定    
		 *  * 此处使用AES-128-CBC加密模式，key需要为16位。    */
//		String cKey = "01256789ABEFIKLP";
		// 需要加密的字串
		String cSrc = "1";//3FZtTemf8LMiWlMan5ZbGw==
		System.out.println(cSrc);
		// 加密
		long lStart = System.currentTimeMillis();
		String enString = AES128Crypter.EncryptString(cSrc);
		System.out.println("加密后的字串是：" + enString);
		long lUseTime = System.currentTimeMillis() - lStart;
		System.out.println("加密耗时：" + lUseTime + "毫秒");
		// 解密
		lStart = System.currentTimeMillis(); 
//		String DeString = AES128Crypter.DecryptString(enString);
		String DeString = AES128Crypter.DecryptString("azm+efcvfXjdFisSdloJxQ==");;
		System.out.println("解密后的字串是：" + DeString);
		lUseTime = System.currentTimeMillis() - lStart;
		System.out.println("解密耗时：" + lUseTime + "毫秒");
		float a = 423423.623f;
		Integer i = Math.round(a);
		System.out.println(i);
		Integer b = Float.floatToIntBits(a);
		System.out.println(b); 
	}
}
