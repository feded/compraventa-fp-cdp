package com.compraventaapp.client.service;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class SetProductoService implements AsyncCallback<Void> {
	
	
	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.getMessage());
	}

	@Override
	public void onSuccess(Void result) {
		Window.alert("Guardao exitosamente");
		
	}


}