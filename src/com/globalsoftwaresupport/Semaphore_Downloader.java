package com.globalsoftwaresupport;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Downloader {
    INSTANCE;
    private Semaphore semaphore = new Semaphore(3,true);
    public void Download() {
        try {
            semaphore.acquire();
            downloadData();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally{
            semaphore.release();
        }
    }
    private void downloadData(){
        try {
            System.out.println("Downloading the data from the web");
            Thread.sleep(3000);
        }
        catch(InterruptedException e){
            throw new RuntimeException(e);
        }

    }
}
public class Semaphore_Downloader {
    public static void main(String[] args){
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i=0;i<15;i++){
            exec.execute(new Runnable(){
                public void run(){
                    Downloader.INSTANCE.Download();
                }
            });
        }
    }
}
