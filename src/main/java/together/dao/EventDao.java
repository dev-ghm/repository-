package together.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.datasource.impl.OracleDataSource;
import together.vo.Event;


public class EventDao {
	public boolean save(Event newEvent) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");

		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn
					.prepareStatement("INSERT INTO EVENTS VALUES(EVENTS_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setInt(1, newEvent.getId());
			stmt.setString(2, newEvent.getTitle());
			stmt.setString(3, newEvent.getDescription());
			stmt.setString(4, newEvent.getTag());
			stmt.setInt(5, newEvent.getSportid());
			stmt.setString(6, newEvent.getHostid());
			stmt.setDate(7, newEvent.getOpenat());
			stmt.setString(8, newEvent.getCapacity());
			stmt.setString(9, newEvent.getAttendee());
			stmt.setDate(10, newEvent.getRegisterat());

			int r = stmt.executeUpdate();

			return r == 1 ? true : false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public List<Event> findById(int id) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			// 식별키로 조회하고,
			PreparedStatement stmt = conn
					.prepareStatement("SELECT * FROM EVENTS WHERE ID=? ");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			List<Event> events = new ArrayList<>();
			while (rs.next()) {
				// rs.getString("writer_id");
				Event one = new Event(rs.getInt("id"),rs.getString("title"), rs.getString("description"),rs.getString("tag")
				,rs.getInt("sport_id"),rs.getString("host_id"),rs.getDate("open_at"),rs.getString("capacity"),rs.getString("attendee"),rs.getDate("register_at"));
				events.add(one);
			}

			return events;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
		
		public List<Event> findAllOrderByOpenAt() throws Exception {
			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
			ods.setUser("fit_together");
			ods.setPassword("oracle");
			try (Connection conn = ods.getConnection()) {
				// 식별키로 조회하고,
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM EVENTS ORDER BY OPEN_AT ASC");

				ResultSet rs = stmt.executeQuery();
				List<Event> events = new ArrayList<>();
				while (rs.next()) {
					/*
					 * (int bookId, int categoryId, String categoryName, String title, 
					 * String publisher, String description, int pages, String imageUrl, int rentalCnt)
					 */
					Event one = new Event(rs.getInt("id"),rs.getString("title"), rs.getString("description"),rs.getString("tag")
							,rs.getInt("sport_id"),rs.getString("host_id"),rs.getDate("open_at"),rs.getString("capacity"),rs.getString("attendee"),rs.getDate("register_at"));
					events.add(one);
				}

				return events;
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}
		
		public List<Event> findByTag(String tag) throws Exception {
			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
			ods.setUser("fit_together");
			ods.setPassword("oracle");
			try (Connection conn = ods.getConnection()) {
				// 식별키로 조회하고,
				PreparedStatement stmt = conn
						.prepareStatement("SELECT * FROM EVENTS WHERE TAG=? ORDER BY OPEN_AT ASC");
				stmt.setString(1, tag);

				ResultSet rs = stmt.executeQuery();
				List<Event> events = new ArrayList<>();
				while (rs.next()) {
					// rs.getString("writer_id");
					Event one = new Event(rs.getInt("id"),rs.getString("title"), rs.getString("description"),rs.getString("tag")
					,rs.getInt("sport_id"),rs.getString("host_id"),rs.getDate("open_at"),rs.getString("capacity"),rs.getString("attendee"),rs.getDate("register_at"));
					events.add(one);
				}

				return events;
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}
		
		public boolean increaseRentalCntByNo(int id) throws Exception {
			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
			ods.setUser("fit_together");
			ods.setPassword("oracle");
			try (Connection conn = ods.getConnection()) {
				// 식별키로 조회하고,
				PreparedStatement stmt = conn.prepareStatement("UPDATE EVENTS SET ATTENDEE=ATTENDEE+1 WHERE ID=?");
				stmt.setInt(1, id);

				int r = stmt.executeUpdate();

				return r == 1 ? true : false;
			} catch (Exception e) {
				System.out.println(e);
				return false;
			}

		}
}
