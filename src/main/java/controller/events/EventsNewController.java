package controller.events;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import together.dao.SportDao;
import together.vo.Sport;


@WebServlet("/events/new")
public class EventsNewController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String type = request.getParameter("type");
			SportDao sportDao = new SportDao();
			List<Sport> sports = sportDao.findByType(type);
			request.setAttribute("sports", sports);
			request.getRequestDispatcher("/WEB-INF/view/events/new.jsp").forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/view/events/error.jsp").forward(request, response);
		}
		
		
	}
}
