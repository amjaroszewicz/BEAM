package com.beam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class SearchUtils {

    public static void generateSearch() {
        //generate array for inputTextbox input
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("myJSON.json"));
            JSONObject jsonObject = (JSONObject) obj; //parse to object
            String name = (String) jsonObject.get("name"); //extract elements
            System.out.println(name);

            //show location
            String location = (String) jsonObject.get("location");
            System.out.println("Location is: " + location);

            //loop array
            JSONArray jArray = (JSONArray) jsonObject.get(" null value ");
            // check how many items need to be iterated for loop
            Iterator<String> iterator = jArray.iterator();
            while(iterator.hasNext()){
                System.out.println("text" + iterator.next());
            }
        }
        catch(FileNotFoundException e){ e.printStackTrace(); }
        catch(IOException e){ e.printStackTrace(); }
        catch(ParseException e){ e.printStackTrace(); }
        catch(Exception e){ e.printStackTrace(); }
    }

    }

