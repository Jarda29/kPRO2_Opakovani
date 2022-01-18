package cz.uhk.pro2.models.io;

import cz.uhk.pro2.models.Person;

import java.util.List;

public interface FileOperations {
    void writeToFile(List<Person> personList);
    List<Person> loadFromFile();
}
