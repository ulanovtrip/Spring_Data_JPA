package ru.itis.spring.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties") // указывает где взять конфигурационный файл
@EnableTransactionManagement
@ComponentScan("ru.itis.spring")
public class JpaConfig {

    // для чтения конфига из файла
    @Autowired
    private Environment environment;

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

    // этот бин создаёт entitymanager, фабрика ентитименеджеров
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        // тут нужно сделать адаптер для hibernate
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.POSTGRESQL);
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setJpaVendorAdapter(adapter);
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan("ru.itis.spring.repositories");
        return entityManagerFactory;
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public HikariConfig hikariConfig() {

        HikariConfig config = new HikariConfig();
        // используем конфиг файл
        config.setJdbcUrl(environment.getProperty("db.url"));
        config.setDriverClassName(environment.getProperty("db.driver-class-name"));
        config.setPassword(environment.getProperty("db.password"));
        config.setUsername(environment.getProperty("db.username"));
        config.setMaximumPoolSize(environment.getProperty("db.hikari.max-pool-size", Integer.class));
        return config;
    }
}
