package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import Controlador.Controller;
import Controlador.ControllerMitologia;
import Modelo.Dios;
import Modelo.Mitologia;
import Vista.Vista;

public class DBManager implements AccesoDatos {

	private ConexionMySQL conn;
	private Connection conexion;
	private PreparedStatement preparedStmt;

	Controller myControl;
	ControllerMitologia controlMitologia;
	Vista vista;

	private String queryVerDioses = "CALL verDioses()";
	private String queryIncluirDioses = "CALL anadirDios(?, ?, ?, ?, ?)";
	private String queryVerMitologias = "CALL verMitologias()";
	private String queryIncluirMitologias = "CALL anadirMitologia(?, ?, ?)";
	private String queryEliminarDios = "CALL eliminarDios(?)";
	private String queryEliminarDioses = "CALL eliminarDioses()";
	private String queryBorrarMitologia = "CALL eliminarMitologia(?)";
	private String queryBorrarTodasMitologias = "CALL eliminarMitologias()";
	private String queryModificarDios = "CALL updateDios(?, ?, ?, ?, ?)";
	private String queryModificarMito = "CALL updateMito(?, ?, ?)";

	public DBManager() {
		conn = new ConexionMySQL();
		conexion = conn.Conectar();
	}

	@Override
	public void subeDios(HashMap<Integer, Dios> lista) {
		try {
			preparedStmt = conexion.prepareStatement(queryIncluirDioses);
		} catch (SQLException e) {
			System.err.println("ERROR " + e.getErrorCode() + " POR LA CONEXION CON LA BBDD: " + e.fillInStackTrace());
			System.err.println();

		} catch (Exception e) {
			System.err.println("ERROR CONEXION");
		}

		for (Entry<Integer, Dios> entry : lista.entrySet()) {
			try {
				preparedStmt.setInt(1, entry.getKey());
				preparedStmt.setString(2, entry.getValue().getNombre());
				preparedStmt.setString(3, entry.getValue().getDefinicion());
				preparedStmt.setString(4, entry.getValue().getMitologia());
				preparedStmt.setString(5, entry.getValue().getCaract());
				preparedStmt.executeUpdate();
				System.out.println(" **********  ENVIO COMPLETADO");

			} catch (SQLException e) {
				// Si en la base de datos ya hay un elemento con el id, se dira por pantalla sin
				// ser error
				// Si es otra cosa se enviara el mensaje de error
				if (e.getErrorCode() == 1062) {
					System.out.println(" ********* NO SUBIDO POR QUE YA HAY UN ELEMENTO CON EL MISMO ID");
				} else if (e.getErrorCode() == 1452) {
					System.out.println(" ********* NO EXISTE LA MITOLOGIA A LA QUE SE ESTA AGREGANDO ESTE DIOS");
				} else {
					System.err.println(
							"ERROR " + e.getErrorCode() + " POR LA CONEXION CON LA BBDD: " + e.fillInStackTrace());
					System.out.println();
				}
			} catch (Exception e) {
				System.err.println("ERROR CONEXION");
				conexion = conn.Conectar();
			}
		}
	}

	@Override
	public void subeMitologia(HashMap<Integer, Mitologia> lista) {
		try {
			preparedStmt = conexion.prepareStatement(queryIncluirMitologias);
		} catch (SQLException e) {
			System.err.println("ERROR " + e.getErrorCode() + " POR LA CONEXION CON LA BBDD: " + e.fillInStackTrace());
			System.err.println();

		} catch (Exception e) {
			System.err.println("ERROR CONEXION");
			conexion = conn.Conectar();
		}

		for (Entry<Integer, Mitologia> entry : lista.entrySet()) {
			try {
				preparedStmt.setInt(1, entry.getKey());
				preparedStmt.setString(2, entry.getValue().getNombre());
				preparedStmt.setString(3, entry.getValue().getDescripcion());
				preparedStmt.executeUpdate();
				System.out.println(" **********  ENVIO COMPLETADO");

			} catch (SQLException e) {
				// Si en la base de datos ya hay un elemento con el id, se dira por pantalla sin
				// ser error
				// Si es otra cosa se enviara el mensaje de error
				if (e.getErrorCode() == 1062) {
					System.out.println(" ********* NO SUBIDO POR QUE YA HAY UN ELEMENTO CON EL MISMO ID");
				} else {
					System.err.println(
							"ERROR " + e.getErrorCode() + " POR LA CONEXION CON LA BBDD: " + e.fillInStackTrace());
					System.out.println();
				}
			} catch (Exception e) {
				System.err.println("ERROR CONEXION");
				conexion = conn.Conectar();
			}
		}
	}

	// Leer de la bbdd
	@Override
	public HashMap<Integer, Dios> leeDios() {
		HashMap<Integer, Dios> Auxiliar = new HashMap<Integer, Dios>();
		
		return Auxiliar;
	}

