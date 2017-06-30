package booking.beans;

import java.io.Serializable;

public class Time implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String availableTime;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAvailableTime() {
		return availableTime;
	}
	public void setAvailableTime(String availableTime) {
		this.availableTime = availableTime;
	}


}
