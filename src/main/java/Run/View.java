/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Run;

import pojo.*;
import Dao.*;
import Misc.*;
import Factory.*;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Scanner;
import org.slf4j.*;

/**
 *
 * @author Johan
 */
public class View {
    
    KlantMysql klantDao = new KlantMysql();
    //KlantFactory klantDao = new KlantFactory();
    AdresMysql adresDao = new AdresMysql();
    //AdresFactory adresDao = new AdresFactory();
    static Logger LOGGER = LoggerFactory.getLogger(View.class);
    
    public void login(){
        
    }
    
    public void hoofdmenu(){
        System.out.println("Maak uw keuze");
            System.out.println("[1] Nieuwe klant");
            System.out.println("[2] Lees klantgegevens");
            System.out.println("[3] Update klantgegevens");
            System.out.println("[4] Verwijder klantgegevens");
            System.out.println("[5] verander Database");
            System.out.println("[6] wijzig connectie");
            System.out.println("[7] Stop");
    }
    
    public void nieuw() throws PropertyVetoException{
        Scanner input = new Scanner(System.in);
        Klant klant1 = new Klant();
        Adres adres1 = new Adres();
        
        System.out.println("voornaam?");
        String temp = input.nextLine();
        klant1.setVoornaam(temp);
        
        System.out.println("achternaam?");
        temp = input.nextLine();
        klant1.setAchternaam(temp);
        
        System.out.println("tussenvoegsel?");
        temp = input.nextLine();
        klant1.setTussenVoegsel(temp);
        
        klant1.setEmail(Validator.email());
        
        System.out.println("straatnaam?");
        temp = input.nextLine();
        adres1.setStraatNaam(temp);
        
        adres1.setPostCode(Validator.postcode());
        
        //System.out.println("postcode?");
        //temp = input.nextLine();
        //adres1.setPostCode(temp);
        
        System.out.println("toevoeging?");
        temp = input.nextLine();
        adres1.setToevoeging(temp);
        
        System.out.println("huisNummer?");
        int i = input.nextInt();
        adres1.setHuisNummer(i);
        
        System.out.println("woonplaats?");
        String dezedoetdusookniets = input.nextLine();
        temp = input.nextLine();
        adres1.setWoonPlaats(temp);
        
        LOGGER.info("klant en adres object aangemaakt");
        klantDao.createKlant(klant1);
        LOGGER.info("klant object doorgegeven aan createKlant");
        adresDao.createAdres(klant1, adres1);
        LOGGER.info("adres object doorgegeven aan createAdres");
        
    }
    
    public void lees(){
        Scanner input = new Scanner(System.in);
        Klant klant1 = new Klant();
        String temp;
        
        System.out.println("Maak uw keuze");
        System.out.println("[1] lees alles");
        System.out.println("[2] lees 1 klant");
        
        int i = input.nextInt();
        
        if(i == 1){
           ArrayList<Klant> klanten = klantDao.readAll();
           klantDao.readAll();
           System.out.println(klanten.size());
            for (Klant klant : klanten) {
                int id = klant.getId();
                System.out.println(klant);
                ArrayList<Adres> adressen = adresDao.readAdresId(id);
                    System.out.println(adressen.size());
                    for (Adres adres : adressen) {
                        System.out.println(adres);
                    }
            }
        }
        else{
            System.out.println("Maak uw keuze");
            System.out.println("[1] via klant_id");
            System.out.println("[2] via voornaam,achternaam");
            
            i = input.nextInt();
            
            switch (i) {
                case 1:
                    System.out.println("vul het klant_id in");
                    int j = input.nextInt();
                    ArrayList<Klant> klanten = klantDao.readKlantId(j);
                    System.out.println(klanten.size());
                    for (Klant klant : klanten) {
                        System.out.println(klant);
                    }
                    ArrayList<Adres> adressen = adresDao.readAdresId(j);
                    System.out.println(adressen.size());
                    for (Adres adres : adressen) {
                        System.out.println(adres);
                    }
                    break;
                case 2:
                    System.out.println("voornaam?");
                    String wtf = input.nextLine();
                    String voornaam = input.nextLine();
                    System.out.println("achternaam?");
                    String achternaam = input.nextLine();
                    
                    ArrayList<Klant> klant2 = klantDao.readKlantNaam(voornaam, achternaam);
                    klantDao.readKlantNaam(voornaam, achternaam);
                    System.out.println(klant2.size());
                    for (Klant klant : klant2) {
                        System.out.println(klant);
                        
                    }
                    break;
                default:
                    System.out.println("verkeerde input");
                    break;
            }
        }
    }
    
    public void verwijder(){
        Scanner input = new Scanner(System.in);
        Klant klant1 = new Klant();
        
            System.out.println("Maak uw keuze");
            System.out.println("[1] via klant_id");
            System.out.println("[2] via voornaam,achternaam, tussenvoegsel");
            
            int i = input.nextInt();
            
        switch (i) {
            case 1:
                System.out.println("vul het klant_id in");
                int j = input.nextInt();
                adresDao.deleteAdres(j);
                klantDao.deleteKlantID(j);
                break;
            case 2:
                System.out.println("voornaam?");
                String watmoetikhiermee = input.nextLine();
                String voornaam = input.nextLine();
                //klant1.setVoornaam(temp);
                System.out.println("achternaam?");
                String achternaam = input.nextLine();
                //klant1.setAchternaam(temp);
                //System.out.println("tussenvoegsel?");
                //String tussenvoegsel = input.nextLine();
                //klant1.setTussenVoegsel(temp);
                klantDao.deleteKlantNaam(voornaam, achternaam);
                break;
            default:
                System.out.println("verkeerde input");
                break;
        }
    }
    
    public void update(){
        Scanner input = new Scanner(System.in);
        Klant klant1 = new Klant();
        Adres adres1 = new Adres();
        
        System.out.println("wat is uw klant_id?");
        int h = input.nextInt();
        
        System.out.println("wat wilt u veranderen?");
        System.out.println("[1] klantgegevens");
        System.out.println("[2] adres");
        
        
        
        
        int i = input.nextInt();
        
        if ( i == 1){
            System.out.println("voornaam?");
            String tja = input.nextLine();
            String voornaam = input.nextLine();
            klant1.setVoornaam(voornaam);
            System.out.println("achternaam?");
            String achternaam = input.nextLine();
            klant1.setAchternaam(achternaam);
            System.out.println("tussenvoegsel?");
            String tussenvoegsel = input.nextLine();
            klant1.setTussenVoegsel(tussenvoegsel);
            System.out.println("email?");
            klant1.setEmail(Validator.email());
            klantDao.updateKlant(klant1, h);
            

        }
           
    }
    public void changeDatabase(){
        Scanner input = new Scanner(System.in);
        Misc misc = new Misc();
        
        System.out.println("welke Database wilt u instellen?");
        System.out.println("[1] Mysql");
        System.out.println("[2] Firebird");
        
        int i = input.nextInt();
        misc.setFactory(i);
        
        
    }
    
}
