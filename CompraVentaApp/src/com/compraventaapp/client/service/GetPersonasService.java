package com.compraventaapp.client.service;

import java.util.List;

import com.compraventaapp.client.model.Persona;
import com.compraventaapp.client.service.records.PersonaRecord;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.widgets.grid.ListGrid;

public class GetPersonasService implements AsyncCallback<List<Persona>> {
	
	private ListGrid grid;
	
	public GetPersonasService(ListGrid personasgrid) {
		grid = personasgrid;
	}
	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.getMessage());
	}
	@Override
	public void onSuccess(List<Persona> result) {
		List<Persona> personas = result;
		grid.setData(PersonaRecord.getRecords(personas)); 
	    for (Persona persona : personas) {
	      System.out.println(persona.getNombre());
	    }
	}
	public ListGrid getGrid() {
		return grid;
	}
	public void setGrid(ListGrid grid) {
		this.grid = grid;
	}

}
