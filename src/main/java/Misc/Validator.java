/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Misc;

import java.util.Scanner;
import org.apache.commons.validator.routines.EmailValidator;

/**
 *
 * @author Johan
 */
public class Validator {
    
    /*public static String postcode(){
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        String postcodeToBeValidated = null;
        
        while(valid == false){
            System.out.println("postcode?");
            postcodeToBeValidated = input.nextLine();
            if (PostcodeValidator(postcodeToBeValidated) == true){   
                valid = true;
            }else {
                System.out.println("foutieve invoer");
            }
        }
        return postcodeToBeValidated;
    }*/

    public static Boolean PostcodeValidator(String Postcode){
        Boolean valid = false;
        
        if(Character.isDigit(Postcode.charAt(0)) == true &&
                Character.isDigit(Postcode.charAt(1)) == true &&
                        Character.isDigit(Postcode.charAt(2)) == true &&
                                Character.isDigit(Postcode.charAt(3)) == true &&
                                       Character.isLetter(Postcode.charAt(4)) == true &&
                                            Character.isLetter(Postcode.charAt(5)) == true){
                                                valid = true;
        }
        return valid;
    }
    
    public static String email(){
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        String mailToBeValidated = null;
        EmailValidator emailVal = EmailValidator.getInstance();
        
        while(valid == false){
            System.out.println("email?");
            mailToBeValidated = input.nextLine();
            if (emailVal.isValid(mailToBeValidated) == true){
                valid = true;
            }else {
                System.out.println("foutieve invoer");
            }
        }
        return mailToBeValidated;
    }

    public static String postcode() {
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        String postcodeToBeValidated = null;
        
        while(valid == false){
            System.out.println("postcode?");
            postcodeToBeValidated = input.nextLine();
            if (PostcodeValidator(postcodeToBeValidated) == true){   
                valid = true;
            }else {
                System.out.println("foutieve invoer");
            }
        }
        return postcodeToBeValidated;//To change body of generated methods, choose Tools | Templates.
    }
    
}
