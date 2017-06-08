package uo.sdi.tests;

import org.junit.*;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class Sesion4Tests {

	@Before
    public void prepare() {
        setBaseUrl("http://localhost:8280/RodriguezAlvarezAlejandro");
    }

//    @Test
//    public void testListarViajes() {
//        beginAt("/");  // Navegar a la URL
//        assertLinkPresent("listarViajesPublico");  // Comprobar que existe el hipervínculo
//        clickLink("listarViajesPublico"); // Seguir el hipervínculo
//
//        assertTitleEquals("ShareMyTrip - Listado de viajes");  // Comprobar título de la página
//
//        // La base de datos contiene 2 viajes tal y como se entrega
//        assertTextPresent("Origen");
//        assertTextPresent("Destino");
//        assertTextPresent("Plazas libres");
//        
//    }
//
////    @Test
////    public void testIniciarSesionConExito() {
////        beginAt("/");  // Navegar a la URL
////        setTextField("login", "usuario1"); // Rellenar primer campo de formulario
////        setTextField("password" , "usuario1");
////        submit(); // Enviar formulario
////        assertTitleEquals("ShareMyTrip - Página principal del usuario");  // Comprobar título de la página
////        assertTextPresent("Gestión de usuario"); // Comprobar cierto texto está presente
////        assertTextPresent("Gestión de viajes");
////    }
//
//    @Test
//    public void testIniciarSesionConExitoConQueryString() {
//    	// Rellenando el formulario HTML
//        beginAt("/validarse?login=usuario2&password=usuario2");  // Navegar a la URL
//        assertTitleEquals("ShareMyTrip - Página principal del usuario");  // Comprobar título de la página
//        assertTextPresent("Gestión de usuario"); // Comprobar cierto texto está presente
//        assertTextPresent("Gestión de viajes");
//    }
//    
//    @Test
//    public void testIniciarSesionSinExito() {
//    	// Rellenando el formulario HTML
//        beginAt("/");  // Navegar a la URL
//        setTextField("login", "yoNoExisto"); // Rellenar primer campo de formulario
//        submit(); // Enviar formulario
//        assertTitleEquals("ShareMyTrip - Inicie sesión");  // Comprobar título de la página
//    }
//    
//    @Test
//    public void testIniciarSesionContraseñaIncorrecta() {
//    	// Rellenando el formulario HTML
//        beginAt("/");  // Navegar a la URL
//        setTextField("login", "usuario1"); // Rellenar primer campo de formulario
//        setTextField("password" , "dddd");
//        submit(); // Enviar formulario
//        assertTextPresent("Nombre de usuario y/o contraseña incorrectos");
//        assertTitleEquals("ShareMyTrip - Inicie sesión");  // Comprobar título de la página
//    }
//    
//    @Test
//    public void testRegistrarseContraseñaIncorrecta(){
//    	beginAt("/registrarse.jsp");
//    	setTextField("login", "Alex");
//    	setTextField("nombre", "Alejandro");
//    	setTextField("apellidos", "Rodriguez Alvarez");
//    	setTextField("email", "alex@mail.com");
//    	setTextField("password", "4444");
//    	setTextField("password2", "5555555");
//        submit(); // Enviar formulario
//        assertTextPresent("contraseñas no coinciden");  
//    }
//    
//    @Test
//    public void testRegistrarseEmailInvalido(){
//    	beginAt("/registrarse.jsp");
//    	setTextField("login", "Alex");
//    	setTextField("nombre", "Alejandro");
//    	setTextField("apellidos", "Rodriguez Alvarez");// Rellenar primer campo de formulario
//    	setTextField("email", "mail.com");
//    	setTextField("password", "5555555");
//    	setTextField("password2", "5555555");
//        submit(); // Enviar formulario
//        assertTextPresent("email incorrecto"); 
//    }
//    
//    @Test
//    public void testRegistrarViaje(){
//    	beginAt("/");
//    	setTextField("login", "usuario1"); // Rellenar primer campo de formulario
//        setTextField("password" , "usuario1");
//        submit();
//        clickLink("crearViaje");
//        assertTitleEquals("ShareMyTrip - Registrar nuevo viaje"); 
//    	setTextField("direccionSalida" , "Argañosa");
//    	setTextField("ciudadSalida" , "Oviedo");
//    	setTextField("provinciaSalida" , "Asturias");
//    	setTextField("paisSalida" , "España");
//    	setTextField("cpSalida" , "33013");
//    	setTextField("latSalida" , "69");
//    	setTextField("lonSalida" , "15");
//    	setTextField("fechaSalida" , "22/03/2018 19:30");
//    	
//    	setTextField("direccionLlegada" , "Serrano");
//    	setTextField("ciudadLlegada" , "Madrid");
//    	setTextField("provinciaLlegada" , "Madrid");
//    	setTextField("paisLlegada" , "España");
//    	setTextField("cpLlegada" , "43521");
//    	setTextField("latLlegada" , "19");
//    	setTextField("lonLlegada" , "25");
//    	setTextField("fechaLlegada" , "22/03/2018 22:30");
//    	
//    	setTextField("fechaCierre" , "21/03/2018 19:30");
//    	setTextField("coste" , "25");
//    	setTextField("comentarios" , "  ");
//    	setTextField("plazasTotales" , "3");
//    	setTextField("plazasDisponibles" , "2");
//    	
//    	submit();
//    	
//    	assertTitleEquals("ShareMyTrip - Página principal del usuario");  // Comprobar título de la página
//    	assertTextPresent("Gestión de usuario"); // Comprobar cierto texto está presente
//        assertTextPresent("Gestión de viajes");
//    }
//    
//    @Test
//    public void testSolicitarPlaza(){
//    	beginAt("/");
//    	setTextField("login", "usuario1"); // Rellenar primer campo de formulario
//        setTextField("password" , "usuario1");
//        submit();
//        clickLink("listarViajesRegistrado");
//        clickLink("solicitarPlaza");
//        assertTitleEquals("ShareMyTrip - Página principal del usuario");  // Comprobar título de la página
//        assertTextPresent("solicitada con éxito");
//    	
//    }
    
    //TEST DE LA PRUEBA DE AUTORÍA
    @Test
    public void testIniciarSesionConExito() {
        beginAt("/");  // Navegar a la URL
        setTextField("login", "usuario1"); // Rellenar primer campo de formulario
        setTextField("password" , "usuario1");
        submit(); // Enviar formulario
        assertTitleEquals("ShareMyTrip - Página principal del usuario");  // Comprobar título de la página
        assertTextPresent("Gestión de usuario"); // Comprobar cierto texto está presente
        assertTextPresent("Gestión de viajes");
    }
//    
    @Test
    public void testIniciarSesionConExitoALaSegunda() {
        beginAt("/");  // Navegar a la URL
        setTextField("login", "usuario1"); // Rellenar primer campo de formulario
        setTextField("password" , "asadad");
        submit(); // Enviar formulario
        assertTextPresent("Nombre de usuario y/o contraseña incorrectos");
        assertTitleEquals("ShareMyTrip - Inicie sesión"); 
        setTextField("login", "usuario1"); // Rellenar primer campo de formulario
        setTextField("password" , "usuario1");
        assertTitleEquals("ShareMyTrip - PRUEBA");  // Comprobar título de la página
        assertTextPresent("contraseña es");
       
    }
//    
    @Test
    public void testIniciarSesionMalBienBien() {
        beginAt("/");  // Navegar a la URL
        setTextField("login", "usuario1"); // Rellenar primer campo de formulario
        setTextField("password" , "asadad");
        submit(); // Enviar formulario
        assertTextPresent("Nombre de usuario y/o contraseña incorrectos");
        assertTitleEquals("ShareMyTrip - Inicie sesión"); 
        setTextField("login", "usuario1"); // Rellenar primer campo de formulario
        setTextField("password" , "usuario1");
        assertTitleEquals("ShareMyTrip -PRUEBA");  // Comprobar título de la página
        assertTextPresent("contraseña es"); // Comprobar cierto texto está presente
        clickLink("principal");
        assertTitleEquals("ShareMyTrip - Página principal del usuario");  // Comprobar título de la página
        assertTextPresent("Gestión de usuario"); // Comprobar cierto texto está presente
        assertTextPresent("Gestión de viajes");
        clickLink("cerrarSesion");
        assertTitleEquals("ShareMyTrip - Inicie sesión"); 
        setTextField("login", "usuario1"); // Rellenar primer campo de formulario
        setTextField("password" , "usuario1");
        assertTextPresent("Gestión de usuario"); // Comprobar cierto texto está presente
        assertTextPresent("Gestión de viajes");
    }

}