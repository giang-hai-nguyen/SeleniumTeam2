set ProjectPath=%~dp0
cd %ProjectPath%

java -classpath "%ProjectPath%bin;%ProjectPath%libs\*" org.testng.TestNG "%ProjectPath%bin\TM_JOOMLA_Articles.xml"
java -classpath "%ProjectPath%bin;%ProjectPath%libs\*" org.testng.TestNG "%ProjectPath%bin\TM_JOOMLA_Banners_Client.xml"
java -classpath "%ProjectPath%bin;%ProjectPath%libs\*" org.testng.TestNG "%ProjectPath%bin\TM_JOOMLA_Category.xml"
java -classpath "%ProjectPath%bin;%ProjectPath%libs\*" org.testng.TestNG "%ProjectPath%bin\TM_JOOMLA_Contacts.xml"
java -classpath "%ProjectPath%bin;%ProjectPath%libs\*" org.testng.TestNG "%ProjectPath%bin\TM_JOOMLA_Weblinks.xml"
pause