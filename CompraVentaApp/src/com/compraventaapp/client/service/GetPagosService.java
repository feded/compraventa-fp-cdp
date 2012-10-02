package com.compraventaapp.client.service;

import java.util.List;

import com.compraventaapp.client.model.RegistroPago;
import com.compraventaapp.client.service.records.RegistroPagosRecord;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.widgets.grid.ListGrid;

public class GetPagosService implements AsyncCallback<List<RegistroPago>> {
	
	private ListGrid grid;
	
	public GetPagosService(ListGrid pagosgrid) {
		grid = pagosgrid;
	}
	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.getMessage());
	}
	@Override
	public void onSuccess(List<RegistroPago> result) {
		List<RegistroPago> pagos = result;
		grid.setData(RegistroPagosRecord.getRecords(pagos)); 
	}

	public ListGrid getGrid() {
		return grid;
	}
	public void setGrid(ListGrid grid) {
		this.grid = grid;
	}

}
