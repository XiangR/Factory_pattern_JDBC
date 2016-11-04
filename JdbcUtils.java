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

	// 使用private来防止别实例化
	private JdbcUtils () {
	}
	
	// 将注册驱动放入到 static中 这样类被装载到虚拟机中  每次就只会运行一次
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
		//真心表示需要这么复杂吗
		
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
