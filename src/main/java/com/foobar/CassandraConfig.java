package com.foobar;

import java.util.Arrays;
import java.util.List;

import org.springframework.cassandra.core.keyspace.CreateKeyspaceSpecification;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages = "com.foobar.foo.repo")
public class CassandraConfig extends AbstractCassandraConfiguration {
 
	public static final String KEYSPACE = "nirav_test";

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }
    

    @Override
    @Bean
    public CassandraClusterFactoryBean cluster() {
        final CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setKeyspaceCreations(getKeyspaceCreations());
        cluster.setKeyspaceDrops(getKeyspaceDrops());
        //cluster.setContactPoints("54.211.217.4");
        cluster.setContactPoints("127.0.0.1");
       //cluster.setPort(7000);
      //  LOGGER.info("Cluster created with contact points [" + environment.getProperty("cassandra.contactpoints") + "] " + "& port [" + Integer.parseInt(environment.getProperty("cassandra.port")) + "].");
        return cluster;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        
        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(KEYSPACE);
        
        specification.ifNotExists(true);

        return Arrays.asList(specification);
    }

   /* @Override
    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
    	DropKeyspaceSpecification dropKeyspaceSpecification=DropKeyspaceSpecification.dropKeyspace(KEYSPACE);
    	dropKeyspaceSpecification.ifExists(true);
        return Arrays.asList(dropKeyspaceSpecification);
    }
*/
    @Override
    protected String getKeyspaceName() {
        return KEYSPACE;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"com.foobar.foo.domain"};
    }
}