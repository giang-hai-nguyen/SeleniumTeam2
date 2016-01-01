package config;

public class Config {
	//username & password of default site 
	public static String default_site = XmlConfiguration.getXmlData("url");
	public static String default_username = XmlConfiguration.getXmlData("username");
	public static String default_password = XmlConfiguration.getXmlData("password");
	
//	public static final String default_username ="admin";
//	public static final String default_password ="admin";
//	public static final String default_site ="http://localhost/Joomla3.4.8/administrator/index.php";

	// Time wait for page loading
	public static long long_wait_time = 5;
	public static long short_wait_time = 2;
	public static int timeout = 5;
	 
	//setting
	public static String contact_yes_featured = "Yes";
	public static String contact_no_featured = "No";
}
