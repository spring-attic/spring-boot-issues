package org.springframework.boot.issues.gh10465.config;

import static org.eclipse.persistence.config.PersistenceUnitProperties.DDL_DATABASE_GENERATION;
import static org.eclipse.persistence.config.PersistenceUnitProperties.DDL_GENERATION;
import static org.eclipse.persistence.config.PersistenceUnitProperties.DDL_GENERATION_MODE;
import static org.eclipse.persistence.config.PersistenceUnitProperties.DROP_AND_CREATE;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
public class JpaConfig extends JpaBaseConfiguration {
    
    private JpaProperties properties;
    
//    <version>1.4.1.RELEASE</version> //Spring Boot 
//    protected JpaConfig(DataSource dataSource, JpaProperties properties,
//            ObjectProvider<JtaTransactionManager> jtaTransactionManager) {
//        super(dataSource, properties, jtaTransactionManager);
//        this.properties = properties;
//    }
    
//  <version>1.5.9.RELEASE</version> //Spring Boot
    protected JpaConfig(DataSource dataSource, JpaProperties properties,
            ObjectProvider<JtaTransactionManager> jtaTransactionManager,
            ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
        super(dataSource, properties, jtaTransactionManager, transactionManagerCustomizers);
        this.properties = properties;
    }

    @Override
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        return new EclipseLinkJpaVendorAdapter();
    }

    @Override
    protected Map<String, Object> getVendorProperties() {
        Map<String, Object> vendorProperties = new HashMap<>();
        vendorProperties.putAll(properties.getProperties());
        vendorProperties.put(DDL_GENERATION, DROP_AND_CREATE);
        vendorProperties.put(DDL_GENERATION_MODE, DDL_DATABASE_GENERATION);

        vendorProperties.put(PersistenceUnitProperties.WEAVING, "static");

        return vendorProperties;
    }

}
