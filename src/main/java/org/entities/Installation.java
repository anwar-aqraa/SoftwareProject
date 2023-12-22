package org.entities;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class Installation {
    private int id;
    private String make;
    private String model;
    private String date;
    private String time;
    private String product;
    private String email;

    public Installation() {

    }

    public Installation(int id, String product, String make, String model, String date, String time, String email) {
        this.email = email;
        this.id = id;
        this.product = product;
        this.date = date;
        this.time = time;
        this.model = model;
        this.make = make;
    }

    public Installation( String product, String make, String model, String date,String time,String email) {
        this.id = Database.getReqId();
        this.email = email;
        this.product = product;
        this.time = time;
        this.date = date;
        this.model = model;
        this.make = make;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }
    public void setProduct(String product){
        this.product=product;}
    public String getMake() {
        return make;
    }
    public void setMake(String make){
        this.make=make;}
    public String getModel() {
        return model;
    }
    public void setModel(String model){
        this.model=model;}
    public String getEmail() {
        return email;
    }
    public void setEmail(String email){
        this.email=email;}

    public void setDate(String date){
        this.date=date;}
    public String getDate() {
        return date;
    }
    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time=time;
    }

    @Override
    public String toString() {
        return  id +
                ",," + product +
                ",,"  +make+
                ",," + model +
                ",," + date +
                ",," + time +
                ",," + email +"\n";
    }
    public boolean isTakenDate(){//****************************

        List<Installation> Installation;
        Installation=Database.getInstall();
        int flag =0;

        for(Installation install: Installation){

            if(install.getDate().equals(this.getDate())){
                flag=1;


                break;
            } }
        return flag == 1;

    }

    public void sendEmail(String title,String message,String msg){
        final String user = "rubasalon5@gmail.com";
        final String emailPass = "wntxcpwbkocnjjdm";
        String to = this.getEmail();
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
                        return new javax.mail.PasswordAuthentication(user,emailPass);
                    }

                });
        try {
            Message message1 = new MimeMessage(session);
            message1.setFrom(new InternetAddress(user));
            message1.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message1.setSubject(title);
            message1.setText(message);
            Transport.send(message1);
            logger.info(msg);
        } catch (Exception ignored) {
            //ignored
        }}
    static Logger logger = Logger.getLogger(Regis.class.getName());








}