<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册</title>
<link rel="icon" href="http://47.102.212.18/iDouBan/icon/iDouBan_favicon.ico" type="image/x-icon">
</head>
<style type="text/css">
 body{
   background:url())  no-repeat center center;
   background-size:cover;
   background-attachment:fixed;
    background-color: #daecda;

}
 #register{
    height: 400px;
    width: 390px;
    background: #f8f8f8;
    text-align: center;
    position: absolute;
    left: 50%;
    top: 50%;
	border-radius: 20px;
    transform: translate(-50%,-50%);

}
#register>input{
    outline: none;
    border-radius: 3px;
    text-decoration: none;
    border-style: none;
    width: 300px;
    height: 42px;
    display: block;
    margin: auto;
    color: black;
    font-size: 14px;
}

#uname{
    background-color: ;
    padding-left: 10px;

}


#upwd{
    background-color: ;
    padding-left: 10px;

}
#upwd1{
    background-color: ;
    padding-left: 10px;
	
}

#entry{
    background-color: #0091ff;
    
    color: white;
}

#uname:focus {
    background: white;
    border: 1px solid grey;
}

#upwd:focus {
    background: white;
    border: 1px solid grey;
}
#entry:hover{
    background: blue;
}
#verify>input{
    margin-bottom: 10px;
    outline: none;
    border-radius: 3px;
    text-decoration: none;
    border-style: none;
    width: 200px;
    height: 42px;
    color: black;
    font-size: 14px;
}  

  a:link,a:visited{
  text-decoration:none;
  } 
</style>

<body>

	<form action="/iDouBan/RegisterServlet" method = "post"  id="register_form">
		<div id="register">
			<div style="height: 60px;">
				<h1>注册账号</h1>
			</div>
			<!-- 用户名表单验证 -->
			    <input type = "text" id="uname" name ="uname" placeholder="请输入邮箱"  
			    	 onblur="isEmail(this.value)" /><br/> 
			    <input type = "password"  id="upwd" name ="upwd" placeholder="请输入密码" 
			    	pattern="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$"
			    			title="请输入6-20个字母、数字、下划线 作为密码"
			    			onblur="isPassword(this.value)"/><br/>
				<input type = "password"  id="upwd1" name ="upwd1" placeholder="请再次输入密码"  onblur="isRepeat()"/><br/>
				
				<input type = "button" id="entry" value = "注册"  onclick="return check()"><br/>
			<a href="http://47.102.212.18/iDouBan/jsp/login.jsp"><font color=blue>已有帐号？登录</font></a>
			 <!-- 表单输入数据给后台，返回的错误的消息提示功能 -->
			<div id="msg" class="register_level">
			    <!-- 提示信息 -->
			    <font color="red" >${requestScope.errorMsg}</font> 
			</div>
		</div>
	</form>
	<!-- js代码 -->
<script type = "text/javascript">
	//表单提交之后调用的函数--由它调用其他判断的函数
	//检查用户输入是否为空
    function check(){

    	var uname = document.getElementById("uname");
        var upwd = document.getElementById("upwd");
        var upwd1 = document.getElementById("upwd1");
        
        if(uname.value == ""){
            alert("请输入用户名")
	        }else if(upwd.value == ""){
	            alert("请输入密码")
		        }else if(upwd1.value == ""){
		            alert("请再次输入密码");
			        }else
			        	{
			            document.forms[0].submit()
			        }
    }
	
	
	
  	//onblur失去焦点事件，用户离开输入框时执行 JavaScript 代码：
	//函数：验证邮箱格式
  	function isEmail(strEmail){
  		//定义正则表达式的变量:邮箱正则
  		var reg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
  		console.log(strEmail);
  		//文本框不为空，并且验证邮箱正则成功，给出正确提示
  		if(strEmail != null && strEmail.search(reg) != -1)
  		{ 
  			
  		}
  		else{
  			alert("请输入正确的邮箱格式");
  			document.getElementById("uname").value="";
  			return false;
  		}
  	}

	//验证密码的格式是否符合要求
	function isPassword(strPwd){
		var passwordReg=/^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).{6,20}$/;
  		if(strPwd != "" && strPwd.search(passwordReg) != -1)
  		{
  		}else{
  			
  			alert("密码6-20位，只允许字母、数字、下划线其中两项!!!");
  			return false;
  		}
  	}


	//验证用户再次输入的密码是否一致
	function isRepeat() {
		if(upwd.value != upwd1.value) {
			alert("两次输入密码不一致！")
			upwd.value  = "";
			upwd1.value = "";
		}
	}

</script>
</body>
</html>