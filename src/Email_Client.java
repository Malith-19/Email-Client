
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//190145G



public class Email_Client {


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        RecipientManagement recipientManagement = new RecipientManagement(); //creating recipient management to manage all recipients
        EmailManagement emailManagement = new EmailManagement(); // creating email management to manage all emails
        ArrayList<BirthdayWishable> todayBirthdayReceivers = recipientManagement.getTodayBirthdayReceivers(); // getting the recipient list of who has birthdays today.
        emailManagement.sentBirthdayWishes(todayBirthdayReceivers); // sending birthday wishes.

        Thread emailReciever =  new EmailReciever(); //creating thread for receive email
        emailReciever.start(); // starting the thread

        System.out.println("All birthday wishes are send!\n");



        label:
        while(true) {
            Scanner scanner = new Scanner(System.in);


            System.out.println("\nEnter option type: \n"
                    + "1 - Adding a new recipient\n"
                    + "2 - Sending an email\n"
                    + "3 - Printing out all the recipients who have birthdays\n"
                    + "4 - Printing out details of all the emails sent\n"
                    + "5 - Printing out the number of recipient objects in the application\n"
                    +"6 - end program");

            String option = scanner.nextLine();

            switch(option){
                case "1":
                    // input format - Official: nimal,nimal@gmail.com,ceo
                    // code to add a new recipient
                    // store details in clientList.txt file


                    System.out.println("Enter the data: \neg1-:Official: nimal,nimal@gmail.com,ceo \neg2-:Office_friend: kamal,kamal@gmail.com,clerk,2000/12/12\neg3-:Personal: sunil,<nick-name>,sunil@gmail.com,2000/10/10 ");
                    String data = scanner.nextLine();
                    recipientManagement.createRecipient(data,true);
                    System.out.println("Added the new recipient!");
                    break;
                case "2":
                    // input format - email, subject, content
                    // code to send an email

                    System.out.println("enter reciever's mail, subject and body with comma seperately\neg-: kamal@gmail.com,Subject,body");

                    String emailData = scanner.nextLine();
                    String[] emailDataList = emailData.split(",");
                    emailManagement.sentMail(emailDataList[0],emailDataList[1],emailDataList[2]);
                    System.out.println("Email sent!");

                    break;
                case "3":
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // code to print recipients who have birthdays on the given date

                    System.out.println("Enter the birthday in this format YYYY/MM/DD\neg-: 2021/01/01");
                    String date = scanner.nextLine();
                    recipientManagement.getBirthdayRecivers(date);

                    break;
                case "4":
                    // code to print the details of all the emails sent on the input date

                    System.out.println("enter the date in this format: YYYY/MM/DD\neg-: 2021/01/01");
                    String date1 = scanner.nextLine();
                    emailManagement.readStoredMails(date1);
                    break;
                case "5":
                    // code to print the number of recipient objects in the application
                    System.out.println("Recipient count: "+recipientManagement.recipientCount());


                    break;
                case "6":
                    emailManagement.storeMails(); //before end the program storing the data of send emails.
                    System.out.println("client ended");
                    break label;


            }
        }


    }
}


