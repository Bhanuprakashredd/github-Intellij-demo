import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Delayed;
class DelayedQueue implements Delayed {
    private String Message;
    private long Duration;
    public DelayedQueue(String Message,long Duration){
        this.Message = Message;
        this.Duration = System.currentTimeMillis()+Duration;
    }
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(Duration-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        if(Duration < ((DelayedQueue) other) .Duration)
                return -1;
        if (Duration > ((DelayedQueue) other) .Duration)
            return +1;
        return 0;
    }
    public long getDuration() {
        return Duration;
    }

    public void setDuration(int Duration) {
        this.Duration = Duration;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getMessage() {
        return Message;
    }

    @Override
    public String toString() {
        return "DelayedQueue [message "+ Message +"]";
    }
}
public class Delayed_Queue {
    public static void main(String[] args){
        BlockingQueue<DelayedQueue> queue = new DelayQueue<>();
        try {
            queue.put(new DelayedQueue("This is the first message entered", 200));
            queue.put(new DelayedQueue("This is the second message entered", 1200));
            queue.put(new DelayedQueue("This is the third message entered", 400));
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(!queue.isEmpty()){
            try{
                System.out.println(queue.take());
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
