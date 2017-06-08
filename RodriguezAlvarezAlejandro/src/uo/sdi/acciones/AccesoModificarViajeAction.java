package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Trip;
import uo.sdi.persistence.PersistenceFactory;

public class AccesoModificarViajeAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		Trip trip = PersistenceFactory.newTripDao().findById(
				((Long.parseLong(request.getParameter("id")))));
		request.setAttribute("trip", trip);
		return "EXITO";

	}

}
