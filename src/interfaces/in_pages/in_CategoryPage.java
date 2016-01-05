package in_pages;

public class in_CategoryPage {
		
		//Filter/Sort, Search, Table
		
		public static final String filter_textbox = "//input[@id='filter_search']";
		public static final String search_button = "//button[@type='submit']";
		public static final String searchtool_button = "//div [@class='btn-wrapper hidden-phone']";
		public static final String status_filter_dropdown = "//select[@name='filter_published']";
		public static final String access_filter_dropdown = "//select[@name='filter_access']";
		public static final String language_filter_dropdown = "//select[@name='filter_language']";
		
		public static final String row_checkbox = "//a[contains(text(),'%s')]/../preceding-sibling::td/input";
		public static final String article_link = "//a[contains(text(),'%s')]";
		public static final String message_header = "//dl[@id='system-message']";
		public static final String publish_status_icon = "//td/a[contains(text(),'%s')]/../../td/a/span";
		

		public static final String filter_status_dropdown = "//div[@id='filter_published_chzn']";
		public static final String filter_status_dropdown_values = "//div[@id='filter_published_chzn']/div/ul/li[text()='%s']";
		public static final String filter_access_dropdown = "//div[@id='filter_access_chzn']";
		public static final String filter_access_dropdown_values = "//div[@id='filter_access_chzn']/div/ul/li[text()='%s']";
		public static final String filter_language_dropdown = "//div[@id='filter_language_chzn']";
		public static final String filter_language_dropdown_values = "//div[@id='filter_language_chzn']/div/ul/li[text()='%s']";
		
				
		//Fill info
		
		public static final String title_texbox ="//input[@id='jform_title']";
		public static final String status_dropdown = "//div[@id='jform_published_chzn']";
		public static final String status_dropdown_values = "//div[@id='jform_published_chzn']/div/ul/li[text()='%s']";
		public static final String access_dropdown = "//div[@id='jform_access_chzn']";
		public static final String access_dropdown_values = "//div[@id='jform_access_chzn']/div/ul/li[text()='%s']";
		public static final String language_dropdown = "//div[@id='jform_language_chzn']";
		public static final String language_dropdown_values = "//div[@id='jform_language_chzn']/div/ul/li[text()='%s']";
		
				
				
		//Help page
		public static final String help_text = "//p[contains (text (), 'The Category Manager is where you can view existing Categories')]";
	}
