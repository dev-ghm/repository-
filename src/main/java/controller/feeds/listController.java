package controller.feeds;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import together.dao.FeedDao;


@WebServlet("/feeds")
public class listController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int p = req.getParameter("p") == null ? 1 : Integer.parseInt(req.getParameter("p"));
			int size = 10; // 페이지당 표시할 개수
			int start = size * (p - 1) + 1;
			int end = size * p;
			
			FeedDao feedDao = new FeedDao();
			int count = feedDao.countAll();
			int totalPages = count/size + (count%size >0 ? 1: 0); 
			
			req.setAttribute("feeds", feedDao.findAll());
			req.setAttribute("totalPages", totalPages);
			req.setAttribute("currentPage",  p);
			req.getRequestDispatcher("/WEB-INF/view/feeds/list.jsp").forward(req, resp);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
