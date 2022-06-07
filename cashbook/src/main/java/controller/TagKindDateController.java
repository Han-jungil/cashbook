package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashbookDao;
import dao.HashtagDao;

/**
 * Servlet implementation class TagDateController
 */
@WebServlet("/TagKindDateController")
public class TagKindDateController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// 접속허가체크
	HttpSession session = request.getSession();
	String sessionMemberId = (String)session.getAttribute("sessionMemberId");
	if(sessionMemberId == null) {
		// 이미 로그인이 되어 있는 상태라면
		response.sendRedirect(request.getContextPath()+"/LoginController");
		return;
	}
	Date nowTime = new Date();
	CashbookDao cashbookDao = new CashbookDao();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");	 // 오늘날짜 뽑아오기
	
	request.setCharacterEncoding("utf-8");
	// 요청값 분석(C)
	String startDate ="";
	String kind = request.getParameter("kind");
	String endDate = "";
	if(!"".equals(request.getParameter("endDate")) && request.getParameter("endDate") != null) {
		endDate = request.getParameter("endDate");
	} else {
		endDate = sf.format(nowTime);
	}
	if(!"".equals(request.getParameter("startDate")) && request.getParameter("startDate") != null) {
		startDate = request.getParameter("startDate");
	} else {
		startDate = cashbookDao.selectStartDate();
	}
	
	//디버깅
	System.out.println("startDateTagKindDate : " + startDate);
	System.out.println("endDateTagKindDate : " + endDate);
	System.out.println("kindTagKindDate : " + kind);
	
	// 메서드 실행 모델값 출력(M)
	HashtagDao hashtagDao = new HashtagDao();
	List<Map<String,Object>> list = hashtagDao.selectTagDateList(kind, startDate, endDate);
	request.setAttribute("list", list);
	
	// 원래있던곳으로 돌아가기(뷰로 돌아가기(V))
	request.getRequestDispatcher("/WEB-INF/view/TagList.jsp").forward(request, response);
	
	}

}
