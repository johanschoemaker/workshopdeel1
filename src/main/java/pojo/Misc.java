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
public class Misc {
    
    private int whichConnector;
    private int factory;
    
    public void setConnector(int i){
        whichConnector = i;
    }
    
    public int getConnector(){
        return whichConnector;
    }
    
    public void setFactory(int i){
        factory = i;
    }
    
    public int getFactory(){
        return factory;
    }
    
}
