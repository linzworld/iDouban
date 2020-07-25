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

import com.lzh.entity.ArticleList;
import com.lzh.entity.Page;
import com.lzh.entity.User;
import com.lzh.service.ArticleEditService;
import com.lzh.service.ArticleListService;

/** * @time 2019年5月5日 下午3:32:30

 * @Description 对文章的具体操作进行处理
 * @author 林泽鸿
 */
@WebServlet("/ArticleListServlet")
public class ArticleListServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	static final String ATICLELIST_VIEW = "/jsp/article_list.jsp";

	/**
	 * ------分页---模糊搜索---返回一个数据集合
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		/**
		 * 业务逻辑层-->调用数据库，返回所要的数据集合
		 */
		ArticleListService articleListService = new ArticleListService();
		ArticleEditService articleEditService  = new ArticleEditService();

		
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

		//预处理的功能---删除文章
		//当前要显示的页面---用户的好友操作-超链接里面的请求所对应的实现方式
   		 String preMethod = request.getParameter("pre_method"); 
		 if(preMethod!=null ) 
			 {
				//请求的方法
				request.setAttribute("pre_method", preMethod);
				String articleIdStr = request.getParameter("article_id");
				if(articleIdStr !=null&&!"".equals(articleIdStr) )
					{
					
					int  articleId = Integer.parseInt(articleIdStr) ;
					//对消息进行预处理---删除文章
					switch (preMethod) 
							{ 
					  		 //用户选择了删除文章
							 case "delete_article": 
							 		{
										articleEditService.articleDelete(articleId);
									}
							}
					}
			 }
		else {}
					// 当前要显示的页面---超链接里面的请求所对应的实现方式
					String method = request.getParameter("method");
					// 请求的方法
					request.setAttribute("method", method);
					// --文章的列表-----switch多重选择
					switch (method)
					{
					// 所有文章列表
					case "article_list":
						{
							// 总数据数
							int totalCount = articleListService.getTotalCount();
			
							// 组装page对象
							// 先set totalCount属性 作为分子
							page.setTotalCount(totalCount);
							page.setCurrentPage(currentPage);
							page.setPageSize(pageSize);
			
							/**
							 * 拿到数据集合--分页
							 */
							List<ArticleList> articleLists = new ArrayList<>();
							articleLists = articleListService.queryArticleListByPage(currentPage, pageSize);
			
							page.setObjects(articleLists);
							// 将数据传给request
							request.setAttribute("p", page);
							request.setAttribute("msg", "所有文章");
			
							// 跳转到article_list.jsp的页面
							request.getRequestDispatcher(ATICLELIST_VIEW).forward(request, response);
							return;
			
						}
					// 搜索框的按钮---所有文章进行搜索
					case "search_article_list":
						{
							//用户所要搜索的内容--模糊搜索---文章标题
							 String sc = request.getParameter("search_content"); 
								 if( sc== null || "".equals(sc))
									 {  							}
								 else
									 {
										//只有输入框输入值才会出现在session中
										session.setAttribute("searchContent",sc);
									 }
			
							 	   String 	searchContent =(String) session.getAttribute("searchContent");
									//符合搜索结果的数据数
									int totalCount =articleListService.getArticleSearchCount(searchContent);		  
									//组装page对象
									//先set totalCount属性 作为分子
									page.setTotalCount(totalCount);
									page.setCurrentPage(currentPage);
									page.setPageSize(pageSize);
											 /**
										 * 拿到数据集合
										 */
										List<ArticleList> articleLists = new ArrayList<ArticleList>();
										//模糊搜索的功能---DAO
										articleLists = articleListService.queryArticleByPage(searchContent, currentPage, pageSize);
										
										   page.setObjects(articleLists);
											//将数据传给request
											request.setAttribute("p", page);
											request.setAttribute("msg", "在所有文章中的搜索结果");
											// 跳转到article_list.jsp的页面
											request.getRequestDispatcher(ATICLELIST_VIEW).forward(request, response);
											return;
							}
					//我的文章列表
					case "my_article_list":
							{
								// 总数据数
								int totalCount = articleListService.getMyTotalCount(userId);
					
								// 组装page对象
								// 先set totalCount属性 作为分子
								page.setTotalCount(totalCount);
								page.setCurrentPage(currentPage);
								page.setPageSize(pageSize);
					
								/**
								 * 拿到数据集合--分页---我的文章
								 */
								List<ArticleList> articleLists = new ArrayList<>();
								articleLists = articleListService.queryMyArticleListByPage(currentPage, pageSize, userId) ;
					
								page.setObjects(articleLists);
								// 将数据传给request
								request.setAttribute("p", page);
								request.setAttribute("msg", "我的文章");
								// 跳转到article_list.jsp的页面
								request.getRequestDispatcher(ATICLELIST_VIEW).forward(request, response);
								return;
							}
						// 搜索框的按钮---所有文章进行搜索
						case "search_my_article_list":
								{
									//用户所要搜索的内容--模糊搜索---文章标题
									 String sc = request.getParameter("search_content"); 
										 if( sc== null || "".equals(sc))
											 {  							}
										 else
											 {
												//只有输入框输入值才会出现在session中
												session.setAttribute("searchContent",sc);
											 }
									 	   String 	searchContent =(String) session.getAttribute("searchContent");
											//符合搜索结果的数据数
											int totalCount =articleListService.getMyArticleSearchCount(searchContent, userId) ;
											//组装page对象
											//先set totalCount属性 作为分子
											page.setTotalCount(totalCount);
											page.setCurrentPage(currentPage);
											page.setPageSize(pageSize);
													 /**
												 * 拿到数据集合
												 */
												List<ArticleList> articleLists = new ArrayList<ArticleList>();
												//模糊搜索的功能---DAO
												articleLists = articleListService.queryMyArticleByPage( searchContent, currentPage, pageSize,userId ) ;
												
												   page.setObjects(articleLists);
													//将数据传给request
													request.setAttribute("p", page);
													request.setAttribute("msg", "在我的文章中的搜索结果");
													// 跳转到article_list.jsp的页面
													request.getRequestDispatcher(ATICLELIST_VIEW).forward(request, response);
													return;
									}
						//我的收藏列表
						case "my_collection_list":
						{
							// 总数据数
							int totalCount = articleListService.getMyCollectionTotalCount(userId) ;
				
							// 组装page对象
							// 先set totalCount属性 作为分子
							page.setTotalCount(totalCount);
							page.setCurrentPage(currentPage);
							page.setPageSize(pageSize);
				
							/**
							 * 拿到数据集合--分页---我的文章
							 */
							List<ArticleList> articleLists = new ArrayList<>();
							articleLists = articleListService.queryMyCollectionByPage(currentPage, pageSize, userId) ;
				
							page.setObjects(articleLists);
							// 将数据传给request
							request.setAttribute("p", page);
							request.setAttribute("msg", "我的收藏");
							// 跳转到article_list.jsp的页面
							request.getRequestDispatcher(ATICLELIST_VIEW).forward(request, response);
							return;
						}
					default: break;
					}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
