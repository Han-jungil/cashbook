package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vo.*; 

public class CashbookDao {
	// CashBookList
	public List<Map<String, Object>> selectCashbookListByMonth(int y, int m) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		/*
		 SELECT 
	 	 cashbook_no cashbookNo
		 	,DAY(cash_date) day
		 	,kind
		 	,cash
		 	,LEFT(memo, 5) memo
		 FROM cashbook
		 WHERE YEAR(cash_date) = ? AND MONTH(cash_date) = ?
		 ORDER BY DAY(cash_date) ASC, kind ASC
		 */
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT"
				+ "		 	cashbook_no cashbookNo"
				+ "		 	,DAY(cash_date) day"
				+ "		 	,kind"
				+ "		 	,cash"
				+ "			,LEFT(memo, 5) memo"
				+ "		 FROM cashbook"
				+ "		 WHERE YEAR(cash_date) = ? AND MONTH(cash_date) = ?"
				+ "		 ORDER BY DAY(cash_date) ASC, kind ASC";
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://3.39.254.208/cashbook","root","mariadb1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, y);
			stmt.setInt(2, m);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cashbookNo", rs.getInt("cashbookNo"));
				map.put("day", rs.getInt("day"));
				map.put("kind", rs.getString("kind"));
				map.put("cash", rs.getInt("cash"));
				map.put("memo", rs.getString("memo"));
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
	
