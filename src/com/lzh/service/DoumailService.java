package com.lzh.service;

import java.util.ArrayList;
import java.util.List;

import com.lzh.dao.DoumailDao;
import com.lzh.dao.UserDao;
import com.lzh.daoImpl.DoumailDaoImpl;
import com.lzh.daoImpl.UserDaoImpl;
import com.lzh.entity.Doumail;
import com.lzh.entity.User;

/**
 * @Description 处理和豆邮相关的业务逻辑，负责数据的拼接 
 * @author 林泽鸿
 * @time 2019年5月14日 上午5:28:39
 */
public class DoumailService
{
	
	
	UserDao userDao  = new UserDaoImpl() ;
	DoumailDao doumailDao = new DoumailDaoImpl() ;
	
		/**
		 * @Description 查询符合条件的数据的总条数---doumail表中查询
		 * @param userId 当前用户的id
		 * @return 总条数
		 */
		public int getTotalCount( int userId ) {
			return doumailDao.getTotalCount(  userId ) ;
			}
		
		
		/**
		 * @Description  分页查询符合条件的豆邮
		 * @param currentPage 当前页面 
		 * @param pageSize 页面大小
		 * @param userId 当前用户的Id
		 * @return 数据集合
		 */
		public List<Doumail> queryDoumailByPage( int currentPage, int pageSize ,int userId ){
		List<Doumail> doumails = new ArrayList<Doumail>() ;
		List<Doumail> doumails1 = new ArrayList<Doumail>() ;
		
		User fromUser = new User();
		User toUser = new User() ;
		
		//先拿到文章表的信息
		doumails1 = doumailDao.queryDoumailByPage(currentPage, pageSize, userId);
		//进行数据的拼接
		for( Doumail doumail : doumails1 )
		{ 
				fromUser = null ;
				toUser = null ;
				//拿到用户的基本信息
				fromUser = userDao.userInfoByUserId(doumail.getFromUserId());
				toUser = userDao.userInfoByUserId(doumail.getToUserId());
				//豆邮发出者---赋给doumails
				doumail.setFromUserImg(fromUser.getPortrait());
				doumail.setFromUserNick(fromUser.getNickname());
				//豆邮接收者
				doumail.setToUserImg(toUser.getPortrait());
				doumail.setToUserNick(toUser.getNickname());
				
				//放到数据集合中
				doumails.add(doumail);
		}
		return doumails;
	}

		
		/**
		 * @Description 查询符合条件的数据的总条数---豆邮消息展示的记录总数
		 * @param userId 当前用户的id
		 * @param toUserId 被当前用户选择的用户id
		 * @return 总条数
		 */
		public int getShowCount( int userId ,int toUserId ) {
			return doumailDao.getShowTotalCount(userId, toUserId);
			}
		
		
		/**
		 * @Description  分页查询符合条件的豆邮----豆邮消息的展示
		 * @param currentPage 当前页面 
		 * @param pageSize 页面大小
		 * @param userId 当前用户的Id
		 * @param toUserId 被当前用户选择的用户id
		 * @return 数据集合
		 */
		public List<Doumail> queryShowByPage( int currentPage, int pageSize ,int userId ,int toUserId){
		List<Doumail> doumails = new ArrayList<Doumail>() ;
		List<Doumail> doumails1 = new ArrayList<Doumail>() ;
		
		User fromUser = new User();
		User toUser = new User() ;
		
		//先拿到文章表的信息
		doumails1 = doumailDao.queryShowByPage(currentPage, pageSize, userId, toUserId) ;
		//进行数据的拼接
		for( Doumail doumail : doumails1 )
		{ 
				fromUser = null ;
				toUser = null ;
				//拿到用户的基本信息
				fromUser = userDao.userInfoByUserId(doumail.getFromUserId());
				toUser = userDao.userInfoByUserId(doumail.getToUserId());
				//豆邮发出者---赋给doumails
				doumail.setFromUserImg(fromUser.getPortrait());
				doumail.setFromUserNick(fromUser.getNickname());
				//豆邮接收者
				doumail.setToUserImg(toUser.getPortrait());
				doumail.setToUserNick(toUser.getNickname());
				
				//放到数据集合中
				doumails.add(doumail);
		}
		return doumails;
	}
		
		/**
		 * @Description  进行豆邮的增加
		 * @param doumail 
		 * @return 成功与否
		 */
		public boolean doumailInsert(Doumail  doumail) {
			//将组装后的对象作为参数传到增加的DAO中
			return doumailDao.doumailInsert(doumail);
		}
		
}
