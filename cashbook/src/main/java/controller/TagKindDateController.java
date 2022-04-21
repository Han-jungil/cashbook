package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HashtagDao;

/**
 * Servlet implementation class TagDateController
 */
@WebServlet("/TagKindDateController")
public class TagKindDateController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	// 요청값 분석(C)
	String startDate = request.getParameter("startDate");
	String endDate = request.getParameter("endDate");
	String kind = request.getParameter("kind");
	
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
