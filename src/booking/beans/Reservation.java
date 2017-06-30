package booking.beans;

public class Reservation {
	private int id;
	private int userId;
	private String roomName;
	private String reservedDate;
	private String bookingStart;
	private String bookingEnd;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getReservedDate() {
		return reservedDate;
	}
	public void setReservedDate(String reservedDate) {
		this.reservedDate = reservedDate;
	}
	public String getBookingStart() {
		return bookingStart;
	}
	public void setBookingStart(String bookingStart) {
		this.bookingStart = bookingStart;
	}
	public String getBookingEnd() {
		return bookingEnd;
	}
	public void setBookingEnd(String bookingEnd) {
		this.bookingEnd = bookingEnd;
	}

}
