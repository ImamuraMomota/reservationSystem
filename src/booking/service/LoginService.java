package booking.service;

import static booking.util.CloseableUtil.*;
import static booking.util.DBUtil.*;

import java.sql.Connection;

import booking.beans.User;
import booking.dao.UserDao;
import booking.util.CipherUtil;

public class LoginService {
	public User login(String loginId, String password) {
		Connection connection = null;
		try {
			connection =  getConnection();
			UserDao userDao = new UserDao();
			String encPassword = CipherUtil.encrypt(password);
			User user = userDao.getloginUser(connection, loginId, encPassword);
			commit(connection);
			return user;

		} catch(RuntimeException e) {
			rollback(connection);
			throw e;

		} catch(Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}
