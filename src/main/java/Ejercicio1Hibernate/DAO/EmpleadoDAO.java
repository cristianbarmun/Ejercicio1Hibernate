package Ejercicio1Hibernate.DAO;

import java.util.List;
import org.hibernate.Session;
import Ejercicio1Hibernate.HibernateFiles.CodeGeneration.Empleado;

public class EmpleadoDAO {

	public static void insertEmployees(Session s, Empleado employee) {

		s.save(employee);
	}

	public static List<Empleado> getAllEmployees(Session s) {
		String hQuery = "from Empleado";
		List<Empleado> clientList = s.createQuery(hQuery, Empleado.class).list();
		return clientList;
	}

	public static Empleado getEmployees(Session s, int employeeId) {
		String hQuery = " from Empleado c " + " where c.employeeId = :employeeId";
		Empleado client = s.createQuery(hQuery, Empleado.class).setParameter("employeeId", employeeId).setMaxResults(1)
				.uniqueResult();
		return client;
	}

}
