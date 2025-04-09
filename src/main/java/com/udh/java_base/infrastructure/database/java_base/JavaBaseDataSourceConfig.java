package com.udh.java_base.infrastructure.database.java_base;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = JavaBaseDataSourceConfig.BASE_PACKAGES,
        entityManagerFactoryRef = JavaBaseDataSourceConfig.ENTITY_MANAGER_FACTORY,
        transactionManagerRef = JavaBaseDataSourceConfig.TRANSACTION_MANAGER
)
public class JavaBaseDataSourceConfig {
    public static final String BASE_PACKAGES = "com.udh.java_base.infrastructure.database.java_base.repository";
    public static final String EMF_PACKAGE = "com.udh.java_base.infrastructure.database.java_base.entity";
    public static final String DB_NAME = "javabase";
    public static final String DATA_SOURCE = DB_NAME + "DataSource";
    public static final String ENTITY_MANAGER_FACTORY = DB_NAME + "EntityManagerFactory";
    public static final String TRANSACTION_MANAGER = DB_NAME + "TransactionManager";
    public static final String PERSISTENCE_UNIT = DB_NAME + "PersistenceUnit";


    @Primary
    @Bean(name = DATA_SOURCE)
    @ConfigurationProperties(prefix = "datasource." + DB_NAME)
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = ENTITY_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier(DATA_SOURCE) DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages(EMF_PACKAGE) // entity MySQL
                .persistenceUnit(PERSISTENCE_UNIT)
                .build();
    }

    @Primary
    @Bean(name = TRANSACTION_MANAGER)
    public PlatformTransactionManager mysqlTransactionManager(
            @Qualifier(ENTITY_MANAGER_FACTORY) EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
