package Modelo;

import java.util.HashMap;

public class Mitologia {

	int id;
	String nombre;
	String descripcion;
//	HashMap<Integer, Dios> diosesPropios;
	
	public Mitologia() {
		
	}

	public Mitologia(int id, String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Mitologia(int num) {
		this(0, null, null);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

//	public HashMap<Integer, Dios> getDiosesPropios() {
//		return diosesPropios;
//	}
//	
//	public void setDiosesPropios(HashMap<Integer, Dios> diosesPropios) {
//		this.diosesPropios = diosesPropios;
//	}
//	
//	public void setDios(int id, Dios dios){
//		this.diosesPropios.put(id, dios);
//	}
}
