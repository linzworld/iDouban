package com.lzh.servlet;

 
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.lzh.entity.User;
import com.lzh.service.UserService;
import com.lzh.util.ImageUrl;

/**
 * @Description 头像的上传
 * @author 林泽鸿
 * @time 2019年4月21日 上午1:41:24
 */
@MultipartConfig(location="/home/tomcat/iDouBan")
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final  String MYPAGE_VIEW = "/jsp/alter.jsp";

/**
 * 调用service层方法
 */
	UserService us = new UserService();
	User user = new User();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		
		//photo对应上传图片对应的name属性   -----key值
		 Part part = request.getPart("photo");
		
		 //放置两个字符串 键-文件名 值-图片上传后的地址
		 Map<String, String> map = new HashMap<>() ;
		 //清空map集合
		map.clear();
			   
		 //原来的文件名------可传入数据库
		String fileName = getFileName(part);
		
		//调用util包中拼接成绝对地址的方法
		 map = ImageUrl.imgUrl(fileName); 

		//键--先拿到一个装有所有键的集合
        Set<String> lastFileNameAll = map.keySet();  
        String lastFileName = null;
			        for (String string : lastFileNameAll) 
			        {  
			          lastFileName = string;
			        }  
					//值
			       Collection<String> imageUrlAll = map.values();  
			       String imageUrl = null;
			       for (String string : imageUrlAll) 
				       {  
				    	   imageUrl = string;
				      }  
								System.out.println("开始存入磁盘");
								//写入磁盘中
								String method = request.getParameter("method");
								if( method==null  ||  "".equals( method ) )
									{
										   //得到session
											HttpSession session = request.getSession();
											//获得session中的userInfo对象
										   User userInfo = new User();
										   userInfo =(User) session.getAttribute("userInfo");
											//图片地址放在user对象中
											user.setPortrait(imageUrl);
											System.out.println("upload--------imgurl---------"+user.getPortrait());
										    user.setUsername(  (String)session.getAttribute("uname"));
										    
													    //user放在session
														session.setAttribute("user",user);
														//放置图片地址到session中
													    session.setAttribute("portrait", imageUrl);
													    
													    userInfo.setPortrait(user.getPortrait());
													   
													   session.setAttribute("userInfo",userInfo);
													
													   us.portrait(userInfo);
														//写入磁盘中
														part.write(lastFileName);
													
														System.out.println("图片属于转发到my_pane页面 ");
														//转发到my_pane页面 
														request.getRequestDispatcher(MYPAGE_VIEW).forward(request, response);
											return;
									 }	
								else 
										{
												//写入磁盘中
												part.write(lastFileName);
												System.out.println("图片属于拿回地址的操作，异步处理");
										        PrintWriter writer = response.getWriter();
										        writer.write(imageUrl);
										        //数据量较大的情况下用flush
										        writer.flush();
										}
								
							

				}
/**
 * @Description 取得上传的文件名
 * @param part filename(文件名)
 * @return
 */
	private String getFileName(Part part){
		String header = part.getHeader("Content-Disposition");
		String fileName = 
				header.substring( header.indexOf("filename=\"") + 10,
						header.lastIndexOf("\"") ) ;
			return fileName;
	}

}
