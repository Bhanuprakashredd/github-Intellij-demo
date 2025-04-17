class Runner3 extends Thread{
    public void run(){
        for(int i=0;i<5;i++) {
            System.out.println("Runner1:"+i);
        }
    }
}
class Runner4 extends Thread{
    Thread t1;
    public Runner4(Thread t1){
        this.t1 = t1;
    }
    public void run(){
        for(int i=0;i<5;i++) {
            try {
                t1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Runner2:"+i);
        }
    }
}

public class join_method {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Runner3();
        Thread t2 = new Runner4(t1);

        t1.start();
        t2.start();
    }
}

