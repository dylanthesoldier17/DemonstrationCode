package com.dylanthesoldier.interfaces;

import com.dylanthesoldier.ThreadPool;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by DylanTheSoldier on 4/29/2016.
 */
public class Blender {
    private static String location = "blender ";
    public int start_frame = 1;
    public int end_frame = 1;
    public int thread_count = 1;
    public String save_location = "//";
    public String blend_file = "";
    public int chunk_sizes = 200;
    public ArrayBlockingQueue<String> cmd_queue;
    public int num_of_chunks = 0;

    private ThreadPool threadPool = new ThreadPool();

    public final synchronized ArrayBlockingQueue<String> getCmdQueue(){
        return cmd_queue;
    }
    public final synchronized int getNumberOfChunks(){
        int num_of_chunks = (end_frame - start_frame) / chunk_sizes;
        int remainder = (end_frame - start_frame) % chunk_sizes;
        if (remainder > 0)
            return num_of_chunks + 1;
        else
            return num_of_chunks;
    }

    public void setOnFinished(Runnable r){
        threadPool.executeUponCompletion(r);
    }

    public void terminateRender(){
        threadPool.enableTerminateFlag();
    }

    public void resetTerminateRenderFlag(){
        threadPool.disableTerminateFlag();
    }

    public void generate_queue() throws InterruptedException {

        num_of_chunks = (end_frame - start_frame) / chunk_sizes;
        /*TODO: Make more efficient use of getting this value to the destination classes*/
        int remainder = (end_frame - start_frame) % chunk_sizes;
        int start = start_frame;
        int end = end_frame;

        if (remainder > 0)
            cmd_queue = new ArrayBlockingQueue<String>(num_of_chunks + remainder);
        else
            cmd_queue = new ArrayBlockingQueue<String>(num_of_chunks);

        for(int i=0; i<num_of_chunks; i++) {
            String cmd = Blender.location + " -b \"" +  blend_file + "\"" + " -s " +
                    start + " -e " + (start + chunk_sizes - 1) + " -t " + thread_count +
                    " -o " + save_location + "######## -a";

            cmd_queue.put(cmd);
            //System.out.println(cmd);
            start += chunk_sizes;
        }

        if (remainder > 0){
            String cmd = location + " -b \"" +  blend_file + "\"" + " -s " +
                    start + " -e " + (end) + " -t " + thread_count +
                    " -o " + save_location + "######## -a";
            cmd_queue.put(cmd);
            num_of_chunks++;
        }

    }

    public void exec_parallel(int count){
        for(int i=0; i<count; i++)
            threadPool.addRunnableToPool(()->{
                try {
                    exec();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
    }

    public void exec() throws IOException, InterruptedException{
        /* Have to consume the processes output to keep from stalling */
        Runtime r = Runtime.getRuntime();
        while(cmd_queue.size() > 0 && !threadPool.getTerminateFlag()){
            Process p = r.exec(cmd_queue.take());
            byte[] buffer = new byte[1024*16];
            InputStream is = p.getInputStream();
            while (p.isAlive())
                /* Buffer capacity appears to be okay */
                is.read(buffer);
        }
    }
    private static Blender defaultBlender;
    public static void setDefaultBlender(Blender b){
        defaultBlender = b;
    }
    public static final Blender getDefaultBlender(){
        return defaultBlender;
    }

}
