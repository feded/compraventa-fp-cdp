package com.compraventaapp.client;

import java.io.InputStream;
import java.util.List;

import com.compraventaapp.client.model.Persona;
import com.compraventaapp.client.model.Producto;
import com.compraventaapp.client.model.RegistroPago;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("compraVentaService")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	List<Persona> getClientes() throws Exception;
	List<RegistroPago> getPagos()throws Exception;
	List<Persona> getProveedores() throws Exception;
	List<Producto> getProductos() throws Exception;
	void guardarPersona(Persona persona) throws Exception;
	void subirArchivo(InputStream file) throws Exception;
	void guardarProducto(Producto producto) throws Exception;;
}
