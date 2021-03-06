package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Member;

public class MemberDao {
	// 회원가입
		public void insertMember(Member member) {
		Member member1= new Member();
		Connection conn = null;
	    PreparedStatement stmt = null;
	    String sql ="INSERT INTO member(member_id , member_pw , NAME , gender, age,create_date) VALUES ( ?, PASSWORD(?), ?, ?, ?, NOW())";
	    try {
	        conn = DriverManager.getConnection("jdbc:mariadb://3.39.254.208/cashbook","root","mariadb1234");
	        stmt = conn.prepareStatement(sql);
	        System.out.println("sql insertMember : " + stmt);	//디버깅
	        stmt.setString(1, member.getMemberId());
	        stmt.setString(2, member.getMemberPw());
	        stmt.setString(3, member.getName());
	        stmt.setString(4, member.getGender());
	        stmt.setInt(5, member.getAge());
	        int row = stmt.executeUpdate();
			if(row == 1) { // 디버깅
				System.out.println("입력성공");
			} else {
				System.out.println("입력실패");
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
		
	}
	// 회원수정
		public int updateMemberController(Member member) {
		// 데이터베이스 자원 준비
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;	
		String sql = "UPDATE member SET member_pw = PASSWORD(?), name = ?, gender = ?, age = ?  WHERE member_id = ?";
			try {
				System.out.println("드라이버 로딩 성공"); //디버깅
				conn = DriverManager.getConnection("jdbc:mariadb://3.39.254.208/cashbook","root","mariadb1234");
				stmt = conn.prepareStatement(sql);
				System.out.println("sql  pdateMemberController : " + stmt);	//디버깅
		        stmt.setString(1, member.getMemberPw());
		        stmt.setString(2, member.getName());
		        stmt.setString(3, member.getGender());
		        stmt.setInt(4, member.getAge());
		        stmt.setString(5, member.getMemberId());
		        row = stmt.executeUpdate();
		        if(row == 1) { // 디버깅
					System.out.println("수정성공");
				} else {
					System.out.println("수정실패");
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
			return row;
		}
	// 회원탈퇴
		@SuppressWarnings("finally")
		public int deletMember(String memberId, String memberPw) {
			int row = -1;
			Connection conn = null;
			 PreparedStatement stmt = null;
			 PreparedStatement stmt2 = null;
			 PreparedStatement stmt3 = null;
			 PreparedStatement stmt4 = null;
			 ResultSet rs = null;
			 List<Integer> list = new ArrayList<Integer>();
			 String sql = "SELECT cashbook_no cashbookNo FROM cashbook"
				 		+ " WHERE member_id=?";
				 
				 String sql2 = "DELETE FROM hashtag"
				 		+ " WHERE cashbook_no=?";
				 
				 String sql3 = "DELETE FROM cashbook"
				 		+ " WHERE member_id=?";
				 
				 String sql4 = "DELETE FROM member"
				 		+ " WHERE member_id=? AND member_pw=PASSWORD(?)";
				 try {
						conn = DriverManager.getConnection("jdbc:mariadb://3.39.254.208/cashbook","root","mariadb1234");
						conn.setAutoCommit(false);
						
						// cashbookNo 가져오기
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, memberId);
						rs = stmt.executeQuery();
						
						while(rs.next()) {
							list.add(rs.getInt("cashbookNo"));
						}
						
						// 해시태그 삭제
						for(int i=0; i<list.size(); i++) {
							stmt2 = conn.prepareStatement(sql2);
							stmt2.setInt(1, list.get(i));
							stmt2.executeUpdate();
							System.out.println("cashbookNo : " + list.get(i));
						}
						
						// 캘린더 정보 삭제
						stmt3 = conn.prepareStatement(sql3);
						stmt3.setString(1, memberId);
						stmt3.executeUpdate();
						
						// 회원 정보 삭제
						stmt4 = conn.prepareStatement(sql4);
						stmt4.setString(1, memberId);
						stmt4.setString(2, memberPw);
						row = stmt4.executeUpdate();
						
						conn.commit();
			} catch (Exception e) {
		        e.printStackTrace();
		     } finally {
		        try {
		           conn.close();
		        } catch (SQLException e) {
		           e.printStackTrace();
		        }
		        return row;
		     }
			
		}
	// 회원정보
		public Member SelectMemberOneController(String memberId) {
			Member member = null;
			Connection conn = null;
		    PreparedStatement stmt = null;
		    ResultSet rs = null;
		    String sql ="SELECT member_id memberId, member_pw memberPw, name, gender, age, create_date createDate FROM member WHERE member_id = ?";
		    try {
		    	System.out.println("드라이버 로딩 성공"); //디버깅
		        conn = DriverManager.getConnection("jdbc:mariadb://3.39.254.208/cashbook","root","mariadb1234");
		        stmt = conn.prepareStatement(sql);
		        System.out.println("sql SelectMemberOneController : " + stmt);	//디버깅
		        stmt.setString(1, memberId);
		        rs = stmt.executeQuery();
		        // 데이터 변환(가공)
		        if(rs.next()) {
		        	member = new Member();
		        	member.setMemberId(rs.getString("memberId"));
		        	member.setMemberPw(rs.getString("memberPw"));
		        	member.setName(rs.getString("name"));
		        	member.setGender(rs.getString("gender"));
		        	member.setAge(rs.getInt("age"));
		        	member.setCreateDate(rs.getString("createDate"));
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		     } finally {
		        try {
		        	rs.close();
					stmt.close();
		           conn.close();
		        } catch (SQLException e) {
		           e.printStackTrace();
		        }
		     }
			return member;
		}
	
	// 로그인
	   public String selectMemberByIdPw(Member member) {
		      String memberId = null;
		      Connection conn = null;
		      PreparedStatement stmt = null;
		      ResultSet rs = null;
		      String sql = "SELECT member_id memberId FROM member WHERE member_id=? AND member_pw=PASSWORD(?)";
		      try {
		         conn = DriverManager.getConnection("jdbc:mariadb://3.39.254.208/cashbook","root","mariadb1234");
		         stmt = conn.prepareStatement(sql);
		         stmt.setString(1, member.getMemberId());
		         stmt.setString(2, member.getMemberPw());
		         rs = stmt.executeQuery();
		         if(rs.next()) {
		            memberId = rs.getString("memberId");
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
		      return memberId;
		   }
}
