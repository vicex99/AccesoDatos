package Modelo;

public class Dios {

	private int id;
	private String nombre;
	private String definicion;
	private int mitologia;
	private String caract;

	public Dios(int id, String nombre, String definicion, int i, String caract) {
		
		this.id = id;
		this.nombre = nombre;
		this.definicion = definicion;
		this.mitologia = i;
		this.caract = caract;
	}
	
	public Dios(int num) {
		this(0, null, null, 0, null);
	}
	
	public Dios() {
		
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

	public String getDefinicion() {
		return definicion;
	}

	public void setDefinicion(String definicion) {
		this.definicion = definicion;
	}

	public int getMitologia() {
		return mitologia;
	}
	
	public void setMitologia(int mitologia) {
		this.mitologia = mitologia;
	}

	public String getCaract() {
		return caract;
	}

	public void setCaract(String caract2) {
		this.caract = caract2;
	}
	
	@Override
	public String toString() {
		String str = " - 1 " + nombre + ", 2 " + definicion  + ", 3 " + mitologia + ", 4 " + caract;
		return str;
	}
}
