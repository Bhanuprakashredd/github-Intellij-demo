class Runner5 implements Runnable{
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Daemon_Thread is running");
        }
    }
}
class Runner6 implements Runnable{
    public void run(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Normal Thread is Running");
    }
}
public class Daemon_Thread {
    public static void main(String[] args){
        Thread t1 = new Thread(new Runner5());
        t1.setDaemon(true);
        Thread t2 = new Thread(new Runner6());
        t1.start();
        t2.start();
    }
}
