
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Recipient {
    static int noOfRecipients = 0;
    private String name;
    private String mailAddress;




    Recipient(String name,String mailAddress){
        this.name = name;
        this.mailAddress = mailAddress;
        noOfRecipients++;
    }

    public String getName() {
        return name;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    abstract String showDetail();

    public void writeFile(String data) throws IOException {
        File file1 = new File ("clientList.txt");
        if(file1.exists()) {
            FileWriter file2 = new FileWriter("clientList.txt",true);
            file2.append("\n"+data);

            file2.close();
        }

        else {
            FileWriter file2 = new FileWriter("clientList.txt");

            file2.write(data);
            file2.close();
        }

    }
}
