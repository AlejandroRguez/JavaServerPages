package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.common.StatusInfo;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.persistence.ApplicationDao;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.TripDao;
import alb.util.log.Log;

public class RechazarSolicitudAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
String resultado = "EXITO";
		
		Long userId = Long.parseLong(request.getParameter("userId"));
		Long tripId = Long.parseLong(request.getParameter("tripId"));
		Long [] ids = {userId, tripId};
		SeatDao sdao = PersistenceFactory.newSeatDao();
		ApplicationDao adao =  PersistenceFactory.newApplicationDao();
		Seat s = sdao.findById(ids);
		Application a = adao.findById(ids);
		TripDao tdao = PersistenceFactory.newTripDao();
		Trip t = tdao.findById(tripId);
		if(s != null){
				s.setStatus(SeatStatus.EXCLUDED);
				sdao.update(s);
				t.setAvailablePax(t.getAvailablePax() + 1);
				tdao.update(t);
				Log.info("Se ha rechazado la solicitud correctamente");
				request.setAttribute("status", new StatusInfo() {{
					value = "La solicitud ha sido rechazada";
				}});
			
		}else if (a != null){
				Seat seat = new Seat();
				seat.setTripId(a.getTripId());
				seat.setUserId(a.getUserId());
				seat.setStatus(SeatStatus.EXCLUDED);
				adao.delete(ids);
				sdao.save(seat);
				Log.info("Se ha rechazado la solicitud correctamente");
				request.setAttribute("status", new StatusInfo() {{
					value = "La solicitud ha sido rechazada";
				}});
			
		}
			
		return resultado;
	}

	

}
