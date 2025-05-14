import java.util.concurrent.atomic.AtomicInteger;

public class Atomic_Integer {
    private static final AtomicInteger Counter = new AtomicInteger(0);
    public static void increment() {
        for (int i = 0; i < 100; i++) {
            Counter.getAndIncrement();
        }
    }
    public static void main(String[] args){
        Thread t1 = new Thread(new Runnable(){
            public void run(){
                increment();
            }
        });
        Thread t2 = new Thread(new Runnable(){
            public void run(){
                increment();
                System.out.println(Counter);
            }
        });
        t1.start();
        t2.start();
    }
}
