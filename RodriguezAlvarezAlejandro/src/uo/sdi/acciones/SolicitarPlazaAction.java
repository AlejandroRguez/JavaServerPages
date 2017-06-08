package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.common.StatusInfo;
import uo.sdi.model.Application;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;

public class SolicitarPlazaAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		Long id =Long.parseLong(request.getParameter("id"));
		User user = (User) request.getSession().getAttribute("user");
		Application a = new Application();
		a.setTripId(id);
		a.setUserId(user.getId());
		PersistenceFactory.newApplicationDao().save(a);
		request.setAttribute("status", new StatusInfo() {{
			value = "Plaza solicitada con éxito";
		}});
		
		return "EXITO";
	}

}
