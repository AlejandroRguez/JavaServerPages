package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.common.StatusInfo;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.UserDao;
import alb.util.log.Log;

public class ModificarDatosAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String resultado = "EXITO";
		
		String nuevoNombre=request.getParameter("nombre");
		String nuevoApellido=request.getParameter("apellidos");
		String nuevoEmail=request.getParameter("email");
		String contraseña=request.getParameter("password");
		String contraseñaNueva=request.getParameter("passwordNueva");
		String contraseñaNueva2=request.getParameter("passwordNueva2");
		
		HttpSession session=request.getSession();
		User usuario=((User)session.getAttribute("user"));
		
		if( nuevoNombre.equals("") || nuevoApellido.equals("")
				   || nuevoEmail.equals("") || contraseña.equals("") 
				   || contraseñaNueva2.equals("")){
			Log.info("Algunos campos obligatorios están vacíos");
			request.setAttribute("status", new StatusInfo() {{
				value = "Algunos campos obligatorios están vacíos";
			}});
			return "FRACASO";
}
		
		if (!usuario.getPassword().equals(contraseña)){
			request.setAttribute("status", new StatusInfo() {{
				value = "Contraseña actual incorrecta";
			}});
			resultado = "FRACASO";
			Log.info("Contraseña actual incorrecta");
		}	else if(!contraseñaNueva.equals(contraseñaNueva2)){
			request.setAttribute("status", new StatusInfo() {{
				value = "Las contraseñas no coinciden";
			}});
			resultado = "FRACASO";
			Log.info( "Las contraseñas nuevas no coinciden");
			
		} 	else  if (!comprobarEmail (nuevoEmail)){
			request.setAttribute("status", new StatusInfo() {{
				value = "E-mail incorrecto";
			}});
			resultado = "FRACASO";
			Log.info("E-mail incorrecto");
			 
		 } else {
			 
			 UserDao dao = PersistenceFactory.newUserDao();
			 
			 usuario.setEmail(nuevoEmail);
			 usuario.setName(nuevoNombre);
			 usuario.setSurname(nuevoApellido);
			 usuario.setPassword(contraseñaNueva);
			 dao.update(usuario);
			 request.setAttribute("user", usuario);		 
			 request.setAttribute("status", new StatusInfo() {{
					value = "Sus datos han sido modificados";
				}});
		 }
		
		return resultado;
	}
	
	private boolean comprobarEmail (String email){
		boolean resultado = true;
		if (email == null
				|| !email.contains("@")
				|| email.startsWith("@")
				|| email.endsWith("@"))
						resultado = false;
		return resultado;
					
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
