package Util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Modelo.Dios;
import Modelo.Mitologia;

public class BDHibernateManger implements AccesoDatos {

	Session session;

	public BDHibernateManger() {
		HibernateUtil util = new HibernateUtil();
		session = util.getSessionFactory().openSession();
	}

	@Override
	public void subeDios(HashMap<Integer, Dios> lista) {
		System.out.println(" ********************** Inicio subida datos Hibernate");
		session.beginTransaction();
		for (Entry<Integer, Dios> entry : lista.entrySet()) {
			session.save(entry.getValue());
		}
		session.getTransaction().commit();
	}

	@Override
	public HashMap<Integer, Dios> leeDios() {
		HashMap<Integer, Dios> Auxiliar = new HashMap<Integer, Dios>();
		System.out.println(" ********************** Inicio Consulta Simple Dios");
		Query q = session.createQuery("select e from Dios e");
		List results = q.list();
		Iterator equiposIterator = results.iterator();
		while (equiposIterator.hasNext()) {
			Dios dios = (Dios) equiposIterator.next();
			Auxiliar.put(dios.getId(), dios);
		}
		System.out.println("Fin Consulta Equipos");
		return Auxiliar;
	}

	@Override
	public void subeMitologia(HashMap<Integer, Mitologia> lista) {
		System.out.println(" ********************** Inicio subida datos Hibernate");

		session.beginTransaction();

		for (Entry<Integer, Mitologia> entry : lista.entrySet()) {
			session.save(entry.getValue());
		}

		session.getTransaction().commit();
	}

	@Override
	public HashMap<Integer, Mitologia> leeMitologia() {
		HashMap<Integer, Mitologia> Auxiliar = new HashMap<Integer, Mitologia>();
		System.out.println(" ********************** Inicio Consulta Simple Dios");
		Query q = session.createQuery("select e from Mitologia e");
		List results = q.list();
		Iterator equiposIterator = results.iterator();
		while (equiposIterator.hasNext()) {
			Mitologia mito = (Mitologia) equiposIterator.next();
			Auxiliar.put(mito.getId(), mito);
		}
		session.getTransaction().commit();
		System.out.println("Fin Consulta Equipos");
		return Auxiliar;
	}

	@Override
	public void eliminarDios(int id) {
		System.out.println("Inicio Borrado");
		session.beginTransaction();
		Dios dios;
		dios = (Dios) session.load(Dios.class, id);
		session.delete(dios);
		session.getTransaction().commit();
	}

	@Override
	public void eliminarTodosDioses() {
		System.out.println("Inicio Borrado");
		session.beginTransaction();
		for (Entry<Integer, Dios> entry : leeDios().entrySet()) {
			session.delete(entry.getValue());
		}
		session.getTransaction().commit();
	}

	@Override
	public void eliminarMitologia(int id) {
		System.out.println("Inicio Borrado");
		session.beginTransaction();
		Mitologia mito;
		mito = (Mitologia) session.load(Mitologia.class, id);
		session.delete(mito);
		session.getTransaction().commit();
	}

	@Override
	public void eliminarTodasMitologias() {
		System.out.println("Inicio Borrado");
		eliminarTodosDioses();
		session.beginTransaction();
		for (Entry<Integer, Mitologia> entry : leeMitologia().entrySet()) {
			session.delete(entry.getValue());
		}
		session.getTransaction().commit();
	}

	@Override
	public void actualizaDios(int id, Dios updateDios) {
		session.beginTransaction();
		session.update(updateDios);
		session.getTransaction().commit();
	}

	@Override
	public void actualizaMitologia(int id, Mitologia updateMitologia) {
		session.beginTransaction();
		session.update(updateMitologia);
		session.getTransaction().commit();
	}
}

class HibernateUtil {

	private SessionFactory sessionFactory;

	public HibernateUtil() {

		try {
			// Create the SessionFactory from standard (hibernate.cfg.xml) config file.
			sessionFactory = new Configuration().configure().buildSessionFactory();

		} catch (Throwable ex) {
			// Log the exception.
			System.err.println("Initial SessionFactory creation failed." + ex);
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
