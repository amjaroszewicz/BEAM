package com.beam;

import java.io.File;
import java.io.IOException;

public class MaintUtils {
    public static boolean exists;
    private static final String FILE_NAME = "BEAMsearch.txt";

     public static boolean checkIndexFile(){
         //example location c:\Users\jaitken\BEAMsearch.txt
         File indexFile = new File(System.getProperty("user.home")+"/" + FILE_NAME);
         exists = indexFile.exists();

         return exists;
    }

    public static void createNewIndexFile() {
        //example location c:\Users\jaitken\BEAMsearch.txt
         try {
             File indexFile = new File(System.getProperty("user.home") + "/" + FILE_NAME);
             indexFile.createNewFile();
         }
         catch (IOException e) {
             e.printStackTrace();
         }
    }
}
