package dao;

import java.sql.*;

import vo.Stats;

public class StatsDao {
	public void insertStats() {	// <-- Listener
		// INSERT INTO stats(day, cnt) VALUES(CURDATE(), 1)
		Connection conn = null;
	    PreparedStatement stmt = null;
	    String sql = "INSERT INTO stats(day, cnt) VALUES(CURDATE(), 1)";
	    try {
			conn = DriverManager.getConnection("jdbc:mariadb://3.39.254.208/cashbook","root","mariadb1234");
			stmt = conn.prepareStatement(sql);
			System.out.println("sql insertcashbook : " + stmt);	//디버깅
			int row = stmt.executeUpdate();
			if(row == 1) { // 디버깅
				System.out.println("입력성공");
			} else {
				System.out.println("입력실패");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	        try {
		           conn.close();
		        } catch (SQLException e) {
		           e.printStackTrace();
		        }
		}
	    
	}
	
	public Stats selectStatsOneByNow() {	// <-- Listener, Controller(Servlet)
		// SELECT * FROM stats WHERE DAY = CURDATE()
		Stats stats = null;
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    String sql = "SELECT * FROM stats WHERE DAY = CURDATE()";
	    try {
			conn = DriverManager.getConnection("jdbc:mariadb://3.39.254.208/cashbook","root","mariadb1234");
			stmt = conn.prepareStatement(sql);
			System.out.println("sql insertcashbook : " + stmt);	//디버깅
			rs = stmt.executeQuery();
			 if(rs.next()) {
				 stats = new Stats();
				stats.setDay(rs.getString("day"));
				stats.setCnt(rs.getInt("cnt"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	        try {
		           conn.close();
		        } catch (SQLException e) {
		           e.printStackTrace();
		        }
		}
        
	    
		return stats;
	}
	
	public int updacashbookatsByNow() { // <-- Listener
		// UPDATE stats SET cnt = cnt+1 WHERE DAY = CURDATE()
		Connection conn = null;
		PreparedStatement stmt = null;
		int row = 0;
		String sql = "UPDATE stats SET cnt = cnt+1 WHERE DAY = CURDATE()";
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://3.39.254.208/cashbook","root","mariadb1234");
			stmt = conn.prepareStatement(sql);
			System.out.println("sql  pdateMemberController : " + stmt);	//디버깅
			row = stmt.executeUpdate();
			if(row == 1) { // 디버깅
				System.out.println("수정성공");
			} else {
				System.out.println("수정실패");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	        try {
		           conn.close();
		        } catch (SQLException e) {
		           e.printStackTrace();
		        }
		}
		return row;
		
	}
	
	public int selectStatsTotalCount() { //<-- Controller(Servlet)
		// SELECT SUM(cnt) from stats
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int cnt = 0;
		String sql = "SELECT SUM(cnt) cnt from stats";
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://3.39.254.208/cashbook","root","mariadb1234");
			System.out.println("conn : " + conn); // 디버깅
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
	        try {
		           conn.close();
		        } catch (SQLException e) {
		           e.printStackTrace();
		        }
		}
		return cnt;
	}
}
