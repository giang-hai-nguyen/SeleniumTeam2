set ProjectPath=%~dp0
cd %ProjectPath%

java -classpath "%ProjectPath%bin;%ProjectPath%libs\*" org.testng.TestNG "%ProjectPath%bin\RunAll_Firefox.xml"
java -classpath "%ProjectPath%bin;%ProjectPath%libs\*" org.testng.TestNG "%ProjectPath%bin\RunAll_Chrome.xml"
java -classpath "%ProjectPath%bin;%ProjectPath%libs\*" org.testng.TestNG "%ProjectPath%bin\RunAll_IE.xml"
pause