.PHONY: clean build run

JAVA_FILES := ProjectCLI.java
CLASSES := $(JAVA_FILES:.java=.class)

build: $(CLASSES)

%.class: %.java 
	javac $<

clean:
	rm ProjectCLI.class MyDatabase.class

run: 
	java -cp .:mssql-jdbc-11.2.0.jre11.jar ProjectCLI
