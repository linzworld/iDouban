package com.lzh.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.lzh.entity.User;
import com.lzh.service.UserService;

/**
 * @Description 显示用户的个人信息和用户修改个人信息
 * @author 林泽鸿
 * @date 2019年4月20日
 */
@WebServlet("/MyPageServlet")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * 跳到个人主页的页面
	 */
	private final String SUCCESS_VIEW = "/jsp/my_page.jsp";
	/**
	 * 调用service层的方法
	 */    
	UserService us = new UserService();
	User user = new User();
	/**
	 *1. 用户修改个人信息
	 *2. 显示用户的个人信息
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//先拿到alter.jsp传来的请求  ---修改个人信息
		//设置utf-8编码
		request.setCharacterEncoding("utf-8");

		//拿到session中的用户名
		HttpSession session = request.getSession();
		
        //将该用户的所有信息给user对象
		user =  (User)session.getAttribute("userInfo") ; 
        
		  //拿到request中的信息，即表单中的用户名和密码 
			//表单提交的内容----放到user对象中
		  user.setNickname(request.getParameter("nickname") );
		  user.setSignature( request.getParameter("signature"));
		  user.setSelfIntroduc(request.getParameter("selfIntroduc"));
		  user.setAddress(request.getParameter("address") );
	
		  //把user对象--- userInfo---放进session中
		  session.setAttribute("userInfo", user);
		  //将要更新的数据放在user对象中，更新数据库
		   boolean ret = us.personage(user);
		//更新成功时的操作，重定向到个人主页
		   if(ret) {
			  	request.getRequestDispatcher(SUCCESS_VIEW).forward(request, response);
			  	return;
		   }
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
