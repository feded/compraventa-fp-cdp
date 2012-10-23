package entity.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import entity.beans.Persona.TipoPersona;

@Entity
public class Venta implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="NROFACTURA", length=10)
    private String nroFactura;

    @Column(name="FECHA", nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    @ManyToOne(optional=false)
    @JoinColumn(name="CLIENTE", nullable=false)
    private Persona cliente;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="venta", fetch=FetchType.EAGER)
    private List<VentaDetalle> detalles;
    
    
    @Transient
    private VentaDetalle detalle;

    public Venta() {
        this.cliente = new Persona();
        this.cliente.setTipoPersona(TipoPersona.CLIENTE.toInt());
        this.detalles = null;
        this.fecha = null;
        this.nroFactura = null;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public Date getFecha() {
        return this.fecha;
    }
    
    public String getNroFactura() {
		return nroFactura;
	}

	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}
    
    public void setCliente(Persona cliente) {
        this.cliente = cliente;
    }
    
    public Persona getCliente() {
        return this.cliente;
    }
    
    public void addDetalle(VentaDetalle d) {
        if (detalles==null) {
            detalles = new ArrayList<VentaDetalle>();
        }
        detalles.add(d);
    }
    
    public List<VentaDetalle> getDetalles() {
    	if (detalles==null) {
            detalles = new ArrayList<VentaDetalle>();
        }
        return detalles;
    }
    
    public void setDetalles(List<VentaDetalle> detalles) {
        this.detalles = detalles;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nroFactura != null ? nroFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.nroFactura == null && other.nroFactura != null) || 
                (this.nroFactura != null && !this.nroFactura.equals(other.nroFactura))) {
            return false;
        }
        return true;
    }
    
    public VentaDetalle getDetalle() {
		if (detalle==null)
			detalle = new VentaDetalle();
		return detalle;
	}

	public void setDetalle(VentaDetalle detalle) {
		this.detalle = detalle;
	}


}
