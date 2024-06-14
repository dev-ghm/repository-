package together.vo;

import java.sql.Date;

public class Event {
	int id;
	String title;
	String description;
	String tag;
	int sportid;
	String hostid;
	Date openat;
	String capacity;
	String attendee;
	Date registerat;
	public Event() {
		super();
	}
	public Event(int id, String title, String description, String tag, int sportid, String hostid, Date openat,
			String capacity, String attendee, Date registerat) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.tag = tag;
		this.sportid = sportid;
		this.hostid = hostid;
		this.openat = openat;
		this.capacity = capacity;
		this.attendee = attendee;
		this.registerat = registerat;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getSportid() {
		return sportid;
	}
	public void setSportid(int sportid) {
		this.sportid = sportid;
	}
	public String getHostid() {
		return hostid;
	}
	public void setHostid(String hostid) {
		this.hostid = hostid;
	}
	public Date getOpenat() {
		return openat;
	}
	public void setOpenat(Date openat) {
		this.openat = openat;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getAttendee() {
		return attendee;
	}
	public void setAttendee(String attendee) {
		this.attendee = attendee;
	}
	public Date getRegisterat() {
		return registerat;
	}
	public void setRegisterat(Date registerat) {
		this.registerat = registerat;
	}
	
	
}
