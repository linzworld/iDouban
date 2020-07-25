package com.lzh.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lzh.entity.Page;
import com.lzh.entity.User;
import com.lzh.service.EveryoneService;
import com.lzh.service.FriendOperationService;
/**
 * @Description 我的关注-好友-黑名单 --显示好友信息 
 * @author 林泽鸿
 * @time 2019年4月29日 下午10:50:02
 */
@WebServlet("/FriendListServlet")
public class FriendListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 我关注的人的信息列表
	 */
     private  static final String ATTENTION_VIEW ="/jsp/attention.jsp";
/**
 * 我的好友的信息列表
 */
    private  static final String FRIEND_VIEW ="/jsp/friend.jsp";
    /**
     * 黑名单的信息列表
     */
        private  static final String  BLACKLIST_VIEW ="/jsp/blacklist.jsp";
            
        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置状态量为无状态
		int status = -1;
		/**
		 * 调用业务逻辑层--EveryoneService
		 */
		EveryoneService es =new EveryoneService();
		/**
		 * 调用业务逻辑层--FriendOperationService
		 */
		FriendOperationService fos = new FriendOperationService() ;
		
        // 改成中文编码
        request.setCharacterEncoding("utf-8");

		   //得到session
			HttpSession session = request.getSession();
			User u = null;
			u = new User();
		    u =( User )session.getAttribute("userInfo");
		    int userId =0;
		    /**
		     * 当前用户的id值
		     */
		    if(u!=null)
		    {
		    	 userId = u.getUserId();
		    }

		/*
		 * //数据总数 int count =es.getTotalCount();
		 */
		//将分页所需的字段，封装到page对象中
		Page page = new Page();
		int currentPage =1 ;
		//当前要显示的页面---超链接里面的请求数据
		 String currentPageStr = request.getParameter("currentPage"); 
				//第一页--默认的页面
				  if(currentPageStr == null || "".equals(currentPageStr))
					  {
						  currentPage = 1; 
					  }
				  else 
					  {
						  //当前页--用户选择的页面数 
						  currentPage = Integer.parseInt(currentPageStr); 
					  }
						//一页的大小
						int pageSize = 10;
						
						//被用户选中的用户id 
						int toUserId = 0 ;
						//字符串形式
					   String toUserIdStr = request.getParameter("toUserId");
					   if(toUserIdStr == null || "".equals(toUserIdStr))
						  {
						    
						  }
					  else 
						  {
							  
							   toUserId = Integer.parseInt(toUserIdStr); 
						  }
						
						//当前要显示的页面---用户的好友操作-超链接里面的请求所对应的实现方式
						 String preMethod = request.getParameter("pre_method"); 
				
						 if(preMethod!=null) 
						 {
							//请求的方法
							request.setAttribute("pre_method", preMethod);
							
							  //用户点击好友--更改好友关系的具体操作
								switch (preMethod) { 
										//从黑名单撤销的超链接
										case "remove_black": 
											{
												//删除其记录A->B
											    fos.friendDelete(userId, toUserId);
											   
										    } 
										//在我关注的人中--取消关注
										case "unfollow":
											{
												//删除其记录A->B
											    fos.friendDelete(userId, toUserId);
												
											}
											//在我的关注中--拉黑
										case "black_on_record":
											{
												status = 3;
												//更新已有的记录--黑名单为3
												fos.friendUpdate(userId, toUserId, status);
												
											}
										case "":
											{
												
											}
										default: break; 
								}
						 }
						 else {
							 
						 }
						//当前要显示的页面---超链接里面的请求所对应的实现方式
						 String method = request.getParameter("method"); 
							//请求的方法
							request.setAttribute("method", method);
						 
				
									//--好友管理的列表-----switch多重选择
									switch (method)
									{
											//关注的人的按钮
											case "attention_list":
													{
															//查询状态为关注--1
															status = 1;
															//总数据数
															int totalCount =es.getFriendTotalCount(userId, status);		  
															
															//组装page对象
															//先set totalCount属性 作为分子
															page.setTotalCount(totalCount);
															page.setCurrentPage(currentPage);
															page.setPageSize(pageSize);
														
															/**
															 * 拿到数据集合--分页
															 */
																List<User> users = new ArrayList<>();
																users = es.queryFriendByPage(userId, status, currentPage, pageSize);
																
																   page.setObjects(users);
																	//将数据传给request
																	request.setAttribute("p", page);
																	
																	
																   //跳转到attention.jsp的页面
																	request.getRequestDispatcher(ATTENTION_VIEW).forward(request, response);
													           return;
														
													}
											//搜索框的按钮
											case "search_attention":
														{	
															//查询状态为关注--1
															status = 1;
															//用户所要搜索的内容--模糊搜索
															 String sc = request.getParameter("search_content"); 
															 if( sc== null || "".equals(sc))
																 {
																 }
															 else
																 {
																	//只有输入框输入值才会出现在session中
																	session.setAttribute("searchContent",sc);
																 }
										
														 	   String 	searchContent =(String) session.getAttribute("searchContent");
																//符合搜索结果的数据数
																int totalCount =es.getFriendSearchCount(searchContent, userId, status);		  
																
																
																
																//组装page对象
																//先set totalCount属性 作为分子
																page.setTotalCount(totalCount);
																page.setCurrentPage(currentPage);
																page.setPageSize(pageSize);
															
																	 /**
																	 * 拿到数据集合
																	 */
																	List<User> users = new ArrayList<>();
																	//模糊搜索的功能---DAO
																	users = es.queryFriendByPage(searchContent, userId, status, currentPage, pageSize);
																	
																	   page.setObjects(users);
																		//将数据传给request
																		request.setAttribute("p", page);
																		
																	   //跳转到attention.jsp的页面
																		request.getRequestDispatcher(ATTENTION_VIEW).forward(request, response);
														           return;
														 
													}
											//好友功能中的查看好友信息列表
											case "friend_list":
												{
														//查询状态为好友--2
														 status = 2 ;
														//总数据数
														int totalCount =es.getFriendTotalCount(userId, status);		  
														
														//组装page对象
														//先set totalCount属性 作为分子
														page.setTotalCount(totalCount);
														page.setCurrentPage(currentPage);
														page.setPageSize(pageSize);
													
														/**
														 * 拿到数据集合
														 */
															List<User> users = new ArrayList<>();
															users = es.queryFriendByPage(userId, status, currentPage, pageSize);
															
															   page.setObjects(users);
																//将数据传给request
																request.setAttribute("p", page);
																
																//设置分组名返回集合
																List<String> groupNames = new ArrayList<String>();
																groupNames = fos.friendGroupNameQuery(userId) ;
																request.setAttribute("groupNames", groupNames);
																
															   //跳转到friend.jsp的页面
																request.getRequestDispatcher(FRIEND_VIEW).forward(request, response);
												           return;
												}
											//好友搜索功能--搜索框--模糊搜索
											case "search_friend":
												{
														//查询状态为好友--2
														status = 2;
														//用户所要搜索的内容--模糊搜索
														 String sc = request.getParameter("search_content"); 
														 if( sc== null || "".equals(sc))
															 {
															 }
														 else
															 {
																//只有输入框输入值才会出现在session中
																session.setAttribute("searchContent",sc);
															 }
										
													 	   String 	searchContent =(String) session.getAttribute("searchContent");
															
													 	   
													 	   //符合搜索结果的数据数
															int totalCount =es.getFriendSearchCount(searchContent, userId, status);		  
															
															//组装page对象
															//先set totalCount属性 作为分子
															page.setTotalCount(totalCount);
															page.setCurrentPage(currentPage);
															page.setPageSize(pageSize);
																
																 /**
																 * 拿到数据集合
																 */
																List<User> users = new ArrayList<>();
																//模糊搜索的功能---DAO
																users = es.queryFriendByPage(searchContent, userId, status, currentPage, pageSize);
																
																page.setObjects(users);
																//将数据传给request
																request.setAttribute("p", page);
																	
																 //跳转到friend.jsp的页面
																request.getRequestDispatcher(FRIEND_VIEW).forward(request, response);
													            return;
													 
												}
												//好友分组搜索功能
											case "search_friend_group":
												{
													//查询状态为好友--2
													status = 2;
													//用户所要更改的目标分组名
													 String sc = request.getParameter("search_content"); 
													 if( sc== null || "".equals(sc))
														 {
														 }
													 else
														 {
															//只有输入框输入值才会出现在session中
															session.setAttribute("searchContent",sc);
														 }
												 	   		String searchContent =(String) session.getAttribute("searchContent");
												 	   
												 	   
														 	   if (fos.friendGroupAlter(userId, toUserId, searchContent)  )
														 	   		{
														 		   		System.out.println("好友分组更改成功！");
														 	   		}
																//符合分组的数据数
																int totalCount =es.getFriendGroupCount(searchContent, userId, status);		  
																
																//组装page对象
																//先set totalCount属性 作为分子
																page.setTotalCount(totalCount);
																page.setCurrentPage(currentPage);
																page.setPageSize(pageSize);
															
																	 /**
																	 * 拿到数据集合
																	 */
																	List<User> users = new ArrayList<>();
																	//好友分组的分页功能
																	users = es.queryFriendGroupByPage(searchContent, userId, status, currentPage, pageSize);
																	
																	page.setObjects(users);
																	//将数据传给request
																	request.setAttribute("p", page);
																		
																	//设置分组名返回集合
																	List<String> groupNames = new ArrayList<String>();
																	groupNames = fos.friendGroupNameQuery(userId) ;
																	request.setAttribute("groupNames", groupNames);
																	
																	 //跳转到friend.jsp的页面
																	request.getRequestDispatcher(FRIEND_VIEW).forward(request, response);
														            return;
												 
													 
												}	
												//更改好友分组功能
												case "alter_friend_group":
												{
														
												}		
												
											//黑名单的信息列表
											case "blacklist_list":
											{
													//查询状态为黑名单--3
													status = 3 ;
													//总数据数
													int totalCount =es.getFriendTotalCount(userId, status);		  
													
													//组装page对象
													//先set totalCount属性 作为分子
													page.setTotalCount(totalCount);
													page.setCurrentPage(currentPage);
													page.setPageSize(pageSize);
												
													/**
													 * 拿到数据集合
													 */
														List<User> users = new ArrayList<>();
														users = es.queryFriendByPage(userId, status, currentPage, pageSize);
														
														   page.setObjects(users);
															//将数据传给request
															request.setAttribute("p", page);
															
															
														   //跳转到friend.jsp的页面
															request.getRequestDispatcher(BLACKLIST_VIEW).forward(request, response);
											           return;
											}
										//黑名单的搜索功能--搜索框--模糊搜索
										case "search_blacklist":
											{
													//查询状态为黑名单--3
													status = 3 ;
													//用户所要搜索的内容--模糊搜索
													 String sc = request.getParameter("search_content"); 
													 if( sc== null || "".equals(sc))
														 {
														 }
													 else
														 {
															//只有输入框输入值才会出现在session中
															session.setAttribute("searchContent",sc);
														 }
									
												 	   String 	searchContent =(String) session.getAttribute("searchContent");
														//符合搜索结果的数据数
														int totalCount =es.getFriendSearchCount(searchContent, userId, status);		  
														
														
														
														//组装page对象
														//先set totalCount属性 作为分子
														page.setTotalCount(totalCount);
														page.setCurrentPage(currentPage);
														page.setPageSize(pageSize);
													
															 /**
															 * 拿到数据集合
															 */
															List<User> users = new ArrayList<>();
															//模糊搜索的功能---DAO
															users = es.queryFriendByPage(searchContent, userId, status, currentPage, pageSize);
															
															page.setObjects(users);
															//将数据传给request
															request.setAttribute("p", page);
																
															 //跳转到friend.jsp的页面
															request.getRequestDispatcher(BLACKLIST_VIEW).forward(request, response);
												            return;
												 
											}
												
												default:
													break;
										}
					
				}
        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
