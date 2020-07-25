<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>我的主页</title>
	<link rel="icon" href="http://47.102.212.18/iDouBan/icon/iDouBan_favicon.ico" type="image/x-icon">
	<!-- 我的页面的样式 -->
 <link rel="stylesheet" type="text/css" href="http://47.102.212.18/iDouBan/css/my_page.css">
	</head>
		<!-- 个人信息的主页 -->
		<body>
			<!-- 顶部的菜单栏 -->
			<nav id="first">
				<div id="first_menu">
					<!-- 跳到servlet，对用户的cookie进行注销 -->
				    <a  href="http://47.102.212.18/iDouBan/ClearLoginServlet">退出登录</a>
				    <!-- 相对于端口号的相对路径 -->
					<a  href="http://47.102.212.18/iDouBan/jsp/alter.jsp">账号管理</a>
					<a  href="http://47.102.212.18/iDouBan/jsp/my_page.jsp">个人主页</a>
					
					<a href="/iDouBan/DoumailServlet?method=my_doumail_list">豆邮</a>
					
					<a href="/iDouBan/FriendListServlet?method=blacklist_list">黑名单</a>
					<a href="/iDouBan/FriendListServlet?method=attention_list">我的关注</a>
					<a href="/iDouBan/FriendListServlet?method=friend_list">我的好友</a>
					<!-- 先跳转servlet 查询第一页的所有人信息 -->
					<a href="/iDouBan/EveryoneListServlet?method=everyone_list">所有人</a>
					<!-- 编辑文章 -->
					<a href="http://47.102.212.18/iDouBan/jsp/article_edit.jsp" target="_blank">写文章</a>
					<a href="/iDouBan/ArticleListServlet?method=article_list">所有文章</a>
					<a href="/iDouBan/ArticleListServlet?method=my_article_list" >我的文章</a>
					<a href="/iDouBan/ArticleListServlet?method=my_collection_list">我的收藏</a>
				</div>
			</nav>
			<!-- 第二个导航栏 -->
			
			<header id="second">
					<nav  id="second_menu">
						<!-- logo部分 -->
							<div class="logo">
							 	<img alt="豆瓣logo" src="http://47.102.212.18/iDouBan/image/豆瓣首页logo.jpg" width=175px height=58px>
							</div>
						<!-- 导航栏部分_可选择部分 -->
							<div class="navbar">
								<ul>
									<li><a href="#">首页</a></li>
									<li><a href="http://47.102.212.18/iDouBan/jsp/my_page.jsp">个人主页</a></li>
									<li><a href="/iDouBan/ArticleListServlet?method=article_list">浏览发现</a></li>
								</ul>	
							</div>
							<!-- 搜索框部分 -->
							<div class="search">
									<form action="">
										<!-- placeholder占位符 -->
										<input type="text" placeholder="搜索你感兴趣的内容和人">
										<!-- placeholder--默认显示值 -->
										<input  type="submit" value="">
									</form>
							</div>
					</nav>
			</header>
			<!-- 显示主要内容的地方 -->
			<div id="main_content">
				<div id="main_content-left">
						<!-- 放置个人头像和信息 -->
						<div id="main_content-left-top">
						
							 <!-- 显示头像 -->
							 <div>
							 <div id="welcome_msg">
						        欢迎您： ${sessionScope.userInfo.username}<br/>
						     </div>
							 <img src="${sessionScope.userInfo.portrait}"  alt="我的头像" id="user_img"  />
							 </div>
				  
							<!-- 个人信息 -->
							<div id="user_info_show">
								<div>个人信息</div>
								<div>昵称：      ${sessionScope.userInfo.nickname}</div>
								<div>个性签名：${sessionScope.userInfo.signature}</div>
								<div>自我介绍：${sessionScope.userInfo.selfIntroduc}</div>
								<div>地址：${sessionScope.userInfo.address}</div>
							 </div>
							 <br>
						</div>
						<div id="main_content-left-centre">
					</div>
				</div>
				
				<div id="main_content-right">
				</div>
			</div>
		</body>
</html>