package uo.sdi.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;
import uo.sdi.acciones.*;
import uo.sdi.common.StatusInfo;
import uo.sdi.model.User;

public class Controlador extends javax.servlet.http.HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Map<String, Map<String, Accion>> mapaDeAcciones; // <rol, <opcion, objeto Accion>>
	private Map<String, Map<String, Map<String, String>>> mapaDeNavegacion; // <rol, <opcion, <resultado, JSP>>>

	public void init() throws ServletException {  
		crearMapaAcciones();
		crearMapaDeJSP();
    }
	

	public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws IOException, ServletException {
		
		String opcion, resultado, jspSiguiente;
		Accion accion;
		String rolAntes, rolDespues;
		
		try {
			
			opcion=req.getServletPath().replace("/","");
				
			rolAntes=obtenerRolDeSesion(req);
			
			accion=buscarAccionParaOpcion(rolAntes, opcion);
				
			resultado=accion.execute(req,res);
				
			rolDespues=obtenerRolDeSesion(req);
			
			jspSiguiente=buscarJSPSegun(rolDespues, opcion, resultado);

			req.setAttribute("jspSiguiente", jspSiguiente);

		} catch(Exception e) {
			if (req.getSession() != null
					&& ((User) req.getSession().getAttribute("user")) != null) {
				jspSiguiente = "/principal.jsp";
				req.setAttribute("status", new StatusInfo() {{
					value = "Se ha producido un error inesperado";
				}});
			} else {
				req.getSession().invalidate();
				jspSiguiente = "/error.jsp";
			}
			Log.error("Se ha producido alguna excepción no manejada [%s]", e);
		}
			
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jspSiguiente); 
			
		dispatcher.forward(req, res);			
	}			
	
	
	private String obtenerRolDeSesion(HttpServletRequest req) {
		HttpSession sesion=req.getSession();
		if (sesion.getAttribute("user")==null)
			return "PUBLICO";
		else
			return "REGISTRADO";
	}

	// Obtiene un objeto accion en funci�n de la opci�n
	// enviada desde el navegador
	private Accion buscarAccionParaOpcion(String rol, String opcion) {
		
		Accion accion=mapaDeAcciones.get(rol).get(opcion);
		Log.debug("Elegida acción [%s] para opción [%s] y rol [%s]",accion,opcion,rol);
		return accion;
	}
	
	
	// Obtiene la p�gina JSP a la que habr� que entregar el
	// control el funci�n de la opci�n enviada desde el navegador
	// y el resultado de la ejecuci�n de la acci�n asociada
	private String buscarJSPSegun(String rol, String opcion, String resultado) {
		
		String jspSiguiente=mapaDeNavegacion.get(rol).get(opcion).get(resultado);
		Log.debug("Elegida página siguiente [%s] para el resultado [%s] tras realizar [%s] con rol [%s]",jspSiguiente,resultado,opcion,rol);
		return jspSiguiente;		
	}
		
		
	private void crearMapaAcciones() {
		
		mapaDeAcciones=new HashMap<String,Map<String,Accion>>();
		
		Map<String,Accion> mapaPublico=new HashMap<String,Accion>();
		//Inicio de sesión
		mapaPublico.put("validarse", new ValidarseAction());
		//Acceso al listado de viajes
		mapaPublico.put("listarViajesPublico", new ListarViajesPublicoAction());
		//Intento de registro
		mapaPublico.put("registrarse" , new RegistrarseAction());
		
		mapaDeAcciones.put("PUBLICO", mapaPublico);
		
		Map<String,Accion> mapaRegistrado=new HashMap<String,Accion>();
		mapaRegistrado.put("verPerfil", new VerPerfilAction());
		mapaRegistrado.put("principal", new PrincipalAction());
		mapaRegistrado.put("accesoModificarDatos" , new AccesoModificarDatosAction());
		mapaRegistrado.put("accesoCrearViaje" , new AccesoCrearViajeAction());
		mapaRegistrado.put("modificarDatos", new ModificarDatosAction());
		mapaRegistrado.put("cerrarSesion", new CerrarSesionAction());
		mapaRegistrado.put("listarViajesRegistrado", new ListarViajesRegistradoAction());
		mapaRegistrado.put("listarViajesUsuario", new ListarViajesUsuarioAction());
		mapaRegistrado.put("crearViaje", new CrearViajeAction());		
		mapaRegistrado.put("solicitarPlaza" , new SolicitarPlazaAction());
	    mapaRegistrado.put("verSolicitudes", new VerSolicitudesAction());
	    mapaRegistrado.put("aceptarSolicitud", new AceptarSolicitudAction());
	    mapaRegistrado.put("rechazarSolicitud", new RechazarSolicitudAction());
		mapaRegistrado.put("cancelarSolicitud", new CancelarSolicitudAction());
		mapaRegistrado.put("cancelarViaje", new CancelarViajeAction());
		mapaRegistrado.put("verPromotor", new VerPromotorAction());
		mapaRegistrado.put("modificarViaje", new ModificarViajeAction());
		mapaRegistrado.put("accesoModificarViaje", new AccesoModificarViajeAction());
		
		
		
		mapaDeAcciones.put("REGISTRADO", mapaRegistrado);
	}
	
	
	private void crearMapaDeJSP() {
				
		mapaDeNavegacion=new HashMap<String,Map<String, Map<String, String>>>();

		// Crear mapas auxiliares vacíos
		Map<String, Map<String, String>> opcionResJSP=new HashMap<String, Map<String, String>>();
		Map<String, String> resJSP=new HashMap<String, String>();

		// Mapa de navegación del público
		resJSP.put("FRACASO","/login.jsp");
		opcionResJSP.put("validarse", resJSP);
		//Listar viajes disponibles
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/listaViajesPublico.jsp");
		opcionResJSP.put("listarViajesPublico", resJSP);
		//Registrarse
		resJSP = new HashMap <String, String>();
		resJSP.put("FRACASO", "/registrarse.jsp");
		opcionResJSP.put("registrarse" , resJSP);
		//Cerrar sesión
		resJSP = new HashMap <String, String>();
		resJSP.put("EXITO", "/login.jsp");
		opcionResJSP.put("cerrarSesion" , resJSP);
		
		mapaDeNavegacion.put("PUBLICO",opcionResJSP);
		
		// Crear mapas auxiliares vacíos
		opcionResJSP=new HashMap<String, Map<String, String>>();
		resJSP=new HashMap<String, String>();
		
		// Mapa de navegación de usuarios registrados
		resJSP.put("EXITO","/principal.jsp");
		opcionResJSP.put("registrarse", resJSP);
		
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/principal.jsp");
		opcionResJSP.put("principal", resJSP);
		
		//Acceder a la vista de modificación de datos del perfil
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/modificarDatos.jsp");
		opcionResJSP.put("accesoModificarDatos", resJSP);
		
		//Acceder a la pantalla de creación de un viaje
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/registroViaje.jsp");
		opcionResJSP.put("accesoCrearViaje", resJSP);
		
		//Iniciar sesión
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/principal.jsp");
		resJSP.put("PRUEBA","/prueba.jsp");
		opcionResJSP.put("validarse", resJSP);
		
		//Modificar datos del usuario
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/principal.jsp");
		resJSP.put("FRACASO", "/modificarDatos.jsp");
		opcionResJSP.put("modificarDatos", resJSP);
		
		//Ver perfil de usuario
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/perfil.jsp");
		opcionResJSP.put("verPerfil", resJSP);
		
		//Lista de viajes en los que un usuario registrado puede solicitar plaza
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/listaViajesRegistrado.jsp");
		opcionResJSP.put("listarViajesRegistrado", resJSP);
		
		//Creación de un viaje
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/principal.jsp");
		resJSP.put("FRACASO", "/registroViaje.jsp");
		opcionResJSP.put("crearViaje", resJSP);
		
		//Lista de viajes en los que el usuario o ha tenido alguna implicación
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/listaViajesUsuario.jsp");
		opcionResJSP.put("listarViajesUsuario", resJSP);
		
		//Solicitar plaza en un viaje
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/principal.jsp");
		opcionResJSP.put("solicitarPlaza", resJSP);
		
		//Ver solicitudes realizadas en un viaje propio
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/solicitudes.jsp");
		opcionResJSP.put("verSolicitudes", resJSP);
		
		//Aceptar solicitud de un usuario a un viaje
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/principal.jsp");
		resJSP.put("FRACASO", "/errorViaje.jsp");
		opcionResJSP.put("aceptarSolicitud", resJSP);
		
		//Rechazar solicitud de un usuario a un viaje
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/principal.jsp");
		opcionResJSP.put("rechazarSolicitud", resJSP);
		
		//Cancelar una solicitud realizada por el usuario
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/principal.jsp");
		opcionResJSP.put("cancelarSolicitud", resJSP);
		
		//Cancelar un viaje
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/principal.jsp");
		opcionResJSP.put("cancelarViaje", resJSP);
		
		//Ver el perfil del promotor del viaje seleccionado
		resJSP = new HashMap <String, String>();
		resJSP.put("EXITO", "/perfilPromotor.jsp");
		opcionResJSP.put("verPromotor" , resJSP);
		
		//Acceder a la pantalla de modificación de viajes
		resJSP = new HashMap <String, String>();
		resJSP.put("EXITO", "/modificarViaje.jsp");
		opcionResJSP.put("accesoModificarViaje" , resJSP);
		
		//Modificar un viaje
		resJSP = new HashMap <String, String>();
		resJSP.put("EXITO", "/principal.jsp");
		resJSP.put("FRACASO", "/errorViaje.jsp");
		opcionResJSP.put("modificarViaje" , resJSP);
		
		
		mapaDeNavegacion.put("REGISTRADO",opcionResJSP);
	}
			
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

		doGet(req, res);
	}

}