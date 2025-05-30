package com;

import com.globalsoftwaresupport.Constants;
import com.globalsoftwaresupport.Philosephore;
import com.globalsoftwaresupport.chopstick;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executerservise=null;
        Philosephore[] philosephores= null;
        chopstick[] chopsticks= null;
        try {
            philosephores = new Philosephore[Constants.NO_Philosopher];
            chopsticks = new chopstick[Constants.No_chop_stick];
            for (int i = 0; i < Constants.No_chop_stick; ++i)
                chopsticks[i] = new chopstick(i);
            executerservise = Executors.newFixedThreadPool(Constants.NO_Philosopher);
            for (int i = 0; i < Constants.NO_Philosopher; ++i) {
                philosephores[i] = new Philosephore(i, chopsticks[i], chopsticks[(i + 1) % Constants.NO_Philosopher]);
                executerservise.execute(philosephores[i]);
            }
            Thread.sleep(Constants.semantic_timeout);
            executerservise.shutdown();

            for (Philosephore philosephore : philosephores) {
                philosephore.setFull(true);
            }
        }
        finally {
            executerservise.shutdown();
            while(!executerservise.isTerminated()){
                   Thread.sleep(1000);
            }
            for (Philosephore philosephore : philosephores){
                System.out.println(philosephore+"eat#"+philosephore.getEatCounter()+"times");
            }
        }
    }
}
