package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.User;

public class VerPerfilAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
			
			String resultado = "EXITO";
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			request.setAttribute("user", user);
			return resultado;
	}

}
