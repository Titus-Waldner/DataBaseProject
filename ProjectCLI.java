
/*-------------------------------------------------------------------------
  Import libraries Ms Sql Version
--------------------------------------------------------------------------*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.Properties;

/*-------------------------------------------------------------------------
  ProjectCLI class implementation
--------------------------------------------------------------------------*/
public class ProjectCLI {
	static Connection connection;
	static String sqlScript = "./refresh.sql"; // file location for refresh .sql file

	public static void main(String[] args) throws Exception {

		// startup sequence
		MyDatabase db = new MyDatabase();
		runConsole(db);

		System.out.println("Exiting...");
	}

	public static void runConsole(MyDatabase db) {

		Scanner console = new Scanner(System.in);
		System.out.println("Welcome to the Steam Game Database Analyzer!\n");
		System.out.println("Please view below for a list of commands.\n");
		printHelp();
		System.out.print("Input > ");
		String line = console.nextLine();
		String[] parts;

		while (line != null && !line.equals("q")) {
			parts = line.split("\\s+");
			for (int i = 0; i < parts.length; i++)
				parts[i] = parts[i].replace('_', ' ');

			// Display a loading message
			System.out.println("Loading.......");

			// help
			if (parts[0].equals("h")) {
				printHelp();
			}
			// t
			else if (parts[0].equals("t")) {
				db.tags();
			}
			// g
			else if (parts[0].equals("g")) {
				db.genres();
			}
			// c
			else if (parts[0].equals("c")) {
				db.categories();
			}
			// p
			else if (parts[0].equals("p")) {
				db.publishers();
			}
			// d
			else if (parts[0].equals("d")) {
				db.developers();
			}
			// s5
			else if (parts[0].equals("s5")) {
				db.s5();
			}
			// m5
			else if (parts[0].equals("m5")) {
				db.m5();
			}
			// gcr
			else if (parts[0].equals("gcr")) {
				db.gcr();
			}
			// mrg
			else if (parts[0].equals("mrg")) {
				db.mrg();
			}
			// rc
			else if (parts[0].equals("rc")) {
				db.rc();
			}
			// cw
			else if (parts[0].equals("cw")) {
				db.cw();
			}
			// lavg
			else if (parts[0].equals("lavg")) {
				db.lavg();
			}
			// tg
			else if (parts[0].equals("tg")) {
				try {
					if (parts.length == 3)
						db.tg(parts[1], parts[2]);
					else
						System.out.println("This command requires 2 arguments.");
				} catch (Exception e) {
					System.out.println("Correct Usage: tg <tag> <price>");
					System.out.println("<tag>: String");
					System.out.println("<price>: double");
				}
			}
			// dc
			else if (parts[0].equals("dc")) {
				try {
					if (parts.length == 2)
						db.dc(parts[1]);
					else
						System.out.println("This command requires 1 argument.");
				} catch (Exception e) {
					System.out.println("Correct Usage: dc <category>");
					System.out.println("<category>: String");
				}
			}
			// gr
			else if (parts[0].equals("gr")) {
				try {
					if (parts.length == 2)
						db.gr(parts[1]);
					else
						System.out.println("This command requires 1 argument.");
				} catch (Exception e) {
					System.out.println("Correct Usage: gr <genre>");
					System.out.println("<genre>: String");
				}
			}
			// apc
			else if (parts[0].equals("apc")) {
				try {
					if (parts.length == 2)
						db.apc(parts[1]);
					else
						System.out.println("This command requires 1 arguments.");
				} catch (Exception e) {
					System.out.println("Correct Usage: apc <publisher/developer>");
					System.out.println("<publisher/developer>: String");
				}
			}
			// lg
			else if (parts[0].equals("lg")) {
				try {
					if (parts.length == 2)
						db.lg(parts[1]);
					else
						System.out.println("This command requires 1 argument.");
				} catch (Exception e) {
					System.out.println("Correct Usage: lg <publisher/developer>");
					System.out.println("<publisher/developer>: String");
				}
			}
			// ccu
			else if (parts[0].equals("ccu")) {
				try {
					if (parts.length == 2)
						db.ccu(parts[1]);
					else
						System.out.println("This command requires 1 argument.");
				} catch (Exception e) {
					System.out.println("Correct Usage: ccu <category>");
					System.out.println("<category>: String");
				}
			}
			// gc
			else if (parts[0].equals("gc")) {
				try {
					if (parts.length == 2)
						db.gc(parts[1]);
					else
						System.out.println("This command requires 1 argument.");
				} catch (Exception e) {
					System.out.println("Correct Usage: gc <publisher>");
					System.out.println("<publisher>: String");
				}
			}
			// grt
			else if (parts[0].equals("grt")) {
				try {
					if (parts.length == 5)
						db.grt(parts[1], parts[2], parts[3], parts[4]); // Call grt with genre, category, start, and end
					else
						System.out.println("This command requires 4 arguments.");
				} catch (Exception e) {
					System.out.println("Correct Usage: grt <genre> <category> <start> <end>");
					System.out.println("<genre>: String");
					System.out.println("<category>: String");
					System.out.println("<start>: String (YYYY-MM-DD)");
					System.out.println("<end>: String (YYYY-MM-DD)");
				}
			}
			// nr
			else if (parts[0].equals("nr")) {
				try {
					if (parts.length == 2)
						db.nr(parts[1]);
					else
						System.out.println("This command requires 1 argument.");
				} catch (Exception e) {
					System.out.println("Correct Usage: nr <developer>");
					System.out.println("<developer>: String");
				}
			} else if (parts[0].equals("refresh")) {
				boolean runCommand = false;
				System.out.println("WARNING: This command drops all tables and repopulates the database from scratch.");
				System.out.println("WARNING: This command may take up to 20 hours to complete.");
				System.out.println("WARNING: Proceed with caution.");
				System.out.println("WARNING: This command was added as per project requirements, but not recommended to use.");
				System.out.println("\nDo you want to continue? Type \"YES\" in all caps if so.");
				System.out.print("Input > ");
				line = console.nextLine();
				if (line.equals("YES")) {
					System.out.println("WARNING: This command can and will take 20 hours to complete.");
					System.out.println("WARNING: Please be absolutely sure before continuing.");
					System.out.println("\nDo you REALLY want to continue? Type \"Yes I do\" with correct capitalization if so.");
					System.out.print("Input > ");
					line = console.nextLine();
					if (line.equals("Yes I do")) {
						runCommand = true;
					}
				}

				// run command
				if (runCommand) {
					try {
						db.removeAndReloadData(sqlScript);
					} catch (Exception e) {
						System.out.println(e);
					}
				}
				// abort command
				else {
					System.out.println("Refresh aborted...");
				}
			}
			// if no command matched the input
			else
				System.out.println("Unknown command inputted. For a list of commands enter \'h\'");

			System.out.print("Input > ");
			line = console.nextLine();
		}

		console.close();
	}

