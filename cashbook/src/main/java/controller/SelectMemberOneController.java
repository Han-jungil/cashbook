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

/**
 * Servlet implementation class SelectMemberOneController
 */
@WebServlet("/SelectMemberOneController")
public class SelectMemberOneController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 접속허가체크
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) {
		// 이미 로그인이 되어 있는 상태라면
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}
		System.out.println("SelectMemberOneControllersessionID: " + sessionMemberId);
		
		// 요청값 받기(c)
		String memberId = sessionMemberId;
		
		// 디버깅
		System.out.println("SelectMemberOneControllerMermerID: " + memberId);
		
		// 메서드 실행하여 모델값 받기
		MemberDao memberDao = new MemberDao();
		Member member = new Member();
		member = memberDao.SelectMemberOneController(memberId);
		
		// 디버깅
		System.out.println(member.getMemberId());
		System.out.println(member.getMemberPw());
		System.out.println(member.getName());
		System.out.println(member.getGender());
		System.out.println(member.getAge());
		// 뷰로 보낼준비
		request.setAttribute("member", member);
		
		// 뷰 포워딩(c)
		request.getRequestDispatcher("/WEB-INF/view/SelectMemberOne.jsp").forward(request, response);
		
	}

}
