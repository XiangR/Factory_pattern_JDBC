package jdbc.dao;

import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {
	// 工厂模式的意义 ？
	private static UserDao userDao = null;
	private static DaoFactory instance = new DaoFactory();
	
	public DaoFactory() {
		
		try {
			Properties prop = new Properties();
			InputStream inStream = this.getClass().getClassLoader().
					getResourceAsStream("daoconfig.properties");
			
			prop.load(inStream);
			
			String userDaoClass = prop.getProperty("userDaoClass");
			Class clazz = Class.forName(userDaoClass);
			userDao = (UserDao) clazz.newInstance();
		} catch (Throwable a) { 
			throw new ExceptionInInitializerError(a);
		}
	}
	
	public static DaoFactory getInstance() {
		return instance;
	}
	
	public static UserDao getUserDao() {
		return userDao;
	}

}
