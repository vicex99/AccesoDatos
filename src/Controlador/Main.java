package Controlador;

import Modelo.DBManager;
import Modelo.FileManager;
import Vista.Vista;

public class Main {

	public static void main(String[] args) {

//		inicializacion
		DBManager bbdd = new DBManager();
		FileManager fichero = new FileManager();
		Controller contr = new Controller();
		ControllerMitologia cotrMitologia = new ControllerMitologia();
		Vista vista = new Vista();

//		controlador conoce a vistas
		contr.setVista(vista);
		cotrMitologia.setVista(vista);

//		vista conoce a controlador
		vista.setController(contr);
		vista.setController(cotrMitologia);

		// inicializar programa
		vista.start();
	}
}
