package controller.feeds;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import together.dao.EventDao;
import together.dao.FeedDao;
import together.vo.Feed;
import together.vo.User;

@WebServlet("/feeds/write-handle")
public class writeHandleController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String writerId = (String) request.getSession().getAttribute("authuser");
		String title = request.getParameter("title");
		String body = request.getParameter("body");
	
		Feed one = new Feed(0, writerId, title, body, new Date(System.currentTimeMillis()), 0);
		FeedDao feedDao = new FeedDao();
		
		boolean r;
		try {
			r = feedDao.save(one);
		
		if (r) {
			response.sendRedirect(request.getContextPath()+ "/feeds");
		} else {
			response.sendRedirect(request.getContextPath()+ "/feeds/write");
		}
			
		} catch (Exception e) {
			e.printStackTrace();
	
		}
		
	}
}
