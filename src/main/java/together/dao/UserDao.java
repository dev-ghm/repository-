package together.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.datasource.impl.OracleDataSource;
import together.vo.User;


public class UserDao {
		
		public boolean save(User newUser) throws Exception {
			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
			ods.setUser("fit_together");
			ods.setPassword("oracle");

			try (Connection conn = ods.getConnection()) {

				PreparedStatement stmt = conn
						.prepareStatement("INSERT INTO USERS VALUES(?, ?, ?, ?, ?, ?, ?)");
				stmt.setString(1, newUser.getId());
				stmt.setString(2, newUser.getPassword());
				stmt.setString(3, newUser.getName());
				stmt.setString(4, newUser.getGender());
				stmt.setInt(5, newUser.getBirth());
				stmt.setString(6, newUser.getEmail());
				stmt.setString(7, newUser.getInterest());

				int r = stmt.executeUpdate();

				return r == 1 ? true : false;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		public User findById(String Id) throws SQLException {
			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
			ods.setUser("fit_together");
			ods.setPassword("oracle");
			try (Connection conn = ods.getConnection()) {
				// 식별키로 조회하고,
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM USERS WHERE ID=?");
				stmt.setString(1, Id);

				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					return new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 
							rs.getInt(5),rs.getString(6),rs.getString(7));
				} else {
					return null;
				}

			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}
		
		public boolean deleteByUserId(String userid) throws SQLException {
			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
			ods.setUser("fit_together");
			ods.setPassword("oracle");

			try (Connection conn = ods.getConnection()) {

				PreparedStatement stmt = 
						conn.prepareStatement("DELETE FROM USERS WHERE ID=?");
				stmt.setString(1, userid);
				
				int r = stmt.executeUpdate();

				return r == 1 ? true : false;
			} catch (Exception e) {
				System.out.println(e);
				return false;
			}

		}

}