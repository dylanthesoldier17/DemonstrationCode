package com.dylanthesoldier.allegion.service_tag_lookup.model;

import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Created by c-dmoore on 10/25/2016.
 */
public class Initializer {

    private Initializer(){}

    public static void init(Stage stage) throws Exception{
        R.stage = stage;
        R.ComputerDescription = readRegistry("HKLM\\SYSTEM\\CurrentControlSet\\services\\LanmanServer\\Parameters", "srvcomment");
        if(R.ComputerDescription == null)
            throw new NullPointerException("The Computer Description can't be parsed!");

        String[] split = R.ComputerDescription.split("::");
        R.ComputerUser = split[0].trim();
        R.ComputerModel = split[1].trim();
        R.ComputerServiceTag = split[2].trim();
    }

    /**
     *
     * @param location path in the registry
     * @param key registry key
     * @return registry value or null if not found
     */
    public static final String readRegistry(String location, String key){
        try {
            // Run reg query, then read output with StreamReader (internal class)
            Process process = Runtime.getRuntime().exec("reg query " +
                    '"'+ location + "\" /v " + key);

            StreamReader reader = new StreamReader(process.getInputStream());
            reader.start();
            process.waitFor();
            reader.join();
            String output = reader.getResult();

            // Parse out the value
            return output.split("REG_SZ")[1].split("\r")[0].trim();
        }
        catch (Exception e) {
            return null;
        }

    }

    static class StreamReader extends Thread {
        private InputStream is;
        private StringWriter sw = new StringWriter();

        public StreamReader(InputStream is) {
            this.is = is;
        }

        public void run() {
            try {
                int c;
                while ((c = is.read()) != -1)
                    sw.write(c);
            } catch (IOException e) {
            }
        }

        public String getResult() {
            return sw.toString();
        }
    }
}
