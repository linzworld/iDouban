package com.lzh.util;

/**
 * @Description  验证用户名和密码是否在数据库中已经存在，如果已存在则返回真，没有则返回假
 * @author 林泽鸿
 * @date 2019年4月18日
 */
public class ValidateUtil {
	/**
	 * @Description 类方法 判空处理 验证用户名
	 * @param userName
	 * @return
	 */
	public static boolean isInvalidUserName(String userName){
		if(userName == null || userName.equals("")){
			System.out.println("isInvalidUserName");
			return true;
		}
		return false;
	}
/**
 * @Description 判断密码是否为空 
 * @param password
 * @return 布尔值
 */
	public static boolean isInvalidPassword(String password){
		if(password == null || password.equals("")){
			return true;
		}
		return false;
	}
}
