package com.dylanthesoldier.interfaces;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by DylanTheSoldier on 4/29/2016.
 */
public class FFMPEG {
    private String location = "ffmpeg ";
    private Runtime runtime = Runtime.getRuntime();
    private String extension = ".mp4";
    private String save_location = "";

    public String getSave_location() {
        return save_location;
    }

    public void setSave_location(String save_location) {
        this.save_location = save_location;
    }

    public boolean concat(File[] input_files)throws IOException, InterruptedException {

        PrintWriter pw = new PrintWriter(save_location + "\\filelist.txt");
        for(File str: input_files)
            pw.println("file \'" + str.getAbsolutePath() + "\'");
        pw.close();

        String cmd = location + " -safe 0 -f concat -i \"" + save_location + "\\filelist.txt\" -c copy \"" + save_location + "\\output" + extension + "\" -y";
        Process p = runtime.exec(cmd);
        byte[] buffer = new byte[16*1024];
        while(p.isAlive()) {
            p.getErrorStream().read(buffer);
            //p.getInputStream().read(buffer);
        }

        if (p.waitFor() == 0)
            return true;
        else
            return false;
    }
}
