package booking.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import booking.beans.Reservation;
import booking.beans.Room;
import booking.beans.Time;
import booking.beans.User;
import booking.service.ReservationService;
import booking.service.RoomService;
import booking.service.TimeService;
import booking.service.UserService;

@WebServlet(urlPatterns = {"/booker"})
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		Room bookingRoom = new RoomService().getRoom(request.getParameter("roomName"));
		String selectedDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		List<Time> times = new TimeService().getTime();
		List<Reservation> reservations = new ReservationService().getReservation();
		List<User> users = new UserService().getUserList();
		request.setAttribute("reservations", reservations);
		request.setAttribute("selectedDate", selectedDate);
		request.setAttribute("bookingRoom", bookingRoom);
		request.setAttribute("times", times);
		request.setAttribute("users", users);

		request.getRequestDispatcher("booker.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {

		Reservation reservation = new Reservation();
		reservation.setUserId(Integer.parseInt(request.getParameter("userId")));
		reservation.setRoomName(request.getParameter("roomName"));
		reservation.setReservedDate(request.getParameter("reservedDate"));
		reservation.setBookingStart(request.getParameter("bookingStart"));
		reservation.setBookingEnd(request.getParameter("bookingEnd"));

		new ReservationService().register(reservation);
		response.sendRedirect("./booker");
	}
}
