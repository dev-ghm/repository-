package together.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.datasource.impl.OracleDataSource;
import together.vo.Sport;

public class SportDao {
	public List<Sport> findAll() throws Exception {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			// 식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT TYPE FROM SPORTS");

			ResultSet rs = stmt.executeQuery();
			List<Sport> sports = new ArrayList<>();
			while (rs.next()) {
				/*
				 * (int bookId, int categoryId, String categoryName, String title, 
				 * String publisher, String description, int pages, String imageUrl, int rentalCnt)
				 */
				Sport one = new Sport(rs.getInt("sport_number"), rs.getString("type"), rs.getString("city"), rs.getString("location")
						, rs.getString("agency"), rs.getString("management"));
				sports.add(one);
			}

			return sports;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public Sport findBySportNumber(int sportNumber)throws Exception  {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			// 식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SPORTS WHERE SPORT_NUMBER=?");
			stmt.setInt(1, sportNumber);
			ResultSet rs = stmt.executeQuery();
			Sport one = null;
			if (rs.next()) {
				/*
				 * (int bookId, int categoryId, String categoryName, String title, 
				 * String publisher, String description, int pages, String imageUrl, int rentalCnt)
				 */
				one = new Sport(rs.getInt("sport_number"), rs.getString("type"), rs.getString("city"), rs.getString("location")
						, rs.getString("agency"), rs.getString("management"));
			}

			return one;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public Sport findBySportType(String sportType)throws Exception  {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@//15.164.48.36:1521/xe");
		ods.setUser("fit_together");
		ods.setPassword("oracle");
		try (Connection conn = ods.getConnection()) {
			// 식별키로 조회하고,
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SPORTS WHERE TYPE=?");
			stmt.setString(1, sportType);
			ResultSet rs = stmt.executeQuery();
			Sport one = null;
			if (rs.next()) {
				/*
				 * (int bookId, int categoryId, String categoryName, String title, 
				 * String publisher, String description, int pages, String imageUrl, int rentalCnt)
				 */
				one = new Sport(rs.getInt("sport_number"), rs.getString("type"), rs.getString("city"), rs.getString("location")
						, rs.getString("agency"), rs.getString("management"));
			}

			return one;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
