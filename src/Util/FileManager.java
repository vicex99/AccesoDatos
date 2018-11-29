package Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import Controlador.Controller;
import Controlador.ControllerMitologia;
import Modelo.Dios;
import Modelo.Mitologia;
import Vista.Vista;

public class FileManager implements AccesoDatos {

	String archivoDios = "dioses.txt";
	String archivoMito = "mitologias.txt";

	Vista vista;
	ControllerMitologia controllerMitologia;
	Controller controllerDioses;

	@Override
	public void subeDios(HashMap<Integer, Dios> lista) {
		try {
			File fich = new File(archivoDios);
			FileWriter fichero = new FileWriter(archivoDios, true);
			BufferedWriter out = new BufferedWriter(fichero);

			boolean primero = true;
			for (Entry<Integer, Dios> dios : lista.entrySet()) {
				// Comprueba si el fichero esta vacio para poner el salto entre los datos de dos
				// Dioses y si es el primero
				if (fich.length() != 0 || !primero)
					out.write("\n");
				primero = false;
				out.write(dios.getValue().getId() + "\n");
				out.write(dios.getValue().getNombre() + "\n");
				out.write(dios.getValue().getDefinicion() + "\n");
				out.write(dios.getValue().getMitologia() + "\n");
				out.write(dios.getValue().getCaract() + "\n");

			}

			System.out.println(" **********  ENVIO COMPLETADO");
			out.close();
		} catch (Exception e) {
			System.err.println("ERROR CONEXION");
			e.printStackTrace();
		}
	}

	@Override
	public void subeMitologia(HashMap<Integer, Mitologia> lista) {
		try {
			File fich = new File(archivoMito);
			FileWriter fichero = new FileWriter(archivoMito, true);
			BufferedWriter out = new BufferedWriter(fichero);

			boolean primero = true;
			for (Entry<Integer, Mitologia> mito : lista.entrySet()) {
				if (fich.length() != 0 || !primero)
					out.write("\n");
				primero = false;
				out.write(mito.getValue().getId() + "\n");
				out.write(mito.getValue().getNombre() + "\n");
				out.write(mito.getValue().getDescripcion() + "\n");

			}

			System.out.println(" **********  ENVIO COMPLETADO");
			out.close();
		} catch (Exception e) {
			System.err.println("ERROR CONEXION");
		}
	}

	@Override
	public HashMap<Integer, Dios> leeDios() {

		HashMap<Integer, Dios> Auxiliar = new HashMap<Integer, Dios>();
		String cadena;
		try {

			FileReader f = new FileReader(archivoDios);
			BufferedReader b = new BufferedReader(f);

			int i = 0;
			Dios jJugador = new Dios(0);

			while ((cadena = b.readLine()) != null) {

				switch (i) {
				case 0:
					jJugador.setId(Integer.parseInt(cadena));
					i++;
					break;

				case 1:
					jJugador.setNombre(cadena);
					i++;
					break;

				case 2:
					jJugador.setDefinicion(cadena);
					i++;
					break;

				case 3:
					jJugador.setMitologia(Integer.parseInt(cadena));
					i++;
					break;

				case 4:
					jJugador.setCaract(cadena);
					i++;
					break;

				default:
					Auxiliar.put(jJugador.getId(), jJugador);
					i = 0;
					jJugador = new Dios(0);
					break;
				}
			}
			Auxiliar.put(jJugador.getId(), jJugador);
			b.close();

		} catch (Exception e) {
			System.err.println("ERROR 2-----> en la lectura del fichero de dioses:");
			System.err.println(e.getStackTrace());
		}
		return Auxiliar;
	}

	@Override
	public HashMap<Integer, Mitologia> leeMitologia() {

		HashMap<Integer, Mitologia> Auxiliar = new HashMap<Integer, Mitologia>();
		String cadena;
		try {

			FileReader f = new FileReader(archivoMito);
			BufferedReader b = new BufferedReader(f);

			int i = 0;
			Mitologia mito = new Mitologia(0);

			while ((cadena = b.readLine()) != null) {

				switch (i) {
				case 0:
					mito.setId(Integer.parseInt(cadena));
					i++;
					break;

				case 1:
					mito.setNombre(cadena);
					i++;
					break;

				case 2:
					mito.setDescripcion(cadena);
					i++;
					break;

				default:
					Auxiliar.put(mito.getId(), mito);
					i = 0;
					mito = new Mitologia(0);
					break;
				}
			}
			Auxiliar.put(mito.getId(), mito);
			b.close();

		} catch (Exception e) {
			System.err.println("ERROR 2-----> en la lectura del fichero de mitologias:");
			System.err.println(e.getStackTrace());
		}
		return Auxiliar;
	}

	@Override
	public void eliminarTodosDioses() {
		FileWriter fichero;
		try {
			fichero = new FileWriter(archivoDios, false);
			BufferedWriter out = new BufferedWriter(fichero);

			out.write("");
			System.out.println("************ BORRADO COMPLETADO");

			out.close();
		} catch (IOException e) {
			System.out.println("ERROR en la consulta - ");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.print("ERROR en la aplicacion -");
			e.getStackTrace();
		}
	}

	@Override
	public void eliminarTodasMitologias() {
		FileWriter fichero;
		try {
			fichero = new FileWriter(archivoMito, false);
			BufferedWriter out = new BufferedWriter(fichero);

			out.write("");
			System.out.println("************ BORRADO COMPLETADO");

			out.close();
		} catch (IOException e) {
			System.out.println("ERROR en la consulta - ");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.print("ERROR en la aplicacion -");
			e.getStackTrace();
		}
	}

