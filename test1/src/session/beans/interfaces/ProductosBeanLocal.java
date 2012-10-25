package session.beans.interfaces;

import javax.ejb.Local;

import entity.beans.Producto;

@Local
public interface ProductosBeanLocal {
	public boolean guardarProducto(Producto producto);
}
