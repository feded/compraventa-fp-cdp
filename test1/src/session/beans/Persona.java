package session.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * Session Bean implementation class Persona
 */
@Stateless
@LocalBean
public class Persona implements PersonaLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext(unitName = "test1")
	private EntityManager mgr= null;
    public Persona() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean guardarPersona(entity.beans.Persona persona) {
		//EntityManager mgr = null;
		try {
			//mgr = emf.createEntityManager();
			//mgr.getTransaction().begin();
			if (persona.getCodPersona() != null) {
				// TODO:ver como editar, probablemente con un query nativo..
				/*mgr.createNativeQuery(
						"update Persona set nombre = ? , apellido = ? , "
								+ "telefono = ? , direccion = ? where codPersona = ? ")
						.setParameter(1, persona.getNombre())
						.setParameter(2, persona.getApellido())
						.setParameter(3, persona.getTelefono())
						.setParameter(4, persona.getDireccion())
						.setParameter(5, persona.getCodPersona())
						.executeUpdate();
			//	mgr.getTransaction().commit();*/
				mgr.merge(persona);
				return true;
			} else {
				mgr.persist(persona);
				//mgr.getTransaction().commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
		//	if (mgr != null)
			//	mgr.close();
		}
	}
   
    @Override
    public boolean eliminarPersona(entity.beans.Persona persona) {
		//EntityManager mgr = null;
		try {
		//	mgr = emf.createEntityManager();
		//	mgr.getTransaction().begin();
			if (persona.getCodPersona() != null) {
				mgr.createNativeQuery(
						"delete from persona where codPersona = ? ")
						.setParameter(1, persona.getCodPersona())
						.executeUpdate();
				//mgr.getTransaction().commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			//if (mgr != null)
			//	mgr.close();
		}
		return false;
	}

	
	


}
