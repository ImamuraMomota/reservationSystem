package booking.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import booking.beans.Room;
import booking.service.RoomService;

@WebServlet(urlPatterns = { "/roomAll" })
public class RoomAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Room> rooms = new RoomService().getRoomList();
//		String currentDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

		Date currentDate = new Date();


		//Date date = sdf.parse("2017/01/01 12:00:00

		// Date型の日時をCalendar型に変換
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);

		// 日時を加算する
		calendar.add(Calendar.DATE, 1);

		String nextDate = new SimpleDateFormat("yyyy/MM/dd").format(calendar.getTime());

		//str = new SimpleDateFormat(DATE_PATTERN).format(cal.getTime());

		System.out.println(nextDate);

		request.setAttribute("nextDate", nextDate);
		request.setAttribute("currentDate", currentDate);
		request.setAttribute("rooms", rooms);
		request.getRequestDispatcher("roomAll.jsp").forward(request, response);
	}
}
