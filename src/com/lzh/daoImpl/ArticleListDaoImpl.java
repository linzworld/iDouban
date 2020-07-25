package com.lzh.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lzh.dao.ArticleListDao;
import com.lzh.entity.ArticleList;
import com.lzh.util.MyDataSource;
/**
 * @Description 对文章列表所需的信息，进行数据库的操作 
 * @author 林泽鸿
 * @time 2019年5月5日 下午4:02:20
 */
public class ArticleListDaoImpl implements   ArticleListDao
{
	private Connection con;
	
	private PreparedStatement stmt;	
	
	
	//新建一个数据库连接池对象
	MyDataSource dataSource = new MyDataSource();
	

	/**
	 * 查询列表的记录总数  article表
	 */
	@Override
	public int getTotalCount() {
		//总数据量
		int count = -1;
		try {
			//获取连接池连接
			con = dataSource.getConnection();
			//查询数据库中记录的总数量
			String sql = "SELECT  count(1) FROM a_article";
			
			stmt = con.prepareStatement(sql);
		
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) //若查询结果不为空
			{
				//总数据量
			count = rs.getInt(1);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			  
			System.out.println("ArticleListDaoImpl---getTotalCount--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return count;
	}


	

	/**
	 * 通过用户定义的页面数和页面大小，进行分页查询  排序 article表
	 */
	@Override
	public List<ArticleList> queryArticleListByPage(int currentPage,int pageSize){
		List<ArticleList> articleLists = null;
		articleLists = new ArrayList<>();
		try {
			con = dataSource.getConnection();
			//分页查询的sql语句
			String sql = "SELECT * FROM a_article  ORDER BY stick DESC, published_time DESC  limit ? offset ?";
			//占位符代表的参数
			//limit 查询的数目====pageSize
			//offset对应的记录开始数
			int begin = ( currentPage-1 )*pageSize;
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, pageSize);
			stmt.setInt(2, begin);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){//拿到数据集合
				ArticleList articleList = new ArticleList();
				//辨别一条记录的值---拿到article表中的数据
				//文章基本信息
				articleList.setArticleId(rs.getInt("article_id"));
				articleList.setTitle(rs.getString("title"));
				articleList.setAuthorId(rs.getInt("author"));
				articleList.setContent(rs.getString("content"));
				//文章的发表时间
				articleList.setPublishedTime(rs.getTimestamp("published_time"));
				//文章对于用户互动的数量--评论数...	
				articleList.setCollectionNum(rs.getInt("collection"));
				articleList.setCommentNum(rs.getInt("comment"));
				articleList.setShareNum(rs.getInt("share"));
				articleList.setStarNum(rs.getInt("star"));
				articleList.setPageView(rs.getInt("page_view"));
				
				//是否置顶
				articleList.setStick(rs.getInt("stick"));
				//放到集合中
				articleLists.add(articleList);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ArticleListDaoImpl---queryArticleListByPage--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return articleLists;
	}
	

	/**
	 * 查询我的文章列表的记录总数  article表--我的文章列表
	 */
	@Override
	public int getMyTotalCount(int userId ) {
		//总数据量
		int count = -1;
		try {
			//获取连接池连接
			con = dataSource.getConnection();
			//查询数据库中记录的总数量
			String sql = "SELECT  count(1) FROM a_article WHERE author = ?";
			
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) //若查询结果不为空
			{
				//总数据量
			count = rs.getInt(1);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			  
			System.out.println("ArticleListDaoImpl---getMyTotalCount--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return count;
	}
	
	
	
	/**
	 * 通过用户定义的页面数和页面大小，进行分页查询  排序 article表
	 */
	@Override
	public List<ArticleList> queryMyArticleListByPage(int currentPage,int pageSize ,int userId){
		List<ArticleList> articleLists = null;
		articleLists = new ArrayList<>();
		try {
			con = dataSource.getConnection();
			//分页查询的sql语句
			String sql = "SELECT * FROM a_article WHERE author  = ?  ORDER BY stick DESC, published_time DESC  limit ? offset ?";
			//占位符代表的参数
			//limit 查询的数目====pageSize
			//offset对应的记录开始数
			int begin = ( currentPage-1 )*pageSize;
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, userId);
			stmt.setInt(2, pageSize);
			stmt.setInt(3, begin);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){//拿到数据集合
				ArticleList articleList = new ArticleList();
				//辨别一条记录的值---拿到article表中的数据
				//文章基本信息
				articleList.setArticleId(rs.getInt("article_id"));
				articleList.setTitle(rs.getString("title"));
				articleList.setAuthorId(rs.getInt("author"));
				articleList.setContent(rs.getString("content"));
				//文章的发表时间
				articleList.setPublishedTime(rs.getTimestamp("published_time"));
				//文章对于用户互动的数量--评论数...	
				articleList.setCollectionNum(rs.getInt("collection"));
				articleList.setCommentNum(rs.getInt("comment"));
				articleList.setShareNum(rs.getInt("share"));
				articleList.setStarNum(rs.getInt("star"));
				articleList.setPageView(rs.getInt("page_view"));
				
				//是否置顶
				articleList.setStick(rs.getInt("stick"));
				//放到集合中
				articleLists.add(articleList);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ArticleListDaoImpl---queryArticleListByPage--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return articleLists;
	}



	/**
	 *文章列表进行模糊搜索--得到符合条件的记录数 
	 */
	@Override
	public int getArticleSearchCount(String searchContent)
	{
		//总数据量
		int count = -1;
		try {
			//获取连接池连接
			con = dataSource.getConnection();
			//查询数据库中记录的总数量---a_article表
			String sql = "SELECT  count(1)   FROM a_article WHERE  title LIKE  ? ";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1,"%"+searchContent+"%");
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) //若查询结果不为空
			{
				//总数据量
			count = rs.getInt(1);
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ArticleListDaoImpl---getArticleSearchCount--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return count;

	}



		/**
		 * 模糊 -- 分页 -- 通过搜索的内容得到符合条件的对象集合 
		 */
		@Override
		public List<ArticleList> queryArticleByPage(String searchContent, int currentPage, int pageSize){
		List<ArticleList> articleLists = null;
		articleLists = new ArrayList<>();
		try {
			con = dataSource.getConnection();
			//分页查询的sql语句SELECT  count(1)   FROM a_article WHERE  title LIKE  ?
			String sql = " SELECT  *  FROM  a_article WHERE title LIKE  ? LIMIT ? OFFSET ? "; 
			//占位符代表的参数
			//limit 查询的数目====pageSize
			//offset对应的记录开始数
			int begin = ( currentPage-1 )*pageSize;
			stmt = con.prepareStatement(sql);
	
			stmt.setString(1, "%"+searchContent+"%");
			stmt.setInt(2 , pageSize);
			stmt.setInt(3 ,  begin);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){//拿到数据集合
				ArticleList articleList = new ArticleList();
				//辨别一条记录的值---拿到article表中的数据
				//文章基本信息
				articleList.setArticleId(rs.getInt("article_id"));
				articleList.setTitle(rs.getString("title"));
				articleList.setAuthorId(rs.getInt("author"));
				articleList.setContent(rs.getString("content"));
				//文章的发表时间
				articleList.setPublishedTime(rs.getTimestamp("published_time"));
				//文章对于用户互动的数量--评论数...	
				articleList.setCollectionNum(rs.getInt("collection"));
				articleList.setCommentNum(rs.getInt("comment"));
				articleList.setShareNum(rs.getInt("share"));
				articleList.setStarNum(rs.getInt("star"));
				articleList.setPageView(rs.getInt("page_view"));
				
				//是否置顶
				articleList.setStick(rs.getInt("stick"));
				//放到集合中
				articleLists.add(articleList);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ArticleListDaoImpl---模糊搜索---queryArticleByPage--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return articleLists;
		
	}



		/**
		 * 得到我的文章中的符合搜索条件的记录数
		 */
		@Override
		public int getMyArticleSearchCount(String searchContent, int authorId)
		{
			//总数据量
			int count = -1;
			try {
				//获取连接池连接
				con = dataSource.getConnection();
				//查询数据库中记录的总数量---a_article表
				String sql = "SELECT  count(1)   FROM a_article WHERE author = ? AND  title LIKE  ? ";
				stmt = con.prepareStatement(sql);
				
				stmt.setInt( 1, authorId ) ;
				stmt.setString( 2,"%"+searchContent+"%" ) ;
				
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) //若查询结果不为空
				{
					//总数据量
				count = rs.getInt(1);
				}	
			}
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println("ArticleListDaoImpl---getMyArticleSearchCount--数据库连接异常");
			} finally{
				dataSource.backConnection(con);
			}
			return count;
		}




		@Override
		public List<ArticleList> queryMyArticleByPage(String searchContent, int currentPage, int pageSize, int authorId)
		{
			List<ArticleList> articleLists = null;
			articleLists = new ArrayList<>();
			try {
				con = dataSource.getConnection();
				//分页查询的sql语句SELECT  count(1)   FROM a_article WHERE  title LIKE  ?
				String sql = " SELECT  *  FROM  a_article WHERE AND author = ? AND  title LIKE  ? LIMIT ? OFFSET ? "; 
				//占位符代表的参数
				//limit 查询的数目====pageSize
				//offset对应的记录开始数
				int begin = ( currentPage-1 )*pageSize;
				stmt = con.prepareStatement(sql);
				stmt.setInt( 1, authorId  );
				stmt.setString( 2, "%"+searchContent+"%");
				stmt.setInt(3 , pageSize);
				stmt.setInt(4 ,  begin);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){//拿到数据集合
					ArticleList articleList = new ArticleList();
					//辨别一条记录的值---拿到article表中的数据
					//文章基本信息
					articleList.setArticleId(rs.getInt("article_id"));
					articleList.setTitle(rs.getString("title"));
					articleList.setAuthorId(rs.getInt("author"));
					articleList.setContent(rs.getString("content"));
					//文章的发表时间
					articleList.setPublishedTime(rs.getTimestamp("published_time"));
					//文章对于用户互动的数量--评论数...	
					articleList.setCollectionNum(rs.getInt("collection"));
					articleList.setCommentNum(rs.getInt("comment"));
					articleList.setShareNum(rs.getInt("share"));
					articleList.setStarNum(rs.getInt("star"));
					articleList.setPageView(rs.getInt("page_view"));
					
					//是否置顶
					articleList.setStick(rs.getInt("stick"));
					//放到集合中
					articleLists.add(articleList);
				}	
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("ArticleListDaoImpl---模糊搜索---queryMyArticleByPage--数据库连接异常");
			} finally{
				dataSource.backConnection(con);
			}
			return articleLists;
			
		}



		/**
		 * SELECT---查找用户收藏的文章的数量
		 */
		@Override
		public int getMyCollectionTotalCount(int userId)
		{
			//总数据量
			int count = -1;
			try {
				//获取连接池连接
				con = dataSource.getConnection();
				//查询数据库中记录的总数量
				String sql = "SELECT  count(1) FROM a_article , a_collection WHERE  a_article.article_id = a_collection.article_id   AND  a_collection.user_id = ? ";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, userId);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) //若查询结果不为空
				{
					//总数据量
				count = rs.getInt(1);
				}	
			} catch (SQLException e) {
				e.printStackTrace();
				  
				System.out.println("ArticleListDaoImpl---getMyCollectionTotalCount--数据库连接异常");
			} finally{
				dataSource.backConnection(con);
			}
			return count;
		}



