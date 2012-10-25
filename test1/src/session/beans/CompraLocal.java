package session.beans;

import javax.ejb.Local;

import entity.beans.Compra;
import entity.beans.Persona;
import entity.beans.Producto;

@Local
public interface CompraLocal {
	public boolean guardarCompra(Compra compra);	
	public Persona getPersona(String id);
	public Producto getProducto(String string);

}
