package session.beans;

import java.math.BigDecimal;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import entity.beans.CompraDetalle;
import entity.beans.Persona;
import entity.beans.Producto;

/**
 * Session Bean implementation class Compra
 */
@Stateless
@LocalBean
public class Compra implements CompraLocal {

    /**
     * Default constructor. 
     */
	
	@PersistenceContext(unitName = "test1")
	private EntityManager em;
    public Compra() {
    
    }
    
    @Override
    public boolean guardarCompra(entity.beans.Compra compra) {
		//EntityManager em = emf.createEntityManager();
        try {
            // Se inicia la transaccion
          //  em.getTransaction().begin();
          //  em.persist(compra);
            // Por cada detalle se actualiza la existencia del producto en
            // cuestion aumentan su existencia en la cantidad comprada
            for (CompraDetalle  cd : compra.getDetalles()) {
                Producto p = em.find(Producto.class,
                        cd.getProducto().getCodProducto());
                if (cd.getCantidad()>=0) {
                    p.setCantidad(p.getCantidad().add(new BigDecimal(cd.getCantidad())));
                    em.merge(p);
                } else {
                    throw new Exception("No existe cantidad suficiente "
                            + "para el Produto[" + p.getCodProducto() + "]");
                }
                // se aumenta el saldo del proveeedor
                Persona prov = em.find(Persona.class, compra.getProveedor().getCodPersona());
                Double vendido = cd.getCantidad() * cd.getPrecio();
                prov.setSaldoDisponible(prov.getSaldoDisponible().add(new BigDecimal(vendido)));
                em.merge(prov);
            }
            // se confirma la transaccion
            //em.getTransaction().commit();
            em.persist(compra);
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            
            e.printStackTrace();
            return false;
        } finally {
            //em.close();
        }
	}
    public Producto getProducto(String id) {
		//EntityManager em = emf.createEntityManager();
		//em.getTransaction().begin();
		Producto p = em.find(Producto.class, id);
		//em.getTransaction().commit();
		//em.close();
		return p;
	}
    public Persona getPersona(String id) {
		//EntityManager em = emf.createEntityManager();
		//em.getTransaction().begin();
		Persona p = em.find(Persona.class, id);
		//em.getTransaction().commit();
		//em.close();
		return p;
	}

	
	
	


}
