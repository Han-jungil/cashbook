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
 * Servlet implementation class InsertMemberController
 */
@WebServlet("/InsertMemberController")
public class InsertMemberController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/InsertMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) request 분석(C)
		request.setCharacterEncoding("utf-8");
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		
		// 디버깅
		System.out.println("InsertMemberController request : " + memberId);
		System.out.println("InsertMemberController request : " + memberPw);
		System.out.println("InsertMemberController request : " + name);
		System.out.println("InsertMemberController request : " + age);
		System.out.println("InsertMemberController request : " + gender);
		
		// 2) 모델값 넣기
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		member.setName(name);
		member.setGender(gender);
		member.setAge(age);
		
		MemberDao memberDao = new MemberDao();
		memberDao.insertMember(member);
		
		System.out.println("회원가입 성공");
		response.sendRedirect(request.getContextPath()+"/LoginController");
	}

}
