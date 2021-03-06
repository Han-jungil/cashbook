package dao;

import java.util.*;
import java.sql.*;

public class HashtagDao {
	// tagRankList
	public List<Map<String,Object>> selectTagRankList() {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			/*
			 	SELECT t.tag, t.cnt, RANK() over(ORDER BY t.cnt DESC) ranking
				FROM 
				(SELECT tag, COUNT(*) cnt
				FROM hashtag
				GROUP BY tag) t
			 */
			conn = DriverManager.getConnection("jdbc:mariadb://3.39.254.208/cashbook","root","mariadb1234");
			String sql = "SELECT t.tag, t.cnt, RANK() over(ORDER BY t.cnt DESC) RANK , t.cashbookNo  "
					+ "FROM "
					+ "(SELECT tag, COUNT(*) cnt, c.cashbook_no cashbookNo "
					+ "FROM hashtag h  "
					+ "INNER JOIN cashbook c "
					+ "ON h.cashbook_no = c.cashbook_no "
					+ "GROUP BY tag) t";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> map = new HashMap <String,Object>();
				map.put("cashbookNo", rs.getInt("cashbookNo"));
				map.put("tag", rs.getString("tag"));
				map.put("cnt", rs.getInt("t.cnt"));
				map.put("rank", rs.getInt("rank"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// tagDateController
	public List<Map<String,Object>> selectTagDateList(String kind, String startDate, String endDate) {
		List<Map<String,Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			/*
			 	SELECT c.cash_date cashDate, h.tag tag
				FROM hashtag h
				INNER JOIN cashbook c
				ON h.cashbook_no = c.cashbook_no
				where cash_date BETWEEN ? AND ?
			 */
			conn = DriverManager.getConnection("jdbc:mariadb://3.39.254.208/cashbook","root","mariadb1234");
			String sql = "SELECT t.tag tag, t.kind kind, t.cnt cnt, RANK() over(ORDER BY t.cnt DESC) RANK, t.cashbookNo cashbookNo "
					+ "FROM "
					+ "(SELECT c.cash_date cashDate, h.tag tag, c.kind kind, COUNT(*) cnt, c.cashbook_no cashbookNo "
					+ "FROM hashtag h  "
					+ "INNER JOIN cashbook c "
					+ "ON h.cashbook_no = c.cashbook_no "
					+ "WHERE kind = ?  "
					+ "AND cash_date BETWEEN ? AND ? "
					+ "GROUP BY tag) t";
			String sql1 = "SELECT t.tag tag, t.kind kind, t.cnt cnt, RANK() over(ORDER BY t.cnt DESC) RANK, t.cashbookNo cashbookNo "
					+ "FROM "
					+ "(SELECT c.cash_date cashDate, h.tag tag, c.kind kind, COUNT(*) cnt, c.cashbook_no cashbookNo "
					+ "FROM hashtag h  "
					+ "INNER JOIN cashbook c "
					+ "ON h.cashbook_no = c.cashbook_no "
					+ "WHERE kind LIKE ?  "
					+ "AND cash_date BETWEEN ? AND ? "
					+ "GROUP BY tag) t";
			if(!kind.equals("") && kind != null) {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, kind);
				stmt.setString(2, startDate);
				stmt.setString(3, endDate);
			} else {
				stmt = conn.prepareStatement(sql1);
				stmt.setString(1, "%"+kind+"%");
				stmt.setString(2, startDate);
				stmt.setString(3, endDate);
			}
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("cashbookNo", rs.getInt("cashbookNo"));
				map.put("kind", rs.getString("kind"));
				map.put("cnt", rs.getInt("cnt"));
				map.put("tag", rs.getString("tag"));
				map.put("rank", rs.getString("RANK"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// TagCategoryController
	public HashMap<String, Object> selectTagCategoryList(int cashbookNo, String tag) {
		HashMap<String,Object> map = new HashMap<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			/*
			 	SELECT h.tag, c.cashbook_no, c.cash, c.kind, c.memo, c.cash_date 
				FROM hashtag h
				INNER JOIN cashbook c
				ON h.cashbook_no = c.cashbook_no
				WHERE tag = ? AND c.cashbook_no = ?
			 */
			conn = DriverManager.getConnection("jdbc:mariadb://3.39.254.208/cashbook","root","mariadb1234");
			String sql = "SELECT h.tag, c.cashbook_no cashbookNo, c.cash cash, c.kind, c.memo, c.cash_date cashDate "
					+ "				FROM hashtag h "
					+ "				INNER JOIN cashbook c "
					+ "				ON h.cashbook_no = c.cashbook_no "
					+ "				WHERE tag = ? AND c.cashbook_no = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tag);
			stmt.setInt(2, cashbookNo);
			rs = stmt.executeQuery();
			if(rs.next())  {
				map.put("tag", rs.getString("tag"));
				map.put("cashbookNo", rs.getInt("cashbookNo"));
				map.put("cash", rs.getInt("cash"));
				map.put("kind", rs.getString("kind"));
				map.put("memo", rs.getString("memo"));
				map.put("cashDate", rs.getString("cashDate"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
			
		return map;
	}
	
	// TagCategoryController
		public List<HashMap<String, Object>> selectTagCategoryRankList(String tag) {
			List<HashMap<String,Object>> list = new ArrayList<HashMap<String, Object>>();
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				/*
				 	SELECT h.tag, c.cashbook_no, c.cash, c.kind, c.memo, c.cash_date 
					FROM hashtag h
					INNER JOIN cashbook c
					ON h.cashbook_no = c.cashbook_no
					WHERE tag = ? AND c.cashbook_no = ?
				 */
				conn = DriverManager.getConnection("jdbc:mariadb://3.39.254.208/cashbook","root","mariadb1234");
				String sql = "SELECT h.tag tag, c.cashbook_no cashbookNo, c.cash cash, c.kind, c.memo, c.cash_date cashDate "
						+ "				FROM hashtag h "
						+ "				INNER JOIN cashbook c "
						+ "				ON h.cashbook_no = c.cashbook_no "
						+ "				WHERE tag = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, tag);
				rs = stmt.executeQuery();
				while(rs.next())  {
					HashMap<String,Object> map = new HashMap<>();
					map.put("tag", rs.getString("tag"));
					map.put("cashbookNo", rs.getInt("cashbookNo"));
					map.put("cash", rs.getInt("cash"));
					map.put("kind", rs.getString("kind"));
					map.put("memo", rs.getString("memo"));
					map.put("cashDate", rs.getString("cashDate"));
					list.add(map);
				}
				System.out.println("selectTagCategoryRankList list.size : " + list.size());
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
				
			return list;
		}
}
