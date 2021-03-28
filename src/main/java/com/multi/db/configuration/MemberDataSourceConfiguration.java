package com.multi.db.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.multi.db.repository.member",
        entityManagerFactoryRef = "memberEntityManagerFactory",
        transactionManagerRef= "memberTransactionManager"
)
public class MemberDataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.member")
    public DataSourceProperties memberDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.member.configuration")
    public DataSource memberDataSource() {
        return memberDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "memberEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean memberEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(memberDataSource())
                .packages("com.multi.db.model.member")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager memberTransactionManager(
            final @Qualifier("memberEntityManagerFactory") LocalContainerEntityManagerFactoryBean memberEntityManagerFactory) {
        return new JpaTransactionManager(memberEntityManagerFactory.getObject());
    }

}
