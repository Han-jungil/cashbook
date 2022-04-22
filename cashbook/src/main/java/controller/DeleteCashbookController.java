package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashbookDao;


@WebServlet("/DeleteCashbookController")
public class DeleteCashbookController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		
		// 디버깅
		System.out.println("cashbookNoUpdateBefore : " + cashbookNo);
	
		// 메서드 실행
		CashbookDao cashbookDao = new CashbookDao();
		cashbookDao.deleteCashbook(cashbookNo);
		
		// 뷰로 돌아가기(v)
		// 원래있던곳으로 돌아가기(뷰로 돌아가기)
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
	}

}
