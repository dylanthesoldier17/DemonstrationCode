package com.dylanthesoldier;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by DylanTheSoldier on 4/29/2016.
 */
public class TimerPool {
    private ArrayList<Timer> timerPool = new ArrayList<>();

    public void addTimer(Runnable run, int start_delay, int interval_in_milli){
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                run.run();
            }
        }, start_delay, interval_in_milli);
        timerPool.add(t);
    }

    public void addTimer(Timer t){
        timerPool.add(t);
    }

    public void removeTimer(Timer t){
        timerPool.remove(timerPool);
    }

    public void removeTimer(int i){
        try{
            timerPool.remove(i);
        }catch(IndexOutOfBoundsException ex){
            // Ignore
        }
    }

    public void stopTimer(int i){
        try{
            timerPool.get(i).cancel();
        }catch(IndexOutOfBoundsException ex){
            // ignore
        }
    }

    public void stopAllTimers(){
        for(Timer t: timerPool)
            t.cancel();
    }

    public final ArrayList<Timer> getPool(){
        return timerPool;
    }

    private static TimerPool defaultTimerPool;
    public static void setDefaultTimerPool(TimerPool tp){
        defaultTimerPool = tp;
    }
    public static final TimerPool getDefaultTimerPool(){
        return defaultTimerPool;
    }
}
