package ec.edu.ups.rest;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ec.edu.ups.ejb.ClienteFacade;
import ec.edu.ups.ejb.ReservaFacade;
import ec.edu.ups.ejb.RestauranteFacade;
import ec.edu.ups.entidades.Cliente;
import ec.edu.ups.entidades.Reserva;
import ec.edu.ups.entidades.Restaurante;

@Path("/crear/")
public class crear {
	
	@EJB
	private ClienteFacade ejbCliente; 
	@EJB
	private ReservaFacade ejbReserva;
	@EJB
	private RestauranteFacade ejbResturante;

	public crear() {
		// TODO Auto-generated constructor stub
	}
	
	/*@POST
    @Path("/registrar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response registrar(@FormParam("cedula") String cedula, @FormParam("nombre") String nombre, @FormParam("apellido") String apellido
    		, @FormParam("telefono") String telefono, @FormParam("direccion") String direccion, @FormParam("correo")String correo){
		
		Cliente cliente = ejbCliente.buscarPorCedula(cedula);
		System.out.println(cedula);
        Jsonb jsonb = JsonbBuilder.create();

        if(cliente != null){
        	System.out.println("Usuario Ya esta creado en la base intente con otro");
        	
        }else{
        	String ok ="Se hizo bien";
        	
        	cliente.setCedula(cedula);
        	cliente.setNombre(nombre);
        	cliente.setApellido(apellido);
        	cliente.setTelefono(telefono);
        	cliente.setDireccion(direccion);
        	cliente.setCorreo(correo);
			ejbCliente.create(cliente);
            //personaFacade.edit(persona);
            return Response.ok(jsonb.toJson(ok)).
            		header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
					.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
 
        }
        return Response.status(404).entity("Usuario no creado").build();

    }*/
	
	@POST
    @Path("/registro")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
	public Response registro(@FormParam("cedula") String cedula, @FormParam("nombre") String nombre, @FormParam("apellido") String apellido
	    		, @FormParam("telefono") String telefono, @FormParam("direccion") String direccion, @FormParam("correo")String correo){
			
		System.out.println(cedula);
        Jsonb jsonb = JsonbBuilder.create();
		if(nombre!=null && nombre.equals("")!=true) {
    		Cliente persona2= new Cliente(cedula, nombre, apellido, telefono, direccion, correo);
        	try {
        		String ok="Usuario creado";
        		
        		ejbCliente.create(persona2);
        		return Response.ok(jsonb.toJson(ok)).
                		header("Access-Control-Allow-Origin", "*")
    					.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
    					.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
        	}catch (Exception e) {
                return Response.status(500).entity("Error al registrar usuario " + e).build();

			}
        	        	
    	}
    	return Response.ok("Usuario creado").build();
    }
	
	@POST
    @Path("/crearrestaurante")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
	public Response crearrestaurante(@FormParam("nombre") String nombre, @FormParam("direccion") String direccion
	    		, @FormParam("telefono") String telefono, @FormParam("aforo") Integer aforo){
			
		System.out.println(nombre);
        Jsonb jsonb = JsonbBuilder.create();
        
		if(nombre!=null && nombre.equals("")!=true) {
			
			Restaurante rest= new Restaurante(nombre, direccion, telefono, aforo);        	
    		try {
        		String ok="Restaurante creado";
        		
        		ejbResturante.create(rest);
        		return Response.ok(jsonb.toJson(ok)).
                		header("Access-Control-Allow-Origin", "*")
    					.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
    					.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
        	}catch (Exception e) {
                return Response.status(500).entity("Error al registrar usuario " + e).build();

			}
        	        	
    	}
    	return Response.ok("restaurante response").build();
    }
	
	@POST
    @Path("/creareserva")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
	public Response creareserva(@FormParam("nombre") String nombreRestaurante,@FormParam("cedula") String cedula,
			@FormParam("numeroPersonas") Integer numeroPersonas, @FormParam("fechaIngreso")String fechaIngreso){
		
		System.out.println("Si llega a crear reserva");
		 
		//Restaurante resturante = null;
		//Cliente cliente = null;
		//Reserva reserva = null;
        Jsonb jsonb = JsonbBuilder.create();
			Restaurante restaurante = ejbResturante.buscarPorNombre(cedula);
			Cliente cliente = ejbCliente.buscarPorCedula(nombreRestaurante);
			//System.out.println("NO se encontro lo necesario");
			System.out.println(cliente);
			System.out.println(restaurante);
		
		if (cliente != null && restaurante != null) {
			
			System.out.println(cliente);
			System.out.println(restaurante);
			
			int capacidad = restaurante.getAforo() - numeroPersonas;
			
			if (capacidad > 1) {
				
				System.out.println("Hasta aqui"+fechaIngreso);
				
				//SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				 //Date dataFormateada = formato.parse(fechaIngreso); 
				
				//DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
				//LocalDate fecha = LocalDate.parse(fechaIngreso, formato); 
				//System.out.println(fecha);
				
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat formato =  new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				
				//cal.setTime(formato.p
				
				//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				//Date date = sdf.parse(fechaIngreso);
				//Calendar cal = Calendar.getInstance();
				//cal.setTime(date);
				
				restaurante.setAforo(capacidad);
				ejbResturante.edit(restaurante);
				
				
				Reserva reserva = new Reserva(cal, numeroPersonas, cliente,restaurante );
				
				ejbReserva.create(reserva);
				return Response.ok(jsonb.toJson(reserva)).
                		header("Access-Control-Allow-Origin", "*")
    					.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
    					.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
				
			}else {
                return Response.status(500).entity("No hay suficiente espacio en este restaurante " ).build();

			}
			
		}else {
			
	    	return Response.ok("No hay cliente ni restaurante").build();
	    	


	}
	}
}
