package javaplanner;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dhtmlx.planner.DHXEv;
import com.dhtmlx.planner.DHXEvent;
import com.dhtmlx.planner.DHXEventsManager;
import com.dhtmlx.planner.DHXStatus;

import models.Reservation;
import models.Salle;
import models.User;
import persistances.DAO;

public class EventsManager extends DHXEventsManager {

	public EventsManager(HttpServletRequest request) {
		super(request);
	}

	@Override
	public Iterable getEvents() {
		ArrayList<CustomEvent> events = new ArrayList<>();
		try {
			int idSalle = Integer.parseInt((String) this.getRequest().getSession().getAttribute("idSalle"));
			User user = (User) this.getRequest().getSession().getAttribute("user");
			List<Reservation> reservations = DAO.getInstance().getAllItems(new Reservation());
			reservations
				.stream()
				.filter(r -> r.getSalle().getId().equals(idSalle))
				.forEach(r -> {
					DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
					CustomEvent ev = new CustomEvent();
					ev.setId(r.getId());
					ev.setStart_date(r.getDateResa_Deb().format(df));
					ev.setEnd_date(r.getDateResa_Fin().format(df));
					ev.setText(r.getUser().getPseudo());
					if (!r.getUser().getPseudo().equals(user.getPseudo())) {
						ev.setReadonly(true);
						ev.setTextColor(CustomEvent.BLACK);
						ev.setColor(CustomEvent.GREY);
					}else{
						ev.setTextColor(CustomEvent.WHITE);
						ev.setColor(CustomEvent.DEFAULT_BACKGROUND);
					}
					events.add(ev);
				});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return events;
		}
	}

	@Override
	public DHXStatus saveEvent(DHXEv event, DHXStatus status) {
			DAO dao = DAO.getInstance();
			Reservation r = new Reservation();
			r.setId(event.getId());
			LocalDateTime start_date = LocalDateTime.ofInstant(event.getStart_date().toInstant(), ZoneId.systemDefault());
			LocalDateTime end_date = LocalDateTime.ofInstant(event.getEnd_date().toInstant(), ZoneId.systemDefault());
			start_date = ajusterDate(start_date);
			end_date = ajusterDate(end_date);
			User user = (User)this.getRequest().getSession().getAttribute("user");
			switch(status){
				case INSERT:
					r.setId(null);
					r.setDateResa_Deb(start_date);
					r.setDateResa_Fin(end_date);
					r.setSalle(dao.getItemById(new Salle(), this.getRequest().getSession().getAttribute("idSalle")));
					r.setUser(dao.getItemsWhere(new User(), "pseudo", user.getPseudo()).get(0));
					r = dao.create(r);
					event.setId(r.getId());
					break;
				case UPDATE:
					r.setDateResa_Deb(start_date);
					r.setDateResa_Fin(end_date);
					r.setSalle(dao.getItemById(new Salle(), this.getRequest().getSession().getAttribute("idSalle")));
					r.setUser(dao.getItemsWhere(new User(), "pseudo", user.getPseudo()).get(0));
					dao.createorUpdateItem(r);
					break;
				case DELETE:
					dao.deleteItem(r);
					break;
				default:
			}
		return status;
	}


	@Override
	public DHXEv createEvent(String id, DHXStatus status) {
		return new DHXEvent();
	}

	private LocalDateTime ajusterDate(LocalDateTime date) {
		if (date.getMinute() < 15){
			date = date.withMinute(0);
		}else if (date.getMinute() <45){
			date = date.withMinute(30);
		}else{
			date = date.withMinute(0);
			date = date.withHour(date.getHour() + 1);
		}
		return date;
	}
}