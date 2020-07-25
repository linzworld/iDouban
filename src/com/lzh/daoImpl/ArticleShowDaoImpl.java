package com.lzh.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lzh.dao.ArticleShowDao;
import com.lzh.entity.Article;
import com.lzh.entity.ArticleComment;
import com.lzh.entity.ArticleReply;
import com.lzh.util.MyDataSource;
/**
 * @Description 与文章的显示相关的所有数据库操作----具体实现类 
 * @author 林泽鸿
 * @time 2019年5月10日 上午1:29:08
 */
public class ArticleShowDaoImpl implements ArticleShowDao
{

	private Connection con;
	
	private PreparedStatement stmt;	
	
	
	//新建一个数据库连接池对象
	MyDataSource dataSource = new MyDataSource();
	
	/**
	 * Query---通过文章id查找文章
	 */
	@Override
	public Article articleByArticlerId(int articleId)
	{

		Article article = new Article();
		try {
			con = dataSource.getConnection();
			String sql = "SELECT * FROM a_article  WHERE article_id = ?";
			
			//查询文章信息
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, articleId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				
				//辨别一条记录的值
				article.setArticleId(rs.getInt("article_id"));
				//文章基本信息
				article.setTitle(rs.getString("title"));
				article.setAuthorId(rs.getInt("author"));
				article.setPublishedTime(rs.getTimestamp("published_time"));
				article.setContent(rs.getString("content"));
				//用户操作文章产生的结果
				article.setCollectionNum(rs.getInt("collection"));
				article.setShareNum(rs.getInt("share"));
				article.setCommentNum(rs.getInt("comment"));
			    article.setStarNum(rs.getInt("star"));
			    article.setStick(rs.getInt("stick"));
			    article.setPageView(rs.getInt("page_view"));
			}	
			return article;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ArticleShowDaoImpl---articleByArticlerId--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return article ;
	}
	
	/**
	 * INSERT --增加一条评论文章的消息
	 * @return 
	 */
	@Override
	public boolean commentInsert(ArticleComment articleComment ) {
		
		boolean judge = false;
		
		
		try {
			//获取数据库连接池中的一个连接
			con = dataSource.getConnection();
			//默认增加时是单向关注
			String sql="insert into a_comment  ( article_id , user_id , c_msg ) values (?,?,?)";
			PreparedStatement stmt=con.prepareStatement(sql) ;
			stmt.setInt ( 1, articleComment.getArticleId() ) ;
			stmt.setInt ( 2, articleComment.getUserComId() ) ;
			//用户评论文章的消息
			stmt.setString(3, articleComment.getComMsg());
			
			int t = stmt.executeUpdate();
			if(t>0) 
			{
				judge = true;
			}
		} catch (SQLException e) {
			judge=false;
			System.out.println("ArticleShowDaoImpl---commentInsert--数据库连接异常");
			e.printStackTrace();
		}
		finally {
			dataSource.backConnection(con);
			}
		return judge;
		
	}

	
	
