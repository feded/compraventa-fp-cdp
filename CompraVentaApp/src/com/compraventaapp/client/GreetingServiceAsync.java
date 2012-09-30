package com.compraventaapp.client;

import java.util.List;

import com.compraventaapp.client.model.Persona;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	void getClientes( AsyncCallback<List<Persona>> callback) throws Exception;
}
