package Vista;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import Modelo.*;
import Util.AccesoDatos;
import Util.BDHibernateManger;
import Util.DBManager;
import Util.FileManager;
import Util.WebManager;
import Controlador.*;

public class Vista {

	Scanner sc = new Scanner(System.in);
	Controller myControlDios;
	ControllerMitologia myControlMito;

	/*
	 * cuando solo se necesita uno es el emisor el que tiene los datos que se envian
	 * al controller
	 */
	AccesoDatos emisor;
	AccesoDatos receptor;

	/*
	 * Inicializa la vista en consola con el menu No termina hasta que se le da al 0
	 * 
	 */

	String menu = "\n *******" + "\n1  - leer Dioses" + "\n2  - leer Mitologia" + "\n3  - incluir Dioses"
			+ "\n4  - incluir Mitologia" + "\n5 - pasar a otra base de datos" + "\n6 - Eliminar dioses "
			+ "\n7 - Eliminar Mitologias " + "\n8 - Eliminar un dios " + "\n9 - Eliminar una mitologia "
			+ "\n0 - terminar programa" + "para cambiar de eleccion otra tecla cualquier boton" + "\n *******";

	String tiposAccesos = "\n *******" + "\n1  - Ficheros" + "\n2  - Base de datos" + "\n3  - Hibernate" + "\n4  - web" + "\n *******";

	public Vista() {
		myControlDios = new Controller();
		myControlMito = new ControllerMitologia();
	}
	
	/*
	 * Inicializa la vista en consola con el menu No termina hasta que se le da al 0
	 * 
	 */

	public void start() {

		boolean fin = false;
		int eleccion = -1;

		do {
			emisor = newAcceso();

			System.out.println(menu);
			
			switch (sc.nextInt()) {
			case 0:
				System.out.println("\nTERMINANDO PROGRAMA");
				fin = true;
				break;
			// leer dioses
			case 1:
				myControlDios.imprimir(emisor, this);
				break;
			// Leer mitologia
			case 2:
				myControlMito.imprimir(emisor, this);
				break;
			// subir dios
			case 3:
				myControlDios.subir(emisor, this);
				break;
			// subir mitologia
			case 4:
				myControlMito.subir(emisor, this);
				break;
			// pasar de uno a otro
			case 5:
				this.setEmisor(emisor);
				this.setReceptor(newAcceso());
				if (this.getReceptor() == null) {
					break;
				}
				myControlDios.intercambiaDatos(receptor, newAcceso());
				break;
			// Eliminar dioses
			case 6:
				myControlDios.eliminarTodos(emisor);
				break;
			// Eliminar Mitologias
			case 7:
				myControlMito.eliminarTodas(emisor);
				break;
			// Eliminar un dios
			case 8:
				myControlDios.eliminar(this.selectorId(), emisor, this);
				break;
			// Eliminar una mitologia
			case 9:
				myControlMito.eliminar(this.selectorId(), emisor);
				break;
			//actualiza un dios
			case 10:
//				myControlDios.
				break;
			// actualiza una mitologia
			case 11:
				
				break;
			default:
				System.out.println("opcion no encontrada, elige entre las disponibles");
				break;
			}
		} while (!fin);
		sc.close();
	}

	// Pedir datos de Dioses
	public HashMap<Integer, Dios> cogerDatosDios(String[] titulos) {
		HashMap<Integer, Dios> datos = new HashMap<Integer, Dios>();

		Dios newDios = new Dios(0);
		int id = 0;

		for (int i = 0; i < titulos.length; i++) {
			System.out.print(titulos[i] + ": ");
			switch (i) {
			case 0:
				id = sc.nextInt();
				break;
			case 1:
				sc.nextLine();
				newDios.setNombre(sc.nextLine());
				break;
			case 2:
				newDios.setDefinicion(sc.nextLine());
				break;
			case 3:
				newDios.setMitologia(Integer.parseInt(sc.nextLine()));
				break;
			case 4:
				newDios.setCaract(sc.nextLine());
				break;
				
			}
		}

		datos.put(id, newDios);
		return datos;
	}
	
