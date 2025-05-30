package com.globalsoftwaresupport;

import java.util.Random;

public class Philosephore implements Runnable {
    private int id;
    private int eatCounter;
    private chopstick left_chopstick;
    private chopstick right_chopstick;
    private Random random;
    private volatile boolean full;
    public Philosephore(int id,chopstick left_chopstick,chopstick right_chopstick){
        this.id = id;
        this.left_chopstick=left_chopstick;
        this.right_chopstick=right_chopstick;
        this.random = new Random();
    }
    public void run(){
        try{
            while(!full){
                think();
                if(left_chopstick.pick_Up(this,State.Left)){
                    if(right_chopstick.pick_Up(this,State.Right)){
                        eat();
                        right_chopstick.pick_Down(this,State.Right);
                    }
                    left_chopstick.pick_Down(this,State.Left);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void think() throws InterruptedException{
        System.out.println(this+"philosphere is thinking...");
        Thread.sleep(random.nextInt(1000));
    }
    public void eat() throws InterruptedException{
        System.out.println(this+"philosphere is eating...");
        eatCounter++;
        Thread.sleep(random.nextInt(1000));
    }
    public void setFull(boolean full){
        this.full = full;
    }
    public Boolean isFull(){
         return this.full;
    }
    public String isString(){
        return "Philosphere"+id;
    }
    public int getEatCounter(){
        return this.eatCounter;
    }
}
