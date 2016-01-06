package in_pages;

public class in_BannerClientPage {
	
			//BannerClient Page options
	
			public static final String new_button = "//li[@id='toolbar-new']/a/span";
			public static final String edit_button = "//li[@id='toolbar-edit']/a/span";
			public static final String publish_button= "//li[@id='toolbar-publish']/a/span";
			public static final String unpublish_button= "//li[@id='toolbar-unpublish']/a/span";
			public static final String archive_button= "//li[@id='toolbar-archive']/a/span";
			public static final String checkin_button= "//li[@id='toolbar-checkin']/a/span";
			public static final String trash_button= "//li[@id='toolbar-trash']/a/span";
			public static final String help_button= "//li[@id='toolbar-help']/a/span";

			
			//Filter/Sort, Search, Table
			
			public static final String filter_textbox = "//input[@id='filter_search']";
			public static final String search_button = "//button[@type='submit']";
			public static final String searchtool_button = "//div[@class='btn-wrapper hidden-phone']";	
			public static final String clear_button = "//div[@class='btn-wrapper']/button[contains (text(), 'Clear')]";
						
			public static final String row_checkbox = "//a[contains(text(),'%s')]/../preceding-sibling::td/input";
			public static final String article_link = "//a[contains(text(),'%s')]";
			public static final String message_header = "//dl[@id='system-message']";
			public static final String publish_status_icon = "//a[contains(text(),'%s')]/../../../td/div/a/span";
			
			public static final String filter_status_dropdown = "//div[@id='filter_state_chzn']/a/span";
			public static final String filter_status_dropdown_values = "//div[@id='filter_state_chzn']/div/ul/li[text()='%s']";
			
			
			
			//Fill info
			
			public static final String clientname_texbox ="//input[@id='jform_name']";
			public static final String contact_textbox = "//input[@id='jform_contact']";
			public static final String email_textbox = "//input[@id='jform_email']";
					
			public static final String status_dropdown = "//div[@id='jform_state_chzn']/a/span";
			public static final String status_dropdown_values = "//div[@id='jform_state_chzn']/a/span/../../div/ul/li[text()='%s']";
			
						
			
			
			//Help page
			public static final String help_text = "//p[contains(text(), 'It is used to add or edit banner clients')]";
}
