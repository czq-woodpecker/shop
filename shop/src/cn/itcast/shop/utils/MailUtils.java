package cn.itcast.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;////注意是这个jar的
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;


/**
 * 发送邮件的工具类
 * @author paperwings
 *
 */
public class MailUtils {
	public static void sendMail(String to,String code) throws AddressException, MessagingException {
		Properties props = new Properties();
		props.setProperty("mail.smtp", "localhost");//设置发送邮件的服务器
		//1.Session对象连接（与邮箱服务器连接）
		Session session = Session.getInstance(props, new Authenticator() {
		
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("service@shop.com", "111");
		}
		});
		
		//2.构建邮件信息
		Message message = new MimeMessage(session);
		//发件人
		message.setFrom(new InternetAddress("service@shop.com"));
		//收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(to));
		//设置标题
		message.setSubject("来自SHOP的激活邮件");
		//设置正文        注意：下面的IP地址为本机IP地址  若计算机设置为动态获取IP地址的话可以会变========================
		message.setContent("<h1>来自SHOP官网的激活邮件</h1><h3><a href='http://110.65.97.102:8080/shop/user_active.action?code="+code+"'>http://110.65.97.102：8080/shop/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
		
		//3.发送对象
		Transport.send(message);
	}
}
