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
 * @Description 查询所有人的信息，得到列表 
 * @author 林泽鸿
 * @time 2019年4月26日 下午1:02:24
 */
@WebServlet("/EveryoneListServlet")
public class EveryoneListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 所有人的信息的列表
	 */
       static final String EVERYONE_VIEW ="/jsp/everyone.jsp";


/**
 * ---1-判空-----2-分页----3-查询结果放在集合中
 */
       @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 调用业务逻辑层 ---EveryoneService
		 */
		EveryoneService es = new EveryoneService();
		/**
		 * 调用业务逻辑层 ---FriendOperationService
		 */
		FriendOperationService fos = new FriendOperationService() ;
		
        // 改成中文编码
        request.setCharacterEncoding("utf-8");
        //好友关系状态
        int status = -1 ;
		   //得到session
			HttpSession session = request.getSession();
			
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
				
		
			   //当前要显示的页面---超链接里面的请求所对应的实现方式
			   String method = request.getParameter("method"); 

			   //请求的方法
			   request.setAttribute("method", method);
		 
			
			

			//预处理的功能
			//当前要显示的页面---用户的好友操作-超链接里面的请求所对应的实现方式
			 String preMethod = request.getParameter("pre_method"); 

			 if(preMethod!=null) 
			 {
				//请求的方法
				request.setAttribute("pre_method", preMethod);
				
				  //用户点击好友--更改好友关系的具体操作
					switch (preMethod) { 
							//关注---增加记录
							case "attention": 
								{
									//增加记录A->B
									fos.friendInsert(userId, toUserId,"无分组");
								    
							    } 
								break;
							//在所有人的列表中--取消关注
							case "unfollow":
								{
									//删除其记录A->B
								    fos.friendDelete(userId, toUserId);
								   
								}
								 break;
								//在所有人（带有记录）中--拉黑
							case "black_on_record":
								{
									status = 3;
									//更新已有的记录--黑名单为3
									fos.friendUpdate(userId, toUserId, status);
								}
								 break;
								//本身无记录
							case "black_without_record":
								{
									status = 3;
									//增加记录A->B
									fos.friendInsert(userId, toUserId,"无分组");
								    //更改其新增的记录的状态为3--黑名单为3
								  fos.friendUpdate(userId, toUserId, status);
								
								}
							    break;
							case "cancel_black":
								{
									//删除其记录A->B
								    fos.friendDelete(userId, toUserId);
								   
								}
								break;
							default:
								   break; 
					}
				
				
			 }
			 else { }
			

		//switch多重选择
		switch (method)
		{

			//默认所有人的按钮
			case "everyone_list":
				{
					//总数据数
					int totalCount =es.getTotalCount();		  
					
					//组装page对象
					//先set totalCount属性 作为分子
					page.setTotalCount(totalCount);
					page.setCurrentPage(currentPage);
					page.setPageSize(pageSize);
				
					/**
					 * 拿到数据集合
					 */
						List<User> users1 = new ArrayList<>();
						users1 = es.queryEveryoneByPage(currentPage, pageSize);
						
						List<User> users = new ArrayList<>();
						
						
						for( User user :  users1) 
							{
	
								//设置关注和拉黑的两个按钮的属性
								status = fos.friendQuery(userId, user.getUserId()) ;
	
							//当成中间站--实际意义不同--用于显示时的判断条件
						      user.setStatus(status);
						      users.add(user);
								
							}
							   page.setObjects(users);
								//将数据传给request
								request.setAttribute("p", page);
								
								//设置分组名返回集合
								List<String> groupNames = new ArrayList<String>();
								request.setAttribute("groupNames", groupNames);
								
								
								
							   //跳转到everyone.jsp的页面
								request.getRequestDispatcher(EVERYONE_VIEW).forward(request, response);
			           return;
				}
				
			//搜索框的按钮
			case "search_users":
				{	
						//用户所要搜索的内容--模糊搜索
					 String sc = request.getParameter("search_content"); 
					 if( sc== null || "".equals(sc))
						 {  }
					 else
						 {
							//只有输入框输入值才会出现在session中
							session.setAttribute("searchContent",sc);
						 }

					 	   String 	searchContent =(String) session.getAttribute("searchContent");
							//符合搜索结果的数据数
							int totalCount =es.getSearchCount(searchContent);		  
							
							
							
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
								users = es.queryEveryoneByPage(currentPage, pageSize,searchContent);
								for( User user :  users) 
								{
	
									//设置关注和拉黑的两个按钮的属性
									status = fos.friendQuery(userId, user.getUserId()) ;
	
								//当成中间站--实际意义不同--用于显示时的判断条件
							      user.setStatus(status);
									
								}
						
								
								   page.setObjects(users);
									//将数据传给request
									request.setAttribute("p", page);
									
								   //跳转到everyone.jsp的页面
									request.getRequestDispatcher(EVERYONE_VIEW).forward(request, response);
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
