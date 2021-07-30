package ec.edu.ups.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.persistence.ForeignKey;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import ec.edu.ups.ejb.ClienteFacade;
import ec.edu.ups.ejb.ReservaFacade;
import ec.edu.ups.ejb.RestauranteFacade;
import ec.edu.ups.entidades.Cliente;
import ec.edu.ups.entidades.Reserva;
import ec.edu.ups.entidades.Restaurante;

@Path("/reservas/")
public class reservas {

	@EJB
	private ClienteFacade ejbCliente; 
	@EJB
	private ReservaFacade ejbReserva;
	@EJB
	private RestauranteFacade ejbRestaurante;
	
	
    public reservas() {
        // TODO Auto-generated constructor stub
    }

    /*@POST
    @Path("/listarCliente")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response listar(@FormParam("cedula") String cedula) {
	
    	Cliente cliente = ejbCliente.buscarPorCedula(cedula);
    	
    	List<Reserva> pedido = new ArrayList<Reserva>();
    	
    	
    	
    	for (Reserva pedidoCabecera : cliente.getReservasCliente()) {
    		
    		Cliente p = new Cliente(cliente.getId(), cliente.getCedula(), cliente.getNombre(), cliente.getApellido(), cliente.getTelefono(), cliente.getDireccion(), cliente.getCorreo());
			Reserva r = new Reserva(pedidoCabecera.getId(), pedidoCabecera.getFecha(), pedidoCabecera.getNumeroPersonas(), p);
			
			pedido.add(r);
		}
    	
    	
    	Jsonb jsonb = JsonbBuilder.create();
    	return Response.status(201).entity(jsonb.toJson(pedido))
    		.header("Access-Control-Allow-Origin", "*")
    		.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
    		.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
    }
    */
    @GET
    @Path("/listarRest")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarRest(@QueryParam("nombre") String nombre) {
	
    	Restaurante restaurante = ejbRestaurante.buscarPorNombre(nombre);
    	
    	List<Reserva> pedido = new ArrayList<Reserva>();
    	
    	
    	
    	for (Reserva pedidoCabecera : restaurante.getReservasRestaurante()) {
    		
    		Restaurante res = new Restaurante(pedidoCabecera.getRestauranteReserva().getId(), pedidoCabecera.getRestauranteReserva().getNombre(), 
    				pedidoCabecera.getRestauranteReserva().getDireccion(), pedidoCabecera.getRestauranteReserva().getTelefono(), 
    				pedidoCabecera.getRestauranteReserva().getAforo());
    		Reserva r = new Reserva(pedidoCabecera.getId(), pedidoCabecera.getFecha(), pedidoCabecera.getNumeroPersonas(), res);
			
			pedido.add(r);
		}
    	
    	
    	Jsonb jsonb = JsonbBuilder.create();
    	return Response.status(201).entity(jsonb.toJson(pedido))
    		.header("Access-Control-Allow-Origin", "*")
    		.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
    		.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
    }
    
  
    
    @GET
    @Path("/listarCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarCliente(@QueryParam("cedula") String cedula) {
    	
    	System.out.println(cedula);

    	Cliente cliente = ejbCliente.buscarPorCedula(cedula);

    	List<Reserva> pedido = new ArrayList<Reserva>();
    	
    	Restaurante rest= new Restaurante();
    	

    	for (Reserva reservac: cliente.getReservasCliente()) {
    		   		
    	Cliente p = new Cliente(cliente.getId(), cliente.getCedula(), cliente.getNombre(), cliente.getApellido(), 
    			cliente.getTelefono(), cliente.getDireccion(), cliente.getCorreo());
    	
    		//Restaurante res = new Restaurante(rest.getNombre());
    	
    		Restaurante res = new Restaurante(rest.getNombre());
			Reserva r = new Reserva(reservac.getFecha(), reservac.getNumeroPersonas(),p,res);
			
			pedido.add(r);
		}
    	
    	
    	Jsonb jsonb = JsonbBuilder.create();
    	return Response.status(201).entity(jsonb.toJson(pedido))
    		.header("Access-Control-Allow-Origin", "*")
    		.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
    		.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
    	
    }
    @GET
    @Path("/listarReservCl")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarReservCl(@QueryParam("cedula") String cedula) {
    	System.out.println(cedula);

    	Reserva reserva = ejbReserva.buscarPorCedula(cedula);
    	
    	List<Reserva> reser = new ArrayList<Reserva>();
    	
		
    	
    	
    	Jsonb jsonb = JsonbBuilder.create();
    	return Response.status(201).entity(jsonb.toJson(reserva))
    		.header("Access-Control-Allow-Origin", "*")
    		.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
    		.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
    	
    }
    
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list_reserva_cliente")
	public Response listReservaCliente(@QueryParam("cedula") String cedula) {
		List<Reserva> lista = new ArrayList<Reserva>();
    	Jsonb jsonb = JsonbBuilder.create();

		try {
			lista = ejbReserva.listReservaCliente(cedula);
			return Response.status(201).entity(jsonb.toJson(lista))
		    		.header("Access-Control-Allow-Origin", "*")
		    		.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
		    		.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			return Response.serverError().build();
		}

	}

}