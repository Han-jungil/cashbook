package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HashtagDao;

/**
 * Servlet implementation class TagCategoryController
 */
@WebServlet("/TagCategoryController")
public class TagCategoryController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청값 분석(c)
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		String tag = request.getParameter("tag");
		
		// 디버깅
		System.out.println(cashbookNo);
		System.out.println(tag);
		
		// 메서드 실행 모델값 출력(M)
		HashtagDao hashtagDao = new HashtagDao();
		Map<String,Object> list = hashtagDao.selectTagCategoryList(cashbookNo, tag);
		request.setAttribute("list", list);
		
		// 디버깅
		System.out.println("TagCategoryController listSize : " + list.size());
		
		// 원래있던곳으로 돌아가기(뷰로 돌아가기(V))
		request.getRequestDispatcher("/WEB-INF/view/TagCategoryController.jsp").forward(request, response);
		
	}

}
