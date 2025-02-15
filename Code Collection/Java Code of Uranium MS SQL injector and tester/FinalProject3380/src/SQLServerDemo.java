import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class SQLServerDemo {
    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(0);
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

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        if (username == null || password == null) {
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

        try {
            // Establish a database connection
            try (Connection connection = DriverManager.getConnection(connectionUrl);
            
                 Statement stmt = connection.createStatement()) {

                connection.setAutoCommit(false);

                // Specify UTF-16LE for reading the file
                Files.lines(Paths.get("src/selectedLines2.txt"), StandardCharsets.UTF_8).forEach(line -> {
                    StringBuilder command = new StringBuilder(line);
                    // Check if line ends with a semicolon (end of SQL command)
                    if (line.trim().endsWith(";")) {
                        try {
                            counter.incrementAndGet();
                            System.out.println("Executing command #" + counter.get());
                            // Execute SQL command
                            	
                            stmt.execute(command.toString());
                            stmt.execute("COMMIT;");
                        } catch (Exception e) {
                            System.err.println("Error executing command: " + command);
                            e.printStackTrace();
                            return;
                        }
                    }
                });

                // Commit transaction

                System.out.println("Transaction committed successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
