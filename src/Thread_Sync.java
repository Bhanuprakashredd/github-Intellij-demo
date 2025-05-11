public class Thread_Sync {
    public static int counter = 0;
    public static void process() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    counter = counter + 1;
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    counter = counter + 1;
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter);
    }
    public static void main(String[] args) throws InterruptedException {
        Thread_Sync syn = new Thread_Sync();
        syn.process();
    }
}
