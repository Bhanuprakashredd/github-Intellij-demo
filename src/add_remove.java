import java.util.*;
class Process{
    private List<Integer> l1 = new ArrayList<>();
    private static int Max_Num = 5;
    private static int Min_Num = 0;
    private int value = 0;
    private final Object lock = new Object();

    public void Produce() throws InterruptedException {
        synchronized(lock){
            while(true){
                if(value==Max_Num){
                    System.out.println("the list is full..");
                    lock.wait();
                }
                else{
                    System.out.println("Adding the number:"+value);
                    l1.add(value);
                    lock.notify();
                    value++;
                }
                Thread.sleep(1000);
            }
        }
    }
    public void Consumer() throws InterruptedException {
        synchronized(lock){
            while(true){
                if(l1.size()==Min_Num){
                    System.out.println("the list is empty..");
                    value = 0;
                    lock.wait();
                }
                else{
                    System.out.println("Removing:"+l1.remove(l1.size()-1));
                    lock.notify();
                    value++;
                }
                Thread.sleep(1000);
            }
        }
    }
}
public class add_remove {
    public static void main(String[] args){
        Process process = new Process();
        Thread t1 = new Thread(new Runnable(){
            public void run(){
                try {
                    process.Produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t2 = new Thread(new Runnable(){
            public void run(){
                try {
                    process.Consumer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();
        t2.start();
    }
}
