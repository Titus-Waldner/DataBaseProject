
# Final Project - Steam Game Database

This is the Final Project Submission for Group 1.
This was made for COMP 3380 A01, Winter Term, 2024.
Members include:

- Garizaldy Gerra | 7881885
- Sahej Garg | 7873602
- Titus Waldner | 7881218

This readme.md file contains information on the contents of the project folder, instructions on how to use the command-line interface, and how to delete and repopulate the database.

## Contents of Project Folder

The content of the prodject folder should be as follows:

- **README.md** - This is the file you are reading, containing information on the entire project.
- **Makefile** - This is the make file configured to run on linux computers on Aviary.
- **mssql-jdbc-11.2.0.jre11.jar** - This is the .jar file for compiling the .java files into class files.
- **auth.cfg** - This file contains authentication information to connect to the Uranium database under Sahej's account. This includes the username and password required.
- **ProjectCLI.java** - This is the java file containing the source code for the command-line interface. This code will be compiled and run to interact with the Uranium database.
- **refresh.sql** - This is the sql file that drops all tables, recreates the tables, and populates it with all Steam Game data.
- **Code_Collection.zip** - This is the collection of Python scripts used to clean the data in our database.

## Instructions on using the CLI

To use the command line interface on Aviary computers, download the .zip folder titled DatabaseProject and follow these instructions:

 1. Open a terminal.
 2. Unzip the project file using the command `unzip DatabaseProject.zip`
 3. Navigate into the project file using the command `cd DatabaseProject`
 4. Run the command `make` to compile the .java file into .class files.
 5. Run the command `make run` to run the ProjectCLI.class file. Running this class file on the Aviary system will connect it to Sahej's Uranium database.
 6. At this point you are now using the project's CLI, and should follow the instructions provided in the terminal. The general format of commands are as follows: `[command] [arg1] [arg2] [arg3] ...` Spaces separate each argument, if a space is wanted in an argument, represent it using an underscore.
 7. To close the project's CLI, enter the `q` command.
 8. To clean up all .class files, run the command `make clean`

## Deleting and Repopulating the Database

Deleting and Repopulating all the tables in the database is possible using the command `refresh` in the project's CLI. This command will execute all SQL statements in the refresh.sql file.

It is NOT recommended to use this command as populating this database may take up to 20 hours to complete. The database will be populated prior to demonstration and TA testing for convenience. The command to run this file is included as per project requirements, but we advise against running this command.

To run this command:

1. Setup and run the project's CLI as mentioned in the previous instructions.
2. Run the command `refresh`
3. Accept the command by entering `YES` (case-sensitive)
4. Accept the command by entering `Yes I do` (case-sensitive)
5. Wait up to 20 hours for the tables to be dropped, created, and populated.

Print statements will be run in the terminal to ensure the script is still running throughout step 5.

## Aviary Notes

The project folder unzipped is roughly 3.6 GB in size. This may cause an issue on Aviary systems if there is a limitted amount of storage available on TA accounts. If this is an issue, the project is additionally available on Sahej's account on the Aviary system.

Here are the details of this location:

Machine: finch.cs.umanitoba.ca
Filepath: data/student/gargs4
