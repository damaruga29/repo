package com.test.servicea.databaseconfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  entityManagerFactoryRef = "postgresEntityManagerFactory",
  transactionManagerRef = "postgresTransactionManager",
  basePackages = { "com.test.servicea.postgresrepository" }
)
public class PostgresDataSourceConfig {

    @Bean(name = "postgresDataSource")
    //@ConfigurationProperties(prefix = "spring.postgres.datasource")
    public DataSource postgresDataSource() {
    	HikariConfig hikariConfig = new HikariConfig();
    	
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/servicea");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("password");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        
        // HikariCP Pool Settings
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(2); //its optional, it will take 10% of the maximum pool size
        hikariConfig.setConnectionTimeout(30000); //controls how long a thread will wait for a connection when the pool is full. 30sec
        hikariConfig.setIdleTimeout(600000); //connection hasn't been used for 10 minutes, it will be closed to free up resources. 10min

        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = "postgresEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("postgresDataSource") DataSource postgresDataSource) {
        return builder
                .dataSource(postgresDataSource)
                .packages("com.test.servicea.entity")
                .build();
    }

    @Bean(name = "postgresTransactionManager")
    public PlatformTransactionManager postgresTransactionManager(
            @Qualifier("postgresEntityManagerFactory") EntityManagerFactory postgresEntityManagerFactory) {
        return new JpaTransactionManager(postgresEntityManagerFactory);
    }
    
}


