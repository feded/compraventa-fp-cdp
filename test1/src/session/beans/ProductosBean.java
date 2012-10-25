package session.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.beans.Producto;

import session.beans.interfaces.ProductosBeanLocal;

/**
 * Session Bean implementation class ProductosBean
 */
@Stateless
public class ProductosBean implements ProductosBeanLocal {

	
	@PersistenceContext(unitName = "test1")
	private EntityManager mgr;
	
    public ProductosBean() {
     	
    }
    
    public boolean guardarProducto(Producto producto) {
		try{
//			mgr.getTransaction().begin();
			if (producto.getCodProducto()!=null){
				//TODO:ver como editar, probablemente con un query nativo..
//				mgr.createNativeQuery("update Producto set cantidad = ? , descripcion = ? , " +
//						"nombre = ? , precio = ? where codProducto = ? ")
//						.setParameter(1, producto.getCantidad())
//						.setParameter(2, producto.getDescripcion())
//						.setParameter(3, producto.getNombre())
//						.setParameter(4, producto.getPrecio())
//						.setParameter(5, producto.getCodProducto())
//						.executeUpdate();
				mgr.merge(producto);
//				mgr.getTransaction().commit();
				return true;
			}else{
				mgr.persist(producto);
//				mgr.getTransaction().commit();
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			//if (mgr!=null) mgr.close();
		}
		
	}

}
