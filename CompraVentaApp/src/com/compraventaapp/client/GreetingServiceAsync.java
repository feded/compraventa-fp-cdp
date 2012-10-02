package com.compraventaapp.client;

import java.util.List;

import com.compraventaapp.client.model.Persona;
import com.compraventaapp.client.model.RegistroPago;
import com.compraventaapp.client.service.GetPagosService;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	void getClientes( AsyncCallback<List<Persona>> callback) throws Exception;
	void getPagos(AsyncCallback<List<RegistroPago>> callback)throws Exception;
}
