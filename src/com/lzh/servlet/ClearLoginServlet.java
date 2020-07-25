package com.lzh.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ClearLoginServlet
 */
/**
 * @Description 清除登录状态 退出自动登录 --对应my_page中的退出登录功能
 * @author 林泽鸿
 * @time 2019年4月25日 上午2:30:35
 */
@WebServlet("/ClearLoginServlet")
public class ClearLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 返回登录界面
	 */
	private final String LOGIN_VIEW="/jsp/login.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   

	        //这里就是把username的cookie设置成0秒有效期，就是直接删除掉了
	        Cookie[] cookies = request.getCookies();
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	             
	                //解决GET请求中文乱码问题---找到用户名相等的cookie
	                if (URLDecoder.decode(cookie.getName(), "utf-8").equals("uname")) { // 表明已经登陆过了，就直接跳转了
	                    cookie.setMaxAge(0);
	                    response.addCookie(cookie);
	                }
	                //找到密码相等的cookie
	                if (URLDecoder.decode(cookie.getName(), "utf-8").equals("upwd")) { // 表明已经登陆过了，就直接跳转了
	                    cookie.setMaxAge(0);
	                    response.addCookie(cookie);
	                }
	                //找到auto值
	                if (URLDecoder.decode(cookie.getName(), "utf-8").equals("auto")) { 
	                    cookie.setMaxAge(0);
	                    response.addCookie(cookie);
	                }
	            }
	        }
	        //重定向
	        	response.sendRedirect(request.getContextPath()+LOGIN_VIEW);
	    }


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
