package booking.service;

import static booking.util.CloseableUtil.*;
import static booking.util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import booking.beans.Room;
import booking.dao.RoomDao;

public class RoomService {

	//リストでルームの情報をとってくるService
	public List<Room> getRoomList() {
		Connection connection = null;
		try {
			connection = getConnection();

			RoomDao roomDao = new RoomDao();
			List<Room> dbRoom = roomDao.getRoomList(connection);
			commit(connection);
			return dbRoom;
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

	//
	public Room getRoom(String name) {
		Connection connection = null;
		try {
			connection = getConnection();

			RoomDao roomDao = new RoomDao();
			Room room = roomDao.getRoom(connection, name);
			commit(connection);
			return room;
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