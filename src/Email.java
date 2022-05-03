import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.util.Properties;

public class Email implements Serializable {
    private String reciever;
    private String subject;
    private String body;

    Email(String reciever,String subject,String body){
        this.reciever = reciever;
        this.subject = subject;
        this.body = body;
    }
    public String getReciever(){
        return reciever;
    }

    public String getSubject(){
        return subject;
    }
    public String getBody(){
        return body;
    }

    public void sendMail(){

        final String username = "malithdilshan91@gmail.com"; //edit this
        final String password = "Malith-21512"; //edit this

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("malithdilshan91@gmail.com"));//edit this
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(reciever)
            );
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);



        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}