package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.common.StatusInfo;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.persistence.ApplicationDao;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.TripDao;

public class CancelarViajeAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
	
		String resultado = "EXITO";
		TripDao tdao = PersistenceFactory.newTripDao();
		SeatDao sdao = PersistenceFactory.newSeatDao();
		ApplicationDao adao = PersistenceFactory.newApplicationDao();
		Trip trip = tdao.findById(Long.parseLong(request.getParameter("id")));
		List<Seat> seats = sdao.findAll();
		List<Application> apps = adao.findByTripId(trip.getId());
		for (Seat s : seats){
			if(s.getTripId().equals(trip.getId())){
				s.setStatus(SeatStatus.EXCLUDED);
				sdao.update(s);
			}
		}
		for(Application a : apps){
			Long [] ids = {a.getUserId(), a.getTripId()};
			Seat s = new Seat();
			s.setStatus(SeatStatus.EXCLUDED);
			s.setTripId(a.getTripId());
			s.setUserId(a.getUserId());
			sdao.save(s);
			adao.delete(ids);
		}
		trip.setStatus(TripStatus.CANCELLED);
		tdao.update(trip);
		Log.info("Se ha cancelado el viaje");
		request.setAttribute("status", new StatusInfo() {{
			value = "El viaje se ha cancelado correctamente";
		}});
		
		return resultado;
	}

}
