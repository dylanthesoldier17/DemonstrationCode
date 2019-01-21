package com.dylanthesoldier;

import java.util.ArrayList;

/**
 * Created by DylanTheSoldier on 4/29/2016.
 */
public class ThreadPool {

    private ArrayList<Thread> threadPool = new ArrayList<>();
    private Boolean terminateFlag = false;

    //region Boolean - Terminate Flag Methods
    public final Boolean getTerminateFlag(){
        return terminateFlag;
    }

    public void enableTerminateFlag(){
        terminateFlag = true;
    }

    public void disableTerminateFlag(){
        terminateFlag = false;
    }
    //endregion

    //region Thread Pool Methods
    public final ArrayList<Thread> getPool(){
        return threadPool;
    }

    public void addThreadToPool(Thread t){
        threadPool.add(t);
    }

    public void addRunnableToPool(Runnable r){
        Thread t = new Thread(r);
        threadPool.add(t);
        t.start();
    }

    public void removeFromThreadPool(Thread t){
        if (threadPool.contains(t))
            threadPool.remove(threadPool.indexOf(t));
    }

    public void interruptThreadPool(){
        for(Thread t: threadPool)
            t.interrupt();
    }
    //endregion

    public void executeUponCompletion(Runnable run){
        Async.exec(()->{
            for(Thread t: threadPool)
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            run.run();
        });
    }

}
