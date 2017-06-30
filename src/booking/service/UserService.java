package booking.service;
import static booking.util.CloseableUtil.*;
import static booking.util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import booking.beans.User;
import booking.dao.UserDao;

public class UserService {
	public List<User> getUserList() {
		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			List<User> dbUser = userDao.getUserList(connection);
			commit(connection);
			return dbUser;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}
