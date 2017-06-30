package booking.service;

import static booking.util.CloseableUtil.*;
import static booking.util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import booking.beans.Reservation;
import booking.dao.ReservationDao;

public class ReservationService {

	public void register(Reservation reservation) {

		Connection connection = null;
		try {
			connection = getConnection();

			ReservationDao reservationDao = new ReservationDao();
			reservationDao.insert(connection, reservation);
			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	private static final int LIMIT_NUM = 1000;
	public List<Reservation> getReservation() {
		Connection connection = null;
		try {
			connection =  getConnection();

			ReservationDao reservationDao = new ReservationDao();
			List<Reservation> dbReservation = reservationDao.getReservations(connection, LIMIT_NUM);
			commit(connection);
			return dbReservation;
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

	public Reservation DeleteReservation(int reservationId) {
		Connection connection = null;
		try {
			connection = getConnection();

			ReservationDao reservationDao = new ReservationDao();
			Reservation reservation = reservationDao.DeleteReservation(connection, reservationId);
			commit(connection);
			return reservation;
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
