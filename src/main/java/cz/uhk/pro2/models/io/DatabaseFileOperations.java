package cz.uhk.pro2.models.io;

import cz.uhk.pro2.models.Person;
import cz.uhk.pro2.models.io.database.DatabaseOperations;

import java.util.List;

public class DatabaseFileOperations implements FileOperations{
    private DatabaseOperations databaseOperations;

    public DatabaseFileOperations(DatabaseOperations databaseOperations) {
        this.databaseOperations = databaseOperations;
    }

    @Override
    public void writeToFile(List<Person> personList) {
        databaseOperations.writeToFile(personList);
    }

    @Override
    public List<Person> loadFromFile() {
        return databaseOperations.loadFromFile();
    }
}
