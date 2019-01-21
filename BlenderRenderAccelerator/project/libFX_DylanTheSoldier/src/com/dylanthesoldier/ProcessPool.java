package com.dylanthesoldier;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by DylanTheSoldier on 4/29/2016.
 */
public class ProcessPool {
    private ArrayList<Process> processPool = new ArrayList<>();
    private  Runtime runtime = Runtime.getRuntime();

    public void addProcess(String cmd) throws IOException {
        processPool.add(runtime.exec(cmd));
    }

    public void addProcess(Process t){
        processPool.add(t);
    }

    public void removeProcess(Process t){
        processPool.remove(processPool);
    }

    public void removeProcess(int i){
        try{
            processPool.remove(i);
        }catch(IndexOutOfBoundsException ex){
            // Ignore
        }
    }

    public void stopProcess(int i){
        try{
            processPool.get(i).destroy();
        }catch(IndexOutOfBoundsException ex){
            // ignore
        }
    }

    public void stopProcess_Force(int i){
        try{
            processPool.get(i).destroyForcibly();
        }catch(IndexOutOfBoundsException ex){
            // ignore
        }
    }

    public void stopAllProcess(){
        for(Process p: processPool)
            p.destroy();
    }

    public void stopAllProcess_Force(){
        for(Process p: processPool)
            p.destroyForcibly();
    }

    public final ArrayList<Process> getPool(){
        return processPool;
    }

    //region Static variables and methods

    private static ProcessPool defaultProcessPool;
    public static void setDefaultTimerPool(ProcessPool pp){
        defaultProcessPool = pp;
    }
    public static final ProcessPool getDefaultProcessPool(){
        return defaultProcessPool;
    }

    //endregion
}
