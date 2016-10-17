/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Misc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.mchange.v2.c3p0.*;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import pojo.*;
import org.slf4j.*;

/**
 *
 * @author Johan
 */
public class Connector {
    Misc misc = new Misc();
    static Logger LOGGER = LoggerFactory.getLogger(Connector.class);
    
    public Connector(){
        
    }
    
    public Connection getConnection() throws SQLException, PropertyVetoException, ClassNotFoundException{
        int i = 1;
        //i = misc.getConnector();
        Connection connection = null;
        if(i == 1){
            connection = connectHikariMySQL();
            LOGGER.info("connectie Hikari aangemaakt");
        }
        if(i == 2){
            connection = connectJDBCMySQL();
        }
        if(1 == 3){
            connection = connectC3p0();
            LOGGER.info("connectie C3p0 aangemaakt");
        }
        return connection;
    }
    
    public Connection connectJDBCMySQL(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "Johan", "Schoemaker");
            
        }catch (SQLException ex){
            LOGGER.info("SQLException");
            LOGGER.info(ex.getMessage());
            System.out.println(ex.getErrorCode());
            System.out.println(ex.getSQLState());
        }catch (ClassNotFoundException e){
            LOGGER.info("ClassNotFoundException");
        }
        return connection;
        
        //Connection connection = DriverManager.getConnection("jdbc:mysql://Acer/mydb", "Johan", "Schoemaker");
        //return connection;
    }
    
    public Connection connectHikariMySQL(){
        HikariConfig config = new HikariConfig();
        Connection connection = null;
        try{
            config.setMinimumIdle(1);
            config.setMaximumPoolSize(2);
            config.setInitializationFailFast(true);
            config.setConnectionTestQuery("VALUES 1");
            config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
            config.addDataSourceProperty("serverName", "localhost");
            config.addDataSourceProperty("port", "3306");
            config.addDataSourceProperty("databaseName", "mydb");
            config.addDataSourceProperty("user", "Johan");
            config.addDataSourceProperty("password", "Schoemaker");
            HikariDataSource dataSource = new HikariDataSource(config);
        
            connection = dataSource.getConnection();
        }catch (SQLException ex){
            LOGGER.info("SQLException");
            LOGGER.info(ex.getMessage());
            System.out.println(ex.getErrorCode());
            System.out.println(ex.getSQLState());
        }
        
        return connection;
    }
    
    public Connection connectC3p0(){
        LOGGER.info("hij zit in ieder geval in de methode");
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        Connection connection = null;
        try{
            cpds.setDriverClass( "com.mysql.jdbc.Driver" );
            cpds.setJdbcUrl( "jdbc:mysql://Acer/mydb" );
            cpds.setUser("Johan");                                  
            cpds.setPassword("Schoemaker");
            connection = cpds.getConnection();
        }catch (PropertyVetoException ex){
            LOGGER.info("SQLException");
            LOGGER.info(ex.getMessage());
        }catch (SQLException ex){
            LOGGER.info("SQLException");
            LOGGER.info(ex.getMessage());
            System.out.println(ex.getErrorCode());
            System.out.println(ex.getSQLState());
        }
        
        return connection;
    }
        
    
    
}
