import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SQLTester {

    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) {

        Properties prop = new Properties();
        String fileName = "src/auth.cfg";
        try {
            FileInputStream configFile = new FileInputStream(fileName);
            prop.load(configFile);
            configFile.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find config file.");
            System.exit(1);
        } catch (IOException ex) {
            System.out.println("Error reading config file.");
            System.exit(1);
        }
        String username = (prop.getProperty("username"));
        String password = (prop.getProperty("password"));

        if (username == null || password == null){
            System.out.println("Username or password not provided.");
            System.exit(1);
        }

        String connectionUrl =
                "jdbc:sqlserver://uranium.cs.umanitoba.ca:1433;"
                + "database=cs3380;"
                + "user=" + username + ";"
                + "password="+ password +";"
                + "encrypt=false;"
                + "trustServerCertificate=false;"
                + "loginTimeout=30;";

        ResultSet resultSet = null;
        try {
            // Establish a database connection
            try (Connection connection = DriverManager.getConnection(connectionUrl);
            	/*
                 Statement stmt = connection.createStatement()) {
            	
            	
                // Start a transaction
                connection.setAutoCommit(false);

                // Prepare to read the file
                StringBuilder command = new StringBuilder();
                Files.lines(Paths.get("src/gameDataBase.db.sql")).forEach(line -> {
                    command.append(line);
                    // Check if line ends with a semicolon (end of SQL command)
                    if (line.trim().endsWith(";")) {
                        try {
                            // Execute SQL command
                            stmt.execute(command.toString());
                            // Reset StringBuilder for next command
                            command.setLength(0);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                */
            	// Create and execute a SELECT SQL statement.
               
                Statement statement = connection.createStatement();) {

                    // Create and execute a SELECT SQL statement.
            	String query = "SELECT COUNT(DISTINCT Game.AppID) AS TotalGamesReleased "
                        + "FROM Game "
                        + "INNER JOIN Genre ON Game.AppID = Genre.AppID "
                        + "INNER JOIN Category ON Game.AppID = Category.AppID "
                        + "WHERE LOWER(CAST(Genre.GenreName AS VARCHAR(MAX))) = LOWER('Indie') "
                        + "AND LOWER(CAST(Category.CategoriesName AS VARCHAR(MAX))) = LOWER('single-player') "
                        + "AND CONVERT(VARCHAR(10), Game.ReleaseDate, 120) >= '2001-04-11' "
                        + "AND CONVERT(VARCHAR(10), Game.ReleaseDate, 120) < '2019-04-12';";



                    resultSet = statement.executeQuery(query);


                    // Inside your try block, after executing the query:
                    while (resultSet.next()) {
                        // Assuming your table columns are something like id, review_text, user_name, user_city
                        // Adjust the column names according to your actual table schema
                        String id = resultSet.getString("TotalGamesReleased"); // Or use the actual column name for the ID

                        // Print the retrieved data
                        System.out.println(id);
                        //System.out.println("done1");
                    }
                    System.out.println("done2");
                }
           // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

