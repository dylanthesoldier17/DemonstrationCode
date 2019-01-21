package com.dylanthesoldier;

/**
 * Created by DylanTheSoldier on 4/29/2016.
 */
public class Async {
    public static Thread exec(Runnable run){
        Thread t = new Thread(run);
        t.start();
        return t;
    }
}
