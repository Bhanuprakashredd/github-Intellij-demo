import java.io.*;
import java.util.*;
class Runner1 implements Runnable{
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println("Runner1:"+i);
        }
    }
}
class Runner2 implements Runnable{
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println("Runner2:"+i);
        }
    }
}
public class with_runnable{
 public static void main(String[] args){
     Thread runner1 = new Thread(new Runner1());
     Thread runner2 = new Thread(new Runner2());
     runner1.start();
     runner2.start();
    }
}
