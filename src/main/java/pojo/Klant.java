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
public class Klant {
    
    public Klant(){

    }
    
    private int id;
    private String voorNaam;
    private String achterNaam;
    private String tussenVoegsel;
    private String email;
    
    public void setId(int i){
        id = i;
    }
    
    public void setVoornaam(String voor){
        voorNaam = voor;
    }
    
    public void setAchternaam(String achter){
        achterNaam = achter;
    }
    
    public void setTussenVoegsel(String tussen){
        tussenVoegsel = tussen;
    }
    
    public void setEmail(String e){
        email = e;
    }
    
    public int getId(){
        return id;
    }
    
    public String getVoorNaam(){
        return voorNaam;
    }
    
    public String getAchterNaam(){
        return achterNaam;
    }
    
    public String getTussenVoegsel(){
        return tussenVoegsel;
    }
    
    public String getEmail(){
        return email;
    }
    @Override 
    public String toString(){
        return voorNaam + " " + achterNaam + " " + tussenVoegsel + " " + email;
    }
}
