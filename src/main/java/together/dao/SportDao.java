package together.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.datasource.impl.OracleDataSource;
import together.vo.Sport;

public class SportDao {
	public List<String> findDistinctType() throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT TYPE FROM SPORTS ");

			ResultSet rs = stmt.executeQuery();
			List<String> types = new ArrayList<>();
			while (rs.next()) {
				String type = rs.getString("type");
				types.add(type);
			}

			return types;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Sport findByNumber(int sportnumber) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SPORTS WHERE SPORT_NUMBER=?");
			stmt.setInt(1, sportnumber);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Sport one = new Sport();

				one.setSportNumber(rs.getInt("sport_number"));
				one.setType(rs.getString("type"));
				one.setCity(rs.getString("city"));
				one.setLocation(rs.getString("location"));
				one.setAgency(rs.getString("agency"));
				one.setManagement(rs.getString("management"));
				return one;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public List<Sport> findByType(String type) throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SPORTS WHERE TYPE=?");
			stmt.setString(1, type);
			
			
			ResultSet rs = stmt.executeQuery();
			List<Sport> sports = new ArrayList<Sport>();
			while (rs.next()) {
				Sport one = new Sport();

				one.setSportNumber(rs.getInt("sport_number"));
				one.setType(rs.getString("type"));
				one.setCity(rs.getString("city"));
				one.setLocation(rs.getString("location"));
				one.setAgency(rs.getString("agency"));
				one.setManagement(rs.getString("management"));
				sports.add(one);
			}

			return sports;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
