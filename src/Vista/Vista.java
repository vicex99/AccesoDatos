package Vista;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import Modelo.*;
import Util.AccesoDatos;
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

	String tiposAccesos = "\n *******" + "\n1  - Ficheros" + "\n2  - Base de datos" + "\n3  - Hibernate" + "\n *******";

	/*
	 * Inicializa la vista en consola con el menu No termina hasta que se le da al 0
	 * 
	 */

	public void start() {

		boolean fin = false;
		int eleccion = -1;

		do {
			System.out.println(tiposAccesos);
			try {
				switch (sc.nextInt()) {
				case 1:
					emisor = new DBManager();
					System.out.println(menu);
					eleccion = sc.nextInt();
					break;
				case 2:
					emisor = new FileManager();
					System.out.println(menu);
					eleccion = sc.nextInt();
					break;
				case 3:
					emisor = new BDHibernateManger();
					System.out.println(menu);
					eleccion = sc.nextInt();
					break;
				default:
					System.err.println("ERROR al introducir los datos que no tienen valor, inserte los indicados");
					break;
				}
			} catch (Exception e) {
				System.err.println("ERROR al introducir los datos en formato erroneo, cerrando programa");
				eleccion = 0;
			}

			switch (eleccion) {
			case 0:
				System.out.println("\nTERMINANDO PROGRAMA");
				fin = true;
				break;
			// leer dioses
			case 1:
				this.setEmisor(emisor);
				myControlDios.imprimir();
				break;
			// Leer mitologia
			case 2:
				this.setEmisor(emisor);
				myControlMito.imprimir();
				break;
			// subir dios
			case 3:
				this.setEmisor(emisor);
				myControlDios.subir();
				break;
			// subir mitologia
			case 4:
				this.setEmisor(emisor);
				myControlMito.subir();
				break;
			// pasar de uno a otro
			case 5:
				this.setEmisor(emisor);
				this.setReceptor(newAcceso());
				if (this.getReceptor() == null) {
					break;
				}
				myControlDios.intercambiaDatos();
				break;
			// Eliminar dioses
			case 6:
				this.setEmisor(emisor);
				myControlDios.eliminarTodos();
				break;
			// Eliminar Mitologias
			case 7:
				this.setEmisor(emisor);
				myControlMito.eliminarTodas();
				break;
			// Eliminar un dios
			case 8:
				this.setEmisor(emisor);
				myControlDios.eliminar(this.selectorId());
				break;
			// Eliminar una mitologia
			case 9:
				this.setEmisor(emisor);
				myControlMito.eliminar(this.selectorId());
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
				newDios.setMitologia(sc.nextLine());
				break;
			case 4:
				newDios.setCaract(sc.nextLine());
				break;
			}
		}

		datos.put(id, newDios);
		return datos;
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
			eleccion = new DBManager();
			break;
		case 2:
			eleccion = new FileManager();
			break;
		case 3:
			eleccion = new FileManager();
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

	// Presentaciones entre MVC
	public void setController(Controller contr) {
		this.myControlDios = contr;
	}

	public void setController(ControllerMitologia contr) {
		this.myControlMito = contr;
	}
}
