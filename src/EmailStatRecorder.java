import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmailStatRecorder implements Observer{



    @Override
    public void update() throws IOException {
        FileWriter fw  = new FileWriter("EmailLog.txt",true);

        Date date = new Date();
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String time = dateFormat.format(date);
        fw.append("an email is received at "+time+"\n");
        fw.close();

    }
}
