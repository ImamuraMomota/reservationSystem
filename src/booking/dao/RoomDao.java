package booking.dao;

import static booking.util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import booking.beans.Room;
import booking.exception.SQLRuntimeException;


public class RoomDao {

	public List<Room> getRoomList(Connection connection) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM rooms;");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Room> dbRoom = toRoomList(rs);
			return dbRoom;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Room> toRoomList(ResultSet rs) throws SQLException {
		List<Room> dbRoom = new ArrayList<>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Room room = new Room();
				room.setId(id);
				room.setName(name);

				dbRoom.add(room);
			}
			return dbRoom;

		} finally {
			close(rs);
		}
	}

	//一つのルームの情報をとってくる
	public Room getRoom(Connection connection, String name) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM rooms WHERE name = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();
			List<Room> roomList = toRoomList(rs);
			if (roomList.isEmpty() == true) {
				return null;
			} else if (2 <= roomList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return roomList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}

