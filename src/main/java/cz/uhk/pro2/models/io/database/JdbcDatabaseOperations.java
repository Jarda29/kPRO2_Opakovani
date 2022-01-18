package cz.uhk.pro2.models.io.database;

import cz.uhk.pro2.models.Person;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDatabaseOperations implements DatabaseOperations {
    private final Connection connection;
    private Statement statement;
    public JdbcDatabaseOperations(String driver, String url) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url);
    }
    @Override
    public void writeToFile(List<Person> personList) {
        try{
            statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE Person");
            for (Person p :
                    personList) {
                String sql = "INSERT INTO Person (name, age) VALUES ("
                        + "'"+p.getName() + "', "
                        + p.getAge()
                        +")";
                statement.executeUpdate(sql);
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> loadFromFile() {
        List<Person> personList = new ArrayList<>();
        try{
            statement = connection.createStatement();
            String sql = "SELECT name, age FROM Person";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Person p = new Person(
                        resultSet.getString("name"),
                        resultSet.getInt("age"));
                personList.add(p);
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return personList;
    }
}
