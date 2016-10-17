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
public abstract class ProduceerAdresF {
    public static AdresInterface produceerAdresFirebird(){
        AdresFirebird firebird = new AdresFirebird();
        return firebird;
    }
}
