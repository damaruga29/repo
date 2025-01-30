package com.test.servicea.databaseconfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
  entityManagerFactoryRef = "accountEntityManagerFactory",
  transactionManagerRef = "accountTransactionManager",
  basePackages = { "com.test.servicea.mysqlrepository" }
)
public class MySqlDataSourceConfig {
	
	@Bean(name="accountDataSource")
	@Primary
	//@ConfigurationProperties(prefix="spring.mysql.datasource")
	public DataSource accountDataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		
		hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/servicea?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("whatsyournameplease");
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");

        // HikariCP Pool Settings
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(2); //its optional, it will take 10% of the maximum pool size
        hikariConfig.setConnectionTimeout(30000); //controls how long a thread will wait for a connection when the pool is full. 30sec
        hikariConfig.setIdleTimeout(600000);  //connection hasn't been used for 10 minutes, it will be closed to free up resources. 10min

        return new HikariDataSource(hikariConfig);
	}
	
	@Primary
	@Bean(name = "accountEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean accountEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("accountDataSource") DataSource accountDataSource) {
		return builder
				.dataSource(accountDataSource)
				.packages("com.test.servicea.entity")
				.build();
	}
	
	@Bean(name = "accountTransactionManager")
	public PlatformTransactionManager accountTransactionManager(
			@Qualifier("accountEntityManagerFactory") EntityManagerFactory accountEntityManagerFactory) {
		return new JpaTransactionManager(accountEntityManagerFactory);
	}

}
