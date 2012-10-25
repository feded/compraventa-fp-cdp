package session.beans;

import java.util.List;

import javax.ejb.Local;

import entity.beans.Compra;
import entity.beans.Persona;
import entity.beans.Producto;
import entity.beans.RegistroPago;
import entity.beans.Venta;

@Local
public interface ListadosLocal {
	public List<Persona> getClientes();
	public List<Persona> getProveedores();
	public List<Producto> getProductos();
	public List<RegistroPago> getRegistroPagos();
	public List<Venta> getVentas();
	public List<Compra> getCompras();
}
