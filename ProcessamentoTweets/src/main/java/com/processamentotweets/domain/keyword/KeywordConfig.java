package com.processamentotweets.domain.keyword;

/**
 * Created by denis on 23/06/17.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({ "classpath:persistence-multiple-db.properties" })
@EnableJpaRepositories(
        basePackages = "com.processamentotweets.domain.keyword",
        entityManagerFactoryRef = "keywordEntityManager",
        transactionManagerRef = "keywordTransactionManager"
)
public class KeywordConfig {
    @Autowired
    private Environment env;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean keywordEntityManager(
        EntityManagerFactoryBuilder builder,
        @Qualifier("keywordDataSource") DataSource dataSource) {
            return builder
                    .dataSource(dataSource)
                    .packages("com.processamentotweets.domain.keyword")
                    .persistenceUnit("keyword")
                    .build();
    }

    @Bean
    @Primary
    public DataSource keywordDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("keyword.jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));

        return dataSource;
    }

    @Bean
    @Primary
    public PlatformTransactionManager keywordTransactionManager(
        @Qualifier("keywordEntityManager") EntityManagerFactory entityManagerFactory) {
            return new JpaTransactionManager(entityManagerFactory);
    }
}
