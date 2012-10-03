package com.compraventaapp.client;

import java.io.InputStream;
import java.util.List;

import com.compraventaapp.client.model.Persona;
import com.compraventaapp.client.model.Producto;
import com.compraventaapp.client.model.RegistroPago;
import com.compraventaapp.client.service.GetPagosService;
import com.compraventaapp.client.service.UploadFileService;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	void getClientes( AsyncCallback<List<Persona>> callback) throws Exception;
	void getPagos(AsyncCallback<List<RegistroPago>> callback)throws Exception;
	void getProveedores( AsyncCallback<List<Persona>> callback) throws Exception;
	void getProductos(AsyncCallback<List<Producto>> callback) throws Exception;
	void guardarProducto(Producto producto, AsyncCallback<Void> callback)throws Exception;
	void guardarPersona(Persona persona, AsyncCallback<Void> callback)throws Exception;
}
