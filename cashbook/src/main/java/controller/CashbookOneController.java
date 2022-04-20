package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashbookDao;
import vo.Cashbook;

@WebServlet("/CashbookOneController")
public class CashbookOneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	// 요청값 분석(c)
	int cashbookNo = 0;
	if(request.getParameter("cashbookNo") != null) {
		cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
	}
	// 디버깅
	System.out.println("CashbookOneController cashbookNo : " + cashbookNo);
	
	// 메서드 이용하여 모델값 구하기(M)
	Cashbook cashbook = new Cashbook();
	CashbookDao cashbookDao = new CashbookDao();
	cashbook = cashbookDao.selectCashbookOne(cashbookNo);
	
	// 뷰로 보낼준비
	request.setAttribute("cashbook", cashbook);
	// 뷰 포워딩(c)
	request.getRequestDispatcher("/WEB-INF/view/CashbookOneController.jsp").forward(request, response);
	}
}
