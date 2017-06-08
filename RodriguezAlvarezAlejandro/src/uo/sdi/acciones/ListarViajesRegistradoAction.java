package uo.sdi.acciones;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import uo.sdi.persistence.ApplicationDao;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.SeatDao;
import alb.util.log.Log;

public class ListarViajesRegistradoAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		List<Trip> viajes;
		List<Trip> viajesDisponibles = new ArrayList<Trip>();
		HttpSession session = request.getSession();
		Long id = ((User) session.getAttribute("user")).getId();
		Date date = Calendar.getInstance().getTime();
		SeatDao seat = PersistenceFactory.newSeatDao();
		ApplicationDao app = PersistenceFactory.newApplicationDao();
		
		try {
			viajes=PersistenceFactory.newTripDao().findAll();
			for(Trip t : viajes){
				if(t.getAvailablePax() > 0 
						&& t.getClosingDate().after(date) 
						&& t.getDepartureDate().after(date)
						&& !(t.getPromoterId().equals(id))
						&& !(t.getStatus().equals(TripStatus.CANCELLED))){
					
					Long ids [] = {id, t.getId()};
					Seat s = seat.findByUserAndTrip(id, t.getId());
					Application a = app.findById( ids);
					
					if (a == null && s == null){
						viajesDisponibles.add(t);
					}
				}
			}
			request.setAttribute("listaViajes", viajesDisponibles);
			Log.debug("Obtenida lista de viajes conteniendo [%d] viajes", viajesDisponibles.size());
		}
		catch (Exception e) {
			Log.error("Algo ha ocurrido obteniendo lista de viajes");
		}
		return "EXITO";
	}

}