	//InsertCashBookController
	public void insertCashbookAction(Cashbook cashbook, List<String> hashtag) {
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	/*
	 insert into cashbook (cash_date, kind, cash, update_date, create_date, memo) 
	 VALUES ('?2022-03-14', '?', ?, 'NOW()', 'NOW()', '?');
	*/
	
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://3.39.254.208/cashbook","root","mariadb1234");
			conn.setAutoCommit(false); // 자동커밋을 해제
			String sql ="insert into cashbook (cash_date, kind, cash, memo, update_date, create_date, member_id) VALUES (?, ?, ?, ?, NOW(), NOW(), ?);";
			// insert + select 방금생선되 행의 키값 ex) select 방금입력한 cashbook_no를 from cashbook;
			// 강사님이 찾아서 쓴것!(PreparedStatement.RETURN_GENERATED_KEYS)
			stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			System.out.println("sql insertCashbook : " + stmt);	//디버깅
			stmt.setString(1, cashbook.getCashDate());
			stmt.setString(2, cashbook.getKind());
			stmt.setInt(3, cashbook.getCash());
			stmt.setString(4, cashbook.getMemo());
			stmt.setString(5, cashbook.getMemberId());
			int row = stmt.executeUpdate();	// insert
			if(row == 1) { // 디버깅
				System.out.println("입력성공");
			} else {
				System.out.println("입력실패");
			}
			rs = stmt.getGeneratedKeys();	// select 방금입력한 cashbook_no from cashbook
			int cashbookNo = 0;
			if(rs.next()) {
				cashbookNo = rs.getInt(1);
			}
			
			// Hashtag를 저장하는 코드
			PreparedStatement stmt2 = null;
			for(String h : hashtag) {
				String sql2 = "INSERT INTO hashtag(cashbook_no, tag, createDate) VALUES(?, ?, NOW())";
				stmt2 = conn.prepareStatement(sql2);
				stmt2.setInt(1, cashbookNo);
				stmt2.setString(2, h);
				int row1 = stmt2.executeUpdate();
				if(row1 == 1) { // 디버깅
					System.out.println("입력성공");
				} else {
					System.out.println("입력실패");
				}
			}
			
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// CashBookOneController
	public Cashbook selectCashbookOne(int cashbookNo) {
		// 데이터베이스 자원 준비
		Cashbook cashbook = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo, cash_date cashDate, kind, cash, update_date updateDate, create_date createDate, memo FROM cashbook WHERE cashbook_no = ?";
		try {
			// 디비 접속
			System.out.println("드라이버 로딩 성공"); //디버깅
			conn = DriverManager.getConnection("jdbc:mariadb://3.39.254.208/cashbook","root","mariadb1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cashbookNo);
			rs = stmt.executeQuery();
			
			// 데이터 변환(가공)
			if(rs.next()) {
				cashbook = new Cashbook();
				cashbook.setCashbookNo(rs.getInt("cashbookNo"));
				cashbook.setCashDate(rs.getString("cashDate"));
				cashbook.setKind(rs.getString("kind"));
				cashbook.setCash(rs.getInt("cash"));
				cashbook.setUpdateDate(rs.getString("updateDate"));
				cashbook.setCreateDate(rs.getString("createDate"));
				cashbook.setMemo(rs.getString("memo"));
			}
			
		return cashbook;
		} catch(Exception e ) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	return cashbook;
	}
	
	// UpdateCashBookController
	public void updateCashbook(Cashbook cashbook, List<String> hashtag) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// "UPDATE cashbook SET cash_date = '2022-03-13', kind = '지출', cash = 630000, update_date = NOW(), memo = 'Tsessebe'  WHERE cashbook_no = 3"
		String sql = "UPDATE cashbook SET cash_date = ?, kind = ?, cash = ?, update_date = NOW(), memo = ?  WHERE cashbook_no = ?";
		try {
			// 디비 접속
			System.out.println("드라이버 로딩 성공"); //디버깅
			conn = DriverManager.getConnection("jdbc:mariadb://3.39.254.208/cashbook","root","mariadb1234");
			conn.setAutoCommit(false); // 자동커밋을 해제
			stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cashbook.getCashDate());
			stmt.setString(2, cashbook.getKind());
			stmt.setInt(3, cashbook.getCash());
			stmt.setString(4, cashbook.getMemo());
			stmt.setInt(5, cashbook.getCashbookNo());
			int row = stmt.executeUpdate();
			if(row == 1) {
				System.out.println("수정성공");
			} else {
				System.out.println("수정실패");
			}
			rs = stmt.getGeneratedKeys();	// select 방금입력한 cashbook_no from cashbook
			int cashbookNo = 0;
			if(rs.next()) {
				cashbookNo = rs.getInt(1);
			}
			
			// Hashtag를 저장하는 코드 (수정해야함)
			PreparedStatement stmt2 = null;
			String deleteHashtagSql = "DELETE FROM hashtag WHERE cashbook_no=? ";
			stmt2=conn.prepareStatement(deleteHashtagSql);
			stmt2.setInt(1, cashbook.getCashbookNo());
			stmt2.executeUpdate(); //hashtag delect 요청
			//2.hashtag table에 새로 태그 저장
			PreparedStatement stmt3 = null;
			for(String h : hashtag) {// hashtag만큼 반복해서 insert
				String insertHashtagSql = "INSERT INTO hashtag(cashBook_no,tag,create_date) VALUES (?,?,NOW())";
				stmt3= conn.prepareStatement(insertHashtagSql);
				stmt3.setInt(1, cashbook.getCashbookNo());
				stmt3.setString(2, h);
				stmt3.executeUpdate();
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// DeleteCashBookController
	public void deleteCashbook(int cashbookNo) {
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		
		String sql = "DELETE FROM cashbook WHERE cashbook_no=?";
		String sql1 = "DELETE FROM hashtag WHERE cashbook_no=?";
		try {
			// 디비 접속
			System.out.println("드라이버 로딩 성공"); //디버깅
			conn = DriverManager.getConnection("jdbc:mariadb://3.39.254.208/cashbook","root","mariadb1234");
			conn.setAutoCommit(false); // 자동커밋을 해제
			// 태그
			stmt1 = conn.prepareStatement(sql1);
			stmt1.setInt(1, cashbookNo);
			int row1 = stmt1.executeUpdate();
			if(row1 == 1) {
				System.out.println("태그삭제성공");
			} else {
				System.out.println("태그삭제실패");
			}
			// 일정
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cashbookNo);
			int row = stmt.executeUpdate();
			if(row == 1) {
				System.out.println("일정삭제성공");
			} else {
				System.out.println("일정삭제실패");
			}
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(SQLException e) {
			e.printStackTrace();
			}
		}
	}
	
}

