package com.lzh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.lzh.dao.UserDao;
import com.lzh.daoImpl.UserDaoImpl;
import com.lzh.entity.Doumail;
import com.lzh.entity.Page;
import com.lzh.entity.User;
import com.lzh.service.DoumailService;
import com.lzh.service.FriendOperationService;

/**
 * @Description 用于豆邮的数据处理和页面的跳转 
 * @author 林泽鸿
 * @time 2019年5月14日 上午5:46:41
 */
@WebServlet("/DoumailServlet")
public class DoumailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DOUMAIL_PAGE = "/jsp/doumail.jsp";   
	 private static final String DOUMAIL_SHOW_PAGE = "/jsp/doumail_show.jsp"; 
	 /**
	  * 黑名单的页面--提醒用户不能进行使用豆邮的功能
	  */
	 private static final String BLACKLIST_PAGE = "/jsp/blacklist.jsp";
	 @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FriendOperationService friendOperationService = new FriendOperationService() ;
		DoumailService doumailService = new DoumailService() ;
		UserDao userDao = new  UserDaoImpl();
		// 改成中文编码
				request.setCharacterEncoding("utf-8");
				 // 得到session---拿到当前用户的用户id
				HttpSession session = request.getSession();
				User u = null; 
				u =  new User();
				u = (User) session.getAttribute("userInfo");
			   int userId = 0;
				  /**
					 * 当前用户的id值
					 */
				if (u != null) { userId = u.getUserId(); }
				// 将分页所需的字段，封装到page对象中
				Page page = new Page();
				int currentPage = 1;
				// 当前要显示的页面---超链接里面的请求数据
				String currentPageStr = request.getParameter("currentPage");
				// 第一页--默认的页面
				if (currentPageStr == null || "".equals(currentPageStr))
					{
						currentPage = 1;
					}
				else
					{
						// 当前页--用户选择的页面数
						currentPage = Integer.parseInt(currentPageStr);
					}
						// 一页的大小
						int pageSize = 10;
		
					
						//定义被对话的用户的id
						int toUserId = 0  ;
						String toUserIdStr = request.getParameter("to_user_id");
						if( toUserIdStr==null  ||  "".equals ( toUserIdStr ) )
							{                                                              }
						//将被对话的用户的id的字符串形式解析为int类型
						else 
							{
							toUserId = Integer.parseInt(toUserIdStr);
							//放在request域中
							request.setAttribute("toUserId", toUserId);
							//得到昵称，并放在session中
							session.setAttribute("nickname", userDao.userInfoByUserId(toUserId).getNickname());
							}		
						
						//对双方的好友关系进行判断，返回结果
						int status1= friendOperationService.friendQuery(userId, toUserId);
						int status2= friendOperationService.friendQuery( toUserId , userId);
						//被拉黑的情况下
						if(status1 == 3 || status2 ==3 ) {
							//返回消息的值
							request.setAttribute("msg", "黑名单限制");
							// 跳转到blacklist.jsp的页面
							request.getRequestDispatcher(BLACKLIST_PAGE).forward(request, response);
							return;
						}
									
					//预处理的功能
					//当前要显示的页面---豆邮对话功能- -超链接里面的请求所对应的实现方式
					 String preMethod = request.getParameter("pre_method"); 
					 if(preMethod!=null ) 
						 {
							//请求的方法
							request.setAttribute("pre_method", preMethod);
							

								//对消息进行预处理----进行豆邮对话
								switch (preMethod) 
									{ 
								  		//用户进行了豆邮对话的内容的提交
										case "doumail_content": 
												{    
													//对话的内容--content
													String content = request.getParameter("content");
													if( content!=null  && !"".equals ( content ) )
														{   
															//设置文本框中的内容的设置
															request.setAttribute("content", content);                           
															//用户评论的内容
															String chatMsg = request.getParameter("content");
															
													
															//将数据组装到一个对象中
																Doumail doumail = new Doumail() ;
															doumail.setFromUserId(userId);
															doumail.setToUserId(toUserId);
															doumail.setChatMsg(chatMsg);
															if ( doumailService.doumailInsert(doumail) )
															{
																System.out.println("豆邮消息增加成功");
															}
														}
													else 
														{
													
														}	
												}
										default: break; 
									}
						 }
						else {			}
							
					
						
						
						
						
						
						// 当前要显示的页面---超链接里面的请求所对应的实现方式
						String method = request.getParameter("method");
						// 请求的方法
						request.setAttribute("method", method);
						//switch多重选择
						switch (method)
						{
								// 我的豆邮列表
								case "my_doumail_list":
									{
										// 总数据数
										int totalCount = doumailService.getTotalCount( userId );
						
										// 组装page对象
										// 先set totalCount属性 作为分子
										page.setTotalCount(totalCount);
										page.setCurrentPage(currentPage);
										page.setPageSize(pageSize);
						
										/**
										 * 拿到数据集合--分页
										 */
										List<Doumail> doumails = new ArrayList<Doumail>();
										doumails = doumailService.queryDoumailByPage(currentPage, pageSize, userId);
										//放入page对象中
										page.setObjects(doumails);
										// 将数据传给request
										request.setAttribute("p", page);
										request.setAttribute("msg", "我的豆邮");
						
										// 跳转到doumail.jsp的页面
										request.getRequestDispatcher(DOUMAIL_PAGE).forward(request, response);
										return;
									}
								case"doumail_show":
									{
										
										// 跳转到doumail_show.jsp的页面
										request.getRequestDispatcher(DOUMAIL_SHOW_PAGE).forward(request, response);
										return;
									}
									
									//豆邮对话消息区中的显示
								case"doumail_show_details":
									{
										// 总数据数
										int totalCount = doumailService.getShowCount(userId, toUserId) ;
						
										// 组装page对象
										// 先set totalCount属性 作为分子
										page.setTotalCount(totalCount);
										page.setCurrentPage(currentPage);
										page.setPageSize(pageSize);
						
										/**
										 * 拿到数据集合--根据评论分页后得到的数据进行查询并且返回结果--得到的是豆邮对话记录的数据集合
										 */
										List<Doumail> doumails = new ArrayList<Doumail>();
										doumails =doumailService.queryShowByPage(currentPage, pageSize, userId, toUserId);
										
										page.setObjects(doumails);
										//1、使用JSONObject
										JSONObject json = new JSONObject(page);
										
										//JSON对象转化为字符串
								        String strJson=json.toString();
								        
								        System.out.println("豆邮对话的展示doumail--"+"strJson:"+strJson);
								        response.setContentType("text/html;charset=UTF-8");
										//用输出流--将数据传给doumail_show.jsp的页面--动态刷新
								        PrintWriter writer = response.getWriter();
								        writer.write(strJson);
								        //数据量较大的情况下用flush
								        writer.flush();
										return;
									}	
									default: break;
								}
	}


	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
