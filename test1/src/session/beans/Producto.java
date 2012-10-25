package session.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * Session Bean implementation class Producto
 */
@Stateless
@LocalBean
public class Producto implements ProductoLocal {

    /**
     * Default constructor. 
     */
	
	@PersistenceContext(unitName = "test1")
	private EntityManager mgr;

    public Producto() {
        // TODO Auto-generated constructor stub
    }
 
	@Override
	public boolean guardarProducto(entity.beans.Producto producto) {
		try{
			//mgr = emf.createEntityManager();
			//mgr.getTransaction().begin();
			if (producto.getCodProducto()!=null){
				
				mgr.merge(producto);
				return true;
			}else{
			
				mgr.persist(producto);
				//mgr.getTransaction().commit();
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			//if (mgr!=null) mgr.close();
		}
		
	}
	
	public boolean eliminarProducto(entity.beans.Producto producto) {
		//EntityManager mgr = null;
		try{
		//	mgr = emf.createEntityManager();
			//mgr.getTransaction().begin();
			if (producto.getCodProducto()!=null){
				mgr.createNativeQuery("delete from producto where codProducto = ? ")
						.setParameter(1, producto.getCodProducto())
						.executeUpdate();
				//mgr.getTransaction().commit();
				//mgr.remove(producto);
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			//if (mgr!=null) mgr.close();
		}
		return false;
	
	
}


}
