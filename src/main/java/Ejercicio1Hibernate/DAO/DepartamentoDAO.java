package Ejercicio1Hibernate.DAO;

import java.util.List;

import org.hibernate.Session;
import Ejercicio1Hibernate.HibernateFiles.CodeGeneration.Departamento;

public class DepartamentoDAO {

	public static void insertDepartaments(Session s, Departamento department) {
		s.save(department);
	}

	public static List<Departamento> getAllDepartment(Session s) {
		String hQuery = "from Empleado";
		List<Departamento> clientList = s.createQuery(hQuery, Departamento.class).list();
		return clientList;
	}

	public static Departamento getDepartment(Session s, int departmentId) {
		String hQuery = " from Departamento c " + " where c.departmentId = :departmentId";
		Departamento client = s.createQuery(hQuery, Departamento.class).setParameter("departmentId", departmentId).setMaxResults(1)
				.uniqueResult();
		return client;
	}

}
