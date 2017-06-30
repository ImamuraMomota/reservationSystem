package booking.dao;
import static booking.util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import booking.beans.Time;
import booking.exception.SQLRuntimeException;

public class TimeDao {

	public List<Time> getTime(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM Time ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Time> dbTime = toTimeList(rs);
			return dbTime;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Time> toTimeList(ResultSet rs)
			throws SQLException {

		List<Time> dbTime = new ArrayList<>();
		try {
			while(rs.next()) {
				int id = rs.getInt("id");
				String availableTime = rs.getString("available_time");


				Time time = new Time();
				time.setId(id);
				time.setAvailableTime(availableTime);

				dbTime.add(time);
			}
			return dbTime;
		} finally {
			close(rs);
		}

		}

	}
