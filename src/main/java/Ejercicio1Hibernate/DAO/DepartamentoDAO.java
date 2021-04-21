package Ejercicio1Hibernate.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import Ejercicio1Hibernate.HibernateFiles.CodeGeneration.Departamento;

public class DepartamentoDAO {

	public static void insertDepartamentos(Session s, int numProvider) {
		for (int id = 1; id <= numProvider; id++) {
			insertDepartamento(s, id);
		}
	}

	public static void insertDepartamento(Session s, int codigo) {
		String nombre = "Nombre " + codigo;
		int codResponsable = 60000 + codigo;

		Departamento departamento = new Departamento(codigo, nombre, codResponsable);
		s.save(departamento);
	}

	public static List<Departamento> getAllDepartamnts(Session s) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Departamento.class);
		List<Departamento> result = criteria.getExecutableCriteria(s).list();
		return result;
	}

	public static Departamento getDepartament(Session s, int departmentId) {
		// deprecado desde 5.2
		Criteria criteria = s.createCriteria(Departamento.class);
		Departamento result = (Departamento) criteria.add(Restrictions.eq("providerId", departmentId)).setMaxResults(1)
				.uniqueResult();
		return result;
	}

}
