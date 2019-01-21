package com.dylanthesoldier.blender_accelerator;
import java.io.File;
import java.util.Timer;

/**
 * Created by DylanTheSoldier on 4/29/2016.
 */
public class R {

    public static void construct(){/* Used to preload the static entries in the class. */}

    public static String blend_file = "";
    public static String end_frame = "0";
    public static String start_frame = "1";

    public static File frame_folder =  null;
    public static Timer affinityTimer = new Timer("Affinity Timer");
}
