package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.*;
import dao.*;

/**
 * Servlet implementation class InsertCashBookController
 */
@WebServlet("/InsertCashBookController")
public class InsertCashBookController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String y = request.getParameter("y");
		String m = request.getParameter("m");
		String d = request.getParameter("d");
		String cashbookDate = y + "-" + m + "-" + d;
		System.out.println("insertBefore" + cashbookDate);
		request.setAttribute("cashbookDate", cashbookDate);
		request.setAttribute("y", y);
		request.setAttribute("m", m);
		
		request.getRequestDispatcher("/WEB-INF/view/InsertCashBookController.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// 1) request 분석(C)
	request.setCharacterEncoding("utf-8");
	String cashbookDate = request.getParameter("cashbookDate");
	int cash = Integer.parseInt(request.getParameter("cash"));
	String kind = request.getParameter("kind");
	String memo = request.getParameter("memo");
	
	
	// 디버깅
	System.out.println(cashbookDate);
	System.out.println(cash);
	System.out.println(kind);
	System.out.println(memo);
	
	// 2) 메서드
	Cashbook cashbook = new Cashbook();
	cashbook.setCashDate(cashbookDate);
	cashbook.setCash(cash);
	cashbook.setKind(kind);
	cashbook.setMemo(memo);
		
	CashbookDao cashbookDao = new CashbookDao();
	cashbookDao.insertCashbookAction(cashbook);
	
	// 원래있던곳으로 돌아가기
	response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
	}
}
