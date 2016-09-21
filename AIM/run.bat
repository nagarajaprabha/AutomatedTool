call mvn clean compile
call mvn package
call mvn assembly:single
call java -jar target/AIM-1.0-SNAPSHOT-jar-with-dependencies.jar