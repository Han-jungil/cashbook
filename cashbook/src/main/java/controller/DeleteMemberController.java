package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;


@WebServlet("/DeleteMemberController")
public class DeleteMemberController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {;
	// 접속허가체크
	HttpSession session = request.getSession();
	String sessionMemberId = (String)session.getAttribute("sessionMemberId");
	if(sessionMemberId == null) {
		// 이미 로그인이 되어 있는 상태라면
		response.sendRedirect(request.getContextPath()+"/LoginController");
		return;
	}
	
	request.setCharacterEncoding("utf-8");
	
	// 요청값 분석(c)
	String memberId = request.getParameter("memberId");
	
	// 디버깅
	System.out.println("DeleteMemberControllerBefore : " + memberId);
	
	// 보내기 위해 넣기
	Member member = new Member();
	member.setMemberId(memberId);
	
	// 뷰로 보낼준비
	request.setAttribute("member", member);
	
	// 뷰
	request.getRequestDispatcher("/WEB-INF/view/DeleteMember.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// 1) request 분석(C)
	String memberId = request.getParameter("memberId");
	String memberPw = request.getParameter("memberPw");
	
	// 디버깅
	System.out.println("DeleteMemberController After : " + memberId);
	System.out.println("DeleteMemberController After : " + memberPw);
	
	// 메서드 이용하여 모델값 구하기(M)
	MemberDao memberDao = new MemberDao();
	int row = memberDao.deletMember(memberId, memberPw);
	System.out.println("탈퇴row " + row);
	
	// 뷰로 보내기
	if (row==1) { //성공시 SelectMemberOnecontroller으로 돌려보냄
        System.out.println("탈퇴성공");
        response.sendRedirect(request.getContextPath()+"//LogoutController");
        return;
     }else if(row==0) {// row==0이면 영향받은 행이 없으므로 (row 기본값 -1), 비밀번호 오류
        System.out.println("탈퇴실패");
        response.sendRedirect(request.getContextPath()+"/UpdateMemberController");
        
     }else if (row==-1) {//row가 -1이면 sql이 작동 안함
        System.out.println("탈퇴예외발생");
        response.sendRedirect(request.getContextPath()+"/UpdateMemberController");

	 }
	}
}
