import java.sql.*;
public class DatabaseConnector {
    // Inspired by https://gist.github.com/eirikbakke/1074542
    // I didn't use all the code from the source, but I choose parts that I needed most or useful for this particular project.
    // I don't want to apply this connection manually all the time.
    // I'm planning to work with only 1 database/schema which is 'finalproject'
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/finalproject";
    private static final String USER = "root";
    private static final String PASS = "1234";
    private Connection connection = null;
    public Connection connect(){
        if(connection == null){
            try {
                Class.forName(JDBC_DRIVER);
                connection  = DriverManager.getConnection(DB_URL,USER,PASS);
                System.out.println("Connection successful!");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Database cannot connect bro, wtf?");
            }
        }
        return connection;
    }
}