	/*-------------------------------------------------------------------------
	Print Help menu 
	--------------------------------------------------------------------------*/

	private static void printHelp() {
		System.out.println("Quick Command Guide:");
		System.out.println();

		String[][] commands = {
				{ "h", "Help menu" },
				{ "t", "The top 10 distinct tags to test" },
				{ "g", "The top 10 distinct genres to test" },
				{ "c", "The top 10 distinct categories to test" },
				{ "p", "The top 10 distinct publishers to test" },
				{ "d", "The top 10 distinct developers to test" },
				{ "s5", "The top 5 games with most screenshots" },
				{ "m5", "The top 5 games with the most movies" },
				{ "lavg", "Genre with the lowest average game price" },
				{ "rc", "Top 25 developers with most reviews, published by multiple publishers" },
				{ "cw", "Returns genre with most curse words in reviews" },
				{ "gcr", "Top 10 publishers with most games and corresponding game compatibility ratio" },
				{ "tg <tag> <price>", "Counts games by tag and minimum price" },
				{ "dc <category>", "Lists game developers by category" },
				{ "gr <genre>", "Shows games in genre with >= 50% positive reviews" },
				{ "apc <publisher/developer>", "Average price for games by publisher or developer" },
				{ "lg <publisher/developer>", "Lists supported languages by publisher or developer" },
				{ "ccu <category>", "Max and min concurrent users for games in a category" },
				{ "gc <publisher>", "Game compatibility comparison across platforms by publisher" },
				{ "mrg", "Most released game genres by a prolific developer" },
				{ "nr <developer>", "Negative ratings count for a developer's top-selling game" },
				{ "grt <genre> <category> <start (YYYY-MM-DD)> <end (YYYY-MM-DD)>",
						"Game release count in genre and category between dates" },
				{ "refresh",
						"Drops, Creates, and Populates tables from scratch." },
				{ "", "!!!WARNING: Refresh command takes 20 hours to complete.!!!" },
				{ "q", "Exit the program" }
		};

		for (String[] command : commands) {
			System.out.printf("%-25s - %s%n", command[0], command[1]);
		}
		System.out.println();
		System.out.println("NOTE: If arguments include spaces, use underscores in their place.");
		System.out.println("---- end help -----");
	}

}

