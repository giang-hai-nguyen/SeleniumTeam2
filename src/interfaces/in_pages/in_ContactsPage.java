package in_pages;

public class in_ContactsPage {
	
	/***************Main Contact Page***************/	
	
	//filter dropdown
	public static final String filter_state_dropdown = "//div[@id='filter_published_chzn']";
	public static final String filter_state_dropdown_values = "//div[@id='filter_published_chzn']/div/ul/li[text()='%s']";
	public static final String filter_category_dropdown = "//div[@id='filter_category_id_chzn']";
	public static final String filter_category_dropdown_values = "//div[@id='filter_category_id_chzn']/div/ul/li[text()='%s']";
	public static final String filter_access_dropdown = "//div[@id='filter_access_chzn']";
	public static final String filter_access_dropdown_values = "//div[@id='filter_access_chzn']/div/ul/li[text()='%s']";
	public static final String filter_language_dropdown = "//div[@id='filter_language_chzn']";
	public static final String filter_language_dropdown_values = "//div[@id='filter_language_chzn']/div/ul/li[text()='%s']";
	
	//paging option
	public static final String paging_dropdown = "//select[@id='limit']";
	
	//table adminlist
	public static final String publish_status_icon = "//td/div[a[contains(text(),'%s')]]/../../td/div/a/span";
	public static final String checkin_status_icon = "//a[contains(text(),'%s')]/preceding-sibling::a/span";
	
	/***************New/Edit Contact Page***************/
		
	//Information option
	public static final String name_textbox = "//input[@id='jform_name']";
	public static final String alias_textbox = "//input[@id='jform_alias']";
	public static final String category_dropdown = "//div[@id='jform_catid_chzn']";
	public static final String category_dropdown_values = "//div[@id='jform_catid_chzn']/div/ul/li[text()='%s']";
	public static final String state_dropdown = "//div[@id='jform_published_chzn']";
	public static final String state_dropdown_values = "//div[@id='jform_published_chzn']/div/ul/li[text()='%s']";
	public static final String access_dropdown = "//div[@id='jform_access_chzn']";
	public static final String access_dropdown_values = "//div[@id='jform_access_chzn']/div/ul/li[text()='%s']";
	public static final String language_dropdown = "//div[@id='jform_language_chzn']";
	public static final String language_dropdown_values = "//div[@id='jform_language_chzn']/div/ul/li[text()='%s']";
	public static final String featured = "//fieldset[@id='jform_featured']/label[text()='%s']";
	public static final String otherInfo_tabs = "//ul[@id='myTabTabs']/li/a[text()='%s']";
		
	// iframe for text
	public static final String otherinfo_iframe = "//iframe[@id='jform_misc_ifr']";
	public static final String frame_textbox ="//body[@id='tinymce']";
	
	// iframe for image
	public static final String image_frame = "//iframe[contains(@src,'option=com_media&view=images')]";
	public static final String image_frame_image_button = "//div/a[@title='Image']";
	public static final String image_frame_insert_button = "//div/button[text()='Insert']";
	public static final String image_frame_url_textbox = "//input[@id='f_url']";
	
	//help page
	public static final String help_text ="//p[contains(text(),'It is used to manage contacts in your Joomla! website.')]";
}
