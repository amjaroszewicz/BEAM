package com.beam;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;



import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class MaintUtils {
    public static boolean exists;
    //Constant created for file name of index file.
    private static final String FILE_NAME = "BEAMsearch.txt";
    private static final String VERSION = "BEAMSearch 1.0.0";
    private static final String CONFIG = "CONFIG";
    private static final String JSONFILE = "BEAMjson.txt";
    private static String nextAvailID;



    //Method checks if an index file exist in the user home directory
    //Returns true/false
    public static void deleteIndexFile(){

        File indexFile = new File(System.getProperty("user.home") + "/" + FILE_NAME);
        indexFile.delete();

    }
    public static void deleteJsonFile(){
        File indexFile = new File(System.getProperty("user.home") + "/" + JSONFILE);
        indexFile.delete();
    }
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
    public static String getJsonFilePath(){
        String fp= System.getProperty("user.home") + "/" + JSONFILE;

        return fp;
    }

    public static void createNewIndexFile() {
        //example location c:\Users\jaitken\BEAMsearch.txt
         try {
             File indexFile = new File(System.getProperty("user.home") + "/" + FILE_NAME);
             indexFile.createNewFile();
             Writer output;
             output = new BufferedWriter(new FileWriter(getIndexFilePath()));  //clears file every time
             output.append(VERSION+"\n");
             output.append("0");
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
    public static void addToJsonFile(JFrame jframe, File file) throws IOException {
        //JsonFactory factory = new JsonFactory();
        //JsonGenerator generator =
                //factory.createGenerator(new File(getJsonFilePath()),JsonEncoding.UTF8).useDefaultPrettyPrinter();
        int count=0;
        String indexFileID="";
        String indexFileLoc="";
        String indexFileMD="";
        HashSet<String> hashSet = null;
        Scanner scan = new Scanner(new File(getIndexFilePath()), "UTF8");
        String versionVar= scan.nextLine();
        nextAvailID = scan.nextLine();

        HashMap<String, HashSet> hashMap = new HashMap<>();

        while(scan.hasNext()) {
            if (scan.hasNextBoolean()) {
                break;
            }
            Scanner scanLine = new Scanner(scan.nextLine());
            scanLine.useDelimiter("\t");
            while(scanLine.hasNextLine()) {
                indexFileID = scanLine.next();
                indexFileLoc = scanLine.next();
                indexFileMD = scanLine.next();
                count=0;

                Scanner scanFile = new Scanner(new File(indexFileLoc));
                while(scanFile.hasNext()){
                    String stringHash = scanFile.next();
                    if(!hashMap.containsKey(stringHash)){

                        hashSet = new HashSet<>();
                        hashSet.add(String.valueOf(count)+","+indexFileID);
                        hashMap.put(stringHash.toString(),hashSet);
                    }
                    else {

                        hashMap.get(stringHash).add(String.valueOf(count)+","+indexFileID);

                    }

                    //HashSet<String> hashSet = new HashSet<>();
                    //generator.writeStringField("word", scanFile.next());
                    //hashSet.add(String.valueOf(count)+","+indexFileID);
                    //hashMap.put(scanFile.next(),hashSet);






                    //generator.writeStringField("wordLocation", String.valueOf(count));
                    //generator.writeStringField("fileID", indexFileID);

                    count++;
                }
            }

        }
        //generator.close();
        ObjectMapper objectMapper  = new ObjectMapper();
        //objectMapper.writeValueAsString(hashSet);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(getJsonFilePath()),hashMap);

        }

    //addToJsonFile2 method has been replaced by addToJsonFile method.
    @Deprecated
    private static void addToJsonFile2(JFrame jframe, File file) throws IOException {
        //initialize variables
        String versionVar;
        String indexFileID="";
        String indexFileLoc="";
        String indexFileMD="";
        int count=0;
        //instantiate json factory + generator
        JsonFactory factory = new JsonFactory();
        //pass Json File path
        JsonGenerator generator = factory.createGenerator(new File(getJsonFilePath()), JsonEncoding.UTF8);
        generator.useDefaultPrettyPrinter();
        //pass index file (BEAMsearch.txt)
        Scanner scan = new Scanner(new File(getIndexFilePath()), "UTF8");
        //reads the first line (version+#)
        versionVar= scan.nextLine();
        //reads the second line, the next avail id
        nextAvailID = scan.nextLine();
        while(scan.hasNext()) {
            if (scan.hasNextBoolean()) {
                break;
            }

            Scanner scanLine = new Scanner(scan.nextLine());
            scanLine.useDelimiter("\t");
            while(scanLine.hasNextLine()) {
                indexFileID = scanLine.next();
                indexFileLoc = scanLine.next();
                indexFileMD = scanLine.next();
                count=0;


                Scanner scanFile = new Scanner(new File(indexFileLoc));
                while(scanFile.hasNext()){
                    generator.writeStartObject();
                    generator.writeStringField("word", scanFile.next());
                    generator.writeStringField("wordLocation", String.valueOf(count));
                    generator.writeStringField("fileID", indexFileID);
                    generator.writeEndObject();
                    count++;
                }
            }

        }
        generator.close();


    }
    //Working properly.
    public static void addFileNameToIndexFile(String indexFileLoc, Long indexFileMD,JFrame jframe) throws IOException {
        //read contents of current file
        ArrayList<String> list = new ArrayList<String>();
        Scanner scanner = new Scanner(new File(getIndexFilePath()), "UTF8");
        //reads version number below
        list.add(scanner.nextLine());
        //reads next available id
        // example currentAvailable= 0, nextAvailableID = 1
        int currentAvailableID = Integer.parseInt(scanner.nextLine());
        int nextAvailableID= currentAvailableID+1;
        list.add(String.valueOf(nextAvailableID));
        //loops through existing files in index file.
        while(scanner.hasNext()) {
            //breaks loop if it sees true/false
            if(scanner.hasNextBoolean()){
                break;
            }
            list.add(scanner.nextLine());
        }
        //add new file to the list array
        list.add(currentAvailableID + "\t" + indexFileLoc+"\t"+indexFileMD);
        //adding boolean/break value
        list.add("TRUE");
        ////////////////////////////////////////////////////////////////////
        //              Read from ArrayList, and write to file           //
        //////////////////////////////////////////////////////////////////
        Writer output;
        output = new BufferedWriter(new FileWriter(getIndexFilePath()));
        for (int i = 0; i < list.size(); i++) {
            output.append(list.get(i));
            output.append("\n");
        }
        output.close();
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
