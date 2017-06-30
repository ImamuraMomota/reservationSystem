package booking.service;

import static booking.util.CloseableUtil.*;
import static booking.util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import booking.beans.Time;
import booking.dao.TimeDao;

public class TimeService {

	public List<Time> getTime() {
		Connection connection = null;
		try {
			connection = getConnection();

			TimeDao timeDao = new TimeDao();
			List<Time> dbTime = timeDao.getTime(connection);
			commit(connection);
			return dbTime;
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
