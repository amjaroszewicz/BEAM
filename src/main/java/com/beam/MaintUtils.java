package com.beam;

import java.io.File;
import java.io.IOException;

public class MaintUtils {
    public static boolean exists;
    //Constant created for file name of index file.
    private static final String FILE_NAME = "BEAMsearch.txt";

    //Method checks if an index file exist in the user home directory
    //Returns true/false
     public static boolean checkIndexFile(){
         //example location c:\Users\jaitken\BEAMsearch.txt
         File indexFile = new File(System.getProperty("user.home")+"/" + FILE_NAME);
         exists = indexFile.exists();

         return exists;
    }
    //Method creates a new index file in the users home directory
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
