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
	
	// 비번 틀릴경우 아뒤변경 안할경우
	if(!memberPw.equals("1234") && memberId.equals("")) {
		response.sendRedirect(request.getContextPath()+"/SelectMemberOneController");
		return;
	}
	// 디버깅
	System.out.println("DeleteMemberControllerBefore : " + memberId);
	System.out.println("DeleteMemberControllerBefore : " + memberPw);
	
	// 메서드 이용하여 모델값 구하기(M)
	MemberDao memberDao = new MemberDao();
	memberDao.deletMember(memberId, memberPw);
	
	// 뷰로 돌아가기
	response.sendRedirect(request.getContextPath()+"/LogoutController");
	}

}