	/**
	 * SELECT--通过文章id查找评论表的信息--a_comment
	 */
	@Override
	public ArticleComment getCommentInfo(int  articleId) {
		ArticleComment articleComment = new ArticleComment() ;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT * FROM  a_comment  WHERE article_id = ?";
			
			//查询评论的信息
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, articleId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				
				//辨别一条记录的值
				articleComment.setCommentId(rs.getInt("comment_id")) ;
				//文章id和用户id
				articleComment.setArticleId(rs.getInt("article_id")) ;
				articleComment.setUserComId(rs.getInt("user_id")) ;
				
				//评论表的具体信息
				articleComment.setComMsg(rs.getString("c_msg"));
				articleComment.setComStar(rs.getInt("c_star")) ;
				articleComment.setComTime(rs.getTimestamp("c_time"));
	
			}	
			return articleComment  ;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ArticleShowDaoImpl---getCommentInfo--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return articleComment;
	}

	

	/**
	 * 查询列表的记录总数  article表
	 */
	@Override
	public int getCommentTotalCount( int  articleId ){
		//总数据量
		int count = -1;
		try {
			//获取连接池连接
			con = dataSource.getConnection();
			//查询数据库中记录的总数量
			String sql = " SELECT  count(1) FROM a_comment WHERE article_id = ? " ;
			
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, articleId);
			ResultSet rs = stmt.executeQuery();
			//若查询结果不为空
			if(rs.next()) 
			{
			//总数据量
			count = rs.getInt(1);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			  
			System.out.println("ArticleShowDaoImpl---getCommentTotalCount--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return count;
	}


	

	/**
	 * 通过用户定义的页面数和页面大小，进行分页查询  排序 article表
	 */
	@Override
	public List<ArticleComment> queryCommentByPage ( int currentPage,int pageSize ,int articleId ){
		List<ArticleComment> articleComments = null;
		articleComments = new ArrayList<>();
		try {
			con = dataSource.getConnection();
			//分页查询的sql语句--降序
			String sql = "SELECT * FROM a_comment WHERE article_id = ?  ORDER BY c_time DESC  limit ? offset ? ";
			/*
			 * 占位符代表的参数 
			 *  limit 查询的数目====pageSize   
			 *  offset对应的记录开始数=====起始的位置（从0开始）
			 */
			int begin = ( currentPage-1 )*pageSize;
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, articleId);
			stmt.setInt(2, pageSize);
			stmt.setInt(3, begin);
			
			ResultSet rs = stmt.executeQuery();
			//拿到数据集合
			while(rs.next()){
				/**
				 * 评论表的基本信息
				 */
				ArticleComment articleComment = new ArticleComment();			
				//辨别一条记录的值
				articleComment.setCommentId(rs.getInt("comment_id")) ;
				//辨别一条记录的值---拿到a_comment表中的数据
				articleComment.setArticleId(rs.getInt("article_id")) ;
				//用户id
				articleComment.setUserComId(rs.getInt("user_id")) ;
				//评论表的具体信息
				articleComment.setComMsg(rs.getString("c_msg"));
				articleComment.setComStar(rs.getInt("c_star")) ;
				articleComment.setComTime(rs.getTimestamp("c_time"));
				//放到集合中
				articleComments.add( articleComment );
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ArticleShowDaoImpl---queryCommentByPage--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return articleComments;
	}



	/**
	 * INSERT --增加一条回复评论的消息
	 */
	@Override
	public boolean replyInsert(ArticleReply articleReply ) {
	
	boolean judge = false;
	
	
	try {
		//获取数据库连接池中的一个连接
		con = dataSource.getConnection();
		//a_reply表的记录的增加
		String sql="insert into a_reply  ( comment_id , from_user_id , to_user_id , reply_msg ) values (? , ? , ? , ?)";
		PreparedStatement stmt=con.prepareStatement(sql) ;
		stmt.setInt ( 1, articleReply.getCommentId() );
		//回复的双方
		stmt.setInt ( 2, articleReply.getUserReplyFromId() ) ;
		stmt.setInt ( 3, articleReply.getUserReplyToId() );
		//用户回复的内容
		stmt.setString( 4, articleReply.getReplyMsg() );
		
		int t = stmt.executeUpdate();
		if(t>0) 
		{
			judge = true;
		}
	} catch (SQLException e) {
		judge=false;
		System.out.println("ArticleShowDaoImpl---replyInsert--数据库连接异常");
		e.printStackTrace();
	}
	finally {
		dataSource.backConnection(con);
		}
	return judge;
	}	
	
	/**
	 * SELECT---通过评论的Id找到该评论的用户id
	 */
	@Override
	public int getUserReplyToId ( ArticleReply articleReply ) {
		//接收回复的人的id
		int  userReplyToId = 0 ;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT user_id  FROM  a_comment  WHERE comment_id = ? " ;
			
			//查询评论的信息
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, articleReply.getCommentId() ) ;
			ResultSet rs = stmt.executeQuery() ;
			if(rs.next()){
				userReplyToId = rs.getInt("user_id") ;
			}	
			return userReplyToId  ;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ArticleShowDaoImpl---getUserReplyToId--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return userReplyToId;
	}
	
	/**
	 * SELECT --a_reply表--条件comment_id 
	 */
	@Override
	public ArticleReply getReplyInfo  (  ArticleComment articleComment ) {
		ArticleReply articleReply = new ArticleReply() ;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT * FROM a_reply  WHERE comment_id = ? " ;
			
			//查询评论的信息
			stmt = con.prepareStatement(sql);
			stmt.setInt( 1, articleComment.getCommentId() );
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				//对应a_reply表中的数据
				articleReply.setReplyId( rs.getInt("reply_id") );
				articleReply.setCommentId(rs.getInt("comment_id") );
				articleReply.setUserReplyFromId(rs.getInt("from_user_id"));
				articleReply.setUserReplyToId(rs.getInt("to_user_id"));
				articleReply.setReplyMsg(rs.getString("reply_msg"));
				articleReply.setReplyTime(rs.getTimestamp("reply_time"));
				articleReply.setReplyStar(rs.getInt("r_star"));
		}	
			return articleReply  ;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ArticleShowDaoImpl---getReplyInfo--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return articleReply;
	}
	
	/**
	 * SELECT--得到ArticleReply对象集合
	 */
	@Override
	public List<ArticleReply> queryReplyByComment ( ArticleComment  articleComment) {
		List<ArticleReply> articleReplys = null;
		articleReplys = new ArrayList<>();
		try {
			con = dataSource.getConnection();
			//根据评论查找的sql语句
			String sql = "SELECT * FROM a_reply WHERE comment_id = ?  ";

			stmt = con.prepareStatement(sql);
			stmt.setInt(1, articleComment.getCommentId());
			ResultSet rs = stmt.executeQuery();
			//拿到数据集合
			while(rs.next()){
				/**
				 * 评论表的基本信息
				 */
				ArticleReply articleReply = new ArticleReply();			
				//辨别一条记录的值
				articleReply.setReplyId( rs.getInt("reply_id") );
				articleReply.setCommentId(rs.getInt("comment_id") );
				articleReply.setUserReplyFromId(rs.getInt("from_user_id"));
				articleReply.setUserReplyToId(rs.getInt("to_user_id"));
				articleReply.setReplyMsg(rs.getString("reply_msg"));
				articleReply.setReplyTime(rs.getTimestamp("reply_time"));
				articleReply.setReplyStar(rs.getInt("r_star"));
				//放到集合中
				articleReplys.add( articleReply );
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ArticleShowDaoImpl---queryReplyByComment--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return articleReplys ;
	}
	
	}
	

