package com.madeira.dao.search;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import com.madeira.util.DbConfig;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class SearchRepository {

    private DbConfig dbConfig;

    public List<Object> executeQuery(String sqlString) {

        Configuration configuration = new Configuration()
        .setProperty("hibernate.connection.driver_class", dbConfig.getDatasourceDriverClassName())
        .setProperty("hibernate.connection.url", dbConfig.getDatasourceUrl())
        .setProperty("hibernate.connection.username", dbConfig.getDatasourceUsername())
        .setProperty("hibernate.connection.password", dbConfig.getDatasourcePassword())
        .setProperty("hibernate.dialect", dbConfig.getHibernateDialect())
        .setProperty("hibernate.hbm2ddl.auto", dbConfig.getHibernateDdlAuto())
        .setProperty("hibernate.show_sql", dbConfig.getJpaShowSql())
        .setProperty("hibernate.format_sql", dbConfig.getHibernateFormatSql());
        
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

        Session session = sessionFactory.openSession();

        NativeQuery<Object> sqlQuery = session.createNativeQuery(sqlString, Object.class);
        List<Object> result = sqlQuery.getResultList();

        session.close();
        sessionFactory.close();
        return result;
    }

}
