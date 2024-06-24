package controller.sports;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import together.dao.SportDao;

@WebServlet("/sports")
public class SportsController extends HttpServlet{

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int p = req.getParameter("p") == null ? 1 : Integer.parseInt(req.getParameter("p"));
			int size = 10; // 페이지당 표시할 개수
//			int start = size * p - size + 1;
			int start = size * (p - 1) + 1;
			int end = size * p;
			
			SportDao sportDao = new SportDao();
			int count = sportDao.countAll();
			int totalPages = count/size + (count%size >0 ? 1: 0); 
			
			req.setAttribute("sports", sportDao.findAll(start, end));
			req.setAttribute("totalPages", totalPages);
			req.setAttribute("currentPage",  p);
			req.getRequestDispatcher("/WEB-INF/view/sports/list.jsp").forward(req, resp);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
