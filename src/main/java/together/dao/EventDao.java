package together.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.datasource.impl.OracleDataSource;
import together.vo.Event;
import together.vo.complex.EventTagCount;


public class EventDao {
	public int generateKey() throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT EVENTS_SEQ.NEXTVAL FROM DUAL");
			ResultSet rs = stmt.executeQuery();
			if(rs.next() ) {
				int key = rs.getInt("nextval");
				return key;
			}else {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	public boolean save(Event newEvent) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");

		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn
					.prepareStatement("INSERT INTO EVENTS VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setInt(1, newEvent.getId());
			stmt.setString(2, newEvent.getTitle());
			stmt.setString(3, newEvent.getDescription());
			stmt.setString(4, newEvent.getTag());
			stmt.setInt(5, newEvent.getSportid());
			stmt.setString(6, newEvent.getHostid());
			stmt.setDate(7, newEvent.getOpenat());
			stmt.setInt(8, newEvent.getCapacity());
			stmt.setInt(9, newEvent.getAttendee());
			stmt.setDate(10, newEvent.getRegisterat());

			int r = stmt.executeUpdate();

			return r == 1 ? true : false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public Event findById(int id) throws SQLException {

		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM EVENTS WHERE ID=?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Event one = new Event();
				one.setId(rs.getInt("id"));
				one.setTitle(rs.getString("title"));
				one.setDescription(rs.getString("description"));
				one.setTag(rs.getString("tag"));
				one.setSportid(rs.getInt("sport_id"));
				one.setHostid(rs.getString("host_id"));
				one.setOpenat(rs.getDate("open_at"));

				one.setCapacity(rs.getInt("capacity"));
				one.setAttendee(rs.getInt("attendee"));
				one.setRegisterat(rs.getDate("register_at"));
				return one;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}
		
		public List<Event> findAll() throws Exception {
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
							,rs.getInt("sport_id"),rs.getString("host_id"),rs.getDate("open_at"),rs.getInt("capacity"),rs.getInt("attendee"),rs.getDate("register_at"));
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
					,rs.getInt("sport_id"),rs.getString("host_id"),rs.getDate("open_at"),rs.getInt("capacity"),rs.getInt("attendee"),rs.getDate("register_at"));
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
		
		public boolean increaseAttendeeById(int id) throws Exception {
			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
			ods.setUser("fit_together");
			ods.setPassword("oracle");
			try (Connection conn = ods.getConnection()) {
				// 식별키로 조회하고,
				PreparedStatement stmt = conn.prepareStatement("UPDATE EVENTS SET ATTENDEE = ATTENDEE+1 WHERE ID=?");
				stmt.setInt(1, id);

				int r = stmt.executeUpdate();
				
				return r == 1 ? true : false;
			} catch (Exception e) {
				System.out.println(e);
				return false;
			}

		}
		
		public List<EventTagCount> countGroupByTag() throws SQLException {
			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
			ods.setUser("fit_together");
			ods.setPassword("oracle");
			try (Connection conn = ods.getConnection()) {

				PreparedStatement stmt = conn.prepareStatement("SELECT TAG, COUNT(*) CNT FROM EVENTS GROUP BY TAG");

				ResultSet rs = stmt.executeQuery();
				List<EventTagCount> tagCounts = new ArrayList<EventTagCount>();
				while (rs.next()) {
					EventTagCount one = new EventTagCount();
					one.setTag(rs.getString("tag"));
					one.setCnt(rs.getInt("CNT"));
					tagCounts.add(one);
				}

				return tagCounts;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public List<Event> findByTitleLikeOrDescriptionLike(String pattern) throws SQLException {

			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
			ods.setUser("fit_together");
			ods.setPassword("oracle");
			try (Connection conn = ods.getConnection()) {

				PreparedStatement stmt = 
						conn.prepareStatement("SELECT * FROM EVENTS WHERE TITLE LIKE ? OR DESCRIPTION LIKE ? ORDER BY OPEN_AT ASC");
				stmt.setString(1, "%"+pattern+"%");
				stmt.setString(2, "%"+pattern+"%");
				ResultSet rs = stmt.executeQuery();
				List<Event> events = new ArrayList<Event>();
				while (rs.next()) {
					Event one = new Event();
					one.setId(rs.getInt("id"));
					one.setTitle(rs.getString("title"));
					one.setDescription(rs.getString("title"));
					one.setTag(rs.getString("tag"));
					one.setSportid(rs.getInt("sport_id"));
					one.setHostid(rs.getString("host_id"));
					one.setOpenat(rs.getDate("open_at"));

					one.setCapacity(rs.getInt("capacity"));
					one.setAttendee(rs.getInt("attendee"));
					one.setRegisterat(rs.getDate("register_at"));

					events.add(one);
				}

				return events;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

		}
		
}
