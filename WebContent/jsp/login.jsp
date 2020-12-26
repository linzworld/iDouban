<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录豆瓣</title>
<link rel="icon" href="http://img.linzworld.cn/img/douban_favicon.ico" type="image/x-icon">

<style type="text/css">
body {
	margin: 0;
	background-image: url('http://47.102.212.18/iDouBan/image/portrait/登录背景设置.jpg');
	background-repeat:no-repeat;
        background-position:0% 0%;
	background-size:contain;
	background-color: #22C3AA;
}
</style>
<!-- 背景图片的设置 CSS  初始化css -->
<style type="text/css">
/*样式初始化*/
		*{
			margin:0;
			padding:0;/*清除内外边距*/
		}
		ul{
			list-style:none;/*去掉列表中的样式的小点*/
		}
		.clearfix:before,.clear:after{/*清除浮动*/
			display:table;
			content:"";
		}
		.clearfix:after{
			clear:both;
		}
		.clearfix{
			*zoom:1;
		}
		 body{
		   background:url())  no-repeat center center;
		   background-size:cover;
		   background-attachment:fixed;
		   background-color:#edf4ed;
		
		}
		 a:link,a:visited{/*去掉下划线*/
		  text-decoration:none;
		  } 
</style>
</head>
<style type="text/css">
/*绝对定位*/
#login {
    background-color: #f8f8f8;
    width: 400px;
    height: 400px;
    float: right;
    margin-right: 100px;
    border-radius: 15px;
    margin-top: 150px;
}
/*登录标题*/
#header_h1{
	 padding: 20px 128px;
	 color: #2ca160;
     font-color: green;
}
/*对表单元素中的input的输入框进行统一格式处理*/
/*对齐中长方形*/
#uname,#upwd {
    margin-bottom: 10px;
    outline: none;
    border-radius: 3px;
    text-decoration: none;
    border-style: none;
    width: 300px;
    height: 42px;
    display: block;/*显示为块级元素，此元素前后会带有换行符*/
    color: black;
    font-size: 14px;
}

#uname,#upwd{/*输入框的初始化布局设置*/
    background-color: ;
    padding-left: 10px;

}
/*登录按钮的样式*/
#entry{
    background-color: #0091ff;
          width: 300px;
    height: 42px;
    margin-bottom: 10px;
    margin-left:50px;
    
    outline: none;
    border-radius: 3px;
    
    
    text-decoration: none;
    border-style: none;
    font-size: 14px;
}

/*输入框被点击时发生反应*/
#uname:focus {
    background: white;/*输入框背景颜色*/
    border: 1px solid grey;/*边框大小和颜色*/
}

#upwd:focus {
    background: white;
    border: 1px solid grey;
}
#entry:hover{
    background: blue;
}
/*验证码的输入框*/
#verify_code{
padding-left: 10px;
    margin-bottom: 10px;
    vertical-align:middle/*input img同一行的方法*/ 
    outline: none;
    dipslay:inline-block;
    border-radius: 3px;
    text-decoration: none;
    border-style: none;
    width: 200px;
    height: 42px;
    color: black;
    font-size: 14px;
        
}  
/*验证码图片*/
#verify_pic{
    margin-bottom: 10px;
    outline: none;
    dipslay:inline-block;
    border-radius: 3px;
    text-decoration: none;
    border-style: none;
    width: 85px;
    height: 42px;
    vertical-align:middle 

}
/*验证码一行*/
.verify_inner{
    dipslay:inline-block;

      margin-bottom: 10px;
    
    outline: none;
    border-radius: 3px;
    text-decoration: none;
    border-style: none;
    width: 300px;
    height: 42px;
    color: black;
    font-size: 14px;
}
/*设置换验证码按钮的位置*/
#btn{
	margin-left:45px;
	color:#220903;
	outline: none;
	  border-radius: 3px;
      text-decoration: none;
      border-style: none;
      text-color:black;
      background-color: #edf4ed;
}
/*div的设置统一长方形方块*/
.login_level{
	 width: 350px;
	 height: 50px;
	 margin-left:50px;
	 center:center;
}
/*三个按钮*/
#select{
	 width: 350px;
	 height: 30px;
	 margin-left:50px;
	 center:center;

}

