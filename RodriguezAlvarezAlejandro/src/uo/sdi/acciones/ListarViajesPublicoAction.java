package uo.sdi.acciones;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Trip;
import uo.sdi.persistence.PersistenceFactory;
import alb.util.log.Log;

public class ListarViajesPublicoAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		List<Trip> viajes;
		List<Trip> viajesDisponibles = new ArrayList<Trip>();
		Date date = Calendar.getInstance().getTime();

		try {
			viajes = PersistenceFactory.newTripDao().findAll();
			for (Trip t : viajes) {
				if (t.getAvailablePax() > 0 && t.getClosingDate().after(date)
						&& t.getDepartureDate().after(date)) {
					viajesDisponibles.add(t);
				}
			}
			request.setAttribute("listaViajes", viajesDisponibles);
			Log.debug("Obtenida lista de viajes conteniendo [%d] viajes",
					viajes.size());
		} catch (Exception e) {
			Log.error("Algo ha ocurrido obteniendo lista de viajes");
		}
		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
