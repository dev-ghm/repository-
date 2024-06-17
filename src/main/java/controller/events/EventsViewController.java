package controller.events;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import together.dao.EventDao;
import together.dao.ParticipantDao;
import together.vo.Event;
import together.vo.Participant;

@WebServlet("/events/*")
public class EventsViewController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("/events/*");
		// int id = Integer.parseInt(request.getParameter("id"));
		try {
//		int id = Integer.parseInt(request.getParameter("id"));
		String uri = request.getRequestURI();
		// System.out.println(uri);
		int id = Integer.parseInt(uri.substring(uri.lastIndexOf("/") + 1));
		// System.out.println(id);
		EventDao eventDao = new EventDao();
		Event event = eventDao.findById(id);
		request.setAttribute("event", event);
		ParticipantDao participantDao = new ParticipantDao();
		List<Participant> participants =participantDao.findByEventId(id);
		request.setAttribute("participants", participants);
		request.getRequestDispatcher("/WEB-INF/view/events/view.jsp").forward(request, response);
		
	} catch (Exception e) {
		e.printStackTrace();
		request.getRequestDispatcher("/WEB-INF/view/events/error.jsp").forward(request, response);
	}
	}
}

