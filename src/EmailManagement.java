import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmailManagement {
    private ArrayList<Email> emails ;

    EmailManagement(){
        emails = new ArrayList<>();

    }

    public void sentMail(String reciever,String subject,String body){
        Email email = new Email(reciever, subject, body);
        emails.add(email);
        email.sendMail();
    }

    public void storeMails() throws IOException, ClassNotFoundException {
        LocalDate Date = java.time.LocalDate.now();
        String fileName = Date.toString()+".txt";
        File file = new File(fileName);


        if(file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Email> loadedEmails= (ArrayList<Email>) ois.readObject();
            ois.close();

            for(Email i:emails){
                loadedEmails.add(i);
            }
            emails = loadedEmails;
        }
        if(emails.size()!=0) {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject((Object) emails);
            oos.close();
        }

    }

    public void readStoredMails(String date) throws IOException, ClassNotFoundException {
        String[] dateSet = date.split("/");
        String filename =  dateSet[0]+"-"+dateSet[1]+"-"+dateSet[2]+".txt";
        File file = new File(filename);
        Boolean hasMails = false;

        LocalDate Date = java.time.LocalDate.now();
        String today = Date.toString();

        if((today.equals(dateSet[0]+"-"+dateSet[1]+"-"+dateSet[2])) && (emails.size()!=0)){
            hasMails = true;

            for (Email email: emails){

                System.out.println(email.getSubject() + "," + email.getReciever());

            }
        }
        if(file.exists()) {
            hasMails = true;
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Email> loadedEmails= (ArrayList<Email>) ois.readObject();
            ois.close();



            for (Email i : loadedEmails) {
                System.out.println(i.getSubject() + "," + i.getReciever());

            }
        }
        if(!hasMails)
            System.out.println("No emails sent on this day!");
    }

    public void sentBirthdayWishes(ArrayList<BirthdayWishable> todayBirthdayReceivers){

        for(BirthdayWishable i : todayBirthdayReceivers){

            if(i instanceof OfficeFriend){
                sentMail(((OfficeFriend) i).getMailAddress(),"Birthday Greeting","Wish you a Happy Birthday.\n-Malith Dilshan");
            }
            else
                sentMail(((PersonalRecipient)i).getMailAddress(),"Birthday Greeting","Hugs and love on your birthday.\n-Malith Dilshan");
        }
    }
}