	// Pedir datos de Dioses
		public Dios cogerDatoUnicoDios(String[] titulos) {

			Dios newDios = new Dios(0);
			int id = 0;

			for (int i = 0; i < titulos.length; i++) {
				System.out.print(titulos[i] + ": ");
				switch (i) {
				case 0:
					id = sc.nextInt();
					break;
				case 1:
					sc.nextLine();
					newDios.setNombre(sc.nextLine());
					break;
				case 2:
					newDios.setDefinicion(sc.nextLine());
					break;
				case 3:
					newDios.setMitologia(Integer.parseInt(sc.nextLine()));
					break;
				case 4:
					newDios.setCaract(sc.nextLine());
					break;
					
				}
			}

			return newDios;
		}

	// Pedir datos de Mitologias
	public HashMap<Integer, Mitologia> cogerDatosMitologia(String[] titulos) {
		HashMap<Integer, Mitologia> datos = new HashMap<Integer, Mitologia>();

		Mitologia newMitologia = new Mitologia(0);
		int id = 0;

		for (int i = 0; i < titulos.length - 2; i++) {
			System.out.print(titulos[i] + ": ");
			switch (i) {
			// Id
			case 0:
				id = sc.nextInt();
				break;
			// Nombre
			case 1:
				sc.nextLine();
				newMitologia.setNombre(sc.nextLine());
				break;
			// Descripcion
			case 2:
				newMitologia.setDescripcion(sc.nextLine());
				break;
			}
		}
		datos.put(id, newMitologia);
		return datos;
	}

	// Sacar por consola Dioses
	public void imprimirDios(HashMap<Integer, Dios> impresion) {
		for (Entry<Integer, Dios> jugador : impresion.entrySet()) {
			if (jugador.getKey() != 0) {
				System.out.println("id: " + jugador.getKey());
				System.out.println("nombre: " + jugador.getValue().getNombre());
				System.out.println("definicion: " + jugador.getValue().getDefinicion());
				System.out.println("caracteristica1: " + jugador.getValue().getMitologia());
				System.out.println("caracteristica2: " + jugador.getValue().getCaract() + "\n");
			} else {
				System.out.println("sin datos");
			}
		}
	}

	// Sacar por consola mitologias
	public void imprimirMitologia(HashMap<Integer, Mitologia> impresion) {
		for (Entry<Integer, Mitologia> jugador : impresion.entrySet()) {
			if (jugador.getKey() != 0) {
				System.out.println("id: " + jugador.getKey());
				System.out.println("nombre: " + jugador.getValue().getNombre());
				System.out.println("definicion: " + jugador.getValue().getDescripcion() + "\n");
			} else {
				System.out.println("sin datos");
			}
		}
	}

	// Pide un numero para cuando haya que elegir un id
	private int selectorId() {
		System.out.println("¿Cuál quieres eliminar?");
		int id = sc.nextInt();
		return id;
	}

	// Coger un receptor
	private AccesoDatos newAcceso() {
		System.out.println(" ¿Qué acceso eliges?\n" + tiposAccesos);
		AccesoDatos eleccion = null;

		switch (sc.nextInt()) {
		case 1:
			eleccion = new FileManager();
			break;
		case 2:
			eleccion = new DBManager();
			break;
		case 3:
			eleccion = new BDHibernateManger();
			break;
		case 4:
			eleccion = new WebManager();
			break;
		default:
			System.out.println("Ninguna seleccionada terminando enlace");
			break;
		}
		return eleccion;
	}

	// receptor
	public AccesoDatos getReceptor() {
		return this.receptor;
	}

	private void setReceptor(AccesoDatos datos) {
		this.receptor = datos;
	}

	// emisor
	public AccesoDatos getEmisor() {
		return this.emisor;
	}

	private void setEmisor(AccesoDatos datos) {
		this.emisor = datos;

	}
}
