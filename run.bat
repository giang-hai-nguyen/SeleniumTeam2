set ProjectPath=%~dp0
cd %ProjectPath%

java -classpath "%ProjectPath%bin;%ProjectPath%libs\*" org.testng.TestNG "%ProjectPath%src\testng.xml"