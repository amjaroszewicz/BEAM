package com.beam;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

public class MaintUtils {
    public static boolean exists;
    //Constant created for file name of index file.
    private static final String FILE_NAME = "BEAMsearch.txt";
    private static final String VERSION = "BEAMSearch 1.0.0";



    //Method checks if an index file exist in the user home directory
    //Returns true/false
     public static boolean checkIndexFile(){
         //example location c:\Users\jaitken\BEAMsearch.txt
         File indexFile = new File(getIndexFilePath());
         exists = indexFile.exists();

         return exists;
    }
    public static boolean checkVersion(JFrame jframe){
        if(checkIndexFile()){
            try {
                Scanner scan = new Scanner(new File(getIndexFilePath()), "UTF8");
                String versionVar= scan.nextLine();

                //JOptionPane.showMessageDialog(jframe,versionVar);
                //JOptionPane.showMessageDialog(jframe,VERSION);
                if(VERSION.compareTo(versionVar) > 0 || VERSION.compareTo(versionVar) < 0 ){
                    JOptionPane.showMessageDialog(jframe,"Index File Corrupt/Version mismatch" + "\n" + "A new index file will be created");
                    createNewIndexFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
         return true;
    }
    public static String getIndexFilePath(){
         String fp= System.getProperty("user.home")+"/"+FILE_NAME;

         return fp;
    }
    //Method creates a new index file in the users home directory
    public static void createNewIndexFile() {
        //example location c:\Users\jaitken\BEAMsearch.txt
         try {
             File indexFile = new File(System.getProperty("user.home") + "/" + FILE_NAME);
             indexFile.createNewFile();
             Writer output;
             output = new BufferedWriter(new FileWriter(getIndexFilePath()));  //clears file every time
             output.append(VERSION);
             output.close();
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
    public static boolean compareModifiedDate(){

         return false;
    }
}