	@Override
	public HashMap<Integer, Mitologia> leeMitologia() {
		HashMap<Integer, Mitologia> Auxiliar = new HashMap<Integer, Mitologia>();

		try {
			preparedStmt = conexion.prepareStatement(queryVerMitologias);
			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				Mitologia nuevo = new Mitologia(rs.getInt("id"), rs.getString("Nombre"), rs.getString("Descripcion"));

				Auxiliar.put(rs.getInt("id"), nuevo);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("ERROR - base de datos MySQL no encontrada");
			conexion = conn.Conectar();
		}
		return Auxiliar;
	}

	// Eliminar todo
	public void eliminarTodosDioses() {
		try {
			preparedStmt = conexion.prepareStatement(queryEliminarDioses);

			preparedStmt.executeUpdate();
			System.out.println(" ********** BORRADO COMPLETADO");

		} catch (SQLException e) {

			System.err.println("ERROR " + e.getErrorCode() + " POR LA CONEXION CON LA BBDD: " + e.fillInStackTrace());
			System.out.println();

		} catch (Exception e) {
			System.err.println("ERROR CONEXION");
			conexion = conn.Conectar();
		}
	}

	@Override
	public void eliminarTodasMitologias() {
		try {
			preparedStmt = conexion.prepareStatement(queryBorrarTodasMitologias);

			preparedStmt.executeUpdate();
			System.out.println(" ********** BORRADO COMPLETADO");

		} catch (SQLException e) {
			System.err.println("ERROR " + e.getErrorCode() + " POR LA CONEXION CON LA BBDD: " + e.fillInStackTrace());
			System.out.println();

		} catch (Exception e) {
			System.err.println("ERROR CONEXION");
			conexion = conn.Conectar();
		}
	}

	public void eliminarDios(int id) {
		try {
			preparedStmt = conexion.prepareStatement(queryEliminarDios);
			preparedStmt.setInt(1, id);

			preparedStmt.executeUpdate();
			System.out.println(" **********  BORRADO COMPLETADO");

		} catch (SQLException e) {
			System.err.println("ERROR " + e.getErrorCode() + " POR LA CONEXION CON LA BBDD: " + e.fillInStackTrace());
			System.out.println();

		} catch (Exception e) {
			System.err.println("ERROR CONEXION");
			conexion = conn.Conectar();
		}
	}

	@Override
	public void eliminarMitologia(int id) {
		try {
			preparedStmt = conexion.prepareStatement(queryBorrarMitologia);
			preparedStmt.setInt(1, id);

			preparedStmt.executeUpdate();
			System.out.println(" **********  BORRADO COMPLETADO");

		} catch (SQLException e) {

			System.err.println("ERROR " + e.getErrorCode() + " POR LA CONEXION CON LA BBDD: " + e.fillInStackTrace());
			System.out.println();

		} catch (Exception e) {
			System.err.println("ERROR CONEXION");
			conexion = conn.Conectar();
		}
	}

	@Override
	public void actualizaDios(int id, Dios updateDios) {
		try {
			preparedStmt = conexion.prepareStatement(queryModificarDios);
			preparedStmt.setInt(1, updateDios.getId());
			preparedStmt.setString(2, updateDios.getNombre());
			preparedStmt.setString(3, updateDios.getDefinicion());
			preparedStmt.setString(4, updateDios.getMitologia());
			preparedStmt.setString(5, updateDios.getCaract());

			preparedStmt.executeUpdate();
			System.out.println(" **********  BORRADO COMPLETADO");

		} catch (SQLException e) {

			System.err.println("ERROR " + e.getErrorCode() + " POR LA CONEXION CON LA BBDD: " + e.fillInStackTrace());
			System.out.println();

		} catch (Exception e) {
			System.err.println("ERROR CONEXION");
			conexion = conn.Conectar();
		}
	}

	@Override
	public void actualizaMitologia(int id, Mitologia updateMitologia) {
		try {
			preparedStmt = conexion.prepareStatement(queryModificarMito);
			preparedStmt.setInt(1, updateMitologia.getId());
			preparedStmt.setString(2, updateMitologia.getNombre());
			preparedStmt.setString(3, updateMitologia.getDescripcion());

			preparedStmt.executeUpdate();
			System.out.println(" **********  BORRADO COMPLETADO");

		} catch (SQLException e) {

			System.err.println("ERROR " + e.getErrorCode() + " POR LA CONEXION CON LA BBDD: " + e.fillInStackTrace());
			System.out.println();

		} catch (Exception e) {
			System.err.println("ERROR CONEXION");
			conexion = conn.Conectar();
		}
	}
}

class ConexionMySQL {

	public String db = "BBDD_1";
	public String url = "jdbc:mysql://localhost/" + db
			+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public String user = "root";
	public String pass = "";

	public Connection Conectar() {
		Connection link = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			link = DriverManager.getConnection(this.url, this.user, this.pass);
			System.out.println(" ********************  base de datos conectada");

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		return link;
	}
}
