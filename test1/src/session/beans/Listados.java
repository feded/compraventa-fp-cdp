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


import entity.beans.Compra;
import entity.beans.Persona;
import entity.beans.Persona.TipoPersona;
import entity.beans.Producto;
import entity.beans.RegistroPago;
import entity.beans.Venta;

/**
 * Session Bean implementation class Listados
 */
@Stateless
public class Listados implements ListadosLocal {

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
	
	public List<Persona> getProveedores() {
		List<Persona> personas = new ArrayList<Persona>();

		
		try {
			CriteriaBuilder cb = mgr.getCriteriaBuilder();
			CriteriaQuery cq = cb.createQuery(Persona.class);
			Root personaEntity = cq.from(Persona.class);
			Path tipo = personaEntity.get("tipoPersona");
			cq.where(cb.and(cb.equal(tipo, TipoPersona.PROVEEDOR.toInt())));
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

	@Override
	public List<Producto> getProductos() {
		List<Producto> productos = new ArrayList<Producto>();
	
		try{
		
			CriteriaBuilder cb = mgr.getCriteriaBuilder();
			CriteriaQuery cq = cb.createQuery(Producto.class);
			Root personaEntity = cq.from(Producto.class);
			TypedQuery q = mgr.createQuery(cq);
			productos = q.getResultList();

		}catch (Exception e) {
			e.printStackTrace();
		}finally{
		//	if (mgr!=null) mgr.close();
		}
		return productos;
	}

	@Override
	public List<RegistroPago> getRegistroPagos() {
		List<RegistroPago> registroPagos = new ArrayList<RegistroPago>();
		
		try{
		
			CriteriaBuilder cb = mgr.getCriteriaBuilder();
			CriteriaQuery cq = cb.createQuery(RegistroPago.class);
			Root personaEntity = cq.from(RegistroPago.class);
			TypedQuery q = mgr.createQuery(cq);
			registroPagos = q.getResultList();

		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			//if (mgr!=null) mgr.close();
		}
		return registroPagos;
	}

	@Override
	public List<Venta> getVentas() {
		List<Venta> ventas = new ArrayList<Venta>();
		
		try{
			
			CriteriaBuilder cb = mgr.getCriteriaBuilder();
			CriteriaQuery cq = cb.createQuery(Venta.class);
			Root personaEntity = cq.from(Venta.class);
			TypedQuery q = mgr.createQuery(cq);
			ventas = q.getResultList();

		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			//if (mgr!=null) mgr.close();
		}
		return ventas;
	}

	

	@Override
	public List<Compra> getCompras() {
		List<Compra> compras = new ArrayList<Compra>();

		try{
			
			CriteriaBuilder cb = mgr.getCriteriaBuilder();
			CriteriaQuery cq = cb.createQuery(Compra.class);
			Root personaEntity = cq.from(Compra.class);
			TypedQuery q = mgr.createQuery(cq);
			compras= q.getResultList();

		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			//if (mgr!=null) mgr.close();
		}
		return compras;
	}

    	
    


}
