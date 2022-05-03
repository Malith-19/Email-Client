import java.io.IOException;

public interface Observable {
   void notifyObservers() throws IOException;
}
