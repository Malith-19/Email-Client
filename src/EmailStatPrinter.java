import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmailStatPrinter implements Observer{

    @Override
    public void update(){

        Date date = new Date();
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String time = dateFormat.format(date);
        System.out.println("an email is received at "+time);
    }
}
