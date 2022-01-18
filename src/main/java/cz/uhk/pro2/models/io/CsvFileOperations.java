package cz.uhk.pro2.models.io;

import cz.uhk.pro2.models.Person;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvFileOperations implements FileOperations {
    private static final String FILE_NAME = "./personList.csv";
    @Override
    public void writeToFile(List<Person> personList) {
        String text = "";
        for (Person p :
                personList) {
            text += p.getName()+","+p.getAge()+"\n";
        }
        System.out.println(text);
        try{
            FileWriter writer = new FileWriter(FILE_NAME);
            writer.write(text);
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> loadFromFile() {
        return null;
    }
}
