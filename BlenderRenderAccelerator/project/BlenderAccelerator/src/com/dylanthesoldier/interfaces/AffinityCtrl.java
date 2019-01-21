package com.dylanthesoldier.interfaces;

import java.io.IOException;

/**
 * Created by DylanTheSoldier on 4/27/2016.
 */
public class AffinityCtrl {

    private String location = "AffinityCtrl ";

    private final String LIST_PROCESSES = "list_processes ";
    private final String RESTORE_AFFINITY = "restore_affinity ";
    private final String SET_AFFINITY = "set_affinity ";
    private final String SET_AFFINITY_ALL = "set_affinity_all ";
    private Runtime exec = Runtime.getRuntime();

    private static final int SUCCESSFUL_EXECUTION = 0;
    private static final int FAILED_EXECUTION = -100;

    public boolean setProcessAffinity(String[] names, int[] core_ids) throws IOException, InterruptedException{
        String apps = "";
        for(int i=0; i<names.length; i++)
            apps += names[i] + ":" + core_ids[i] + ",";

        String msg = location + " cmd=" + SET_AFFINITY + ";apps=" + apps;
        if (exec.exec(msg).waitFor() == FAILED_EXECUTION)
            return false;
        else
            return true;
    }

    public static int cores_to_cpuID(int[] cores){
        int cpuID = 0;
        for(int core: cores)
            cpuID += (int)Math.pow(2, core);
        return cpuID;
    }

    public boolean restoreProcessAffinity(){
        String msg = location + " cmd=" + RESTORE_AFFINITY;
        try {
            if (exec.exec(msg).waitFor() == FAILED_EXECUTION)
                return false;
            else
                return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setAffinityAll(int cpu_id) throws IOException, InterruptedException{
        String msg = location + " cmd=" + SET_AFFINITY_ALL + ";cpu_id=" + cpu_id;
        if (exec.exec(msg).waitFor() == FAILED_EXECUTION)
            return false;
        else
            return true;
    }

    public boolean setAffinityAll(int cpu_id, String[] omitList) throws IOException, InterruptedException{
        String omit = "";
        for(String item: omitList)
            omit += item + ",";

        String msg = location + " cmd=" + SET_AFFINITY_ALL + ";cpu_id=" + cpu_id + ";omit=" + omit;
        if (exec.exec(msg).waitFor() == FAILED_EXECUTION)
            return false;
        else
            return true;
    }

    private static AffinityCtrl defaultAffinityCtrl;
    public static void setDefaultAffinityCtrl(AffinityCtrl ac){
        defaultAffinityCtrl = ac;
    }
    public static final AffinityCtrl getDefaultAffinityCtrl(){
        return defaultAffinityCtrl;
    }
}
