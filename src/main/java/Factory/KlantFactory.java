/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Dao.*;
import pojo.*;


/**
 *
 * @author Johan
 */
public abstract class KlantFactory {
    
    public KlantInterface DetermineKlantDatabse(){
        Misc misc = new Misc();
        int i = misc.getFactory();
        KlantInterface klantFactory = null;
        
        if (i == 1 ){
           klantFactory = ProduceerKlantM.produceerKlantMysql();
        } else if ( i == 2){
           klantFactory = ProduceerKlantF.produceerKlantFirebird();
        }
        return klantFactory;
    }
    
    
    
}
    

