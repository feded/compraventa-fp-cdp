package com.compraventaapp.client.service.records;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.compraventaapp.client.model.RegistroPago;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class RegistroPagosRecord extends ListGridRecord {
	
	public RegistroPagosRecord(){
		
	}
	
	public RegistroPagosRecord(String codPago, String codPersona, Date fecha, BigDecimal monto, int estado, String tipo){
		setCodPago(codPago);
		setCodPersona(codPersona);
		setFecha(fecha);
		setMonto(monto);
		setEstado(estado);
		setTipo(tipo);
		
	}

	private void setTipo(String tipo) {
		setAttribute("tipoPago", tipo);
		
	}

	private void setEstado(int estado) {
		setAttribute("estado", estado);
		
	}

	private void setMonto(BigDecimal monto) {
		setAttribute("monto", monto);
		
	}

	private void setFecha(Date fecha) {
		setAttribute("fecha", fecha);
		
	}

	private void setCodPersona(String codPersona) {
		setAttribute("codPersona" , codPersona);
		
	}

	private void setCodPago(String codPago) {
		setAttribute("codPago", codPago);
		
	}
	
	
	public static RegistroPagosRecord[] getRecords(List<RegistroPago> records){
		List<RegistroPagosRecord> pagosRecords = new ArrayList<RegistroPagosRecord>();
		for (RegistroPago pago: records){
			pagosRecords.add(new RegistroPagosRecord(pago.getCodPago(), pago.getCodPersona(), pago.getFecha(), pago.getMonto(), pago.getEstado(), pago.getTipo()));
		}
		RegistroPagosRecord [] registroPagosRecords = new RegistroPagosRecord[pagosRecords.size()];
		for (int i=0; i<pagosRecords.size(); i++){
			registroPagosRecords[i] = pagosRecords.get(i);
		}
		
		return registroPagosRecords;
	}

}
