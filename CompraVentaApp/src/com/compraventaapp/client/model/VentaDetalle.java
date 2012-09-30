package com.compraventaapp.client.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class VentaDetalle implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Integer id;

    @ManyToOne(optional=false)
    @JoinColumn(name="PRODUCTO", nullable=false)
    private Producto producto;

    @Column(name="CANTIDAD", nullable=false)
    private Integer cantidad;

    @Column(name="PRECIO", nullable=false)
    private Double precio;
    
    @ManyToOne
    @JoinColumn(name="VENTA", nullable=false)
    private Venta venta;

    public VentaDetalle() {
        this.id = null;
        this.precio = null;
        this.producto = new Producto();
        this.venta = null;
        this.cantidad = null;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    
    public void setProducto(Producto p) {
        this.producto = p;
    }
    
    public Producto getProducto() {
        return this.producto;
    }
    
    public void setCantidad(Integer cant) {
        this.cantidad = cant;
    }
    
    public Integer getCantidad() {
        return this.cantidad;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    public Double getPrecio() {
        return this.precio;
    }
    
    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
    public Venta getVenta() {
        return this.venta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof VentaDetalle)) {
            return false;
        }
        VentaDetalle other = (VentaDetalle) object;
        if ((this.id == null && other.id != null) || 
                (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }


}
