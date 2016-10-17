/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Run;

import java.beans.PropertyVetoException;
import java.util.Scanner;
import pojo.*;

/**
 *
 * @author Johan
 */
public class Controller {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PropertyVetoException {
        Scanner input = new Scanner(System.in);
        View view = new View();
        Misc misc = new Misc();
        int i = 0;
        int j = 2;
        int h = 1;
        
        
        
        //view.login();
        
        while (i != 7){
            misc.setConnector(j);
            misc.setFactory(h);
            view.hoofdmenu();
        
            i = input.nextInt(); 
        
            if (i == 1){
                view.nieuw();
            }
            if (i == 2){
                view.lees();
            }
            if (i == 3){
                view.update();
            }
            if (i == 4){
                view.verwijder();
            }
            if (i == 5){
                view.changeDatabase();
            }
        }
        
    }
    
}
