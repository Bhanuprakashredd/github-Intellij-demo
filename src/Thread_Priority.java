class Worker implements Runnable{
       public void run(){
           System.out.println("Thread priority was:2");
       }
}
class Worker2 implements Runnable{
    public void run(){
        System.out.println("Thread priority was:5");
    }
}

public class Thread_Priority {
    public static void main(String[] args)
    {
        Thread t1 = new Thread(new Worker());
        Thread t2 = new Thread(new Worker2());
        t1.setPriority(2);
        t2.setPriority(5);
        t1.start();
        t2.start();
    }
}
