package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DevtoolsHibernateBugApplication {
	public static void main(String[] args) {
		SpringApplication.run(DevtoolsHibernateBugApplication.class, args);

		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(new SomeTable());
        tx.commit();
    }

    private static SessionFactory getSessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();

        return new MetadataSources(registry)
            .buildMetadata()
            .buildSessionFactory();
    }
}
