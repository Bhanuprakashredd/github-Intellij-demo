import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Barriers implements Runnable{
    private final Random random;
    private int Id;
    private int Random;
    private CyclicBarrier barrier;

    public void run(){
        doWork();
    }
    public void doWork(){
        try{
            System.out.println("thread Id is"+ Id);
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try{
            barrier.await();
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
    public Barriers(int Id,CyclicBarrier barrier){
        this.Id = Id;
        this.random = new Random();
        this.barrier = barrier;
    }

}
public class Cyclic_Barrier {
    public static void main(String[] args){
        ExecutorService service = Executors.newFixedThreadPool(5);
        CyclicBarrier barrier = new CyclicBarrier(5,new Runnable(){
            public void run(){
                System.out.println("Execution of the Threads is completed:");
            }
        });
        for(int i=0;i<5;++i){
            service.execute(new Barriers(i+1,barrier));
        }
        service.shutdown();
    }
}
