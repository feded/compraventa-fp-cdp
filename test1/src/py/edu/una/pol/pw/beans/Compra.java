package py.edu.una.pol.pw.beans;

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


import py.edu.una.pol.pw.beans.Persona.TipoPersona;


@Entity
public class Compra{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="NROFACTURA", length=10)
    private String nroFactura;

    @Column(name="FECHA", nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    @ManyToOne(optional=false)
    @JoinColumn(name="PROVEEDOR", nullable=false)
    private Persona proveedor;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="compra", fetch=FetchType.EAGER)
    private List<CompraDetalle> detalles;
    
    @Transient
    private CompraDetalle detalle;

    public Compra() {
        this.proveedor = new Persona();
        this.proveedor.setTipoPersona(TipoPersona.PROVEEDOR.toInt());
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


    public void setProveedor(Persona proveedor) {
        this.proveedor = proveedor;
    }

    public Persona getProveedor() {
        return this.proveedor;
    }

    public void addDetalle(CompraDetalle d) {
        if (detalles==null) {
            detalles = new ArrayList<CompraDetalle>();
        }
        detalles.add(d);
    }

    public List<CompraDetalle> getDetalles() {
    	if (detalles==null) {
            detalles = new ArrayList<CompraDetalle>();
        }
        return detalles;
    }

    public void setDetalles(List<CompraDetalle> detalles) {
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
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.nroFactura == null && other.nroFactura != null) ||
                (this.nroFactura != null && !this.nroFactura.equals(other.nroFactura))) {
            return false;
        }
        return true;
    }

	public String getNroFactura() {
		return nroFactura;
	}

	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}

	public CompraDetalle getDetalle() {
		if (detalle==null)
			detalle = new CompraDetalle();
		return detalle;
	}

	public void setDetalle(CompraDetalle detalle) {
		this.detalle = detalle;
	}

   
}
