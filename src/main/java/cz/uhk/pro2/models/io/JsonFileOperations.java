package cz.uhk.pro2.models.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import cz.uhk.pro2.models.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonFileOperations implements FileOperations {
    private Gson gson;
    private static final String FILE_NAME = "./personList.json";

    public JsonFileOperations(){
        //gson = new Gson();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void writeToFile(List<Person> personList) {
        String jsonText = gson.toJson(personList);
        System.out.println(jsonText);
        try{
            FileWriter writer = new FileWriter(FILE_NAME);
            writer.write(jsonText);
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> loadFromFile() {
        try {
            FileReader reader = new FileReader(FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder jsonText = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine())!=null){
                jsonText.append(line);
            }
            Type targetType = new TypeToken<ArrayList<Person>>(){}.getType();
            return gson.fromJson(jsonText.toString(), targetType);

        }catch (IOException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
