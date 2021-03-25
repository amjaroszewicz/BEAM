package com.beam;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;


import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Scanner;

public class MaintUtils {
    public static boolean exists;
    //Constant created for file name of index file.
    private static final String FILE_NAME = "BEAMsearch.txt";
    private static final String VERSION = "BEAMSearch 1.0.0";
    private static final String CONFIG = "CONFIG";
    private static final String JSONFILE = "BEAMjson.txt";
    private static int nextAvailID;



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
    public static void addToIndex(JFrame jframe, File file){
        //When file is added to index
        HashMap<String, String> hashMap = new HashMap<String, String>();
        //grab current index file

        int indexFileID=0;
        String indexFileLoc="";
        long indexFileMD=0;
        try {
            Scanner scan = new Scanner(new File(getIndexFilePath()), "UTF8");
            String versionVar= scan.nextLine();
            nextAvailID = Integer.parseInt(scan.nextLine()) ;
            //scan.nextLine();
            JsonFactory factory = new JsonFactory();

            JsonGenerator generator = factory.createGenerator(
                    new File(System.getProperty("user.home") + "/" + JSONFILE), JsonEncoding.UTF8);
            generator.useDefaultPrettyPrinter();

            while(scan.hasNext()){
                if(scan.hasNextBoolean()){
                    break;
                }

                    indexFileID = Integer.parseInt(scan.next());
                    indexFileLoc = scan.next();
                    indexFileMD = Long.parseLong(scan.next());
                    //for debugging only (below)
                    JOptionPane.showMessageDialog(jframe, indexFileID + indexFileLoc + indexFileMD);
                    //Scan through load file below
                Scanner scanIndex = new Scanner(new File(indexFileLoc));
                int count=0;
                //"scan" through files that already exist in the index.
                while(scanIndex.hasNext()) {


                    generator.writeStartObject();
                    String jsonText;
                    generator.writeStringField("word",scanIndex.next());
                    generator.writeStringField("wordLocation", String.valueOf(count));
                    generator.writeStringField( "fileID", String.valueOf(indexFileID));
                    generator.writeEndObject();
                    count++;
                }
            }
            generator.close();




        } catch (IOException e) {
            e.printStackTrace();
        }

        //add new file
        try {
            Scanner scanNewFile = new Scanner(file, "UTF8");
            int count=0;
            JsonFactory factory = new JsonFactory();
            OutputStream fos = new FileOutputStream(new File(System.getProperty("user.home") + "/" + JSONFILE), true);
            JsonGenerator generator = factory.createGenerator(fos, JsonEncoding.UTF8);
            generator.useDefaultPrettyPrinter();

            while(scanNewFile.hasNext()) {


                generator.writeStartObject();
                generator.writeStringField("word",scanNewFile.next());
                generator.writeStringField("wordLocation", String.valueOf(count));
                generator.writeStringField( "fileID", String.valueOf(nextAvailID));
                generator.writeEndObject();
                count++;


            }
            generator.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


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
