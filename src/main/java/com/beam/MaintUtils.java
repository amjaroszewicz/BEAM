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
    public static int nextAvailableID(){
         //read from index file and get next available id.
        int id;
         return id=0;
    }
    public static void incrementNextAvailID(){
         // Increment next available ID. To be used after a file is added.
    }
    public static void addToIndex(){
        //When file is added to index
    }
    public static void removeFromIndex(){
         //When file is removed from index
    }
    public static void checkFiles(){
         //When refresh button is used, check if files exist and if date modified has changed.
    }
}
