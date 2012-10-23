package session.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import entity.beans.Persona;
import entity.beans.Persona.TipoPersona;

/**
 * Session Bean implementation class Listados
 */
@Stateless
public class Listados implements ListadosRemote {

    /**
     * Default constructor. 
     */
	@PersistenceContext(unitName = "test1")
	private EntityManager mgr;
	
    public Listados() {
    	
    }
    	
	public List<Persona> getClientes() {
		List<Persona> personas = new ArrayList<Persona>();

		
		try {
			CriteriaBuilder cb = mgr.getCriteriaBuilder();
			CriteriaQuery cq = cb.createQuery(Persona.class);
			Root personaEntity = cq.from(Persona.class);
			Path tipo = personaEntity.get("tipoPersona");
			cq.where(cb.and(cb.equal(tipo, TipoPersona.CLIENTE.toInt())));
			TypedQuery q = mgr.createQuery(cq);
			personas = q.getResultList();
			// mgr.getTransaction().begin();
			// Query qry =
			// mgr.createQuery("select codPersona, numeroDocumento, nombre, apellido, telefono, "
			// +
			// "direccion, tipoPersona, saldoDisponible from persona where tipoPersona = ?",
			// "Clientes");
			// qry.setParameter(1, 1);
			// return qry.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//    			if (mgr != null)
//    				mgr.close();
		}
		return personas;
	}

    	
    


}
