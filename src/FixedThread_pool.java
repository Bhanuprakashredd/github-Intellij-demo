import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Work1 implements Runnable{
    private int id;
    public Work1(int id){
        this.id = id;
    }
    public void run(){
        System.out.println("the work id:"+id+"the thread id"+Thread.currentThread().getName());
        long duration = (long) (Math.random()*5);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
public class FixedThread_pool {
    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for(int i=1;i<6;i++) {
            executor.execute(new Work(i));
        }
    }
}

