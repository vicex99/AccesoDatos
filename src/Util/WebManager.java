package Util;

import java.util.HashMap;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import Auxilares.ApiRequests;
import Modelo.Dios;
import Modelo.Mitologia;

public class WebManager implements AccesoDatos {

	ApiRequests encargadoPeticiones;
	private String SERVER_PATH, GET_DEUS, SET_DEUS, GET_MITHOLOGY, SET_MITHOLOGY, UPDATE_DEUS, DELETE_DEUS,
			UPDATE_MITOLOGY, DELETE_MITOLOGY; // Datos de la conexion

	public WebManager() {

		encargadoPeticiones = new ApiRequests();

		SERVER_PATH = "http://localhost/json/AccesoDatosJSON/";
		GET_DEUS = "leeDioses.php";
		SET_DEUS = "escribirDios.php";
		UPDATE_DEUS = "actualizarDios.php";
		DELETE_DEUS = "borrarDios.php";

		GET_MITHOLOGY = "leeMitologias.php";
		SET_MITHOLOGY = "escribirMitologia.php";
		UPDATE_MITOLOGY = "actualizarMitologia.php";
		DELETE_MITOLOGY = "borrarMitologia.php";

	}

	@Override
	public void subeDios(HashMap<Integer, Dios> lista) {
		try {

			JSONObject objDios = new JSONObject();
			JSONObject objPeticion = new JSONObject();
			
			Entry<Integer, Dios> deus = lista.entrySet().iterator().next();
			int key = (int) deus.getKey();
			objDios.put("nombre", lista.get(key).getNombre());
			objDios.put("descripcion", lista.get(key).getDefinicion());
			objDios.put("idMitologia", lista.get(key).getMitologia());
			objDios.put("caracteristicas", lista.get(key).getCaract());

			// Tenemos el jugador como objeto JSON. Lo añadimos a una peticion
			// Lo transformamos a string y llamamos al
			// encargado de peticiones para que lo envie al PHP

			objPeticion.put("peticion", "add");
			objPeticion.put("diosAnnadir", objDios);

			String json = objPeticion.toJSONString();

			System.out.println("Lanzamos peticion JSON para almacenar un Dios");

			String url = SERVER_PATH + SET_DEUS;

			System.out.println("La url a la que lanzamos la petición es " + url);
			System.out.println("El json que enviamos es: ");
			System.out.println(json);
			// System.exit(-1);

			String response = encargadoPeticiones.postRequest(url, json);

			System.out.println("El json que recibimos es: ");

			System.out.println(response); // Traza para pruebas
//			System.exit(-1);

			// Parseamos la respuesta y la convertimos en un JSONObject

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay algún error de parseo (json
										// incorrecto porque hay algún caracter
										// raro, etc.) la respuesta será null
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else { // El JSON recibido es correcto

				// Sera "ok" si todo ha ido bien o "error" si hay algún problema
				String estado = (String) respuesta.get("estado");
				if (estado.equals("ok")) {

					System.out.println("Almacenado jugador enviado por JSON Remoto");

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido algún error

					System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			}
		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el método 'annadirDios' de la clase JSON REMOTO");
//			 e.printStackTrace();
			System.out.println("Fin ejecución");
			System.exit(-1);
		}
	}

	@Override
	public HashMap<Integer, Dios> leeDios() {

		HashMap<Integer, Dios> auxhm = new HashMap<Integer, Dios>();

		try {

			System.out.println("---------- Leemos datos de JSON --------------------");
			System.out.println("Lanzamos peticion JSON para dioses");

			String url = SERVER_PATH + GET_DEUS; // Sacadas de configuracion

//			 System.out.println("La url a la que lanzamos la petición es " +
//			 url); // Traza para pruebas

			String response = encargadoPeticiones.getRequest(url);

			// Para ver el string tal cual lo manda el php
//			 System.out.println(response); // Traza para pruebas

			// Para parar el programa y sacar solo el JSON
			// System.exit(0);

			// Parseamos la respuesta y la convertimos en un JSONObject
			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay algún error de parseo (json
										// incorrecto porque hay algún caracter
										// raro, etc.) la respuesta será null
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else { // El JSON recibido es correcto
				// Sera "ok" si todo ha ido bien o "error" si hay algún problema
				String estado = (String) respuesta.get("estado");
				// Si ok, obtenemos array de jugadores para recorrer y generar hashmap
				if (estado.equals("ok")) {
					JSONArray array = (JSONArray) respuesta.get("dioses");

					if (array.size() > 0) {

						// Declaramos variables
						Dios nuevoDeus;
						String nombre;
						String definicion;
						String caract;
						int idMito;
						int id;

						for (int i = 0; i < array.size(); i++) {
							JSONObject row = (JSONObject) array.get(i);

							nombre = row.get("nombre").toString();
							id = Integer.parseInt(row.get("id").toString());
							definicion = row.get("descripcion").toString();
							idMito = Integer.parseInt(row.get("idMitologia").toString());
							caract = row.get("caracteristicas").toString();

							nuevoDeus = new Dios(id, nombre, definicion, idMito, caract);

							auxhm.put(id, nuevoDeus);
						}

						System.out.println("Acceso JSON Remoto - Leidos datos correctamente y generado hashmap");
						System.out.println();

					} else { // El array de jugadores está vacío
						System.out.println("Acceso JSON Remoto - No hay datos que tratar");
						System.out.println();
					}

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido algún error

					System.out.println("Ha ocurrido un error en la busqueda de datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			}

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error en la busqueda de datos");

			e.printStackTrace();

			System.exit(-1);
		}

		return auxhm;
	}

	@Override
	public void subeMitologia(HashMap<Integer, Mitologia> lista) {
		try {
			JSONObject objMito = new JSONObject();
			JSONObject objPeticion = new JSONObject();

			Entry<Integer, Mitologia> deus = lista.entrySet().iterator().next();
			int key = (int) deus.getKey();
			objMito.put("id", lista.get(key).getId());
			objMito.put("nombre", lista.get(key).getNombre());
			objMito.put("descripcion", lista.get(key).getDescripcion());

			// Tenemos el jugador como objeto JSON. Lo añadimos a una peticion
			// Lo transformamos a string y llamamos al
			// encargado de peticiones para que lo envie al PHP

			objPeticion.put("peticion", "add");
			objPeticion.put("mithologyAnnadir", objMito);

			String json = objPeticion.toJSONString();

			System.out.println("Lanzamos peticion JSON para almacenar una mitologia");

			String url = SERVER_PATH + SET_MITHOLOGY;

			System.out.println("La url a la que lanzamos la petición es " + url);
			System.out.println("El json que enviamos es: ");
			System.out.println(json);
			// System.exit(-1);

			String response = encargadoPeticiones.postRequest(url, json);

			System.out.println("El json que recibimos es: ");

			System.out.println(response); // Traza para pruebas
//			System.exit(-1);

			// Parseamos la respuesta y la convertimos en un JSONObject

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay algún error de parseo (json
										// incorrecto porque hay algún caracter
										// raro, etc.) la respuesta será null
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else { // El JSON recibido es correcto

				// Sera "ok" si todo ha ido bien o "error" si hay algún problema
				String estado = (String) respuesta.get("estado");
				if (estado.equals("ok")) {

					System.out.println("Almacenado jugador enviado por JSON Remoto");

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido algún error

					System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			}
		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el método 'annadirMitologia' de la clase JSON REMOTO");
			 e.printStackTrace();
			System.out.println("Fin ejecución");
			System.exit(-1);
		}
	}

	@Override
	public HashMap<Integer, Mitologia> leeMitologia() {

		HashMap<Integer, Mitologia> auxhm = new HashMap<Integer, Mitologia>();

		try {

			System.out.println("---------- Leemos datos de JSON --------------------");
			System.out.println("Lanzamos peticion JSON para mitologias");

			String url = SERVER_PATH + GET_MITHOLOGY; // Sacadas de configuracion

//			 System.out.println("La url a la que lanzamos la petición es " +
//			 url); // Traza para pruebas

			String response = encargadoPeticiones.getRequest(url);

			// Para ver el string tal cual lo manda el php
			System.out.println("traza prueba respuesta");
			System.out.println(response); // Traza para pruebas
			System.out.println("traza prueba respuesta");
			// Para parar el programa y sacar solo el JSON
//			 System.exit(0);

			// Parseamos la respuesta y la convertimos en un JSONObject
			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay algún error de parseo (json
										// incorrecto porque hay algún caracter
										// raro, etc.) la respuesta será null
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else { // El JSON recibido es correcto
				// Sera "ok" si todo ha ido bien o "error" si hay algún problema
				String estado = (String) respuesta.get("estado");
				// Si ok, obtenemos array de jugadores para recorrer y generar hashmap
				if (estado.equals("ok")) {
					JSONArray array = (JSONArray) respuesta.get("mitologias");

					if (array.size() > 0) {

						// Declaramos variables
						Mitologia nuevoMito;
						String nombre;
						String definicion;
						int id;

						for (int i = 0; i < array.size(); i++) {
							JSONObject row = (JSONObject) array.get(i);

							nombre = row.get("nombre").toString();
							id = Integer.parseInt(row.get("id").toString());
							definicion = row.get("definicion").toString();

							nuevoMito = new Mitologia(id, nombre, definicion);

							auxhm.put(id, nuevoMito);
						}

						System.out.println("Acceso JSON Remoto - Leidos datos correctamente y generado hashmap");
						System.out.println();

					} else { // El array de jugadores está vacío
						System.out.println("Acceso JSON Remoto - No hay datos que tratar");
						System.out.println();
					}

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido algún error

					System.out.println("Ha ocurrido un error en la busqueda de datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);
				}
			}

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error en la busqueda de datos");
			e.printStackTrace();

			System.exit(-1);
		}

		return auxhm;
	}

	@Override
	public void actualizaDios(Dios updateDios) {
		try {
			JSONObject objDeus = new JSONObject();
			JSONObject objPeticion = new JSONObject();

			objDeus.put("id", updateDios.getId());
			objDeus.put("nombre", updateDios.getNombre());
			objDeus.put("descripcion", updateDios.getDefinicion());
			objDeus.put("idMitologia", updateDios.getMitologia());
			objDeus.put("caracteristicas", updateDios.getCaract());

			// Tenemos el jugador como objeto JSON. Lo añadimos a una peticion
			// Lo transformamos a string y llamamos al
			// encargado de peticiones para que lo envie al PHP

			objPeticion.put("peticion", "update");
			objPeticion.put("DeusUpdate", objDeus);

			String json = objPeticion.toJSONString();

			System.out.println("Lanzamos peticion JSON para actualizar un dios");

			String url = SERVER_PATH + UPDATE_DEUS;

			System.out.println("La url a la que lanzamos la petición es " + url);
			System.out.println("El json que enviamos es: ");
			System.out.println(json);
			// System.exit(-1);

			String response = encargadoPeticiones.postRequest(url, json);

			System.out.println("El json que recibimos es: ");

//			System.out.println(response); // Traza para pruebas
			System.exit(-1);

			// Parseamos la respuesta y la convertimos en un JSONObject

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay algún error de parseo (json
										// incorrecto porque hay algún caracter
										// raro, etc.) la respuesta será null
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else { // El JSON recibido es correcto

				// Sera "ok" si todo ha ido bien o "error" si hay algún problema
				String estado = (String) respuesta.get("estado");
				if (estado.equals("ok")) {
					System.out.println("Actualizacion dios enviado por JSON Remoto");

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido algún error

					System.out.println("Acceso JSON REMOTO - Error al actualizar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);
				}
			}
		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el método 'DeusUpdate' de la clase JSON REMOTO");
			// e.printStackTrace();
			System.out.println("Fin ejecución");
			System.exit(-1);
		}
	}

	@Override
	public void actualizaMitologia(Mitologia updateMitologia) {
		try {
			JSONObject objMito = new JSONObject();
			JSONObject objPeticion = new JSONObject();

			objMito.put("id", updateMitologia.getId());
			objMito.put("nombre", updateMitologia.getNombre());
			objMito.put("descripcion", updateMitologia.getDescripcion());

			// Tenemos el jugador como objeto JSON. Lo añadimos a una peticion
			// Lo transformamos a string y llamamos al
			// encargado de peticiones para que lo envie al PHP

			objPeticion.put("peticion", "update");
			objPeticion.put("MitoUpdate", objMito);

			String json = objPeticion.toJSONString();

			System.out.println("Lanzamos peticion JSON para actualizar una mitologia");

			String url = SERVER_PATH + UPDATE_DEUS;

			System.out.println("La url a la que lanzamos la petición es " + url);
			System.out.println("El json que enviamos es: ");
			System.out.println(json);
			// System.exit(-1);

			String response = encargadoPeticiones.postRequest(url, json);

			System.out.println("El json que recibimos es: ");

//			System.out.println(response); // Traza para pruebas
			System.exit(-1);

			// Parseamos la respuesta y la convertimos en un JSONObject

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay algún error de parseo (json
										// incorrecto porque hay algún caracter
										// raro, etc.) la respuesta será null
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else { // El JSON recibido es correcto

				// Sera "ok" si todo ha ido bien o "error" si hay algún problema
				String estado = (String) respuesta.get("estado");
				if (estado.equals("ok")) {
					System.out.println("Actualizacion dios enviado por JSON Remoto");

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido algún error

					System.out.println("Acceso JSON REMOTO - Error al actualizar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);
				}
			}
		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el método 'MitologyUpdate' de la clase JSON REMOTO");
			// e.printStackTrace();
			System.out.println("Fin ejecución");
			System.exit(-1);
		}
	}

	@Override
	public void eliminarDios(int id) {
		try {
			JSONObject objPeticion = new JSONObject();

			// Tenemos el jugador como objeto JSON. Lo añadimos a una peticion
			// Lo transformamos a string y llamamos al
			// encargado de peticiones para que lo envie al PHP

			objPeticion.put("peticion", "delete");
			objPeticion.put("DeusDelete", id);

			String json = objPeticion.toJSONString();

			System.out.println("Lanzamos peticion JSON para actualizar una mitologia");

			String url = SERVER_PATH + DELETE_DEUS;

			System.out.println("La url a la que lanzamos la petición es " + url);
			System.out.println("El json que enviamos es: ");
			System.out.println(json);
			// System.exit(-1);

			String response = encargadoPeticiones.postRequest(url, json);

			System.out.println("El json que recibimos es: ");

			System.out.println(response); // Traza para pruebas
//			System.exit(-1);

			// Parseamos la respuesta y la convertimos en un JSONObject

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay algún error de parseo (json
										// incorrecto porque hay algún caracter
										// raro, etc.) la respuesta será null
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else { // El JSON recibido es correcto

				// Sera "ok" si todo ha ido bien o "error" si hay algún problema
				String estado = (String) respuesta.get("estado");
				if (estado.equals("ok")) {
					System.out.println("Borrado dios enviado por JSON Remoto");

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido algún error

					System.out.println("Acceso JSON REMOTO - Error al borrar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);
				}
			}
		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el método 'MitologyUpdate' de la clase JSON REMOTO");
			// e.printStackTrace();
			System.out.println("Fin ejecución");
			System.exit(-1);
		}
	}

	@Override
	public void eliminarTodosDioses() {

	}

	@Override
	public void eliminarMitologia(int id) {

	}

	@Override
	public void eliminarTodasMitologias() {

	}
}
