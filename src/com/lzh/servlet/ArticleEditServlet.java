package com.lzh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lzh.entity.Article;
import com.lzh.entity.User;
import com.lzh.service.ArticleEditService;
/**
 * @Description 用于文章的编辑 
 * @author 林泽鸿
 * @time 2019年5月2日 下午3:30:50
 */
@WebServlet("/ArticleEditServlet")
public class ArticleEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String  ARTICLE_EDIT_VIEW = "/jsp/article_edit.jsp";
  /**
   * 引用业务逻辑层--文章的修改和编辑
   */
	ArticleEditService 	articleEditService = new ArticleEditService();
	Article article = new Article();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
				//传输HTML代码---div中的HTML代码
				String transfer = (String) request.getParameter("transfer");
						//文章的标题
							String title  =  (String) request.getParameter("title");
							   //得到session
								HttpSession session = request.getSession();
								User u = null;
								u = new User();
							    u =( User )session.getAttribute("userInfo");
							    int userId =0 ;
							    /**
							     * 当前用户的id值
							     */
							    if(u!=null)
							    {
							    	 userId = u.getUserId();
							    }	
								//包括作者的id（当前用户）一起放到实体类中
								article.setContent(transfer);
								article.setTitle(title);
								article.setAuthorId(userId);
								
								//通过前端是否能够返回文章的id--字符串类型
								String articleIdStr = request.getParameter("article-id");
								/**
								 * 文章id
								 */
								int articleId = 1 ;

								// 当前要显示的页面---超链接里面的请求所对应的实现方式
								String method = request.getParameter("method");
								// 请求的方法
								request.setAttribute("method", method);
								
								// --编辑文章的功能-----switch多重选择
								switch (method)
								{
									// 直接写文章，写完文章上传---insert
									case "edit_article":
										{
												//调用service方法--新增文章
												articleId = articleEditService.articleInsert(article) ;
														//新增文章之后的标签处理
														if( articleId !=-1 )
															{                     }	
														else  
															{					}
														//对四个可能为空的用户选择的分类名进行有判断性的插入操作
														String a = request.getParameter("a");
														String b = request.getParameter("b");
														String c = request.getParameter("c");
														String s = request.getParameter("s");
															//进行文章分类标签的增加
															//非空判断
															if( a == null  ||  "".equals(a) )
																	{ 										}
															else 
																{
																articleEditService.articleNewTag ( a ,articleId ) ;
																}
															if( b == null  ||  "".equals(b) )
																	{	  									}
															else 
																{    
																articleEditService.articleNewTag ( b,articleId ) ;
																}
															if( c  == null  ||  "".equals( c ) )
															    { 											}
															else 
																{    
																articleEditService.articleNewTag ( c ,articleId ) ;
																}
															if( s  == null  ||  "".equals( s ) )
															    {  												 }
															else 
																{    
																articleEditService.articleNewTag ( s ,articleId ) ;
																}
															// 跳转到article_list.jsp的页面
															request.getRequestDispatcher("/ArticleListServlet?method=my_article_list").forward(request, response);
															return ;
										}
									//请求自己编辑过的文章，从而进行编辑----select	
									case"search_edit_article":	
										{
												
										}
										break ;
									// 修改完文章后，文章的提交，提交方式为update数据
									case "alter_article":
										{
										    articleId = Integer.parseInt(articleIdStr);
										    article.setArticleId(articleId);
										    boolean ret = false ;
										    ret = articleEditService.articleModify(article);
										    if(ret) 
										    	{
										    		System.out.println("修改文章成功");
										    	}
										    request.setAttribute("edit_msg", "修改文章");
										    request.getRequestDispatcher(ARTICLE_EDIT_VIEW).forward(request, response);
											return ;
										
										}
									default: break;
								}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doGet(request, response);
		
	}

}
