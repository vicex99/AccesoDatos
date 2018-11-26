package Modelo;

import java.util.HashMap;

import Controlador.Controller;
import Controlador.ControllerMitologia;
import Vista.Vista;

public interface AccesoDatos {

	public void subeDios(HashMap<Integer, Dios> lista);   
	public HashMap<Integer, Dios> leeDios();
	
	public void subeMitologia(HashMap<Integer, Mitologia> lista);   
	public HashMap<Integer, Mitologia> leeMitologia();
	
	public void actualizaDios(int id, Dios updateDios);
	public void actualizaMitologia(int id, Mitologia updateMitologia);
	
	public void eliminarDios(int id);
	public void eliminarTodosDioses();
	
	public void eliminarMitologia(int id);
	public void eliminarTodasMitologias();
}