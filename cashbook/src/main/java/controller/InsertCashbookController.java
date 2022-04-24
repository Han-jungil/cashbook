package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.*;
import dao.*;

/**
 * Servlet implementation class InsertCashBookController
 */
@WebServlet("/InsertCashbookController")
public class InsertCashbookController extends HttpServlet {

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
		String y = request.getParameter("y");
		String m = request.getParameter("m");
		String d = request.getParameter("d");
		String cashbookDate = y + "-" + m + "-" + d;
		System.out.println("insertBefore" + cashbookDate);
		request.setAttribute("cashbookDate", cashbookDate);
		request.setAttribute("y", y);
		request.setAttribute("m", m);
		
		request.getRequestDispatcher("/WEB-INF/view/InsertCashbookController.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// 접속허가체크
	HttpSession session = request.getSession();
	String sessionMemberId = (String)session.getAttribute("sessionMemberId");
	if(sessionMemberId == null) {
		// 이미 로그인이 되어 있는 상태라면
		response.sendRedirect(request.getContextPath()+"/LoginController");
		return;
	}
		
	// 1) request 분석(C)
	request.setCharacterEncoding("utf-8");
	String memberId = sessionMemberId;
	String cashbookDate = request.getParameter("cashbookDate");
	int cash = Integer.parseInt(request.getParameter("cash"));
	String kind = request.getParameter("kind");
	String memo = request.getParameter("memo");
	
	
	// 디버깅
	System.out.println(memberId);
	System.out.println(cashbookDate);
	System.out.println(cash);
	System.out.println(kind);
	System.out.println(memo);
	
	// 2) 모델값 넣기
	Cashbook cashbook = new Cashbook();
	cashbook.setCashDate(cashbookDate);
	cashbook.setMemberId(memberId);
	cashbook.setCash(cash);
	cashbook.setKind(kind);
	cashbook.setMemo(memo);
		
	//Hashtag
    List<String> hashtag = new ArrayList<>();
    String memo2 = memo.replace("#", " #");
    String[] arr = memo2.split(" ");
    for(String s : arr) {
       if(s.startsWith("#")) {
          String temp = s.replace("#", "");
          if(temp.length()>0) {
             hashtag.add(temp);
          }
       }
    }
    
	// 디버깅
	System.out.println(hashtag.size() +"<-- hashtagSzieInsertCashbookController.doPost()");
	    for(String h : hashtag) {
	       System.out.println(h + " <-- hashtag InsertCashbookController.doPost()");
	    }
	
	// 메서드
	CashbookDao cashbookDao = new CashbookDao();
	cashbookDao.insertCashbookAction(cashbook, hashtag);
		
	// 원래있던곳으로 돌아가기(뷰로 돌아가기)
	response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
		
	}
}
