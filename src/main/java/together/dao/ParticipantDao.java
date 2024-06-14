package together.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.datasource.impl.OracleDataSource;
import together.vo.Event;
import together.vo.Participant;


public class ParticipantDao {
	public boolean save(Participant newParticipant) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");

		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn
					.prepareStatement("INSERT INTO PARTICIPANTS VALUES(PARTICIPANTS_SEQ.NEXTVAL, ?, ?, ?, ?)");
			stmt.setInt(1, newParticipant.getId());
			stmt.setString(2, newParticipant.getUserid());
			stmt.setInt(3, newParticipant.getEventid());
			stmt.setString(4, newParticipant.getJoinat());

			int r = stmt.executeUpdate();

			return r == 1 ? true : false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public List<Participant> findByEventId(int eventid) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			// 식별키로 조회하고,
			PreparedStatement stmt = conn
					.prepareStatement("SELECT * FROM PARTICIPANTS WHERE EVENT_ID=? ");
			stmt.setInt(1, eventid);

			ResultSet rs = stmt.executeQuery();
			List<Participant> participants = new ArrayList<>();
			while (rs.next()) {
				// rs.getString("writer_id");
				Participant one = new Participant(rs.getInt("id"),rs.getString("user_id"), rs.getInt("event_id"),rs.getString("join_at"));
				participants.add(one);
			}

			return participants;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public List<Participant> findByUserId(int userid) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			// 식별키로 조회하고,
			PreparedStatement stmt = conn
					.prepareStatement("SELECT * FROM PARTICIPANTS WHERE USER_ID=? ");
			stmt.setInt(1, userid);

			ResultSet rs = stmt.executeQuery();
			List<Participant> participants = new ArrayList<>();
			while (rs.next()) {
				// rs.getString("writer_id");
				Participant one = new Participant(rs.getInt("id"),rs.getString("user_id"), rs.getInt("event_id"),rs.getString("join_at"));
				participants.add(one);
			}

			return participants;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}