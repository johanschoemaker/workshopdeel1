/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author Johan
 */
public class Adres {
    
    public Adres(){
        
    }
    
    private String straatNaam;
    private String postCode;
    private String toeVoeging;
    private int huisNummer;
    private String woonPlaats;
    
    public void setStraatNaam(String straat){
        straatNaam = straat;
    }
    
    public void setPostCode(String code){
        postCode = code;
    }
    
    public void setToevoeging(String toe){
        toeVoeging = toe;
    }
    
    public void setHuisNummer(int nummer){
        huisNummer = nummer;
    }
    
    public void setWoonPlaats(String plaats){
        woonPlaats = plaats;
    }
    
    public String getStraatNaam(){
        return straatNaam;
    }
    
    public String getPostCode(){
        return postCode;
    }
    
    public String getToevoeging(){
        return toeVoeging;
    }
    
    public int getHuisNummer(){
        return huisNummer;
    }
    
    public String getWoonPlaats(){
        return woonPlaats;
    }
    
    @Override
    public String toString(){
        return straatNaam + " " + postCode + " " + toeVoeging + " " + huisNummer + " " + woonPlaats;
    }    
}
