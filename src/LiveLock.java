import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLock {
    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();
    public static void main(String[] args){
        Thread t1 = new Thread(new Runnable(){
            public void run(){
                Worker1();
            }
        });
        Thread t2 = new Thread(new Runnable(){
            public void run(){
                Worker2();
            }
        });
        t1.start();
        t2.start();
    }
    public static void Worker1(){
        while(true){
          try{
              lock1.tryLock(50, TimeUnit.MILLISECONDS);
              System.out.println("Worker1 is aquired lock1..");
          } catch (InterruptedException e) {
              throw new RuntimeException(e);
          }
          System.out.println("Worker1 tries to aquires the lock2..");
          if(lock2.tryLock()){
              System.out.println("Worker1 aquired the lock2...");
              lock2.unlock();
          }
          else{
              System.out.println("worker1 cannot aquires the lock2");
          }
          lock1.unlock();
        }

    }
    public static void Worker2(){
        while(true){
            try{
                lock2.tryLock(50,TimeUnit.MILLISECONDS);
                System.out.println("Worker2 aquired the lock2");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Worker2 tries to Aquires the lock1...");
            if(lock1.tryLock()){
                System.out.println("WORKER2 Aquires the lock1..");
                lock1.unlock();
            }
            else{
                System.out.println("Worker2 cannot aquires the lock1...");
            }
            lock2.unlock();

        }
    }
}
