import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class FirstBarrier implements Runnable{
    private BlockingQueue<Integer> queue;
    public FirstBarrier(BlockingQueue<Integer> queue){
        this.queue = queue;
    }
    public void run(){
       int counter =0;
            while(true){
                try {
                    queue.put(counter);
                    System.out.println("the inserted value into the queue is"+" "+counter);
                    counter++;
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
class SecondBarrier implements Runnable{
    private BlockingQueue<Integer> queue;
    public SecondBarrier(BlockingQueue<Integer> queue){
        this.queue = queue;
    }
    public void run(){
        while(true){
            try {
                int counter = queue.take();
                System.out.println("the removed value from the queue is"+" "+counter);
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
public class Barrier_Queue {
    public static void main(String[] args){
      BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        FirstBarrier first = new FirstBarrier(queue);
        SecondBarrier second = new SecondBarrier(queue);
        new Thread(first).start();
        new Thread(second).start();
    }
}