		/**
		 * SELECT---返回一个对象集合（articleLists）--我的收藏
		 */
		@Override
		public List<ArticleList> queryMyCollectionByPage(int currentPage, int pageSize, int userId)
		{
			List<ArticleList> articleLists = null;
			articleLists = new ArrayList<>();
			try {
				con = dataSource.getConnection();
				//分页查询的sql语句
				String sql = "SELECT * FROM  a_article , a_collection  WHERE a_article.article_id = a_collection.article_id   AND  a_collection.user_id = ?  ORDER BY stick DESC, published_time DESC  limit ? offset ?";
				//占位符代表的参数
				//limit 查询的数目====pageSize
				//offset对应的记录开始数
				int begin = ( currentPage-1 )*pageSize;
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, userId);
				stmt.setInt(2, pageSize);
				stmt.setInt(3, begin);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){//拿到数据集合
					ArticleList articleList = new ArticleList();
					//辨别一条记录的值---拿到article表中的数据
					//文章基本信息
					articleList.setArticleId(rs.getInt("article_id"));
					articleList.setTitle(rs.getString("title"));
					articleList.setAuthorId(rs.getInt("author"));
					articleList.setContent(rs.getString("content"));
					//文章的发表时间
					articleList.setPublishedTime(rs.getTimestamp("published_time"));
					//文章对于用户互动的数量--评论数...	
					articleList.setCollectionNum(rs.getInt("collection"));
					articleList.setCommentNum(rs.getInt("comment"));
					articleList.setShareNum(rs.getInt("share"));
					articleList.setStarNum(rs.getInt("star"));
					articleList.setPageView(rs.getInt("page_view"));
					
					//是否置顶
					articleList.setStick(rs.getInt("stick"));
					//放到集合中
					articleLists.add(articleList);
				}	
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("ArticleListDaoImpl---queryMyCollectionByPage--数据库连接异常");
			} finally{
				dataSource.backConnection(con);
			}
			return articleLists;
		}
	
}