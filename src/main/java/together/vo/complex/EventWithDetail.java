package together.vo.complex;

import together.vo.Event;
import together.vo.Sport;

public class EventWithDetail {
	Event event;
	Sport sport;
	boolean Joined;
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Sport getSport() {
		return sport;
	}
	public void setSport(Sport sport) {
		this.sport = sport;
	}
	public boolean isJoined() {
		return Joined;
	}
	public void setJoined(boolean joined) {
		Joined = joined;
	}
	
	
}
