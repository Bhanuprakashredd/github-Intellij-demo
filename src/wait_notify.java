class Process1 {
    public void Produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("first statement ran in producer");
            wait();
            System.out.println("after notify calls");
        }
    }

    public void Consumer() throws InterruptedException {
        Thread.sleep(1000);
        synchronized (this) {
            System.out.println("first statement ran in Consumer");
            notify();
            System.out.println("though,there is notify method it run first");
        }
    }
}
public class wait_notify {
    public static void main(String[] args) {
        Process1 wa = new Process1();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    wa.Produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    wa.Consumer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();
        t2.start();
    }
}

