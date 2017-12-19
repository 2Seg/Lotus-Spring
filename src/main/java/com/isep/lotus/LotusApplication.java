package com.isep.lotus;


import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class LotusApplication {

	private static final SessionFactory ourSessionFactory;

	static {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			ourSessionFactory = configuration.buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession() throws HibernateException {
		return ourSessionFactory.openSession();
	}



	public static void main(final String[] args) throws Exception {
		SpringApplication.run(LotusApplication.class, args);

//		final Session session = getSession();
//		try {
//			System.out.println("querying all the managed models...");
//			final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
//			for (Object key : metadataMap.keySet()) {
//				final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
//				final String entityName = classMetadata.getEntityName();
//				final Query query = session.createQuery("from " + entityName);
//				System.out.println("executing: " + query.getQueryString());
//				for (Object o : query.list()) {
//					System.out.println("  " + o);
//				}
//			}
//		} finally {
//			session.close();
//		}




	}

}
