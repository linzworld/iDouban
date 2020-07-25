
package com.lzh.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lzh.dao.ArticleEditDao;
import com.lzh.entity.Article;
import com.lzh.util.MyDataSource;
/**
 * @Description 文章的编辑的数据库功能实现 
 * @author 林泽鸿
 * @time 2019年5月14日 下午11:15:30
 */
public class ArticleEditDaoImpl implements ArticleEditDao
{
	
	private Connection con;
	
	
	
	//新建一个数据库连接池对象
	MyDataSource dataSource = new MyDataSource();

	
	/**
	 * 向数据库插入一条记录
	 */
	@Override
	public int articleInsert(Article article)
	{
		int articleId = -1;
			try {
					//获取数据库连接池中的一个连接
					con = dataSource.getConnection();
					//将HTML代码存入friend表中
					String sql="INSERT INTO a_article (title,author,content) VALUES (?,?,?)";
					PreparedStatement stmt=con.prepareStatement(sql);
					stmt.setString( 1,article.getTitle() );
					stmt.setInt( 2, article.getAuthorId() );
					stmt.setString(3, article .getContent());
					 stmt.executeUpdate();
		
							String sql1 = "SELECT LAST_INSERT_ID()"	;
							PreparedStatement stmt1=con.prepareStatement(sql1);
							ResultSet rs = stmt1.executeQuery();
							
							if( rs.next())//结果不为空
								{
									articleId = rs.getInt(1);
								}
						
					} catch (SQLException e) {
						articleId=-1;
						System.out.println("articleDaoImpl---articleInsert--数据库连接异常");
						e.printStackTrace();
					}
					finally {
						dataSource.backConnection(con);
					}
		return articleId ;
	}
	/**
	 * 更新文章表中的一条记录----a_article
	 */
@Override
	public boolean articleUpdate(Article article) {
	
	//返回结果
	boolean judge = false ;
	
	try {
		//数据库连接池
		con = dataSource.getConnection();
		//更改a_article表--------文章标题和内容 
		String sql="UPDATE a_article SET title = ? , content = ? WHERE   article_id = ?  " ;
		PreparedStatement stmt=con.prepareStatement(sql);
		
		stmt.setString( 1,article.getTitle() );
		stmt.setString( 2, article .getContent() );
		stmt.setInt( 3, article.getArticleId() );
		//进行更新操作，返回成功更新的条数 
		int ret = stmt.executeUpdate() ;
		//记录数大于等于0，即代表更新成功
		if(ret >= 0)
		{
			judge = true;
		}

	} catch (SQLException e) {
		e.printStackTrace();
		  
		System.out.println("articleDaoImpl---articleUpdate--数据库连接异常");
	} finally{
		dataSource.backConnection(con);
	}
	return judge;
}
	
	
	/**
	 * 查询a_tag表是否含有符合条件的记录
	 */
	@Override
	public int tagExist(String tagName)
	{
		int judge = -1;
		int count = 0 ;
		try {
			
			//获取数据库连接池中的一个连接
			con = dataSource.getConnection();
			//在a_tag表中查找符合条件的记录的条数
			String sql="SELECT  *  FROM a_tag WHERE tag_name = ?";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString( 1,tagName) ;
			ResultSet rs = stmt.executeQuery();
					if(rs.next()) //若查询结果不为空
						{
							//总数据量
							count = rs.getInt(1);
							if(count!=0) 
							{
								//代表存在该文章分类标签的记录
								judge = rs.getInt("tag_id") ;
							}
						}	
					
		} catch (SQLException e) {
			judge=-1;
			System.out.println("articleDaoImpl---tagExist--数据库连接异常");
			e.printStackTrace();
		}
		finally {
			dataSource.backConnection(con);
			}
		return judge;
	}

	/**
	 * 插入一条记录到a_tag表--拿到插入之后的记录的主键id值
	 */
	@Override
	public int tagInsert(String tagName)
	{
	
			int articleId = -1 ;
			
				try {
						//获取数据库连接池中的一个连接
						con = dataSource.getConnection();
						//将文章分类名存入a_tag表中
						String sql="INSERT INTO a_tag (tag_name) VALUES (?)";
						PreparedStatement stmt=con.prepareStatement(sql);
						stmt.setString( 1, tagName);
						stmt.executeUpdate();
						
								//拿到上一次插入的记录的主键id值
								String sql1 = "SELECT LAST_INSERT_ID()"	;
								PreparedStatement stmt1=con.prepareStatement(sql1);
								ResultSet rs = stmt1.executeQuery();	
								if( rs.next())//结果不为空
									{
										articleId = rs.getInt(1);
									}
						} catch (SQLException e) {
							articleId = -1;
							System.out.println("articleDaoImpl---tagInsert--数据库连接异常");
							e.printStackTrace();
						}
						finally {
							dataSource.backConnection(con);
						}
			return articleId;
	}
	/**
	 * 插入一条记录到article_to_tag表中--返回操作结果
	 */
	@Override
	public boolean middleInsert(int articleId , int tagId) {
		//返回结果
		boolean judge=false ;
				try {
					//获取数据库连接池中的一个连接
					con = dataSource.getConnection();
					String sql="insert into article_to_tag ( article_id , tag_id ) values ( ? , ? ) " ;
					PreparedStatement ptmt=con.prepareStatement(sql);
					ptmt.setInt(1,articleId);
					ptmt.setInt(2,tagId);
					int t = ptmt.executeUpdate();
					if(t>0) {
						judge=true ;
					}
				
				}
				catch (SQLException e) {
					judge=false ;
					System.out.println("articleDaoImpl---middleInsert--数据库连接异常");
					e.printStackTrace();
				}
		finally {
			dataSource.backConnection(con);
			}
		return judge;
	}
	
	
	/**
	 * 删除所有关于articleId的记录
	 */
	@Override
	public boolean middleDelete ( int articleId ) {
		
		//返回结果
		boolean judge = false;
		
			try {
				//数据库连接池
				con = dataSource.getConnection();
				//删除中间表的有关articleId的所有记录    --articleId 
				String sql="DELETE FROM article_to_tag WHERE article_id = ? ";
				PreparedStatement stmt=con.prepareStatement(sql);
				
				stmt.setInt(1,articleId);
				
				//进行删除操作，返回成功删除的条数 
				int ret = stmt.executeUpdate();
				//返回结果大于等于0，则删除成功
				if(ret >=0 )
				{
					judge = true;
				}
	
			}
			catch (SQLException e) {
				judge = false ;
				e.printStackTrace();
				System.out.println("articleDaoImpl---middleDelete--数据库连接异常");
				  
			} finally{
				dataSource.backConnection(con);
			}
			return judge;
	}
	
	/**
	 * DELETE---删除文章
	 */
	@Override
	public boolean articleDelete(int articleId)
	{
		//返回结果
		boolean judge = false;
		
			try {
				//数据库连接池
				con = dataSource.getConnection();
				//删除文章表的有关articleId的所有记录 
				String sql="DELETE FROM a_article WHERE article_id = ? ";
				PreparedStatement stmt=con.prepareStatement(sql);
				
				stmt.setInt(1,articleId);
				
				//进行删除操作，返回成功删除的条数 
				int ret = stmt.executeUpdate();
				//返回结果大于等于0，则删除成功
				if(ret >=0 )
				{
					judge = true;
				}
	
			}
			catch (SQLException e) {
				judge = false ;
				e.printStackTrace();
				System.out.println("articleDaoImpl---articleDelete--数据库连接异常");
				  
			} finally
			{
				dataSource.backConnection(con);
			}
			return judge;
	}
	@Override
	public int articleQuery(Article article)
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