/*-------------------------------------------------------------------------
  My Database Connection
--------------------------------------------------------------------------*/
class MyDatabase {
	private Connection connection;

	public MyDatabase() {
		// read auth.cfg file for username and password
		Properties prop = new Properties();
		String fileName = "auth.cfg";
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

		// get username and password
		String username = (prop.getProperty("username"));
		String password = (prop.getProperty("password"));

		try {
			// create string with username and password
			String connectionUrl = "jdbc:sqlserver://uranium.cs.umanitoba.ca:1433;"
					+ "database=cs3380;"
					+ "user=" + username + ";"
					+ "password=" + password + ";"
					+ "encrypt=false;"
					+ "trustServerCertificate=false;"
					+ "loginTimeout=30;";
			// create a connection to the database
			connection = DriverManager.getConnection(connectionUrl);
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}

	}

	// a function to clean the string
	public String cleanStringInput(String myString) {
		return myString.replace("\'", "").replace("\"", "").replace("--", "").replace(";", "");
	}

	// a function to print tabularized results, throws SQL exception
	public void printResults(ResultSet resultSet) throws SQLException {
		ResultSetMetaData metaData = resultSet.getMetaData();
		int numColumns = metaData.getColumnCount();

		// Print column headers
		for (int i = 1; i <= numColumns; i++) {
			System.out.print(String.format("%-25s", metaData.getColumnLabel(i)));
		}
		System.out.println();

		// Print rows
		while (resultSet.next()) {
			for (int i = 1; i <= numColumns; i++) {
				System.out.print(String.format("%-25s", resultSet.getString(i)));
			}
			System.out.println();
		}
	}

	/*-------------------------------------------------------------------------
	SQL Queries
	--------------------------------------------------------------------------*/
	// removes and refreshes data
	// WARNING: CAN TAKE 20 HOURS TO RUN
	public void removeAndReloadData(String script) throws IOException, SQLException {
		BufferedReader reader = new BufferedReader(new FileReader(script));
		String line = reader.readLine();
		// assumes each query is its own line
		System.out.println("Beginning removal and reloading process...");
		while (line != null) {
			System.out.println(line);
			this.connection.createStatement().execute(line);
			line = reader.readLine();
		}
		System.out.println("Completed removal and reloading process...");
		reader.close();
	}

