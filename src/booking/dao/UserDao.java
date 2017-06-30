package booking.dao;

import static booking.util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import booking.beans.User;
import booking.exception.SQLRuntimeException;

public class UserDao {
	public User getloginUser(Connection connection, String loginId, String password) {
		PreparedStatement ps = null;

		try {
			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, loginId);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);

			if (userList.isEmpty() == true) {
				return null;
			}
			else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			}
			else {
				return userList.get(0);
			}

		} catch (SQLException e) {
				throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> dbuser = new ArrayList<>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_Id");
				String password = rs.getString("password");
				String name = rs.getString("name");

				User user = new User();
				user.setId(id);
				user.setLoginId(loginId);
				user.setPassword(password);
				user.setName(name);

				dbuser.add(user);
			}
			return dbuser;
		} finally {
			close(rs);
		}
	}


	public List<User> getUserList(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<User> dbuser = toUserList(rs);
			return dbuser;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
