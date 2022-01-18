package cz.uhk.pro2.models.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbInitializer {
    private final String driver;
    private final String url;

    public DbInitializer(String driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public void init(){
        try{
            // načteme driver
            Class.forName(driver);

            Connection connection = DriverManager.getConnection(url);

            Statement statement = connection.createStatement();

            String sqlPersonCreate =
                    "CREATE TABLE Person "
                    +"( "
                            + "id INT NOT NULL GENERATED ALWAYS AS IDENTITY "
                            + "CONSTRAINT Person_PK PRIMARY KEY, "
                            + "name VARCHAR(50), "
                            + "age integer "
                    +")";
            statement.executeUpdate(sqlPersonCreate);
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
