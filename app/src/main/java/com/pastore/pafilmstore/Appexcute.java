package com.pastore.pafilmstore;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Appexcute {
    private static Appexcute instance;
    public static Appexcute getInstance(){
        if(instance==null)
            instance = new Appexcute();
        return instance;
    }

    private final ScheduledExecutorService netwwIO = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService networkIO(){
        return  netwwIO;
    }
}
