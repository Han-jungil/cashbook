package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vo.Cashbook;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashbookDao;

@WebServlet("/UpdateCashbookController")
public class UpdateCashbookController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 요청값 분석(c)
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		String cashDate = request.getParameter("cashDate");
		String kind = request.getParameter("kind");
		int cash = Integer.parseInt(request.getParameter("cash"));
		String memo = request.getParameter("memo");
		
		// 디버깅
		System.out.println("cashbookNoUpdateBefore : " + cashbookNo);
		System.out.println("cashDateUpdateBefore : " + cashDate);
		System.out.println("kindUpdateBefore : " + kind);
		System.out.println("cashUpdateBefore : " + cash);
		System.out.println("memoUpdateBefore : " + memo);
		
		// 메서드 이용하여 모델값 구하기(M)
		Cashbook cashbook = new Cashbook();
		cashbook.setCashbookNo(cashbookNo);
		cashbook.setCashDate(cashDate);
		cashbook.setKind(kind);
		cashbook.setCash(cash);
		cashbook.setMemo(memo);
		
		// 뷰로 보낼준비
		request.setAttribute("cashbook", cashbook);
		
		// 뷰 포워딩(c)
		request.getRequestDispatcher("/WEB-INF/view/UpdateCashbookOneController.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 1) request 분석(C)
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		String cashDate = request.getParameter("cashDate");
		int cash = Integer.parseInt(request.getParameter("cash"));
		String kind = request.getParameter("kind");
		String memo = request.getParameter("memo");
		
		// 디버깅 
		System.out.println("cashbookNoUpdateAfter : " + cashbookNo);
		System.out.println("cashDateUpdateAfter : " + cashDate);
		System.out.println("kindUpdateAfter : " + kind);
		System.out.println("cashUpdateAfter : " + cash);
		System.out.println("memoUpdateAfter : " + memo);
		
		// 2) 모델값 넣기
		Cashbook cashbook = new Cashbook();
		cashbook.setCashbookNo(cashbookNo);
		cashbook.setCashDate(cashDate);
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
		// 메서드 실행
		CashbookDao cashbookDao = new CashbookDao();
		cashbookDao.updateCashbook(cashbook, hashtag);
		
		// 원래있던곳으로 돌아가기(뷰로 돌아가기)
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
	}

}
