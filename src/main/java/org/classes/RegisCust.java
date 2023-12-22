package org.classes;

import org.entities.Regis;
import org.entities.Database;


import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisCust {
public static Logger logger=Logger.getLogger(RegisCust.class.getName());
    public Regis recordCustomer(){
        Scanner in=new Scanner(System.in);
        Regis regis = new Regis();
        logger.info("Enter customer Name: ");
        regis.setname(in.nextLine());
        logger.info("Enter customer Email:  ");
        String email = in.nextLine();

            while (true) {
                if (isValidEmail(email)) {
                    regis.setEmail(email);
                    break; // Exit the while loop
                } else {
                    System.out.println("Invalid Email... Please try another email!");
                    email = in.nextLine(); // Get a new email
                }
            }

        logger.info("Enter customer Phone:  ");
        regis.setPhone(in.nextLine());
        logger.info("Enter customer Address:  ");
        regis.setAddress(in.nextLine());
        logger.info("Enter customer Password:  ");
        regis.setPassword(in.nextLine());
        regis.setId(Database.get_Id());
        if(regis.isUnUniqueEmail()){//dosnt work
            logger.info("This email is already taken.....PLEAS! Enter new Email");
          //  logger.info("Enter new Email");
            regis.setEmail(in.nextLine());

        }

        addNewCustomer(regis);
        return regis;

    }
    public void addNewCustomer(Regis regis) {
        Database.storeObject("RecordCustomer",regis);
        Login2 login=new Login2();
        login.setEmail(regis.getEmail());
        login.setPassword(regis.getPassword());
        login.setRul("customer");
        Database.storeObject("Login",login);
        logger.info("You have been one of our Customer, Thank you!");
}
    public void takenMsg() {
        logger.info("This email is already taken  ");
    }
    public boolean isValidEmail(String email){

        String enterEmail="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,9}$";
        Pattern pattern=Pattern.compile(enterEmail,Pattern.CASE_INSENSITIVE);
        Matcher matcher=pattern.matcher(email);
        return matcher.find();
    }


  }
