package together.vo.complex;

import together.vo.Participant;
import together.vo.User;

public class ParticipantWithDetail {
	Participant participant;
	User user;

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
