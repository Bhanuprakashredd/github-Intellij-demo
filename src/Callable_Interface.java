import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class CPU implements Callable{
    private int ID;
    public CPU(int ID){
        this.ID = ID;
    }
    public String call() throws Exception{
        Thread.sleep(1000);
        return "The ID is"+" "+ID;
    }
}
public class Callable_Interface {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        ArrayList<Future<String>> list = new ArrayList<>();
        for(int i=0;i<10;++i) {
            Future<String> futures = service.submit(new CPU(i+1));
            list.add(futures);
        }
        for(Future<String> f:list){
            try{
                System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
