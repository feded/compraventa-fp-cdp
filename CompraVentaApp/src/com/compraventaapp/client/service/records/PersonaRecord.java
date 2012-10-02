package com.compraventaapp.client.service.records;

import java.util.ArrayList;
import java.util.List;

import com.compraventaapp.client.model.Persona;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class PersonaRecord extends ListGridRecord {
	
	public PersonaRecord() {  
	 
	}  
	  
	public PersonaRecord(String name, String apellido) {  
    	setNombre(name);  
    	setApellido(apellido);  
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
			personaRecords.add(new PersonaRecord(persona.getNombre(), persona.getApellido()));
		}
		PersonaRecord [] personasRecord = new PersonaRecord[personaRecords.size()];
		for (int i=0; i<personaRecords.size(); i++){
			personasRecord[i] = personaRecords.get(i);
		}
		
		return personasRecord;
	}
	   
}
