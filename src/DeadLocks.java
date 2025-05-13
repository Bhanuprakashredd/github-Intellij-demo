import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLocks {
    private static Lock lock1 = new ReentrantLock(true);
    private static Lock lock2 = new ReentrantLock(true);
    public static void Worker1() throws InterruptedException {
        lock1.lock();
        System.out.println("Worker1 aquires Lock1");
        Thread.sleep(1000);
        lock2.lock();
        System.out.println("Worker1 aquires lock2");
        Thread.sleep(1000);
    }
    public static void Worker2() throws InterruptedException {
        lock2.lock();
        System.out.println("Worker2 aquires Lock2");
        Thread.sleep(1000);
        lock1.lock();
        System.out.println("Worker2 aquires lock1");
        Thread.sleep(1000);
    }
    public static void main(String[] args){
        //Deadlock deadlock = new Deadlock();
        //new Thread(deadlock::Worker1,"worker1").start();
        //new Thread(deadlock::Worker2,"worker2").start();
        Thread t1 = new Thread(new Runnable(){
            public void run(){
                try {
                    Worker1();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t2 = new Thread(new Runnable(){
            public void run(){
                try {
                    Worker2();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();
        t2.start();

    }
}
