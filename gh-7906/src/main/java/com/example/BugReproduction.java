package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BugReproduction implements CommandLineRunner {
    public void run(String... args) {
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(new SomeTable());
        tx.commit();
    }

    private SessionFactory getSessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();

        return new MetadataSources(registry)
            .buildMetadata()
            .buildSessionFactory();
    }
}
