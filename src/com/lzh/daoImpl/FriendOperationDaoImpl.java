package com.lzh.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lzh.dao.FriendOperationDao;
import com.lzh.util.MyDataSource;
/**
 * @Description 好友关系操作的具体实现类 
 * @author 林泽鸿
 * @time 2019年4月30日 下午5:43:06
 */
public class FriendOperationDaoImpl implements FriendOperationDao
{

	private Connection con;
	
	//新建一个数据库连接池对象
	MyDataSource dataSource = new MyDataSource();
	
	
			/**
			 * 更改好友关系---update--friend表
			 */
			@Override
			public boolean friendUpdate(int fromUserId, int toUserId ,int status)
			{
				//返回结果
				boolean judge = false;
				
				try {
					//数据库连接池
					con = dataSource.getConnection();
					//更改friend表的字段       
					String sql="UPDATE friend SET status = ?  WHERE from_user_id = ? AND to_user_id = ? ";
					PreparedStatement stmt=con.prepareStatement(sql);
					
					stmt.setInt(1,status);
					stmt.setInt(2,fromUserId);
					stmt.setInt(3,toUserId);
					
					//进行更新操作，返回成功更新的条数 
					int ret = stmt.executeUpdate();
					if(ret == 1)
					{
						judge = true;
					}
		
				} catch (SQLException e) {
					e.printStackTrace();
					  
					System.out.println("friendopreation---friendUpdate--数据库连接异常");
				} finally{
					dataSource.backConnection(con);
				}
				return judge;
			}
		
			
			/**
			 * 删除好友关系---delete--friend表
			 */
			@Override
			public boolean friendDelete(int fromUserId, int toUserId) {
				//返回结果
				boolean judge = false;
				
				try {
					//数据库连接池
					con = dataSource.getConnection();
					//更改user表的个人信息的字段      portrait=?, 
					String sql="DELETE FROM friend WHERE from_user_id = ? AND to_user_id = ? ";
					PreparedStatement stmt=con.prepareStatement(sql);
					
					stmt.setInt(1,fromUserId);
					stmt.setInt(2,toUserId);
					
					//进行删除操作，返回成功删除的条数 
					int ret = stmt.executeUpdate();
					if(ret == 1)
					{
						judge = true;
					}
		
				} catch (SQLException e) {
					e.printStackTrace();
					  
					System.out.println("friendopreation---friendDelete--数据库连接异常");
				} finally{
					dataSource.backConnection(con);
				}
				return judge;
			}
	
			/**
			 *增加好友关系---insert--friend表(默认增加时是单向关注--即status为1)
			 */
			@Override
			public boolean friendInsert(int fromUserId, int toUserId) {
				
				boolean judge = false;
				
				
				try {
					//获取数据库连接池中的一个连接
					con = dataSource.getConnection();
					//默认增加时是单向关注
					String sql="insert into friend (from_user_id,to_user_id,status) values (?,?,1)";
					PreparedStatement stmt=con.prepareStatement(sql);
					stmt.setInt(1, fromUserId);
					stmt.setInt(2, toUserId);
					int t = stmt.executeUpdate();
					if(t>0) 
					{
						judge = true;
					}
				} catch (SQLException e) {
					judge=false;
					System.out.println("friendopreation---friendInsert--数据库连接异常");
					e.printStackTrace();
				}
				finally {
					dataSource.backConnection(con);
					}
				return judge;
				
			}
			
			
			/**
			 * 找到是否存在该记录--并且查询出该记录中的status--friend表
			 */
			@Override
			public int friendQuery(int fromUserId, int toUserId) {
				// 默认好友关系的无状态为0
				
				int status = 0;
				
				try {
					con = dataSource.getConnection();
					
					String sql="SELECT status  FROM friend WHERE from_user_id = ? AND to_user_id = ?";
					PreparedStatement stmt=con.prepareStatement(sql);
					stmt.setInt(1, fromUserId);
					stmt.setInt(2, toUserId);
					ResultSet res=stmt.executeQuery();
					while(res.next())
					{
						status=res.getInt("status");
					}
				} 
				catch (SQLException e) {
					e.printStackTrace();
					System.out.println("friendopreation---friendQuery--数据库连接异常");
				}
				finally {
					dataSource.backConnection(con);
					}
				return status;
			}

			
			/**
			 * 判断好友分组名是否存在--group表
			 */
			@Override
			public boolean friendGroupExist( String groupName)
			{
				boolean judge = false ;
				int count = 0 ;
				try {
					//获取数据库连接池中的一个连接
					con = dataSource.getConnection();
					//在group表中查找符合条件的记录的条数
					String sql="SELECT  *  FROM friend_group WHERE  group_name_to = ?  ";
					PreparedStatement stmt=con.prepareStatement(sql);
					stmt.setString( 1 , groupName ) ;
					ResultSet rs = stmt.executeQuery();
							if(rs.next()) //若查询结果不为空
								{
									//总数据量
									count = count+ 1 ;
									if(count==1) 
									{
										//代表存在该好友分组标签	
										judge =true ;
									}
								}	
				} catch (SQLException e) {
					judge=false;
					System.out.println("friendopreation---friendGroupExist--数据库连接异常");
					e.printStackTrace();
				}
				finally {
					dataSource.backConnection(con);
					}
				return judge;
	}
			