/*底部两个链接的位置*/
#login_bottom_find{

      margin-left: 50px;
    
    border-style: none;
    width: 300px;
    height: 42px;
    color: black;
    font-size: 14px;
}
#login_bottom_sign{
  color:blue;
      margin-left:180px;
    border-style: none;
    width: 300px;
    height: 42px;
    color: black;
    font-size: 14px;
}




</style>
<form action="/iDouBan/LoginServlet" method="post" id="login_form">
<%-- <font color="red">${requestScope.message}</font> --%>
<div id="login">
		<div ><h1 id="header_h1">登录豆瓣</h1>
		    </div>
<!-- 登录的长方形界面框 -->
	<div id="name" class="login_level">
	<!-- 获取cookie中的对象的值 -->
	   <input type="text" id="uname"   name="uname"  value="${cookie.uname.value}" placeholder="请输入用户名">
	   
	 </div>
	 <div id="pwd" class="login_level">  
	   <input type="password" id="upwd" name="upwd"  value="${cookie.upwd.value}"  placeholder="请输入密码" > 
	   
	 </div>
	 
	 <!-- 验证码的输入框 + 验证码图片 -->
		 <div id="verify" class="login_level">
		 <!-- 验证码-->
		<div class="verify_inner">
			 	<input type="text" name="image"  placeholder="请输入验证码" id="verify_code">
		
				<img src="/iDouBan/VerifyCodeServlet" width="80" height ="42" id="verify_pic">
		</div>
		</div>
			 
		
		 <div  id="select"> 
		 <label>
			    <input type="checkbox" name="auto" id="auto"/>自动登录 
	     </label>
	      <label>
			    <input type="checkbox" name="remember" value="" id="remember"/>记住密码
		 </label>
		 <label>
			    <input type="button" value="看不清? 换一张" id="btn">
		 </label>
		 </div>
		 <div>
			    <input type="button" onclick="check()" value="登录" id="entry" />
		</div>
		
		<!-- 自动登录的功能 -->
		
		<div>
			    <a href="http://47.102.212.18/iDouBan/jsp/register.jsp" id="login_bottom_find"><font color=blue>忘记密码</font></a>
				<a href="http://47.102.212.18/iDouBan/jsp/register.jsp" id="login_bottom_sign"><font color=blue>注册账号</font></a>
	    </div>
	    <!-- 表单输入数据给后台，返回的错误的消息提示功能 -->
	    <div id="msg" class="login_level">
	    	<!-- 提示信息 -->
	    	<font color="red" >${requestScope.errorMsg}</font> 
	    	  <%-- <!-- 提示信息 --><font color="red">${requestScope.imageMsg}</font> --%>
	    	  <%--  <!-- 提示信息 --><font color="red">${requestScope.pwdError}</font> --%> 
	    </div>
	    
	    
	</div>
</form>

<!--  自动登录按钮被点击后 非空判断  -->
<c:if test="${not empty cookie.auto.value}">
<!-- 重定位到登陆界面 -->
	 <c:redirect url="http://47.102.212.18/iDouBan/jsp/alter.jsp" />
</c:if>


 <!-- JS代码-->
	<script type="text/javascript">
     //检查用户输入是否为空
    function check(){
        var username = document.getElementById("uname")
        var password = document.getElementById("upwd")
        var code = document.getElementById("verify_code")
        if(username.value == ""){
            alert("请输入用户名")
        }else if(password.value == ""){
            alert("请输入密码")
        }else if(code.value == ""){
            alert("请输入验证码");
        }else{
            document.forms[0].submit()
        }
    }
     	//按钮点击，更改验证码的图片 动态获取img元素----不用重定位或者转发，在原页面实现验证码图片的转化 
	    document.getElementById("btn").onclick = function () {
	        
	        // 改变src，从而让浏览器发送请求到servlet
	        //该方法先获取其标签img 再对应的是第一个标签（下标从0开始）
	        //根据系统时间在点击之后改变验证码
	        document.getElementsByTagName("img")[0].src =
	            "/iDouBan/VerifyCodeServlet?time=" + new Date().getTime();
	    	};
	</script>

	
</body>
</html>
