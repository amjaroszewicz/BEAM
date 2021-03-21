package com.beam;

import java.io.File;
import java.io.IOException;

public class MaintUtils {
    public static boolean exists;
    private static final String FILE_NAME = "BEAMsearch.txt";

     public static boolean checkIndexFile(){
         File indexFile = new File(System.getProperty("user.home")+"/" + FILE_NAME);
         exists = indexFile.exists();

         return exists;
    }

    public static void createNewIndexFile() {
         try {
             File indexFile = new File(System.getProperty("user.home") + "/" + FILE_NAME);
             indexFile.createNewFile();
         }
         catch (IOException e) {
             e.printStackTrace();
         }
    }
}