	@Override
	public void eliminarDios(int id) {
		HashMap<Integer, Dios> Auxiliar = new HashMap<Integer, Dios>();
		String cadena;
		try {

			FileReader f = new FileReader(archivoDios);
			BufferedReader b = new BufferedReader(f);

			int i = 0;
			Dios dios = new Dios(0);

			while ((cadena = b.readLine()) != null) {

				switch (i) {
				case 0:
					dios.setId(Integer.parseInt(cadena));
					i++;
					break;

				case 1:
					dios.setNombre(cadena);
					i++;
					break;

				case 2:
					dios.setDefinicion(cadena);
					i++;
					break;

				case 3:
					dios.setMitologia(Integer.parseInt(cadena));
					i++;
					break;

				case 4:
					dios.setCaract(cadena);
					i++;
					break;

				default:
					if (dios.getId() != id)
						Auxiliar.put(dios.getId(), dios);

					i = 0;
					dios = new Dios(0);
					break;
				}
			}
			if (dios.getId() != id)
				Auxiliar.put(dios.getId(), dios);
			this.eliminarTodosDioses();
			this.subeDios(Auxiliar);
			System.out.println("************ Archivo eliminado");
			b.close();

		} catch (Exception e) {
			System.err.println("ERROR 2-----> en la eliminacion del fichero:");
			System.err.println(e.getStackTrace());
		}
	}

	@Override
	public void eliminarMitologia(int id) {
		HashMap<Integer, Mitologia> Auxiliar = new HashMap<Integer, Mitologia>();
		String cadena;
		try {

			FileReader f = new FileReader(archivoMito);
			BufferedReader b = new BufferedReader(f);

			int i = 0;
			Mitologia mito = new Mitologia(0);

			while ((cadena = b.readLine()) != null) {

				switch (i) {
				// Guarda los datos de cada Mitologia en un objeto auxiliar
				case 0:
					mito.setId(Integer.parseInt(cadena));
					i++;
					break;

				case 1:
					mito.setNombre(cadena);
					i++;
					break;

				case 2:
					mito.setDescripcion(cadena);
					i++;
					break;

				// Cuando ya termina de recorer los datos de cada Mitologia la guarda si no es
				// la selecionada
				default:
					if (mito.getId() != id)
						Auxiliar.put(mito.getId(), mito);

					i = 0;
					mito = new Mitologia(0);
					break;
				}
			}
			// para el ultimo objeto que ya sale del bucle sin la posiblidad de subirlo
			if (mito.getId() != id)
				Auxiliar.put(mito.getId(), mito);
			this.eliminarTodosDioses();
			this.subeMitologia(Auxiliar);
			System.out.println("************ Archivo eliminado");
			b.close();

		} catch (Exception e) {
			System.err.println("ERROR 2-----> en la eliminacion del fichero:");
			System.err.println(e.getStackTrace());
		}
	}

	@Override
	public void actualizaDios(int id, Dios updateDios) {
		HashMap<Integer, Dios> Auxiliar = new HashMap<Integer, Dios>();
		String cadena;
		try {

			FileReader f = new FileReader(archivoDios);
			BufferedReader b = new BufferedReader(f);

			int i = 0;
			Dios dios = new Dios(0);

			while ((cadena = b.readLine()) != null) {

				switch (i) {
				case 0:
					dios.setId(Integer.parseInt(cadena));
					i++;
					break;

				case 1:
					dios.setNombre(cadena);
					i++;
					break;

				case 2:
					dios.setDefinicion(cadena);
					i++;
					break;

				case 3:
					dios.setMitologia(Integer.parseInt(cadena));
					i++;
					break;

				case 4:
					dios.setCaract(cadena);
					i++;
					break;

				default:
					if (dios.getId() != id)
						Auxiliar.put(dios.getId(), dios);
					else
						Auxiliar.put(id, updateDios);

					i = 0;
					dios = new Dios(0);
					break;
				}
			}
			if (dios.getId() != id)
				Auxiliar.put(dios.getId(), dios);
			this.eliminarTodosDioses();
			this.subeDios(Auxiliar);
			System.out.println("************ Archivo eliminado");
			b.close();

		} catch (Exception e) {
			System.err.println("ERROR 2-----> en la eliminacion del fichero:");
			System.err.println(e.getStackTrace());
		}

	}

	@Override
	public void actualizaMitologia(int id, Mitologia updateMitologia) {
		HashMap<Integer, Mitologia> Auxiliar = new HashMap<Integer, Mitologia>();
		String cadena;
		try {

			FileReader f = new FileReader(archivoDios);
			BufferedReader b = new BufferedReader(f);

			int i = 0;
			Mitologia mito = new Mitologia(0);

			while ((cadena = b.readLine()) != null) {

				switch (i) {
				case 0:
					mito.setId(Integer.parseInt(cadena));
					i++;
					break;

				case 1:
					mito.setNombre(cadena);
					i++;
					break;

				case 2:
					mito.setDescripcion(cadena);
					i++;
					break;

				default:
					if (mito.getId() != id)
						Auxiliar.put(mito.getId(), mito);
					else
						Auxiliar.put(id, updateMitologia);

					i = 0;
					mito = new Mitologia(0);
					break;
				}
			}
			if (mito.getId() != id)
				Auxiliar.put(mito.getId(), mito);
			this.eliminarTodosDioses();
			this.subeMitologia(Auxiliar);
			System.out.println("************ Archivo eliminado");
			b.close();

		} catch (Exception e) {
			System.err.println("ERROR 2-----> en la eliminacion del fichero:");
			System.err.println(e.getStackTrace());
		}
	}
}
