import java.io.Serializable;


public class RecievedEmail implements Serializable {
    private String subject;
    private String from;


    public RecievedEmail(String subject,String from){
        this.subject = subject;
        this.from = from;


    }
    public String getSubject(){
        return subject;
    }

    public String getFrom(){
        return from;
    }


}

