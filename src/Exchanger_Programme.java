import java.util.concurrent.Exchanger;
class FirstClass implements Runnable{
    private Exchanger<Integer> exchange;
    private int Counter;
    public FirstClass(Exchanger<Integer> exchange){
        this.exchange = exchange;
    }
    public void run() {
        while (true) {
            Counter++;
            System.out.println("The FirstClass Counter value is:" + Counter);
            try {
                Counter = exchange.exchange(Counter);
                System.out.println("The Incremented value of FirstClass is:" + Counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class SecondClass implements Runnable{
    private Exchanger<Integer> exchange;
    private int Counter;
    public SecondClass (Exchanger<Integer> exchange){
        this.exchange = exchange;
    }
    public void run() {
        while (true) {
            Counter--;
            System.out.println("The SecondClass Counter value is:" + Counter);
            try {
                Counter = exchange.exchange(Counter);
                System.out.println("The Decremented value of SecondClass is:" + Counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class Exchanger_Programme {
    public static void main(String[] args){
        Exchanger<Integer> exchange = new Exchanger<>();
        FirstClass first = new FirstClass(exchange);
        SecondClass second = new SecondClass(exchange);
        new Thread(first).start();
        new Thread(second).start();
    }
}
