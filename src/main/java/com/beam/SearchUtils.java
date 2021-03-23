package com.beam;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class SearchUtils {

    public static void generateSearch(String[] Args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("First_Name", "Shikhar");
        // create incremental loop
        MaintUtils.getIndexFilePath();
        try {
            FileWriter file = new FileWriter("E:/output.json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("JSON file created: "+jsonObject);
    }


    }

