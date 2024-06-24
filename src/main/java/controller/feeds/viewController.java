package controller.feeds;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import together.dao.FeedDao;
import together.vo.Feed;

@WebServlet("/feeds/view")
public class viewController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		
		Feed found;
		if(request.getParameter("no") == null) {
			found = null;
		}else {
			int no = Integer.parseInt(request.getParameter("no"));
			
			FeedDao feedDao = new FeedDao();
			
			
				boolean f = feedDao.increaseReadCntByNo(no);
				found = feedDao.findByNo(no);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		request.getRequestDispatcher("/WEB-INF/view/feeds/view.jsp").forward(request, response);
}
}
