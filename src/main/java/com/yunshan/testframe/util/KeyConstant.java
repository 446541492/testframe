package com.yunshan.testframe.util;

/**
 * @Title: KeyConstant.java
 * @Package com.yunshan.testframe.util
 * @Description: TODO(系统常量)
 * @author <a href="http://www.wanglay.com">Lei Wang</a>
 * @date 2016年6月12日 下午5:04:10
 * @version V1.0 Update Logs: *
 *          **************************************************** Name: Date:
 *          Description: ******************************************************
 */
public interface KeyConstant
{
	/**
	 * session保存登录用户的键值
	 */
	String SESSION_KEY_USER = "loginUser";
	/**
	 * 跳转登录页面的请求路径
	 */
	String TO_LOGIN = "/index";

	enum CarAlarmConstant
	{
		 SOS("sos", 1), overspeed("超速", 2);
		 // 成员变量
        private String name;
        private int code;
        // 构造方法
        private CarAlarmConstant(String name, int index) {
            this.name = name;
            this.code = index;
        }

        // 普通方法
        public static String getName(int code) {
            for (CarAlarmConstant c : CarAlarmConstant.values()) {
                if (c.getCode() == code) {
                    return c.name;
                }
            }
            return null;
        }

        // get set 方法
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

		@Override
		public String toString()
		{
			
			return String.valueOf(this.code);
			
		}
        
	}
}
