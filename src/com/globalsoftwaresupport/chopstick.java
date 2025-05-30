package com.globalsoftwaresupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class chopstick {
    private int id;
    private Lock lock;
    public chopstick(int id){
        this.id = id;
        this.lock = new ReentrantLock();
    }
    public Boolean pick_Up(Philosephore philosephore,State state) throws InterruptedException{
          if(lock.tryLock(10, TimeUnit.MILLISECONDS)){
              System.out.println("Philosephore"+"picks_up"+" "+state.toString()+" "+this);
              return true;
          }
          return false;
    }
    public void pick_Down(Philosephore philosephore, State state){
        lock.unlock();
        System.out.println("Philosephore"+"picks_down"+state.toString()+" "+this);
    }
    public String toString(){
        return "chopStick"+id;
    }
}
