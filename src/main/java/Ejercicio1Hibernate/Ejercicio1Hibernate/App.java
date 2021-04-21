package Ejercicio1Hibernate.Ejercicio1Hibernate;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Hello world!
 *
 */
public class App {
	private static Logger logger = LogManager.getLogger(App.class);

	static SessionFactory sessionFactory;

	public static void main(String[] args) {
		String methodName = App.class.getSimpleName() + ".main()";
		logger.info(String.format("%1$s: >>>>>> Main execution started.", methodName));

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		int modifiedProviderId = 1;

		try {

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
