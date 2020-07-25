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

import com.lzh.entity.Article;
import com.lzh.entity.ArticleComment;
import com.lzh.entity.ArticleReply;
import com.lzh.entity.Page;
import com.lzh.entity.User;
import com.lzh.service.ArticleInteractionService;
import com.lzh.service.ArticleShowService;

/**
 * @Description 文章的展示--文章主体-评论区-回复区
 * @author 林泽鸿
 * @time 2019年5月5日 下午4:24:51
 */
@WebServlet("/ArticleShowServlet")
public class ArticleShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ARTICLESHOW_VIEW = "/jsp/article_show.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 业务逻辑层-->调用数据库，返回所要的数据集合
		 */
		ArticleShowService articleShowService = new ArticleShowService();
		ArticleInteractionService articleInteractionService = new ArticleInteractionService() ;
		// 改成中文编码
		request.setCharacterEncoding("utf-8");

		// 得到session
		HttpSession session = request.getSession();
		User u = null;
		u = new User();
		u = (User) session.getAttribute("userInfo");
		int userId = 0;
		/**
		 * 当前用户的id值
		 */
			if (u != null)
				{
					userId = u.getUserId();
				}
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
							//先定义文章的id
							int articleId = 0  ;
							//当前页面对应的是文章的id---用来唯一辨识页面的东西
							String articleIdStr = request.getParameter("article_id");
							
							if(articleIdStr==null  ||  "".equals(articleIdStr))
								{                                							}
							//将文章id的字符串形式解析为int类型
							else 
								{
									articleId = Integer.parseInt(articleIdStr);
								}
							//定义评论的id
							int commentId = 0  ;
							String commentIdStr = request.getParameter("comment_id");
							if( commentIdStr==null  ||  "".equals ( commentIdStr ) )
								{                                                              }
							//将评论的id的字符串形式解析为int类型
							else 
								{
								commentId = Integer.parseInt(commentIdStr);
								}		
										
						//预处理的功能
						//当前要显示的页面---用户的好友操作-超链接里面的请求所对应的实现方式
						 String preMethod = request.getParameter("pre_method"); 
						 if(preMethod!=null ) 
							 {
								//请求的方法
								request.setAttribute("pre_method", preMethod);
								
									//对消息进行预处理----回复-评论-点赞
									switch (preMethod) { 
									  		//用户选择了回复的按钮,并且进行了回复的内容的提交
											case "reply_content": 
													{
														//共同代码
														//回复的内容--textarea中填写的内容
														String replyMsg = request.getParameter("content") ;
														
													    //组装数据到ArticleReply
														ArticleReply articleReply = new ArticleReply() ;
															
														articleReply.setCommentId(  commentId ) ;
														articleReply.setUserReplyFromId(userId);
														articleReply.setReplyMsg(replyMsg);
														
														//定义被回复的回复的人的Id
														int toUserId = 0  ;
														String toUserIdStr = request.getParameter("reply_to_use_id");
														//回复的回复的提交
														if( toUserIdStr !=null && !"".equals(toUserIdStr))
															{  
															//将被回复的回复的人的Id的字符串形式解析为int类型
															toUserId = Integer.parseInt(toUserIdStr);
															//表单中含有的参数
															 articleReply.setUserReplyToId( toUserId  )   ;
																//调用service层
																if( articleShowService.replyInsertReply(articleReply)) 
																	{
																		System.out.println("回复的回复消息增加成功");
																	}
																else 
																	{   													}
															}
														//用户选择了回复的按钮,并且进行了回复的内容的提交
														else 
														{
																//调用service层
																if( articleShowService.replyInsert(articleReply) ) 
																	{
																		System.out.println("回复消息增加成功");
																	}
																else 
																	{   													}
															}		
												    } 
													break;
											//用户进行评论的操作	
											case "comment_content":
													{
														//用户评论的内容
														String comMsg = request.getParameter("content");
														
												
														//将数据组装到一个对象中
														ArticleComment articleComment = new ArticleComment();
														
														articleComment.setArticleId(articleId);
														articleComment.setUserComId(userId);
														articleComment.setComMsg(comMsg);
														if( articleShowService.commentInsert(articleComment) ) 
															{
																System.out.println("评论消息增加成功");
															}
														else 
															{                             								     }
													}
													break;
											//文章点赞	
											case"article_star":
													{
														articleInteractionService.starInsert(articleId, 1, userId) ;
													}
												break;
											//评论点赞
											case"comment_star":
													{
														articleInteractionService.starInsert(commentId, 2, userId) ;
													}	
													break;
											//回复点赞	
											case"reply_star":
													{
														articleInteractionService.starInsert(articleId, 3, userId) ;
													}	
													break;
											//取消文章点赞	
											case"article_star_cancel":
													{
														articleInteractionService.starDelete(articleId, 1, userId);
													}	
													break;
											//取消评论点赞	
											case"comment_star_cancel":
													{
														articleInteractionService.starDelete(commentId, 2, userId);

													}	
													break;
											//取消回复点赞	
											case"reply_star_cancel":
													{
														articleInteractionService.starDelete(articleId, 3, userId);
													}	
													break;
										
											default:
												   break; 
									}
									
									 //文章的收藏功能
									 switch(preMethod) {
									 			//收藏文章
									 			case"article_collection":
									 				{
										 					articleInteractionService.collectionInsert(articleId, userId) ;
									 				}
									 				break ;
									 			//取消收藏文章
									 			case "article_collection_cancel":
									 				{
									 						articleInteractionService.collectionDelete(articleId, userId) ;
									 				}
									 				break;
									 				default:  break; 
									 }			
									 
									 //文章的转发功能
									 switch(preMethod) {
									 			//收藏文章
									 			case"article_share":
									 				{
										 					articleInteractionService.shareInsert(articleId, userId) ;
									 				}
									 				break ;
									 			//取消收藏文章
									 			case "article_share_cancel":
									 				{
									 						articleInteractionService.shareDelete(articleId, userId) ;
									 				}
									 				break;
									 				default:  break; 
									 }			
							 }
						 else {  }

						 			// 当前要显示的页面---超链接里面的请求所对应的实现方式
									String method = request.getParameter("method");
									// 请求的方法
									request.setAttribute("method", method);
												// --查看文章的方式-----switch多重选择
												switch (method)
												{
												//展示文章的按钮
												case "article_show":
													{
														
														Article article = new Article() ;
														//拿到查询结果的值
														article = articleShowService.getArticleInfo(articleId);
														// 将数据传给request
														request.setAttribute("article", article);
														
															 //判断用户是否已点赞文章
															 if( articleInteractionService.starExist(articleId, 1, userId) ==1)
																{
																	request.setAttribute("starStatus", "已点赞文章");
																}
															else 
																{
																	request.setAttribute("starStatus", "未点赞文章");
																}
										 						//判断用户是否已收藏文章
										 						if( articleInteractionService.collectionExist(articleId, userId) )
																	{
																		request.setAttribute("collectionStatus", "已收藏文章");
																	}
																else 
																	{
																		request.setAttribute("collectionStatus", "未收藏文章");
																	}
										 						//判断用户是否已转发文章
										 						if( articleInteractionService.shareExist(articleId, userId) )
																	{
																		request.setAttribute("shareStatus", "已转发文章");
																	}
																else 
																	{
																		request.setAttribute("shareStatus", "未转发文章");
																	}
														// 跳转到article_show.jsp的页面
														request.getRequestDispatcher(ARTICLESHOW_VIEW).forward(request, response);
														return ;					
													}
												//文章下方的评论区展示
												case"comment_show":
													{
														// 总数据数
														int totalCount = articleShowService.getCommentTotalCount(articleId) ;
										
														// 组装page对象
														// 先set totalCount属性 作为分子
														page.setTotalCount(totalCount);
														page.setCurrentPage(currentPage);
														page.setPageSize(pageSize);
										
														/**
														 * 拿到数据集合--分页
														 */
														List<ArticleComment> articleComments = new ArrayList<ArticleComment>();
														articleComments =articleShowService.queryCommentByPage(currentPage, pageSize ,articleId ,userId );
										
														page.setObjects(articleComments);
					
														//1、使用JSONObject
														JSONObject json = new JSONObject(page);
														
												        //JSON对象转化为字符串
												        String strJson=json.toString();
												        
												        System.out.println("strJson:"+strJson);
												        response.setContentType("text/html;charset=UTF-8");
														//用输出流--将数据传给article_show.jsp的页面
												        PrintWriter writer = response.getWriter();
										
												        writer.write(strJson);
												        //数据量较大的情况下用flush
												        writer.flush();
										
														return;
													}	
													//评论区中的回复的显示
												case"reply_show":
													{
														// 总数据数
														int totalCount = articleShowService.getCommentTotalCount(articleId) ;
										
														// 组装page对象
														// 先set totalCount属性 作为分子
														page.setTotalCount(totalCount);
														page.setCurrentPage(currentPage);
														page.setPageSize(pageSize);
										
														/**
														 * 拿到数据集合--根据评论分页后得到的数据进行查询并且返回结果
														 */
														List<ArticleReply> articleReplys = new ArrayList<ArticleReply>();
														articleReplys =articleShowService.queryReplyByComment ( currentPage, pageSize, articleId ) ;
														
														page.setObjects(articleReplys);
														//1、使用JSONObject
														JSONObject json = new JSONObject(page);
														
														//JSON对象转化为字符串
												        String strJson=json.toString();
												        
												        System.out.println("回复articleReplys--"+"strJson:"+strJson);
												        response.setContentType("text/html;charset=UTF-8");
														//用输出流--将数据传给article_show.jsp的页面--动态刷新
												        PrintWriter writer = response.getWriter();
												        writer.write(strJson);
												        //数据量较大的情况下用flush
												        writer.flush();
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
