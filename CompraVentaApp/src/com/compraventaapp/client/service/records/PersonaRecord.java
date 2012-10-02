package com.compraventaapp.client.service.records;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.compraventaapp.client.model.Persona;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class PersonaRecord extends ListGridRecord {
	
	public PersonaRecord() {  
	 
	}  
	  
	public PersonaRecord(String numeroDocumento, String name, String apellido, String direccion, String telefono, BigDecimal saldo) {  
    	setnumeroDocumento(numeroDocumento);
		setNombre(name);  
    	setApellido(apellido);
    	setDireccion(direccion);
    	setTelefono(telefono);
    	setsaldoDisponible(saldo);
	}  
	  
	private void setsaldoDisponible(BigDecimal saldo) {
	
		setAttribute("saldo", saldo);
	}

	private void setTelefono(String telefono) {
		setAttribute("telefono", telefono);
		
	}

	private void setDireccion(String direccion) {
		setAttribute("direccion", direccion);
		
	}

	private void setnumeroDocumento(String numeroDocumento) {
		setAttribute("ciruc", numeroDocumento);
		
	}

	public void setNombre(String nombre){
		setAttribute("nombre", nombre);
	}
	
	public void setApellido(String apellido){
		setAttribute("apellido", apellido);
	}
	
	
	public static PersonaRecord[] getRecords(List<Persona> personas){
		List<PersonaRecord> personaRecords = new ArrayList<PersonaRecord>();
		for (Persona persona: personas){
			personaRecords.add(new PersonaRecord(persona.getNumeroDocumento(),persona.getNombre(), persona.getApellido(), persona.getDireccion(),persona.getTelefono(), persona.getSaldoDisponible()));
		}
		PersonaRecord [] personasRecord = new PersonaRecord[personaRecords.size()];
		for (int i=0; i<personaRecords.size(); i++){
			personasRecord[i] = personaRecords.get(i);
		}
		
		return personasRecord;
	}
	   
}
