package jdbc.dao;

import jdbc.domain.User;

/*
 *	��һ�����ݷ��ʲ�Ķ���
 * 	Ϊҵ���߼������
*/
public interface UserDao {

	public void addUser(User user);
		
	public User getUser(int userId);
	
	public User findUser(String name, String password);
	
	public void update(User user);
	
	public void delete(User user);
}
