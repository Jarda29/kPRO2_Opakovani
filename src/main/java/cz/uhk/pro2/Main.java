package cz.uhk.pro2;

import cz.uhk.pro2.gui.MainFrame;
import cz.uhk.pro2.models.MyGenericClass;
import cz.uhk.pro2.models.Person;
import cz.uhk.pro2.models.database.DbInitializer;
import cz.uhk.pro2.models.io.CsvFileOperations;
import cz.uhk.pro2.models.io.DatabaseFileOperations;
import cz.uhk.pro2.models.io.FileOperations;
import cz.uhk.pro2.models.io.JsonFileOperations;
import cz.uhk.pro2.models.io.database.DatabaseOperations;
import cz.uhk.pro2.models.io.database.JdbcDatabaseOperations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Runnable repeatingCode = () -> {
            Thread.currentThread().setName("RepeatingThread");
            while (true){
                System.out.println(Thread.currentThread().getName()+": Hello");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread threadRepeatingCode = new Thread(repeatingCode);
        threadRepeatingCode.start();

        List<String> myList = new ArrayList<>();
        myList.add("aaa");


        List<Person> myList2 = new ArrayList<>();


        FileOperations myFileOp = new JsonFileOperations();
        MyGenericClass<FileOperations> myGenericClass =
                new MyGenericClass<>(myFileOp);


        Person personA = new Person("A", 18);
        Class<Person> clsP = Person.class;

        System.out.println(clsP.getName());
        for (Field field :
                clsP.getDeclaredFields()) {
            System.out.println(field.getName());
        }



	// write your code here
        String databaseDriver = "org.apache.derby.jdbc.EmbeddedDriver";
        String url = "jdbc:derby:PRO2";

        DbInitializer dbInit = new DbInitializer(databaseDriver, url);
        //dbInit.init();
try{
    DatabaseOperations databaseOperations =
            new JdbcDatabaseOperations(databaseDriver, url);

    FileOperations fileOperations =
            new DatabaseFileOperations(databaseOperations);
    MainFrame mainFrame = new MainFrame(800,600, fileOperations);
    mainFrame.setVisible(true);

}catch (Exception e){
    e.printStackTrace();
    threadRepeatingCode.interrupt();
}

    }
}
