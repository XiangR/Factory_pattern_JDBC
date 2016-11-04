package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.DataSource.MyDataSource;
import jdbc.DataSource.MyDataSource2;

public final class JdbcUtils {

//	private static String url = "jdbc:mysql://localhost:3306/jdbc";
//	private static String user = "root";
//	private static String password = "111111";

	// import the MyDataSource
	private static MyDataSource2 myDataSource = null;

	// ʹ��private����ֹ��ʵ����
	private JdbcUtils () {
	}
	
	// ��ע���������뵽 static�� �����౻װ�ص��������  ÿ�ξ�ֻ������һ��
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			myDataSource = new MyDataSource2();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException { 
//		return DriverManager.getConnection(url, user, password);
		return myDataSource.getConnection();
	}
	
	public static void free(Connection conn, Statement st, ResultSet rs) {
		//���ı�ʾ��Ҫ��ô������
		
		try {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null)
					try {
						conn.close();
//						myDataSource.free(conn);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}
	}
}
