package together.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.datasource.impl.OracleDataSource;
import together.vo.Feed;
import together.vo.Sport;


public class FeedDao {
	public boolean save(Feed newFeed) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");

		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn
					.prepareStatement("INSERT INTO FEEDS VALUES(FEEDS_SEQ.NEXTVAL, ?, ?, ?, ?, ?)");
			stmt.setString(1, newFeed.getWriterId());
			stmt.setString(2, newFeed.getTitle());
			stmt.setString(3, newFeed.getBody());
			stmt.setDate(4, newFeed.getWritedAt());
			stmt.setInt(5, newFeed.getReadCnt());

			int r = stmt.executeUpdate();

			return r == 1 ? true : false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	/*
	 * "SELECT * FROM FEEDS WHERE NO = ?" 를 수행할 메서드를 만들거임.
	 */
	public Feed findByNo(int no) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			// 식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM FEEDS WHERE NO = ?");
			stmt.setInt(1, no);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				// rs.getString("writer_id");
				return new Feed(rs.getInt("no"), rs.getString("writer_id"), rs.getString("title"), rs.getString("body"),
						rs.getDate("writed_at"), rs.getInt("read_cnt"));
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	/*
	 * "SELECT * FROM FEEDS ORDER BY WRITED_AT DESC" 를 수행할 메서드를 만들거임.
	 */
	public List<Feed> findAll() throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			// 식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM FEEDS ORDER BY WRITED_AT DESC");

			ResultSet rs = stmt.executeQuery();
			List<Feed> feeds = new ArrayList<>();
			while (rs.next()) {
				// rs.getString("writer_id");
				Feed one = new Feed(rs.getInt("no"), rs.getString("writer_id"), rs.getString("title"), rs.getString("body"),
						rs.getDate("writed_at"), rs.getInt("read_cnt"));
				feeds.add(one);
			} 
			
			return feeds;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public List<Feed> findByWriterId(String writerId) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			// 식별키로 조회하고,
			PreparedStatement stmt = conn
					.prepareStatement("SELECT * FROM FEEDS WHERE WRITER_ID=? ORDER BY WRITED_AT DESC");
			stmt.setString(1, writerId);

			ResultSet rs = stmt.executeQuery();
			List<Feed> feeds = new ArrayList<>();
			while (rs.next()) {
				// rs.getString("writer_id");
				Feed one = new Feed(rs.getInt("no"), rs.getString("writer_id"), rs.getString("title"),
						rs.getString("body"), rs.getDate("writed_at"), rs.getInt("read_cnt"));
				feeds.add(one);
			}

			return feeds;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public boolean setNullToWriterIdByNo(int no) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			// 식별키로 조회하고,
			PreparedStatement stmt = conn
					.prepareStatement("UPDATE FEEDS SET WRITER_ID=NULL WHERE NO=?");
			stmt.setInt(1, no);

			int  r= stmt.executeUpdate();

			return r == 1 ? true : false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}
	
	public boolean increaseReadCntByNo(int no) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			// 식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("UPDATE FEEDS SET READ_CNT=READ_CNT+1 WHERE NO=?");
			stmt.setInt(1, no);

			int r = stmt.executeUpdate();

			return r == 1 ? true : false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}
	
	public boolean delete(int no) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			// 식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM FEEDS WHERE NO=?");
			stmt.setInt(1, no);

			int r = stmt.executeUpdate();

			return r == 1 ? true : false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}
	
	public boolean update(Feed feed) throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			// 식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("UPDATE FEEDS SET TITLE=?, BODY=? WHERE NO=?");
			stmt.setString(1, feed.getTitle());
			stmt.setString(2,feed.getBody());
			stmt.setInt(3,feed.getNo());

			int r = stmt.executeUpdate();

			return r == 1 ? true : false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
}
	
	public int countAll() throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM FEEDS");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int cnt = rs.getInt("count(*)");
				return cnt;
			} else {
				return -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public List<Feed> findAll2(int start, int end) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement(
					"SELECT * FROM (SELECT ROWNUM RN, F.* FROM (SELECT * FROM FEEDS ORDER BY TITLE) F) WHERE RN BETWEEN ? AND ?");
			stmt.setInt(1, start);
			stmt.setInt(2, end);

			ResultSet rs = stmt.executeQuery();
			List<Feed> sports = new ArrayList<Feed>();
			while (rs.next()) {
				Feed one = new Feed();

				one.setNo(rs.getInt("no"));
				one.setWriterId(rs.getString("writerid"));
				one.setTitle(rs.getString("title"));
				one.setBody(rs.getString("body"));
				one.setWritedAt(rs.getDate("writedat"));
				one.setReadCnt(rs.getInt("readcnt"));
				sports.add(one);
				
			}

			return sports;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int generateKey() throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT FEEDS_SEQ.NEXTVAL FROM DUAL");
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
	
}










