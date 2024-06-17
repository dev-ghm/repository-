package controller.events;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import together.dao.EventDao;
import together.dao.ParticipantDao;
import together.vo.Event;
import together.vo.Participant;
import together.vo.User;

@WebServlet("/events/new-handle")
public class EventsNewHandleController extends  HttpServlet{
		
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			EventDao eventDao = new EventDao();
			User authUser = (User) request.getSession().getAttribute("authUser");
		
			String tag = request.getParameter("tag");
			String title = request.getParameter("title");
			int capacity = Integer.parseInt(request.getParameter("capacity"));
			String description = request.getParameter("description");
			Date openat = Date.valueOf(request.getParameter("openat"));
			int sportid = Integer.parseInt(request.getParameter("sportid"));
			
			
			Date registerat = new Date(System.currentTimeMillis());
			String hostid = authUser.getId();
			int attendee = 1;
			int id = eventDao.generateKey();
			
			Event event = new Event(id, title, description, tag, sportid, hostid, openat, capacity, attendee,
					registerat);
			
		
			boolean r = eventDao.save(event);
			
			if(r) {
				Participant participant = new Participant();
				participant.setUserid(hostid);
				participant.setJoinat(registerat);
				participant.setEventid(id);
				
				ParticipantDao participantDao = new ParticipantDao();
				participantDao.save(participant);
				
			}
			
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
