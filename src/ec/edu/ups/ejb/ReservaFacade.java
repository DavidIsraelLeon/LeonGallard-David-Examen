package ec.edu.ups.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import ec.edu.ups.entidades.Cliente;
import ec.edu.ups.entidades.Reserva;
import ec.edu.ups.entidades.Restaurante;

/**
 * Session Bean implementation class ReservaFacade
 */
@Stateless
public class ReservaFacade extends AbstractFacade<Reserva>{

	@PersistenceContext(unitName = "LeonGallard-David-Examen")
	private EntityManager em;
	
    public ReservaFacade() {
    	super(Reserva.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	
	public Reserva buscarPorCedula(String cedula) {
		
		System.out.println("Nombre cedula: "+cedula);
		Reserva reserva=null;
    	String consulta = "select c.NOMBRE, r.NUMEROPERSONAS, rest.AFORO,rest.NOMBRE as NombreRestaurante from reserva r\n" + 
    			"left join cliente c on c.id=r.clientereserva_id\n" + 
    			"left join restaurante rest on rest.ID=r.RESTAURANTERESERVA_ID"
    			+ "WHERE c.cedula=:cedula";
    	try {
    		reserva= (Reserva) em.createQuery(consulta).setParameter("cedula",cedula).getSingleResult();
    		System.out.println("Devolviendo consulta"+reserva);

    	}catch(Exception e) {
    		System.out.println(">>>Warning (buscarPorNombrerestaurante: )"+e.getMessage());
    	}
    	return reserva;
    }
	
	public List<Reserva> listReservaCliente(String cedula)throws SQLException {
		System.out.println("cedula en reserva facade");
		List<Reserva> lista = new ArrayList<Reserva>();
		String sql = "SELECT restaurante_id FROM Reserva res, Cliente cli"
				+ "WHERE cli.cedula=:cedula AND res.clientereserva_id=cli.id";
		lista = em.createQuery(sql, Reserva.class).setParameter("cedula", cedula).getResultList();
		System.out.println(lista);
		return lista;
	}

}
