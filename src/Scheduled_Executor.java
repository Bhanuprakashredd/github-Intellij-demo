import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class Woe implements Runnable{
    public void run(){
      System.out.println("ThreadPool Size is similar to 2");
    }

}
public class Scheduled_Executor {
    public static void main(String[] args){
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(new Woe(),1000,2000, TimeUnit.MILLISECONDS);
    }
}
