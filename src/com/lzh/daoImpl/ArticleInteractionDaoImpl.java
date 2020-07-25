package com.lzh.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lzh.dao.ArticleInteractionDao;
import com.lzh.util.MyDataSource;

/**
 * @Description 文章用户互动的功能--点赞，收藏，转发，评论
 * @author 林泽鸿
 * @time 2019年5月10日 下午8:34:30
 */
public class ArticleInteractionDaoImpl implements ArticleInteractionDao
{

	private Connection con;
	
	//新建一个数据库连接池对象
	MyDataSource dataSource = new MyDataSource();
	
	/**
	 * QUERY--在a_comment表中找到符合该文章id的记录总数 
	 */
	@Override
	public int comNumQuery( int articleId ){
		//默认给该文章的评论总数为0
		int comNum = 0 ;
		try {
			con = dataSource.getConnection();
			
			String sql="SELECT count(1)  FROM a_comment WHERE article_id = ? " ;
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setInt( 1, articleId );
			ResultSet rs = stmt.executeQuery();
			 //若查询结果不为空
			if(rs.next())
			{
				//总评论数
				comNum = rs.getInt(1);
			}	
		} 
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ArticleInteractionDaoImpl---comNumQuery--数据库连接异常");
		}
		finally {
			dataSource.backConnection(con);
			}
		return comNum;
	}

	
	/**
	 * UPDATE--更新文章表中的评论数
	 */
	@Override
	public boolean comNumUpdate( int comNum,  int articleId)
	{
		//返回结果
		boolean judge = false;
		
		try {
			//数据库连接池
			con = dataSource.getConnection();
			//更新文章的评论总数
			String sql="UPDATE a_article SET comment = ?  WHERE  article_id = ?  ";
			PreparedStatement stmt=con.prepareStatement(sql);
			
			stmt.setInt(1,comNum );
			stmt.setInt(2,articleId );
			//进行更新操作，返回成功更新的条数 
			int ret = stmt.executeUpdate();
			if(ret == 1)
			{
				judge = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			  
			System.out.println("ArticleInteractionDaoImpl---comNumUpdate--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return judge;
	}
	
	/**
	 * SELECT---在a_star表中查找符合条件的记录----共用
	 */
	@Override
	public boolean starExist(int typeId ,int type ,int userId ) {
		boolean judge = false ;
		int count = 0 ;
		try {
			//获取数据库连接池中的一个连接
			con = dataSource.getConnection();
			//在a_star表中查找符合条件的记录的条数
			String sql="SELECT  *  FROM a_star WHERE type_id = ? AND type = ? AND user_id  = ? ";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setInt( 1,typeId) ;
			stmt.setInt( 2,type ) ;
			stmt.setInt( 3,userId) ;
			ResultSet rs = stmt.executeQuery();
					if(rs.next()) //若查询结果不为空
						{
							//总数据量
							count = count+ 1 ;
							if(count==1) 
							{
								//代表存在该文章分类标签的记录
								judge =true ;
							}
						}	
		} catch (SQLException e) {
			judge=false;
			System.out.println("ArticleInteractionDaoImpl---starExist--数据库连接异常");
			e.printStackTrace();
		}
		finally {
			dataSource.backConnection(con);
			}
		return judge;
}	
	/**
	 * INSERT---增加一条点赞记录---共用
	 */
	@Override
	public boolean starInsert( int typeId ,int type ,int userId ){
		
		boolean judge = false;
		
		
		try {
			//获取数据库连接池中的一个连接
			con = dataSource.getConnection();
			//增加点赞记录
			String sql="insert into a_star ( type_id ,type , user_id ) values (?,?,?)";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setInt(1, typeId);
			stmt.setInt(2, type);
			stmt.setInt(3, userId);
			
			int t = stmt.executeUpdate();
			if(t>0) 
			{
				judge = true;
			}
		} catch (SQLException e) {
			judge=false;
			System.out.println("ArticleInteractionDaoImpl---starInsert--数据库连接异常");
			e.printStackTrace();
		}
		finally {
			dataSource.backConnection(con);
			}
		return judge;
	}
	/**
	 * DELETE---删除一条点赞记录---共用
	 */
	@Override
	public boolean starDelete( int typeId ,int type ,int userId ) {
		//返回结果
		boolean judge = false;
		
		try {
			//数据库连接池
			con = dataSource.getConnection();
			//删除a_star表的符合条件的字段      
			String sql="DELETE FROM a_star WHERE type_id = ? AND type = ? AND user_id = ? ";
			PreparedStatement stmt=con.prepareStatement(sql);
			
			stmt.setInt(1,typeId);
			stmt.setInt(2,type);
			stmt.setInt(3, userId);
			
			//进行删除操作，返回成功删除的条数 
			int ret = stmt.executeUpdate();
			if(ret == 1)
			{
				judge = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			  
			System.out.println("ArticleInteractionDaoImpl---starDelete--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return judge;
	}

	/**
	 * SELECT--查找a_star表中的文章的点赞总数---共用
	 */
	@Override
	public int starNumQuery (   int articleId , int type ) {
				//默认给该文章的点赞总数为0
				int starNum = 0 ;
				try {
					con = dataSource.getConnection();
					//文章的点赞数
					String sql="SELECT count(1)  FROM a_star WHERE type_id = ?  AND type = ?" ;
					PreparedStatement stmt=con.prepareStatement(sql);
					stmt.setInt( 1, articleId );
					stmt.setInt( 2, type);
					ResultSet rs = stmt.executeQuery();
					 //若查询结果不为空
					if(rs.next())
					{
						//总点赞数
						starNum = rs.getInt(1);
					}	
				} 
				catch (SQLException e) {
					e.printStackTrace();
					System.out.println("ArticleInteractionDaoImpl---starNumQuery--数据库连接异常");
				}
				finally {
					dataSource.backConnection(con);
					}
				return starNum ;
			}
	
	/**
	 * UPDATE--更新文章表中的评论数
	 */
	@Override
	public boolean starNumUpdate( int starNum,  int articleId)
	{
		//返回结果
		boolean judge = false;
		
		try {
			//数据库连接池
			con = dataSource.getConnection();
			//更改文章的点赞数
			String sql="UPDATE a_article SET star = ?  WHERE  article_id = ?  ";
			PreparedStatement stmt=con.prepareStatement(sql);
			
			stmt.setInt(1,starNum );
			stmt.setInt(2,articleId );
			//进行更新操作，返回成功更新的条数 
			int ret = stmt.executeUpdate();
			if(ret == 1)
			{
				judge = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			  
			System.out.println("ArticleInteractionDaoImpl---starNumUpdate--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return judge;
	}
	
	/**
	 * UPDATE---评论表中点赞数的更新
	 */
	@Override
	public boolean comStarNumUpdate( int starNum ,  int commentId ) {
			//返回结果
			boolean judge = false;
			
			try {
				//数据库连接池
				con = dataSource.getConnection();
				//更改评论的点赞数
				String sql="UPDATE a_comment SET c_star = ?  WHERE  comment_id = ?  ";
				PreparedStatement stmt=con.prepareStatement(sql);
				
				stmt.setInt(1,starNum );
				stmt.setInt(2,commentId );
				//进行更新操作，返回成功更新的条数 
				int ret = stmt.executeUpdate();
				if(ret == 1)
				{
					judge = true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
				  
				System.out.println("ArticleInteractionDaoImpl---comStarNumUpdate--数据库连接异常");
			} finally{
				dataSource.backConnection(con);
			}
			return judge;
		}
	
	
	/**
	 * UPDATE---回复表中点赞数的更新
	 */
	@Override
	public boolean replyStarNumUpdate( int starNum ,  int replyId ) {
			//返回结果
			boolean judge = false;
			
			try {
				//数据库连接池
				con = dataSource.getConnection();
				//更改评论的点赞数
				String sql="UPDATE a_reply SET r_star = ?  WHERE reply_id = ?  ";
				PreparedStatement stmt=con.prepareStatement(sql);
				
				stmt.setInt(1,starNum );
				stmt.setInt(2,replyId );
				//进行更新操作，返回成功更新的条数 
				int ret = stmt.executeUpdate();
				if(ret == 1)
				{
					judge = true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
				  
				System.out.println("ArticleInteractionDaoImpl---replyStarNumUpdate--数据库连接异常");
			} finally{
				dataSource.backConnection(con);
			}
			return judge;
		}

	
	/**
	 * SELECT---查询a_collection中是否含有该记录
	 */
	@Override
	public boolean collectionExist(int articleId, int userId)
	{
		boolean judge = false ;
		int count = 0 ;
		try {
			//获取数据库连接池中的一个连接
			con = dataSource.getConnection();
			//在a_collection表中查找符合条件的记录的条数
			String sql="SELECT  *  FROM a_collection WHERE article_id = ? AND  user_id  = ? ";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setInt( 1,articleId) ;
			stmt.setInt( 2,userId ) ;
			ResultSet rs = stmt.executeQuery();
					if(rs.next()) //若查询结果不为空
						{
							//总数据量
							count = count+ 1 ;
							if(count==1) 
							{
								//代表存在该文章分类标签的记录
								judge =true ;
							}
						}	
		} catch (SQLException e) {
			judge=false;
			System.out.println("ArticleInteractionDaoImpl---collectionExist--数据库连接异常");
			e.printStackTrace();
		}
		finally {
			dataSource.backConnection(con);
			}
		return judge;
	}
	
	
	
	/**
	 * INSERT---增加一条记录到a_collection表中
	 */
	@Override
	public boolean collectionInsert(int articleId, int userId)
	{
		boolean judge = false;
				
				
				try {
					//获取数据库连接池中的一个连接
					con = dataSource.getConnection();
					//默认增加时是单向关注
					String sql="insert into a_collection ( article_id , user_id ) values ( ? , ? ) ";
					PreparedStatement stmt=con.prepareStatement(sql);
					stmt.setInt( 1, articleId);
					stmt.setInt( 2, userId);
					
					int t = stmt.executeUpdate();
					if(t>0) 
					{
						judge = true;
					}
				} catch (SQLException e) {
					judge=false;
					System.out.println("ArticleInteractionDaoImpl---collectionInsert--数据库连接异常");
					e.printStackTrace();
				}
				finally {
					dataSource.backConnection(con);
					}
				return judge;
	}

	/**
	 * DELETE---删除一条收藏文章的记录
	 */
	@Override
	public boolean collectionDelete(int articleId, int userId)
	{
		//返回结果
		boolean judge = false;
		
		try {
			//数据库连接池
			con = dataSource.getConnection();
			//删除a_collection表的符合条件的字段      
			String sql="DELETE FROM a_collection WHERE article_id = ? AND user_id = ? " ;
			PreparedStatement stmt=con.prepareStatement(sql);
			
			stmt.setInt(1,articleId);
			stmt.setInt(2, userId);
			
			//进行删除操作，返回成功删除的条数 
			int ret = stmt.executeUpdate();
			if(ret == 1)
			{
				judge = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			  
			System.out.println("ArticleInteractionDaoImpl---collectionDelete--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return judge;
	}
	
	/**
	 *  SELECT---在a_colletion中查找文章Id为所传进去的参数的记录数
	 */
	@Override
	public int collectionNumQuery (   int articleId   ) {
		//默认给该文章的收藏总数为0
		int collectionNum = 0 ;
		try {
			con = dataSource.getConnection();
			//文章的收藏数
			String sql="SELECT count(1)  FROM a_collection WHERE article_id = ? " ;
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setInt( 1, articleId );
			ResultSet rs = stmt.executeQuery();
			 //若查询结果不为空
			if(rs.next())
			{
				//总收藏数
				collectionNum = rs.getInt(1);
			}	
		} 
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ArticleInteractionDaoImpl---collectionNumQuery--数据库连接异常");
		}
		finally {
			dataSource.backConnection(con);
			}
		return collectionNum ;
	}

	/**
	 * UPDATE---更新a_article表中的收藏总数
	 */
	@Override
	public boolean collectionNumUpdate(int collectionNum, int articleId)
	{
		//返回结果
		boolean judge = false;
		
		try {
			//数据库连接池
			con = dataSource.getConnection();
			//更改文章的收藏数
			String sql="UPDATE a_article SET collection = ?  WHERE  article_id = ?  ";
			PreparedStatement stmt=con.prepareStatement(sql);
			
			stmt.setInt(1,collectionNum );
			stmt.setInt(2,articleId );
			//进行更新操作，返回成功更新的条数 
			int ret = stmt.executeUpdate();
			if(ret == 1)
			{
				judge = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			  
			System.out.println("ArticleInteractionDaoImpl---collectionNumUpdate--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return judge;
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * SELECT---查询a_collection中是否含有该记录
	 */
	@Override
	public boolean shareExist(int articleId, int userId)
	{
		boolean judge = false ;
		int count = 0 ;
		try {
			//获取数据库连接池中的一个连接
			con = dataSource.getConnection();
			//在a_share表中查找符合条件的记录的条数
			String sql="SELECT  *  FROM a_share WHERE article_id = ? AND  user_id  = ? ";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setInt( 1,articleId) ;
			stmt.setInt( 2,userId ) ;
			ResultSet rs = stmt.executeQuery();
					if(rs.next()) //若查询结果不为空
						{
							//总数据量
							count = count+ 1 ;
							if(count==1) 
							{
								//代表存在该文章被该用户转发的记录
								judge =true ;
							}
						}	
		} catch (SQLException e) {
			judge=false;
			System.out.println("ArticleInteractionDaoImpl---shareExist--数据库连接异常");
			e.printStackTrace();
		}
		finally {
			dataSource.backConnection(con);
			}
		return judge;
	}
	
	
	
	/**
	 * INSERT---增加一条记录到a_share表中
	 */
	@Override
	public boolean shareInsert(int articleId, int userId)
	{
		boolean judge = false;
				
				
				try {
					//获取数据库连接池中的一个连接
					con = dataSource.getConnection();
					//默认增加时是单向关注
					String sql="insert into a_share ( article_id , user_id ) values ( ? , ? ) ";
					PreparedStatement stmt=con.prepareStatement(sql);
					stmt.setInt( 1, articleId);
					stmt.setInt( 2, userId);
					
					int t = stmt.executeUpdate();
					if(t>0) 
					{
						judge = true;
					}
				} catch (SQLException e) {
					judge=false;
					System.out.println("ArticleInteractionDaoImpl---shareInsert--数据库连接异常");
					e.printStackTrace();
				}
				finally {
					dataSource.backConnection(con);
					}
				return judge;
	}

	/**
	 * DELETE---删除一条收藏文章的记录
	 */
	@Override
	public boolean shareDelete(int articleId, int userId)
	{
		//返回结果
		boolean judge = false;
		
		try {
			//数据库连接池
			con = dataSource.getConnection();
			//删除a_share表的符合条件的字段      
			String sql="DELETE FROM a_share WHERE article_id = ? AND user_id = ? " ;
			PreparedStatement stmt=con.prepareStatement(sql);
			
			stmt.setInt(1,articleId);
			stmt.setInt(2, userId);
			
			//进行删除操作，返回成功删除的条数 
			int ret = stmt.executeUpdate();
			if(ret == 1)
			{
				judge = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			  
			System.out.println("ArticleInteractionDaoImpl---shareDelete--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return judge;
	}
	
	/**
	 *  SELECT---在a_colletion中查找文章Id为所传进去的参数的记录数
	 */
	@Override
	public int shareNumQuery (   int articleId   ) {
		//默认给该文章的收藏总数为0
		int shareNum = 0 ;
		try {
			con = dataSource.getConnection();
			//文章的收藏数
			String sql="SELECT count(1)  FROM a_share WHERE article_id = ? " ;
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setInt( 1, articleId );
			ResultSet rs = stmt.executeQuery();
			 //若查询结果不为空
			if(rs.next())
			{
				//总收藏数
				shareNum = rs.getInt(1);
			}	
		} 
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ArticleInteractionDaoImpl---shareNumQuery--数据库连接异常");
		}
		finally {
			dataSource.backConnection(con);
			}
		return shareNum ;
	}

	/**
	 * UPDATE---更新a_article表中的收藏总数
	 */
	@Override
	public boolean shareNumUpdate(int shareNum, int articleId)
	{
		//返回结果
		boolean judge = false;
		
		try {
			//数据库连接池
			con = dataSource.getConnection();
			//更改文章的收藏数
			String sql="UPDATE a_article SET share = ?  WHERE  article_id = ?  ";
			PreparedStatement stmt=con.prepareStatement(sql);
			
			stmt.setInt(1,shareNum );
			stmt.setInt(2,articleId );
			//进行更新操作，返回成功更新的条数 
			int ret = stmt.executeUpdate();
			if(ret == 1)
			{
				judge = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			  
			System.out.println("ArticleInteractionDaoImpl---shareNumUpdate--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return judge;
	}

	
	
	
	
}
