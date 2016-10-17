/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import pojo.Misc;

/**
 *
 * @author Johan
 */
public abstract class AdresFactory {
    public AdresInterface DetermineAdresDatabse(){
        Misc misc = new Misc();
        int i = misc.getFactory();
        AdresInterface adresFactory = null;
        
        if (i == 1 ){
           adresFactory = ProduceerAdresM.produceerAdresMysql();
        } else if ( i == 2){
           adresFactory = ProduceerAdresF.produceerAdresFirebird();
        }
        return adresFactory;
    }
    
}
