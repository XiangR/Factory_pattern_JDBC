package jdbc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.JdbcUtils;
import jdbc.dao.DaoException;
import jdbc.dao.UserDao;
import jdbc.domain.User;

public class UserDaoJdbcImpl implements UserDao {

	public void addUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into user (name, birthday, money) values (?,?,?)";
//			ps = conn.prepareStatement(sql);
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getName());
			// caution
			ps.setDate(2, new java.sql.Date(user.getBirthday().getTime()));
			ps.setFloat(3, user.getMoney());
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				user.setId(rs.getInt(1));
			}
			
		} catch(SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.free(conn, ps, rs);
		}
	}

	public User getUser(int userId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select id, name, birthday, money from user where id = ?" ;
//			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				user = mappingUser(rs);
			}			
		} catch(SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.free(conn, ps, rs);
		}
		return user;
	}

	public User findUser(String name, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select id, name, birthday, money from user where name = ?" ;
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				user = mappingUser(rs);
			}			
		} catch(SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.free(conn, ps, rs);
		}
		return user;
	}

	public void update(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			// 这里曾因为最后多了一个逗号而出错
			String sql = "update user set name = ?, birthday = ?, money = ? where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setDate(2, new java.sql.Date(user.getBirthday().getTime()));
			ps.setFloat(3, user.getMoney());
			ps.setInt(4, user.getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.free(conn, ps, rs);
		}
	}

	public void delete(User user) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			// use the userId to delete
			String sql = "delete from user where id = " + user.getId();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.free(conn, st, rs);
		}

	}

	private User mappingUser(ResultSet rs) throws SQLException {
		User user;
		user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setBirthday(rs.getDate("birthday"));
		user.setMoney(rs.getFloat("money"));
		return user;
	}


}
