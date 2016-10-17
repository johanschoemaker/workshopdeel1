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
public abstract class ProduceerKlantM extends KlantFactory {
    public static KlantInterface produceerKlantMysql(){
        KlantMysql mysql = new KlantMysql();
        return mysql;
    }
}
