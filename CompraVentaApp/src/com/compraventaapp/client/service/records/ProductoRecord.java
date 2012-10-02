package com.compraventaapp.client.service.records;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import com.compraventaapp.client.model.Producto;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class ProductoRecord extends ListGridRecord {
	
	public ProductoRecord() {  
	 
	}  
	  
	public ProductoRecord(String name, String descripcion, BigDecimal precio, BigDecimal cantidad) {  
		setNombre(name);  
    	setDescripcion(descripcion);
    	setPrecio(precio);
    	setCantidad(cantidad);
    
	}  
	  
	private void setPrecio(BigDecimal precio) {
	
		setAttribute("precio", precio);
	}

	private void setCantidad(BigDecimal cantidad) {
		
		setAttribute("cantidad", cantidad);
	}

	private void setDescripcion(String descripcion) {
		setAttribute("descripcion", descripcion);
		
	}

	public void setNombre(String nombre){
		setAttribute("nombre", nombre);
	}
	
	
	public static ProductoRecord[] getRecords(List<Producto> productos){
		List<ProductoRecord> productoRecords = new ArrayList<ProductoRecord>();
		for (Producto producto: productos){
			productoRecords.add(new ProductoRecord(producto.getNombre(), producto.getDescripcion(), producto.getPrecio(), producto.getCantidad()));
		}
		ProductoRecord [] productosRecord = new ProductoRecord[productoRecords.size()];
		for (int i=0; i<productoRecords.size(); i++){
			productosRecord[i] = productoRecords.get(i);
		}
		
		return productosRecord;
	}
	
	public static Producto getProducto(ProductoRecord rec){
		Producto p = new Producto();
		p.setNombre(rec.getAttribute("nombre"));
		p.setCodProducto(rec.getAttribute("codProducto"));
		p.setDescripcion(rec.getAttribute("descripcion"));
		p.setCantidad(new BigDecimal(rec.getAttributeAsDouble("cantidad").doubleValue()));
		p.setPrecio(new BigDecimal (rec.getAttributeAsDouble("precio").doubleValue()));
		return p;
	}
	   
}