			/**
			 * 在分组表中新增一条记录---group表
			 */
			@Override
			public boolean friendGroupInsert ( String groupName )
			{
				
				boolean judge = false;
				
				
				try {
					//获取数据库连接池中的一个连接
					con = dataSource.getConnection();
					//增加一条好友的分组记录
					String sql="insert into friend_group (group_name_to ) values ( ? )";
					PreparedStatement stmt=con.prepareStatement(sql);
					stmt.setString(1, groupName);
					int t = stmt.executeUpdate();
					if(t>0) 
					{
						judge = true;
					}
				} catch (SQLException e) {
					judge=false;
					System.out.println("friendopreation---friendGroupInsert--数据库连接异常");
					e.printStackTrace();
				}
				finally {
					dataSource.backConnection(con);
					}
				return judge;
			}
			
			
			
			/**
			 * 找到是否存在该记录--并且查询出该记录中的group_id---group表
			 */
			@Override
			public int getGroupIdBygroupName (String groupName ) 
			{
				//查询结果--group_id
				int	groupId= 0 ;			
				try {
					con = dataSource.getConnection();
					
					String sql="SELECT *  FROM friend_group WHERE  group_name_to = ?";
					PreparedStatement stmt=con.prepareStatement(sql);
					stmt.setString(1, groupName);
					ResultSet res=stmt.executeQuery();
					while(res.next())
					{
						groupId=res.getInt("group_id");
					}
				} 
				catch (SQLException e) {
					e.printStackTrace();
					System.out.println("friendopreation---getGroupIdBygroupName--数据库连接异常");
				}
				finally {
					dataSource.backConnection(con);
					}
				return groupId;
			}
			
			
			/**
			 * 更改好友分组---update-----friend表
			 */
			@Override
			public boolean friendGroupUpdate(int userId , int toUserId ,int status , int groupId)
			{
				//返回结果
				boolean judge = false;
				
				try {
					//数据库连接池
					con = dataSource.getConnection();
					//更改friend表的字段       
					String sql="UPDATE friend SET group_id = ?  WHERE from_user_id = ? AND to_user_id = ? AND status = ?  ";
					PreparedStatement stmt=con.prepareStatement(sql);
					
					stmt.setInt(1,groupId);
					stmt.setInt(2,userId);
					stmt.setInt(3,toUserId);
					stmt.setInt(4,  status);
					//进行更新操作，返回成功更新的条数 
					int ret = stmt.executeUpdate();
					if(ret == 1)
					{
						judge = true;
					}
		
				} catch (SQLException e) {
					e.printStackTrace();
					  
					System.out.println("friendopreation---friendGroupUpdate--数据库连接异常");
				} finally{
					dataSource.backConnection(con);
				}
				return judge;
			}
			
			/**
			 *SELECT---查询好友分组名集合 
			 */
			@Override
			public List<String> friendGroupNameQuery( int userId  ){
				List<String> groupNames = null;
				groupNames = new ArrayList<>();
				String groupName ;
				try {
					con = dataSource.getConnection();
					//联表查询的sql语句---为好友情况
					String sql = "SELECT * FROM friend, friend_group  WHERE friend.from_user_id = ? AND  friend.status = 2  AND  friend.group_id  =  friend_group.group_id  ";
					PreparedStatement stmt=con.prepareStatement(sql);
					//占位符代表的参数
					stmt.setInt( 1, userId );
					ResultSet rs = stmt.executeQuery();
					while(rs.next()){//拿到数据集合
						int ret = 0 ;
						//拿到好友分类名
						groupName = rs.getString("friend_group.group_name_to") ;
							//循环过滤重复的分组名
							for( String gn :groupNames )
								{
									if( groupName.equals(gn) )
									{ 
										ret = 1;
									}
								}
							//不重复的情况
							if( ret == 0 ) 
								{
									groupNames.add(groupName);
								}
							ret =  0 ; 	
					}	
			
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("friendopreation---friendGroupNameQuery--数据库连接异常");
				} finally{
					dataSource.backConnection(con);
				}
				return groupNames;
			}
			
			
}
			


