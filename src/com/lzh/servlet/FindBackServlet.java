package com.lzh.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lzh.entity.User;

/**
 * @Description 找回密码--邮箱验证 
 * @author 林泽鸿
 * @time 2019年5月15日 上午2:44:19
 */
@WebServlet("/FindBackPassword")
public class FindBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			 // 得到session---拿到当前用户的用户id
				HttpSession session = request.getSession();
				User u = null; 
				u =  new User();
				u = (User) session.getAttribute("userInfo");
			   int userId = 0;
			   String username ="";
				  /**
					* 当前用户的id值
					*/
			   		if (u != null) 
			   			{
			   				userId = u.getUserId(); 
			   				username=u.getUsername() ;
			   			}
			   		//数据库查询是否存在该用户名对应的用户，存在的话，给其两个字段设置值,outTime,code
			   		
			   		System.out.println(userId+username);
			   		
			   		
	
			   		
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
