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

	public void intercambiaDatos(AccesoDatos receptor, AccesoDatos emisor) {

		HashMap<Integer, Dios> lista;

		lista = emisor.leeDios();
		receptor.subeDios(lista);
	}

	// sacar por pantalla Dios
	public void imprimir(AccesoDatos acceso, Vista vista) {
		HashMap<Integer, Dios> impresion = acceso.leeDios();

		vista.imprimirDios(impresion);
	}

	// añadir dios
	public void subir(AccesoDatos acceso, Vista vista) {
		acceso.subeDios(vista.cogerDatosDios(this.getTitulos()));
	}

	// eliminar una fila de dioses
	public void eliminar(int id, AccesoDatos acceso, Vista vista) {
		acceso.eliminarDios(id);
	}

	// eliminar tabla dioses
	public void eliminarTodos(AccesoDatos acceso) {
		acceso.eliminarTodosDioses();
	}

	// titulos para la vista
	public String[] getTitulos() {
		return titulos;
	}

	public void setTitulos(String[] titulos) {
		this.titulos = titulos;
	}
}
