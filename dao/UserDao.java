package jdbc.dao;

import jdbc.domain.User;

/*
 *	是一个数据访问层的对象
 * 	为业务逻辑层服务
*/
public interface UserDao {

	public void addUser(User user);
		
	public User getUser(int userId);
	
	public User findUser(String name, String password);
	
	public void update(User user);
	
	public void delete(User user);
}
