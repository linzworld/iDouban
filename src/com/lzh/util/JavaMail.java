package com.lzh.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * @Description 用于用户找回密码，邮件功能 
 * @author 林泽鸿
 * @time 2019年5月15日 下午10:07:40
 */
public class JavaMail{
	
	public static void main(String[] args) throws Exception
	{
		
		//邮箱协议信息的封装，配置属性
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");//协议：smtp
		props.setProperty("mail.smtp.host", "smtp.qq.com");
		props.setProperty("mail.stmp.port", "465");//端口号
		props.setProperty("mail.smpt.auth", "true");//需要授权
		//QQ:SSL安全验证
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		
		//私有化的，只能有getInstance返回session对象--通过session对象访问服务器
		Session session = Session.getInstance(props);
		//创建邮件
		MimeMessage message = createMimeMessage(session,"2448974318@qq.com","282107958@qq.com","2448974318@qq.com","2448974318@qq.com");
		//传输对象
		Transport transport = session.getTransport() ;//建立连接对象
		transport.connect("2448974318@qq.com","tbludggodvkadjdf");//建立连接，其中密码以授权码的形式，
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	
	}
	public static  MimeMessage createMimeMessage(Session session,String send ,String receive,String cReceive ,String mReceive) throws Exception
	{
		//邮件内容
		MimeMessage message = new MimeMessage(session) ;
		//纯文本，带图片
		//发件人
		Address address = new InternetAddress(send, "发件人的名字","UTF-8");
		message.setFrom(address);
		message.setSubject("这是标题。。。。","UTF-8");
		message.setContent("正文内容收件人","text/html; charset=UTF-8");
		//收件人类型，TO普通收件人，CC抄送，BCC密送。
		message.setRecipient(MimeMessage.RecipientType.TO , new InternetAddress(receive,"收件人A" , "UTF-8") );
		message.setRecipient(MimeMessage.RecipientType.CC , new InternetAddress(cReceive,"收件人B" , "UTF-8") );
		message.setRecipient(MimeMessage.RecipientType.BCC , new InternetAddress(mReceive,"收件人C" , "UTF-8") );
		
		//发件时间
		message.setSentDate(new Date());
		message.saveChanges();//保存邮件
		return message ;
	}
	
	
}