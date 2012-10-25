package session.beans;

import java.util.List;

import javax.ejb.Local;

import entity.beans.Persona;
import entity.beans.Producto;

@Local
public interface ListadosLocal {
	public List<Persona> getClientes();
	public List<Persona> getProveedores();
	public List<Producto> getProductos();
}
