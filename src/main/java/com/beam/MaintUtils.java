package com.beam;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class MaintUtils {
    public static boolean exists;
    //Constant created for file name of index file.
    private static final String FILE_NAME = "BEAMsearch.txt";
    private static final String VERSION = "BEAMSearch 1.0.0";
    private static final String CONFIG = "CONFIG";
    private static final String JSONFILE = "BEAMjson.txt";
    private static String nextAvailID;
    private static int currentAvailableID;
    private static int nextAvailableID;

    private static int getCurrentAvailableID(){
        return currentAvailableID;
    }
    private static void setCurrentAvailableID(int id){
        currentAvailableID=id;
    }
    public static int getNextAvailableID(){
        return nextAvailableID;
    }
    private static void setNextAvailableID(int id){
        nextAvailableID=id;
    }


    public static void deleteIndexFile(){

        File indexFile = new File(System.getProperty("user.home") + "/" + FILE_NAME);
        indexFile.delete();

    }
    public static void deleteJsonFile(){
        File indexFile = new File(System.getProperty("user.home") + "/" + JSONFILE);
        indexFile.delete();
    }
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

    public static void recreateJsonFile(JFrame jframe) throws IOException {
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
                    count++;
                }
            }

        }
        ObjectMapper objectMapper  = new ObjectMapper();
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


    public static ArrayList getIndexFileList() throws FileNotFoundException {
        ArrayList<String> list = new ArrayList<String>();
        Scanner scanner = new Scanner(new File(getIndexFilePath()), "UTF8");
        list.add(scanner.nextLine());//reads version
        setCurrentAvailableID(Integer.parseInt(scanner.nextLine()));
        setNextAvailableID(currentAvailableID+1);
        list.add(String.valueOf(getNextAvailableID()));
        //loops through existing files in index file.
        while(scanner.hasNext()) {
            //breaks loop if it sees true/false
            if(scanner.hasNextBoolean()){
                break;
            }
            list.add(scanner.nextLine());
        }
        return list;
    }
    //Working properly.
    public static void addFileNameToIndexFile(ArrayList<String> list, String indexFileLoc,
                                              Long indexFileMD,JFrame jframe) throws IOException {
        list.add(currentAvailableID + "\t" + indexFileLoc+"\t"+indexFileMD);//add new file to the list array
        list.add("TRUE");//adding boolean/break value
        //Read from ArrayList, and write to file
        Writer output = new BufferedWriter(new FileWriter(getIndexFilePath()));
        for (int i = 0; i < list.size(); i++) {
            output.append(list.get(i));
            output.append("\n");
        }
        output.close();
    }

    public static void removeFromIndex(ArrayList<String> list,int id) throws IOException {
        list.remove(id+2);
        list.add("TRUE");//adding boolean/break value

        Writer output = new BufferedWriter(new FileWriter(getIndexFilePath()));
        for (int i = 0; i < list.size(); i++) {
            output.append(list.get(i));
            output.append("\n");
        }
        output.close();

    }
    public static void populateJtable(DefaultTableModel dtm, ArrayList<String> list) throws IOException {
        list.remove(0); //remove version
        list.remove(0); //remove next available id
        for (int i = 0; i < list.size(); i++) {
            Scanner scanner = new Scanner(list.get(i));
            scanner.useDelimiter("\t");
            int fileID = Integer.parseInt(scanner.next());
            File file = new File(scanner.next());
            try {
                Path file2 = Paths.get(file.getAbsolutePath());
                BasicFileAttributes attr =
                        Files.readAttributes(file2, BasicFileAttributes.class);
                dtm.addRow(new Object[] {file.getName(),attr.lastModifiedTime(), attr.size() + " bytes",file.getAbsolutePath()});
                //System.out.println(list.get(i));
            } catch (IOException e) {
                System.out.println(e);
            }
        }


    }
    public static void checkFiles(){
         //When refresh button is used, check if files exist and if date modified has changed.
    }
    public static boolean compareModifiedDate(){

         return false;
    }
}
