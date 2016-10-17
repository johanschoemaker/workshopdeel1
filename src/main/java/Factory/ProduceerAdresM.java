/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Dao.*;

/**
 *
 * @author Johan
 */
public abstract class ProduceerAdresM {
     public static AdresInterface produceerAdresMysql(){
        AdresMysql mysql = new AdresMysql();
        return mysql;
    }
}

