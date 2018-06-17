package com.foobar;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "barEntityManagerFactory",
    transactionManagerRef = "barTransactionManager", basePackages = {"com.foobar.bar.repo"})
public class BarDbConfig {

  @Bean(name = "barDataSource")
  @ConfigurationProperties(prefix = "bar.datasource")
  public DataSource dataSource() {
	  DataSource d= DataSourceBuilder.create().build();
	  return d;
  }

  @Bean(name = "barEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean barEntityManagerFactory(
      EntityManagerFactoryBuilder builder, @Qualifier("barDataSource") DataSource dataSource) {
    LocalContainerEntityManagerFactoryBean i= builder.dataSource(dataSource).packages("com.foobar.bar.domain").persistenceUnit("bar")
        .build();
    Map<String, Object> properties = new HashMap<>();
    properties
    .put(org.hibernate.cfg.Environment.HBM2DDL_AUTO,"create");
    properties
    .put(org.hibernate.cfg.Environment.USE_NEW_ID_GENERATOR_MAPPINGS,false);
    i.setJpaPropertyMap(properties);
    return i;
  }

  @Bean(name = "barTransactionManager")
  public PlatformTransactionManager barTransactionManager(
      @Qualifier("barEntityManagerFactory") EntityManagerFactory barEntityManagerFactory) {
    return new JpaTransactionManager(barEntityManagerFactory);
  }

}