	// t
	public void tags() {
		try {
			String sql = "SELECT DISTINCT TOP 10 TagName FROM Tag;";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("No tags found.");
			} else {
				printResults(resultSet);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	// g
	public void genres() {
		try {
			String sql = "SELECT DISTINCT TOP 10 GenreName FROM Genre;";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("No genres found.");
			} else {
				printResults(resultSet);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	// c
	public void categories() {
		try {
			String sql = "SELECT DISTINCT TOP 10 CategoriesName FROM Category;";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("No categories found.");
			} else {
				printResults(resultSet);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	// p
	public void publishers() {
		try {
			String sql = "SELECT DISTINCT TOP 10 PublishersName FROM Publisher;";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("No publishers found.");
			} else {
				printResults(resultSet);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	// d
	public void developers() {
		try {
			String sql = "SELECT DISTINCT TOP 10 DevelopersName FROM Developer;";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("No developers found.");
			} else {
				printResults(resultSet);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	// s5
	public void s5() {
		try {
			String sql = "SELECT TOP 5 AppID, COUNT(ScreenshotLinks) AS TotalNumberOfScreenshots "
					+ "FROM Screenshot "
					+ "GROUP BY AppID "
					+ "ORDER BY TotalNumberOfScreenshots DESC;";

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("No results found.");
			} else {
				printResults(resultSet);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	// m5
	public void m5() {
		try {
			String sql = "SELECT TOP 5 AppID, COUNT(ScreenshotLinks) AS TotalNumberOfScreenshots "
					+ "FROM Screenshot "
					+ "GROUP BY AppID "
					+ "ORDER BY TotalNumberOfScreenshots DESC;";

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("No results found.");
			} else {
				printResults(resultSet);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	// tg
	public void tg(String tag, String price) {
		double doublePrice = Double.parseDouble(price);
		try {
			String sql = "SELECT COUNT(*) "
					+ "FROM Game "
					+ "INNER JOIN Tag ON Game.AppID = Tag.AppID "
					+ "WHERE LOWER(Tag.TagName) = ? "
					+ "AND Game.Price > ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, cleanStringInput(tag.toLowerCase()));
			statement.setDouble(2, doublePrice);
			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("No results found.");
			} else {
				printResults(resultSet);
			}
			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	// dc
	public void dc(String category) {
		try {
			String sql = "SELECT DISTINCT TOP 10 Developer.DevelopersName "
					+ "FROM Developer "
					+ "INNER JOIN Game ON Developer.AppID = Game.AppID "
					+ "INNER JOIN Category ON Game.AppID = Category.AppID "
					+ "WHERE LOWER(Category.CategoriesName) = ? "
					+ "ORDER BY Developer.DevelopersName;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, cleanStringInput(category.toLowerCase()));
			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("No results found.");
			} else {
				printResults(resultSet);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	// gr
	public void gr(String genre) {
		try {
			String sql = "SELECT TOP 10 GameName "
					+ "FROM ( "
					+ "    SELECT DISTINCT CAST(Game.Name AS VARCHAR(MAX)) AS GameName, "
					+ "           Game.Positive * 100 / NULLIF(Game.Positive + Game.Negative, 0) AS PositivePercentage "
					+ "    FROM Game "
					+ "    INNER JOIN Genre ON Game.AppID = Genre.AppID "
					+ "    INNER JOIN Reviews ON Game.AppID = Reviews.app_id "
					+ "    WHERE LOWER(Genre.GenreName) = ? "
					+ "         AND Game.Positive * 100 / NULLIF(Game.Positive + Game.Negative, 0) >= 50 "
					+ ") AS Subquery "
					+ "ORDER BY PositivePercentage DESC;";
	
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, cleanStringInput(genre.toLowerCase()));
			ResultSet resultSet = statement.executeQuery();
	
			if (!resultSet.isBeforeFirst()) {
				System.out.println("No results found.");
			} else {
				printResults(resultSet);
			}
	
			resultSet.close();
			statement.close();
	
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}
	
	
	// apc
	public void apc(String publisherOrDeveloper) {
		try {
			String sql = "SELECT AVG(Game.Price) AS AveragePrice "
					+ "FROM Game "
					+ "LEFT JOIN Developer ON Game.AppID = Developer.AppID "
					+ "LEFT JOIN Publisher ON Game.AppID = Publisher.AppID "
					+ "WHERE LOWER(Developer.DevelopersName) = ? "
					+ "OR LOWER(Publisher.PublishersName) = ?;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, cleanStringInput(publisherOrDeveloper.toLowerCase()));
			statement.setString(2, cleanStringInput(publisherOrDeveloper.toLowerCase()));

			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("No results found.");
			} else {
				printResults(resultSet);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	// lg
	public void lg(String publisherOrDeveloper) {
		try {
			String sql = "SELECT DISTINCT TOP 25 "
					+ "LanguageAudio.Language AS AudioLanguage, "
					+ "LanguageInterface.Language AS InterfaceLanguage "
					+ "FROM Game "
					+ "LEFT JOIN Developer ON Game.AppID = Developer.AppID "
					+ "LEFT JOIN Publisher ON Game.AppID = Publisher.AppID "
					+ "INNER JOIN LanguageAudio ON Game.AppID = LanguageAudio.AppID "
					+ "INNER JOIN LanguageInterface ON Game.AppID = LanguageInterface.AppID "
					+ "WHERE LOWER(Developer.DevelopersName) = ? "
					+ "OR LOWER(Publisher.PublishersName) = ? "
					+ "ORDER BY AudioLanguage, InterfaceLanguage;";

			PreparedStatement statement = connection.prepareStatement(sql);
			String cleanedInput = cleanStringInput(publisherOrDeveloper.toLowerCase());
			statement.setString(1, cleanedInput);
			statement.setString(2, cleanedInput);

			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("No results found.");
			} else {
				printResults(resultSet);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	// ccu
	public void ccu(String category) {
		try {

			String sql = "WITH categoryCCU AS ( "
					+ "SELECT Category.CategoriesName, OperatingSystem.SupportedSystem, Game.peakCCU "
					+ "FROM Game "
					+ "INNER JOIN Category ON Game.AppID = Category.AppID "
					+ "INNER JOIN OperatingSystem ON Game.AppID = OperatingSystem.AppID "
					+ "WHERE LOWER(Category.CategoriesName) = ? ), "
					+ "selectedCCU AS ("
					+ "SELECT TOP 10 CategoriesName AS categoryName, SupportedSystem AS supportSystem, "
					+ "MAX(peakCCU) AS maxCCU, MIN(peakCCU) AS minCCU "
					+ "FROM categoryCCU "
					+ "GROUP BY CategoriesName, SupportedSystem "
					+ "ORDER BY maxCCU DESC) "
					+ "SELECT * FROM selectedCCU;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, cleanStringInput(category.toLowerCase()));

			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("No results found.");
			} else {
				printResults(resultSet);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	// gc
	public void gc(String publisher) {
		try {
			// Prepare the SQL statement
			String sql = "WITH gameCompatibility AS ( "
					+ "SELECT Publisher.PublishersName, OperatingSystem.SupportedSystem "
					+ "FROM Game "
					+ "INNER JOIN Publisher ON Game.AppID = Publisher.AppID "
					+ "INNER JOIN OperatingSystem ON Game.AppID = OperatingSystem.AppID "
					+ "WHERE LOWER(Publisher.PublishersName) = ? "
					+ ") "
					+ "SELECT TOP 25 PublishersName AS publisherName, SupportedSystem AS supportSystem, "
					+ "COUNT(SupportedSystem) AS GamesCompatibleAcrossPlatforms "
					+ "FROM gameCompatibility "
					+ "GROUP BY PublishersName, SupportedSystem "
					+ "ORDER BY GamesCompatibleAcrossPlatforms DESC;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, cleanStringInput(publisher.toLowerCase()));

			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("No results found.");
			} else {
				printResults(resultSet);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	// gcr
	public void gcr() {
		try {
			String sql = "WITH publisherCompatibility AS ( "
					+ "SELECT Publisher.PublishersName, "
					+ "SUM(CASE WHEN OperatingSystem.SupportedSystem LIKE '%Windows%' THEN 1 ELSE 0 END) AS TotalWindowsGames, "
					+ "SUM(CASE WHEN OperatingSystem.SupportedSystem LIKE '%Mac%' THEN 1 ELSE 0 END) AS TotalMacGames, "
					+ "SUM(CASE WHEN OperatingSystem.SupportedSystem LIKE '%Linux%' THEN 1 ELSE 0 END) AS TotalLinuxGames, "
					+ "COUNT(Game.AppID) AS TotalGamesByPublisher "
					+ "FROM Game "
					+ "INNER JOIN Publisher ON Game.AppID = Publisher.AppID "
					+ "INNER JOIN OperatingSystem ON Game.AppID = OperatingSystem.AppID "
					+ "GROUP BY Publisher.PublishersName "
					+ ") "
					+ "SELECT TOP 10 PublishersName AS publisherName, TotalWindowsGames, TotalMacGames, TotalLinuxGames, "
					+ "ROUND((TotalWindowsGames * 100.0 / TotalGamesByPublisher), 2) AS WindowsCompatibility, "
					+ "ROUND((TotalMacGames * 100.0 / TotalGamesByPublisher), 2) AS MacCompatibility, "
					+ "ROUND((TotalLinuxGames * 100.0 / TotalGamesByPublisher), 2) AS LinuxCompatibility, "
					+ "TotalGamesByPublisher "
					+ "FROM publisherCompatibility "
					+ "ORDER BY TotalGamesByPublisher DESC, PublishersName;";
			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("No results found.");
			} else {
				printResults(resultSet);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	// grt
	public void grt(String genre, String category, String start, String end) {
		try {
			String query = "SELECT COUNT(DISTINCT Game.AppID) AS TotalGamesReleased "
							+ "FROM Game "
							+ "INNER JOIN Genre ON Game.AppID = Genre.AppID "
							+ "INNER JOIN Category ON Game.AppID = Category.AppID "
							+ "WHERE LOWER(CAST(Genre.GenreName AS VARCHAR(MAX))) = LOWER(?) "
							+ "AND LOWER(CAST(Category.CategoriesName AS VARCHAR(MAX))) = LOWER(?) "
							+ "AND CONVERT(VARCHAR(10), Game.ReleaseDate, 120) >= ? "
							+ "AND CONVERT(VARCHAR(10), Game.ReleaseDate, 120) < ?;";
	
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, genre.toLowerCase());
			statement.setString(2, category.toLowerCase());
			statement.setString(3, start);
			statement.setString(4, end);
	
			ResultSet resultSet = statement.executeQuery();
	
			if (!resultSet.isBeforeFirst()) {
				System.out.println("No results found.");
			} else {
				while(resultSet.next()) {
					int totalGamesReleased = resultSet.getInt("TotalGamesReleased");
					System.out.println("TotalGamesReleased: " + totalGamesReleased);
				}
			}
	
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}
	
	
	// mrg
	public void mrg() {
		try {
			String sql = "WITH DevelopersWithOver10Games AS ( " +
					"    SELECT " +
					"        Developer.AppID " +
					"    FROM " +
					"        Developer " +
					"    INNER JOIN Game ON Developer.AppID = Game.AppID " +
					"    GROUP BY " +
					"        Developer.AppID " +
					"    HAVING " +
					"        COUNT(Game.AppID) > 10 " +
					"), " +
					"GameCountsByGenre AS ( " +
					"    SELECT " +
					"        Developer.AppID AS DeveloperAppID, " +
					"        Genre.GenreName, " +
					"        COUNT(Game.AppID) AS GameCount " +
					"    FROM " +
					"        Developer " +
					"    INNER JOIN Game ON Developer.AppID = Game.AppID " +
					"    INNER JOIN Genre ON Game.AppID = Genre.AppID " +
					"    WHERE " +
					"        Developer.AppID IN (SELECT AppID FROM DevelopersWithOver10Games) " +
					"    GROUP BY " +
					"        Developer.AppID, Genre.GenreName " +
					"), " +
					"RankedGenresByDeveloper AS ( " +
					"    SELECT " +
					"        DeveloperAppID, " +
					"        GenreName, " +
					"        GameCount, " +
					"        RANK() OVER (PARTITION BY DeveloperAppID ORDER BY GameCount DESC) AS GenreRank " +
					"    FROM " +
					"        GameCountsByGenre " +
					") " +
					"SELECT TOP 25 " +
					"    Developer.DevelopersName, " +
					"    rg.GenreName, " +
					"    rg.GameCount " +
					"FROM " +
					"    RankedGenresByDeveloper rg " +
					"INNER JOIN Developer ON rg.DeveloperAppID = Developer.AppID " +
					"WHERE " +
					"    rg.GenreRank = 1 " +
					"ORDER BY " +
					"    rg.GameCount DESC, Developer.DevelopersName;";

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("No results found.");
			} else {
				printResults(resultSet);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	// cw
	public void cw() {
		try {
			String sql = "WITH CurseWordCounts AS ( " +
					"    SELECT " +
					"        Genre.GenreName, " +
					"        COUNT(*) AS CurseWordCount " +
					"    FROM " +
					"        Reviews " +
					"    INNER JOIN Game ON Reviews.app_id = Game.AppID " +
					"    INNER JOIN Genre ON Game.AppID = Genre.AppID " +
					"    WHERE " +
					"        LOWER(CONVERT(nvarchar(max), Reviews.review_text)) LIKE '%fuck%' OR " +
					"        LOWER(CONVERT(nvarchar(max), Reviews.review_text)) LIKE '%ruin%' OR " +
					"        LOWER(CONVERT(nvarchar(max), Reviews.review_text)) LIKE '%bad%' " +
					"    GROUP BY Genre.GenreName " +
					") " +
					"SELECT TOP 1 " +
					"    GenreName, " +
					"    COALESCE(SUM(CurseWordCount), 0) AS TotalCurseWordsInGenre " +
					"FROM " +
					"    CurseWordCounts " +
					"GROUP BY " +
					"    GenreName " +
					"ORDER BY " +
					"    TotalCurseWordsInGenre DESC;";

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("No results found.");
			} else {
				printResults(resultSet);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	// rc
	public void rc() {
		try {
			String sql = "WITH DeveloperPublisherCounts AS ( " +
					"    SELECT " +
					"        Game.AppID, " +
					"        COUNT(DISTINCT Publisher.PublishersName) AS PublisherCount " +
					"    FROM " +
					"        Game " +
					"    INNER JOIN Publisher ON Game.AppID = Publisher.AppID " +
					"    GROUP BY " +
					"        Game.AppID " +
					"    HAVING " +
					"        COUNT(DISTINCT Publisher.PublishersName) > 1 " +
					"), " +
					"DeveloperReviewCounts AS ( " +
					"    SELECT " +
					"        Developer.DevelopersName, " +
					"        COUNT(Reviews.review_score) AS ReviewCount " +
					"    FROM " +
					"        DeveloperPublisherCounts DPC " +
					"    INNER JOIN Game ON DPC.AppID = Game.AppID " +
					"    INNER JOIN Developer ON Game.AppID = Developer.AppID " +
					"    INNER JOIN Reviews ON Game.AppID = Reviews.app_id " +
					"    GROUP BY " +
					"        Developer.DevelopersName " +
					") " +
					"SELECT TOP 25 " +
					"    DevelopersName AS DeveloperName, " +
					"    ReviewCount " +
					"FROM " +
					"    DeveloperReviewCounts " +
					"ORDER BY " +
					"    ReviewCount DESC;";

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("No results found.");
			} else {
				printResults(resultSet);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	// nr
	public void nr(String developer) {
		try {
			String sql = "WITH DeveloperTopSellingGame AS ( " +
					"    SELECT TOP 1 " +
					"        Game.AppID, " +
					"        Game.Name, " +
					"        Game.DLCcount " +
					"    FROM " +
					"        Game " +
					"    INNER JOIN Developer ON Game.AppID = Developer.AppID " +
					"    WHERE " +
					"        LOWER(Developer.DevelopersName) = LOWER(?) " +
					"    ORDER BY " +
					"        Game.DLCcount DESC " +
					") " +
					"SELECT " +
					"    Game.Name, " +
					"    ISNULL(Game.Negative, 0) AS TotalNegativeReviews " +
					"FROM " +
					"    Game " +
					"INNER JOIN DeveloperTopSellingGame ON DeveloperTopSellingGame.AppID = Game.AppID;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, cleanStringInput(developer.toLowerCase()));
			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("No results found.");
			} else {
				printResults(resultSet);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	// lavg
	public void lavg() {
		try {
			String sql = "WITH GenreAveragePrice AS ( "
					+ "    SELECT "
					+ "        Genre.GenreName, "
					+ "        AVG(Game.Price) AS AveragePrice "
					+ "    FROM "
					+ "        Game "
					+ "    INNER JOIN Genre ON Game.AppID = Genre.AppID "
					+ "    GROUP BY "
					+ "        Genre.GenreName "
					+ ") "
					+ "SELECT "
					+ "    GenreName, "
					+ "    AveragePrice "
					+ "FROM GenreAveragePrice "
					+ "WHERE AveragePrice = ( "
					+ "    SELECT MIN(AveragePrice) FROM GenreAveragePrice "
					+ ");";

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("No results found.");
			} else {
				printResults(resultSet);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

}
