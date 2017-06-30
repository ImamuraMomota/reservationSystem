package booking.dao;
import static booking.util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import booking.beans.Reservation;
import booking.exception.NoRowsUpdatedRuntimeException;
import booking.exception.SQLRuntimeException;

public class ReservationDao {

	public void insert(Connection connection, Reservation reservation) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO reservations (");
			sql.append("user_id");
			sql.append(", room_name");
			sql.append(", reserved_date");
			sql.append(", booking_start");
			sql.append(", booking_end");
			sql.append(") values (");
			sql.append("?");//user_id
			sql.append(", ?");//room_name
			sql.append(", ?");//reserved_date
			sql.append(", ?");//booking_start
			sql.append(", ?");//booking_end
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, reservation.getUserId());
			ps.setString(2, reservation.getRoomName());
			ps.setString(3, reservation.getReservedDate());
			ps.setString(4, reservation.getBookingStart());
			ps.setString(5, reservation.getBookingEnd());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public List<Reservation> getReservations(Connection connection, int num) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM reservations ");
			sql.append("ORDER BY reserved_date DESC limit "+ num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Reservation> dbReservation = toReservationList(rs);
			return dbReservation;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Reservation> toReservationList(ResultSet rs)
			throws SQLException {

		List<Reservation> dbReservation = new ArrayList<>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				String roomName = rs.getString("room_name");
				String reservedDate = rs.getString("reserved_date");
				String bookingStart = rs.getString("booking_start");
				String bookingEnd = rs.getString("booking_end");

				Reservation reservation = new Reservation();
				reservation.setId(id);
				reservation.setUserId(userId);
				reservation.setRoomName(roomName);
				reservation.setReservedDate(reservedDate);
				reservation.setBookingStart(bookingStart);
				reservation.setBookingEnd(bookingEnd);

				dbReservation.add(reservation);
			}
			return dbReservation;
		} finally {
			close(rs);
		}
	}

	public Reservation DeleteReservation(Connection connection, int reservationId) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM reservations WHERE id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, reservationId);

			int updatecouont = ps.executeUpdate();
			if (updatecouont == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
		return null;
	}
}
