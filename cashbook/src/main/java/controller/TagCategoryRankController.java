package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HashtagDao;


@WebServlet("/TagCategoryRankController")
public class TagCategoryRankController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청값 받기
		String tag = request.getParameter("tag");
		
		System.out.println("TagCategoryRankController tag : " + tag);
		
		// 모델값 뽑기
		HashtagDao hashtagDao = new HashtagDao();
		List<HashMap<String, Object>> list = hashtagDao.selectTagCategoryRankList(tag);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/view/TagCategoryRankController.jsp").forward(request, response);
		
		}

}
