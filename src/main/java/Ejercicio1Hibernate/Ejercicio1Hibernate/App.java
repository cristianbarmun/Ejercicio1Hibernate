package Ejercicio1Hibernate.Ejercicio1Hibernate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Ejercicio1Hibernate.DAO.DepartamentoDAO;
import Ejercicio1Hibernate.HibernateFiles.CodeGeneration.Departamento;

/**
 * Hello world!
 *
 */
public class App {
	private static Logger logger = LogManager.getLogger(App.class);

	static SessionFactory sessionFactory;

	public static void main(String[] args) {
		Scanner lector = new Scanner(System.in);

		String methodName = App.class.getSimpleName() + ".main()";
		logger.info(String.format("%1$s: >>>>>> Main execution started.", methodName));

		// La SessionFactory se instancia 1 vez por ejecución del programa y se obtienen
		// de SessionFactory
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		int modifiedDepartmentId = 1;

		try {
			// Las opereaciones save/update/delete
			tx = session.beginTransaction();

			// Insertamos proveedores
			int numDepartment = 5;
			Departamento department = new Departamento();

			DepartamentoDAO.insertDepartaments(session, department);

			// Recuperamos y listamos proveedores
			List<Departamento> departments = DepartamentoDAO.getAllDepartment(session);
			logger.info(String.format("%1$s: number of departments = %2$s.", methodName, departments.size()));

			// pedimos datos del departamento por teclado
			System.out.println("Diga el codigo del departamento: ");
			int codigoDpt = lector.nextInt();
			System.out.println("Diga el nombre del departamento: ");
			String nombreDpt = lector.nextLine();
			System.out.println("Diga el codigo del responsable del departamento: ");
			int codResponsable = lector.nextInt();

			// Actualizamos departamento 1
			Departamento departmento = departments.stream().findFirst().orElse(null);
			if (departmento != null) {
				departmento.setCodigo(codigoDpt);
				departmento.setNombre(nombreDpt);
				departmento.setCodResponsable(codResponsable);
			}

			session.save(departmento);
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(String.format("%1$s: error when inserting registries.", methodName), e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
