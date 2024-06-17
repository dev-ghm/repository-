package together.vo;

import java.sql.Date;

public class Participant {
	int id;
	String userid;
	int eventid;
	Date joinat;
	public Participant() {
		super();
	}
	public Participant(int id, String userid, int eventid, Date joinat) {
		super();
		this.id = id;
		this.userid = userid;
		this.eventid = eventid;
		this.joinat = joinat;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getEventid() {
		return eventid;
	}
	public void setEventid(int eventid) {
		this.eventid = eventid;
	}
	public Date getJoinat() {
		return joinat;
	}
	public void setJoinat(Date joinat) {
		this.joinat = joinat;
	}
	
}
