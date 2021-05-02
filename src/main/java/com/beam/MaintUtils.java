package com.beam;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
/**
 * MaintUtils Class
 *
 * This class contains utility methods for the application.
 * @author Jesse Aitken, Michael Hauser, Mile Limehouse, Ashwin Srivastava. Alexander Crawford.
 *
 */

public class MaintUtils {
    ////////////////////////////////////////////////////////////////
    //                          Fields                            //
    ////////////////////////////////////////////////////////////////
    public static boolean exists;
    //Constant created for file name of index file.
    private static final String FILE_NAME = "BEAMsearch.txt";
    private static final String VERSION = "BEAMSearch 1.0.0";
    private static final String CONFIG = "CONFIG";
    private static final String JSONFILE = "BEAMjson.txt";
    private static String nextAvailID;
    private static int currentAvailableID;
    private static int nextAvailableID;
    ////////////////////////////////////////////////////////////////
    //                          Methods                           //
    ////////////////////////////////////////////////////////////////
    /**
     * This method returns the current available ID,
     *
     */
    private static int getCurrentAvailableID(){
        return currentAvailableID;
    }
    /**
     * This method sets the current avilable ID.
     *
     */
    private static void setCurrentAvailableID(int id){
        currentAvailableID=id;
    }
    /**
     * This method gets the next available ID.
     */
    public static int getNextAvailableID(){
        return nextAvailableID;
    }
    /**
     * This method sets the next available ID.
     */
    private static void setNextAvailableID(int id){
        nextAvailableID=id;
    }
    /**
     * This method is used during testing to delete the index file that is generated.
     *
     */
    public static void deleteIndexFile(){

        File indexFile = new File(System.getProperty("user.home") + "/" + FILE_NAME);
        indexFile.delete();

    }
    /**
     * This method is used during testing to delete the json file that is generated.
     *
     *
     */
    public static void deleteJsonFile(){
        File indexFile = new File(System.getProperty("user.home") + "/" + JSONFILE);
        indexFile.delete();
    }
    /**
     * This method checks if an index file exists and returns T/F.
     *
     * @return True if the file exist, false otherwise.
     */
    public static boolean checkIndexFile(){
         //example location c:\Users\jaitken\BEAMsearch.txt
         File indexFile = new File(getIndexFilePath());
         exists = indexFile.exists();

         return exists;
    }
    /**
     * This method checks the version from the index file.
     * If it detects a different version and/or corrupted index file, it will create a new index file, and alert
     * the user with a JOptionPane.
     */
    public static void checkVersion(JFrame jframe){
        if(checkIndexFile()){
            try {
                Scanner scan = new Scanner(new File(getIndexFilePath()), "UTF8");
                String versionVar= scan.nextLine();
                if(VERSION.compareTo(versionVar) > 0 || VERSION.compareTo(versionVar) < 0 ){
                    JOptionPane.showMessageDialog(jframe,"Index File Corrupt/Version mismatch" + "\n" + "A new index file will be created");
                    createNewIndexFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    /**
     * This method returns the index file path
     *
     * @return Returns a string with the index file path.
     */
    public static String getIndexFilePath(){
         String fp= System.getProperty("user.home")+"/"+FILE_NAME;
         return fp;
    }
    /**
     * This method returns the JSON file path.
     *
     * @return Returns a string with he JSON file path.
     */
    public static String getJsonFilePath(){
        String fp= System.getProperty("user.home") + "/" + JSONFILE;
        return fp;
    }
    /**
     * This method creates a new index file in the users home directory.
     * Example: c:\Users\jaitken\BEAMsearch.txt
     *
     */
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
    /**
     * This method updates/recreates the JSON file when files are added or removed.
     *
     */
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
    /**
     * This method adds each file from the index(file) to an ArrayList and returns it.
     *
     * @return Returns an ArrayList containing all files currently in the index.
     */
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
    /**
     * This method adds a file to the index.
     * @param list The contents of the current index file.
     * @param indexFileLoc The file location for the file being added.
     * @param indexFileMD the last modified date for the file being added.
     */
    public static void addFileNameToIndexFile(ArrayList<String> list, String indexFileLoc,
                                              Long indexFileMD) throws IOException {
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
    /**
     * This method removes a file from the index.
     *
     * @param list An ArrayList containing the index file (by line). (Typically use method getIndexFileList())
     * @param id ID of the row being removed
     */
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
    /**
     * This method populates the JTable.
     *
     * @param dtm The DefaultTableModel of the JTable to update.
     * @param list The ArrayList of items to add to the JTable (typically use GetIndexFileList())
     */
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
            } catch (IOException e) {
            }
        }
    }
    /**
     * This method will be used to check if the files in the index have been modified .
     */
    public static void checkFiles(){
         //When refresh button is used, check if files exist and if date modified has changed.
    }
    /**
     * This method will be used to compare the modified date of two files.
     * @return Returns True if the file has change, false if it has not.
     */
    public static boolean compareModifiedDate(){

         return false;
    }
}
