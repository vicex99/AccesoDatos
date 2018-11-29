package Controlador;

import java.util.HashMap;

import Modelo.Mitologia;
import Util.AccesoDatos;
import Util.DBManager;
import Util.FileManager;
import Vista.Vista;

public class ControllerMitologia {

	DBManager bbdd;
	FileManager fichero;
	Vista vista;

	private String[] titulos = { "id", "nombre", "descripcion", "caracteristica 1", "caracteristica 2" };

	public void intercambiaDatos(AccesoDatos emisor, AccesoDatos receptor) {

		HashMap<Integer, Mitologia> lista;

		lista = emisor.leeMitologia();
		receptor.subeMitologia(lista);
	}

	// Sacar por pantalla Mitologia
	public void imprimir(AccesoDatos acceso, Vista vista) {
		HashMap<Integer, Mitologia> impresion = acceso.leeMitologia();

		vista.imprimirMitologia(impresion);
	}

	// Añadir Mitologia
	public void subir(AccesoDatos acceso, Vista vista) {
		acceso.subeMitologia(vista.cogerDatosMitologia(this.getTitulos()));
	}

	// Eliminar una mitologia
	public void eliminarTodas(AccesoDatos acceso) {
		acceso.eliminarTodasMitologias();
	}

	// Eliminar mitologias
	public void eliminar(int id, AccesoDatos acceso) {
		acceso.eliminarMitologia(id);
	}

	// Titulos para la vista
	public String[] getTitulos() {
		return titulos;
	}

	public void setTitulos(String[] titulos) {
		this.titulos = titulos;
	}

	// Conexiones MVC
	public void setBBDD(DBManager bbdd) {
		this.bbdd = bbdd;
	}

	public void setFichero(FileManager fichero) {
		this.fichero = fichero;
	}

	public void setVista(Vista vista) {
		this.vista = vista;
	}
}
