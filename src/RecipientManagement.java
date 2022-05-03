
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class RecipientManagement {
    ArrayList<Recipient> recipientList;
    ArrayList<BirthdayWishable> Wishables;
    String date;

    RecipientManagement() throws IOException{
        recipientList = new ArrayList<>();
        Wishables = new ArrayList<>();
        loadData();
        setDate();
    }

    public void createRecipient(String data,boolean writeFile) throws IOException {
        String[] dataSet = data.split(" ");
        if (dataSet.length==1) {
            System.out.println("invalid input!");
        }

        else {
            String[] elements = dataSet[1].split(",");


            if(dataSet[0].equals("Official:")) {

                OfficialRecipient recipient = new OfficialRecipient(elements[0],elements[1],elements[2]);
                if(writeFile)
                    recipient.writeFile(data);
                recipientList.add(recipient);

            }

            else if(dataSet[0].equals("Office_friend:")) {
                OfficeFriend recipient = new OfficeFriend(elements[0],elements[1],elements[2],elements[3]);
                if(writeFile)
                    recipient.writeFile(data);
                recipientList.add(recipient);
                Wishables.add(recipient);

            }
            else if(dataSet[0].equals("Personal:")) {
                PersonalRecipient recipient = new PersonalRecipient(elements[0],elements[1],elements[2],elements[3]);
                if(writeFile)
                    recipient.writeFile(data);
                recipientList.add(recipient);
                Wishables.add(recipient);
            }
            else {
                System.out.println("invalid input!");
            }
        }


    }

    public void loadData() throws IOException {
        File file1 = new File ("clientList.txt");
        if (file1.exists()){
            Scanner reader = new Scanner(file1);
            while(reader.hasNextLine()) {
                String data = reader.nextLine();
                createRecipient(data,false);
            }
        }
    }

    public int recipientCount() {
        return Recipient.noOfRecipients;
    }

    public void getBirthdayRecivers(String date){
        date = date.substring(5);
        for(BirthdayWishable i : Wishables){
            String recipientBirthday = (i.getBirthday()).substring(5);
            if(date.equals(recipientBirthday))
                System.out.println(i.getName());

        }
    }

    private void setDate(){
        LocalDate Date = java.time.LocalDate.now();
        String date = Date.toString();

        this.date = date.substring(0,4) + "/"+date.substring(5,7)+"/"+date.substring(8,10);

    }

    public ArrayList<BirthdayWishable> getTodayBirthdayReceivers(){
        ArrayList<BirthdayWishable> todayBirthdayReceivers = new ArrayList<>();
        String today = date.substring(5);

        for(BirthdayWishable i: Wishables){

            String recipientBirthday = (i.getBirthday()).substring(5);
            if (today.equals(recipientBirthday)){
                todayBirthdayReceivers.add(i);

            }
        }
        return todayBirthdayReceivers;
    }

}
