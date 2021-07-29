package ec.edu.ups.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidades.Cliente;
import ec.edu.ups.entidades.Restaurante;

/**
 * Session Bean implementation class RestauranteFacade
 */
@Stateless
public class RestauranteFacade extends AbstractFacade<Restaurante>{

	@PersistenceContext(unitName = "LeonGallard-David-Examen")
	private EntityManager em;
	
    public RestauranteFacade() {
    	super(Restaurante.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	
	public Restaurante buscarPorNombre(String nombre) {
		
		System.out.println("Nombre restaurante: "+nombre);
		Restaurante restaurante=null;
    	String consulta = "Select c From Restaurante c Where c.nombre=:nombre";
    	try {
    		restaurante= (Restaurante) em.createQuery(consulta).setParameter("nombre", nombre).getSingleResult();
    	}catch(Exception e) {
    		System.out.println(">>>Warning (buscarPorNombrerestaurante: )"+e.getMessage());
    	}
    	return restaurante;
    }

}
