package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import together.dao.EventDao;

@WebServlet("/search")
public class SearchCountroller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String q = request.getParameter("q");
		if(q == null) {
			request.getRequestDispatcher("/WEB-INF/view/search-form.jsp").forward(request, response);
		}else {
			try {
				EventDao eventDao = new EventDao(); 
				
					request.setAttribute("foundEvents" ,eventDao.findByTitleLikeOrDescriptionLike(q));
					request.getRequestDispatcher("/WEB-INF/view/search-result.jsp").forward(request, response);					
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
