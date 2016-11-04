package jdbc.dao;

import java.util.Date;
import jdbc.dao.impl.UserDaoJdbcImpl;
import jdbc.domain.User;

public class UserDaoTest {

	public static void main(String[] args) {
		
//		UserDao userDao = new UserDaoJdbcImpl();

		// 这是一种父类的引用指向子类的对象
		UserDao userDao = DaoFactory.getInstance().getUserDao();
		
		/*  
			User u = userDao.getUser(14);
			userDao.delete(u);
		*/
		 	User user = new User();
			user.setBirthday(new Date());
			user.setMoney(1000.0f);
			user.setName("dao name1");
			userDao.addUser(user);
			System.out.println(user.getId());
		/*
			User user1 = new User();
			user1.setBirthday(new Date());
			user1.setMoney(2000.0f);
			user1.setName("dao name2");
			userDao.addUser(user1);
		*/
		
		/*	一个测试用例
		 * User u = userDao.getUser(7);
			u.setMoney(20000.1f);
			userDao.update(u);
		 */
		
		/*	一个测试用例		
		 * User u = userDao.getUser(6);
			u.setMoney(20000.1f);
			userDao.update(u);
		*/
	
		
//		User u = userDao.findUser(user.getName(), null);
//		System.out.println(u.getId());

	}
	
	
}
