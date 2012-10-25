package session.beans;

import javax.ejb.Local;

import entity.beans.Producto;

@Local
public interface ProductoLocal {
	//public boolean guardarProducto(Producto producto);

	public boolean guardarProducto(Producto producto);
	public boolean eliminarProducto(entity.beans.Producto producto);
}
