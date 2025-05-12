import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockWorker{
    private static Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    public static void Produce() throws InterruptedException{
        lock.lock();
        System.out.println("Produce methods runs..");
        condition.await();
        System.out.println("Producer Method Runs..");
        lock.unlock();
    }
    public static void Consumer() throws InterruptedException{
        lock.lock();
        Thread.sleep(1000);
        System.out.println("Consumer method Starts..");
        condition.signal();
        Thread.sleep(1000);
        lock.unlock();
    }
}
public class Lock_Condition {
    public static void main(String[] args){
        LockWorker wor = new LockWorker();
        Thread t1 = new Thread(new Runnable(){
            public void run(){
                try {
                    wor.Produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t2 = new Thread(new Runnable(){
            public void run(){
                try {
                    wor.Consumer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();
        t2.start();
    }
}
