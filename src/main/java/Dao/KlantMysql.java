/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import Misc.*;
import java.beans.PropertyVetoException;
import pojo.Klant;
import org.slf4j.*;
import Factory.*;
/**
 *
 * @author Johan
 */
public class KlantMysql implements KlantInterface {
    
    Connector connector = new Connector();
    static Logger LOGGER = LoggerFactory.getLogger(KlantMysql.class);
    
    public KlantMysql(){
        
    }
    
    public void createKlant(Klant klant) throws PropertyVetoException, ClassNotFoundException{
        
        try{ 
            
            Connection connection = connector.getConnection();
            PreparedStatement preparedstatement = connection.prepareStatement("insert into KLANT(voornaam, achternaam, " 
                + "tussenvoegsel, email) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            
            
            
            
            
            preparedstatement.setString(1,klant.getVoorNaam());
            preparedstatement.setString(2,klant.getAchterNaam() );
            preparedstatement.setString(3,klant.getTussenVoegsel());
            preparedstatement.setString(4,klant.getEmail());
            preparedstatement.executeUpdate();
            
            ResultSet resultSet = preparedstatement.getGeneratedKeys();
            
            if (resultSet.isBeforeFirst()){
                resultSet.next();
                klant.setId(resultSet.getInt(1)); //wijs door db gegenereerde id toe aan klant
            }
            LOGGER.info("klant aangemaakt");
            
        }catch(SQLException ex){
          LOGGER.info("iets mis, geen klant aangemaakt");
        }
    }
    
    public ArrayList<Klant> readKlantId(int id){
        ArrayList enkel = new ArrayList();
        
        try{ 
            Connection connection = connector.getConnection();
            
            
            Statement stmt = connection.createStatement();

            String sql = "SELECT voornaam, achternaam " 
                + "tussenvoegsel, email FROM KLANT WHERE klant_id = " + id;
            ResultSet rs = stmt.executeQuery(sql);
            
            Klant klant = new Klant();
            
            if (rs.next()){
                klant.setVoornaam(rs.getString("voornaam"));
                klant.setAchternaam(rs.getString("achternaam"));
                klant.setTussenVoegsel(rs.getString("tussenvoegsel"));
                klant.setEmail(rs.getString ("email"));
                enkel.add(klant);
            }
            LOGGER.info("klant uitgelezen");
        }catch(Exception ex){
          LOGGER.info("iets misgegaan, geen klant uitgelezen");
        }
        return enkel;
    }
    
    public ArrayList<Klant> readKlantNaam(String voornaam, String achternaam){
        
        ArrayList enkel = new ArrayList();
        
        try{ 
            Connection connection = connector.getConnection();
            
            
            
            Statement stmt = connection.createStatement();
            
            String sql1 = "SELECT * FROM klant WHERE voornaam = '" + voornaam + "' AND achternaam = '" + achternaam + "'";
            
            
            ResultSet rs = stmt.executeQuery(sql1);
            
            
            Klant klant = new Klant();
            
            if (rs.next()){
                klant.setVoornaam(rs.getString("voornaam"));
                klant.setAchternaam(rs.getString("achternaam"));
                klant.setTussenVoegsel(rs.getString("tussenvoegsel"));
                klant.setEmail(rs.getString ("email"));
                klant.setId(rs.getInt("klant_id"));
                enkel.add(klant);
            }
            LOGGER.info("klant uitgelezen");
            
                 
        }catch(Exception ex){
          LOGGER.info("iets mis, geen klant uitgelezen");
        }
        return enkel;
    }
    
    public ArrayList<Klant> readAll(){
        ArrayList<Klant> alles = new ArrayList<>();
        try{
            Connection connection = connector.getConnection();
            Statement stmt = connection.createStatement();
            

            String sql = "SELECT klant_id, voornaam, achternaam, " 
                + "tussenvoegsel, email FROM KLANT";
            ResultSet rs = stmt.executeQuery(sql);
      
            while(rs.next()){
                Klant klant = new Klant();
                klant.setVoornaam(rs.getString("voornaam"));
                klant.setAchternaam(rs.getString("achternaam"));
                klant.setTussenVoegsel(rs.getString("tussenvoegsel"));
                klant.setEmail(rs.getString ("email"));
                //int id = rs.getInt("klant_id");
                alles.add(klant);
                
        
            }
            LOGGER.info("alle klanten uitgelezen");
  
      }
        catch(Exception ex){
          LOGGER.info("iets misgegaan, alle klanten niet uitgelezen");
        }
        return alles;
    }
    
    
    public void deleteKlantID(int id){
        try{
            
            Connection connection = connector.getConnection();
            
            Statement stmt = connection.createStatement();
            String sql = "DELETE  FROM Klant " +
                   "WHERE klant_id = " + id ;
            stmt.executeUpdate(sql);
            
            LOGGER.info("klant verwijderd");
      }
        catch(Exception ex){
            LOGGER.info("iets misgegaan, klant niet verwijderd");
        }
    }
    
    public void deleteKlantNaam(String voornaam, String achternaam){ 
        try{
            
            Connection connection = connector.getConnection();
                
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM Klant " +
                   "WHERE voornaam = '" + voornaam + "' AND achternaam = '" + achternaam + "'";
            statement.executeUpdate(sql);
            LOGGER.info("klant verwijderd");
                
            }
            catch(Exception e){
                LOGGER.info("iets misgegaan, klant niet verwijderd");
            }
          
    }
    public void updateKlant(Klant klant, int id){
        Scanner input = new Scanner(System.in);
        try{ 
            connector.getConnection();
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "Johan", "Schoemaker");
            
            PreparedStatement stmt = connection.prepareStatement("UPDATE klant SET voornaam = ?, achternaam = ?, tussenvoegsel = ?, email = ? WHERE klant_id = " + id);
            
            stmt.setString(1, klant.getVoorNaam());
            stmt.setString(2, klant.getAchterNaam());
            stmt.setString(3, klant.getTussenVoegsel());
            stmt.setString(4, klant.getEmail());
            stmt.executeUpdate();
            
        }
        catch(Exception ex){
            LOGGER.info("iets misgegaan, klant niet geüpdate");
        }
    }
}
