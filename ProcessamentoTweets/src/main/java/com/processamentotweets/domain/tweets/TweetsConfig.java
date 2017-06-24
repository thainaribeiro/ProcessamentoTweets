package com.processamentotweets.domain.tweets;

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

/**
 * Created by denis on 23/06/17.
 */
@Configuration
@PropertySource({ "classpath:persistence-multiple-db.properties" })
@EnableJpaRepositories(
        basePackages = "com.processamentotweets.domain.tweets",
        entityManagerFactoryRef = "tweetsEntityManager",
        transactionManagerRef = "tweetsTransactionManager"
)
public class TweetsConfig {
    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean tweetsEntityManager(
            EntityManagerFactoryBuilder builder,
            @Qualifier("tweetsDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.processamentotweets.domain.tweets")
                .persistenceUnit("tweets")
                .build();
    }

    @Bean
    public DataSource tweetsDataSource() {
        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("tweets.jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager tweetsTransactionManager(
            @Qualifier("tweetsEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
