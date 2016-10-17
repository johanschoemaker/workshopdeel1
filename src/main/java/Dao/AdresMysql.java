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
import pojo.*;
import org.slf4j.*;
import Factory.*;
/**
 *
 * @author Johan
 */
public class AdresMysql implements AdresInterface {
    
    Connector connector = new Connector();
    static Logger LOGGER = LoggerFactory.getLogger(AdresMysql.class);
   
    
    public void createAdres(Klant klant,Adres adres) throws PropertyVetoException{
        try{
            connector.getConnection();
        }catch(SQLException iets){
            
        }
        
        
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "Johan", "Schoemaker"); 
                
            
            PreparedStatement preparedstatement = connection.prepareStatement("insert into ADRES(straatnaam, postcode, " 
                + "toevoeging, huisnummer, woonplaats, klant) values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ){ 
            System.out.println("connection created");
            
            
            
            
            
            preparedstatement.setString(1,adres.getStraatNaam());
            preparedstatement.setString(2,adres.getPostCode() );
            preparedstatement.setString(3,adres.getToevoeging());
            preparedstatement.setInt(4,adres.getHuisNummer());
            preparedstatement.setString(5,adres.getWoonPlaats());
            preparedstatement.setInt(6,klant.getId());
            preparedstatement.executeUpdate();
            
            ResultSet resultSet = preparedstatement.getGeneratedKeys();
            
            if (resultSet.isBeforeFirst()){
                resultSet.next();
                klant.setId(resultSet.getInt(1)); //wijs door db gegenereerde id toe aan klant
            }
            
        }catch(SQLException ex){
          ex.printStackTrace();
        }
            
        
    }
    public ArrayList<Adres> readAdresId(int id){
        ArrayList enkel = new ArrayList();
        
        try{ 
            connector.getConnection();
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "Johan", "Schoemaker");
            
            
            Statement stmt = connection.createStatement();

            String sql = "SELECT straatnaam, postcode, " 
                + "toevoeging, huisnummer, woonplaats FROM ADRES WHERE klant = " + id;
            ResultSet rs = stmt.executeQuery(sql);
            
            Adres adres = new Adres();
            
            if (rs.next()){
                adres.setStraatNaam(rs.getString("straatnaam"));
                adres.setPostCode(rs.getString("postcode"));
                adres.setToevoeging(rs.getString("toevoeging"));
                adres.setHuisNummer(rs.getInt ("huisnummer"));
                adres.setWoonPlaats(rs.getString("woonplaats"));
                enkel.add(adres);
            }     
        }catch(Exception ex){
          ex.printStackTrace();
        }
        return enkel;
    }
    
    /*public ArrayList<Klant> readKlantNaam(String voornaam, String achternaam){
        
        ArrayList enkel = new ArrayList();
        
        try{ 
            connector.getConnection();
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "Johan", "Schoemaker");
            
            //int id = klant.getId();
            
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM klant WHERE voornaam = '" + voornaam + "' AND achternaam = '" + achternaam + "'";
            //String sql = "SELECT voornaam, achternaam " 
            //    + "tussenvoegsel, email FROM KLANT WHERE voornaam = " + voornaam + ", achternaam = " + achternaam + ", tussenvoegsel = " + tussenvoegsel;
            ResultSet rs = stmt.executeQuery(sql);
            
            Klant klant = new Klant();
            
            if (rs.next()){
                klant.setVoornaam(rs.getString("voornaam"));
                klant.setAchternaam(rs.getString("achternaam"));
                klant.setTussenVoegsel(rs.getString("tussenvoegsel"));
                klant.setEmail(rs.getString ("email"));
                enkel.add(klant);
            }
                 
        }catch(Exception ex){
          ex.printStackTrace();
        }
        return enkel;
    }*/
    
    public ArrayList<Adres> readAllAdres(int id){
        ArrayList<Adres> alles = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "Johan", "Schoemaker");
                Statement stmt = connection.createStatement();){
            connector.getConnection();
            
            
            System.out.println("database connected");
            
            

            String sql = "SELECT straatnaam, postcode, " 
                + "toevoeging, huisnummer, woonplaats FROM ADRES WHERE klant = " + id;
            ResultSet rs = stmt.executeQuery(sql);
      
            while(rs.next()){
                Adres adres = new Adres();
                adres.setStraatNaam(rs.getString("straatnaam"));
                adres.setPostCode(rs.getString("postcode"));
                adres.setToevoeging(rs.getString("toevoeging"));
                adres.setHuisNummer(rs.getInt ("huisnummer"));
                adres.setWoonPlaats(rs.getString("woonplaats"));
                alles.add(adres);
        
            }
  
      }
        catch(Exception ex){
          ex.printStackTrace();
        }
        return alles;
    }
    
    public void deleteAdres(int id){
        try{
            
            connector.getConnection();
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "Johan", "Schoemaker");
            
            Statement stmt = connection.createStatement();
            String sql = "DELETE  FROM adres " +
                   "WHERE klant = " + id ;
            stmt.executeUpdate(sql);
            
            LOGGER.info("adres verwijderd");
      }
        catch(Exception ex){
            LOGGER.info("iets misgegaan, klant niet verwijderd");
        }
    
    }
    
    public void updateKlant(Klant klant){
        
        try{ 
            connector.getConnection();
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "Johan", "Schoemaker");
            PreparedStatement stmt = connection.prepareStatement("UPDATE klant SET voornaam = ?, achternaam = ?, tussenvoegsel = ?, email = ? WHERE klant_id = ?");
            
            stmt.setString(1, klant.getVoorNaam());
            stmt.setString(2, klant.getAchterNaam());
            stmt.setString(3, klant.getTussenVoegsel());
            stmt.setString(4, klant.getEmail());
            stmt.setInt(5, klant.getId());            
            stmt.executeUpdate();
            
        }
        catch(Exception ex){
        
        }
    }
    
}
