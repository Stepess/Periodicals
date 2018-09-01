package model.dao.jdbc;

import javax.sql.DataSource;

import model.service.resource.manager.DataBaseManager;
import model.service.resource.manager.ResourceManager;
import org.apache.commons.dbcp2.BasicDataSource;
/*
import org.apache.commons.dbcp.BasicDataSource;
*/


public class ConnectionPoolHolder {
    private static volatile DataSource source;

    public static DataSource getSource() {
        if (source == null){
            synchronized (ConnectionPoolHolder.class) {
                if (source == null) {
                    ResourceManager manager = new DataBaseManager();
                    BasicDataSource basicSource = new BasicDataSource();
                    basicSource.setDriverClassName("com.mysql.jdbc.Driver");
                    basicSource.setUrl(manager.getProperty("db.connect.url"));
                    basicSource.setUsername(manager.getProperty("db.connect.username"));
                    basicSource.setPassword(manager.getProperty("db.connect.password"));
                    basicSource.setMinIdle(5);
                    basicSource.setMaxIdle(10);
                    basicSource.setMaxOpenPreparedStatements(100);
                    source = basicSource;
                }
            }
        }
        return source;
    }
}
