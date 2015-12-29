package in_pages;

public class in_WeblinksPage {

	//Web links Page options
	
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
	public static final String status_filter_dropdown = "//select[@name='filter_published']";
	public static final String category_filter_dropdown = "//select[@name='filter_category_id']";
	public static final String access_filter_dropdown = "//select[@name='filter_access']";
	
	public static final String row_checkbox = "//a[contains(text(),'%s')]/../preceding-sibling::td/input";
	public static final String article_link = "//a[contains(text(),'%s')]";
	public static final String message_header = "//dl[@id='system-message']";
	public static final String publish_status_icon = "//a[contains(text(),'%s')]/../following-sibling::td[1]/a/span";
	public static final String access_status = "//a[contains(text(),'%s')]/../following-sibling::td[5]";
	
	
	//Fill info
	
	public static final String title_texbox ="//input[@id='jform_title']";
	public static final String url_texbox ="//input[@id='jform_url']";
	public static final String status_dropdown ="//select[@id='jform_state']";
	
	
	//Save, Save & close, Save & New, Cancel buttons 
	
	public static final String save_button ="//li[@id='toolbar-apply']/a/span";
	public static final String saveclose_button="//li[@id='toolbar-save']/a/span";
	public static final String savenew_button="//li[@id='toolbar-save-new']/a/span";
	public static final String cancel_button="[@id='toolbar-cancel']/a/span";
	
	//Help page
	public static final String help_text= "//p[contains (text (), 'The Web Links Manager allows you to manage links to other web sites and organize them into categories')]";
	
	
}
