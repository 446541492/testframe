package com.yunshan.testframe.util.crypt;

public class MD5UserInfo {

	private static String vailKey = "034679DEP";
	
	/**
	 * 验证用户请求信息是否安全操作，防止恶意访问
	 * @param timestamp	时间戳
	 * @param token		验证请求标示
	 * @return
	 */
	public static boolean vailUserInfo(String timestamp,String token){
		String vailValue = timestamp+vailKey;
		String sign = MD5Crypter.encryptHEX(vailValue);
		System.out.println("所生成的sign："+sign);
		if(token!=null&&sign.equalsIgnoreCase(token.trim())){
			return true;
		}
		return false;
	}
	
	public static void main(String args[]){
		String timestamp=System.currentTimeMillis()+"";  
		String userId = "1234567";
		System.out.println("时间戳："+timestamp);
		System.out.println("用户加密ID："+userId);
		
		boolean sign = vailUserInfo("1459329145257","AC8C871AE71EE10F4FA515ABD3A1FB41");
		System.out.println("验证结果："+sign);
		System.out.println("CABEC688FDDC3E441572277DE723BD05".equals("CABEC688FDDC3E441572277DE723BD05"));
	}
}
