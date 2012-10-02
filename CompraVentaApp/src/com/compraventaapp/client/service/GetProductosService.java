package com.compraventaapp.client.service;

import java.util.List;

import com.compraventaapp.client.model.Producto;
import com.compraventaapp.client.service.records.ProductoRecord;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.widgets.grid.ListGrid;

public class GetProductosService implements AsyncCallback<List<Producto>> {
	
	private ListGrid grid;
	
	public GetProductosService(ListGrid productosgrid) {
		grid = productosgrid;
	}
	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.getMessage());
	}
	@Override
	public void onSuccess(List<Producto> result) {
		List<Producto> productos = result;
		grid.setData(ProductoRecord.getRecords(productos)); 
	    for (Producto producto : productos) {
	      System.out.println(producto.getNombre());
	    }
	}
	public ListGrid getGrid() {
		return grid;
	}
	public void setGrid(ListGrid grid) {
		this.grid = grid;
	}


}