class runner1 extends Thread{
    public void run(){
        for(int i=0;i<5;i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Runner1:"+i);
        }
    }
}
class runner2 extends Thread{
    public void run(){
        for(int i=0;i<5;i++) {
            System.out.println("Runner2:"+i);
        }
    }
}

public class with_Thread {
    public static void main(String[] args){
        Thread t1 = new runner1();
        Thread t2 = new runner2();
        t1.start();
        t2.start();
    }
}
