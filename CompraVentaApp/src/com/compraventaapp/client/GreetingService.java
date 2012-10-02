package com.compraventaapp.client;

import java.util.List;

import com.compraventaapp.client.model.Persona;
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
}
