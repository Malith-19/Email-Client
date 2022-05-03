import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class EmailSerializer extends Thread{
    private BlockingQueue blockingQueue;

    EmailSerializer(BlockingQueue blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    public void run(){
        while(true){
            try{

                RecievedEmail recievedEmail = blockingQueue.dequeue();


                File file = new File("EmailCollection.txt");


                FileOutputStream fos = new FileOutputStream(file,true);


                ObjectOutputStream oos = new ObjectOutputStream(fos);


                oos.writeObject(recievedEmail);


                oos.close();
                fos.close();


            }catch (Exception e){
                System.out.println("Serialization Error!!!");
                e.printStackTrace();
            }
        }
    }



}

