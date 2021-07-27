package ec.edu.ups.rest;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ec.edu.ups.ejb.ClienteFacade;
import ec.edu.ups.ejb.ReservaFacade;
import ec.edu.ups.ejb.RestauranteFacade;
import ec.edu.ups.entidades.Cliente;
import ec.edu.ups.entidades.Restaurante;


@Path("/buscar/")
public class buscar {

	public buscar() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	@EJB
	private ClienteFacade ejbCliente; 
	@EJB
	private ReservaFacade ejbReserva;
	@EJB
	private RestauranteFacade ejbResturante;
	
	
	/***
	 * 
	 * BUSCAR cliente por numero de cedula
	 * @param cedula
	 * @return
	 */
	@GET
    @Path("/cliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cliente(@QueryParam("cedula") String cedula) {
	
	Jsonb jsonb = JsonbBuilder.create();
	System.out.println("Cedula" + cedula);
	
	//List<Cliente> list = new ArrayList<Cliente>();
	
	Cliente cli =ejbCliente.buscarPorCedula(cedula);

	
	//Cliente cli = ejbCliente.buscarPorCedula(cedula);
	
	//list.add(cli);
	//System.out.println(list);
	
	return Response.ok(jsonb.toJson(cli))
	.header("Access-Control-Allow-Origin", "*")
	.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
    }
	
	/***
	 * BUSCAR RESTAURANTE POR NOMBRE
	 * @param nombre
	 * @return
	 */
	@GET
    @Path("/restaurante")
    @Produces(MediaType.APPLICATION_JSON)
    public Response restaurante(@QueryParam("nombre") String nombre) {
	Jsonb jsonb = JsonbBuilder.create();
	
	Restaurante rest  = ejbResturante.buscarPorNombre(nombre);
	
	return Response.ok(jsonb.toJson(rest))
    		.header("Access-Control-Allow-Origin", "*")
			.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
			.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
    }
	
}
