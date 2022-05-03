import java.util.LinkedList;

public class BlockingQueue {

    private final int max_queue_size;

    private LinkedList<RecievedEmail> mailList;

    public BlockingQueue(int Size) {

        mailList = new LinkedList<RecievedEmail>();
        this.max_queue_size = Size;
    }


    public synchronized void enqueue(RecievedEmail mail){
        try{

            while(mailList.size()>=max_queue_size){
                wait();
            }

            mailList.add(mail);

            notifyAll();
        }
        catch(Exception e){

        }
    }


    public synchronized RecievedEmail dequeue(){
        try{

            while(mailList.size()==0){

                wait();
            }

            RecievedEmail lastmail = mailList.remove();
            notifyAll();
            return lastmail;
        }
        catch(Exception e){
            return null;
        }
    }



}
