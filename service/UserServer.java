package jdbc.service;

import jdbc.dao.UserDao;



public class UserServer {
	private UserDao userDao;
	
	public void regist(jdbc.domain.User user) {
		userDao.addUser(user);
	}
	
}
