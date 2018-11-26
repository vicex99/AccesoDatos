package Controlador;

import java.util.HashMap;

import Modelo.*;
import Util.AccesoDatos;
import Util.DBManager;
import Util.FileManager;
import Vista.Vista;

public class Controller {

	DBManager bbdd;
	FileManager fichero;
	Vista vista;

	private String[] titulos = { "id", "nombre", "descripcion", "mitologia", "caracteristica 2" };

	public void intercambiaDatos() {

		AccesoDatos receptor = vista.getReceptor();
		AccesoDatos emisor = vista.getEmisor();

		HashMap<Integer, Dios> lista;

		lista = emisor.leeDios();
		receptor.subeDios(lista);
	}

	// sacar por pantalla Dios
	public void imprimir() {
		AccesoDatos acceso = vista.getEmisor();
		HashMap<Integer, Dios> impresion = acceso.leeDios();

		vista.imprimirDios(impresion);
	}

	// añadir dios
	public void subir() {
		AccesoDatos acceso = vista.getEmisor();
		acceso.subeDios(vista.cogerDatosDios(this.getTitulos()));
	}

	// eliminar una fila de dioses
	public void eliminar(int id) {
		AccesoDatos acceso = vista.getEmisor();
		acceso.eliminarDios(id);
	}

	// eliminar tabla dioses
	public void eliminarTodos() {
		AccesoDatos acceso = vista.getEmisor();
		acceso.eliminarTodosDioses();
	}

	// titulos para la vista
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
