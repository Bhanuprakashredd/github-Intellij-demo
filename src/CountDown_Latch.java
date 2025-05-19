import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Wor implements Runnable{
    private CountDownLatch latch;
    private int ID;
    public void run(){
        DoWork();
        latch.countDown()
;    }
    public Wor(int ID,CountDownLatch latch){
        this.ID = ID;
        this.latch = latch;
    }
    private void DoWork(){
        try {
            System.out.println("The Current Thread running_Id" + " " + ID);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
public class CountDown_Latch {
    public static void main(String[] args){
        CountDownLatch latch = new CountDownLatch(10);
        ExecutorService service = Executors.newSingleThreadExecutor();
        for(int i=0;i<5;i++){
            service.execute(new Wor(i,latch));
        }
        try{
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("the execution is completed");
        service.shutdown();
    }
}
