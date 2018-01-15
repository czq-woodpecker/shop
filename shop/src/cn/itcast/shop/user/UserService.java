package cn.itcast.shop.user;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.utils.MailUtils;
import cn.itcast.shop.utils.UUIDUtils;

/**
 * 用户模块：业务层代码
 * @author paperwings
 *
 */

//开启事务
@Transactional
public class UserService {
	//注入Dao
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 业务层注册用户的代码
	 * @param user
	 */
	public void regist(User user) {
		//保存用户
		user.setState(0);//0未激活  1已激活
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();	//生成激活码
		user.setCode(code);
		userDao.save(user);
		//发送邮件
		try {
			MailUtils.sendMail(user.getEmail(), code);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	//业务层根据激活码查询用户的方法
	public User findByCode(String code) {

		return userDao.findByCode(code);
	}

	//业务层修改用户的方法
	public void update(User existUser) {
		userDao.update(existUser);
		
	}

	//业务层登录的方法
	public User login(User user) {
		return userDao.login(user);
	}

	public User findByUserName(String username) {
		return userDao.findByUserName(username);
	}
}
