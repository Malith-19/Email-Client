
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.search.FlagTerm;
import javax.mail.*;
//import com.sun.mail.pop3.POP3Store;

public class EmailReciever extends Thread implements Observable{
    private BlockingQueue blockingQueue;
    private ArrayList<Observer> observers;
    private Thread emailSerializer;

    public EmailReciever(){
        blockingQueue = new BlockingQueue(100); // I put max size for blocking queue as 100.
        emailSerializer = new EmailSerializer(blockingQueue);
        observers = new ArrayList<>();
        observers.add(new EmailStatPrinter());
        observers.add(new EmailStatRecorder());
        emailSerializer.start();

    }
    public void receiveEmail() throws MessagingException {
        receiveEmail("malithdilshan91@gmail.com", "Malith-21512");

    }

    public void receiveEmail(String user,String password) throws MessagingException {
        try {
            //1) get the session object
            Properties properties = new Properties();
            properties.put("mail.store.protocol", "imaps");
            Session emailSession = Session.getDefaultInstance(properties);

            //2) create the POP3 store object and connect with the pop server
            Store emailStore = emailSession.getStore("imaps");
            emailStore.connect("imap.gmail.com",user, password);

            //3) create the folder object and open it
            Folder emailFolder = emailStore.getFolder("INBOX");
            emailFolder.open(Folder.READ_WRITE);

            //4) retrieve the messages from the folder in an array and print it
            //Message[] messages = emailFolder.getMessages();
            Message[] messages = emailFolder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
            emailFolder.setFlags(messages, new Flags(Flags.Flag.SEEN), true);
            //emailFolder.setFlags(new Message[] {messages[0]}, new Flags(Flags.Flag.SEEN), true);  //for a single message mark as read
            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                RecievedEmail email = new RecievedEmail(message.getSubject(),message.getFrom().toString());
                blockingQueue.enqueue(email); //enqueue the mail to blocking queue
                notifyObservers(); // notifying to observers

            }

            //5) close the store and folder objects
            emailFolder.close(false);
            emailStore.close();

        } catch (NoSuchProviderException | IOException e) {e.printStackTrace();}


    }

    public void notifyObservers() throws IOException {
        for(Observer observer:observers){
            observer.update();
        }
    }

    public void run(){
        while(true){
            try {
                receiveEmail();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }




}