package in_pages;

public class in_ContactsPage {
	
	/***************Main Contact Page***************/	
	//search option
	public static final String filter_textbox = "//input[@id='filter_search']";
	public static final String search_button ="//button[text()='Search']";
	public static final String clear_button ="//button[text()='Clear']";
	
	//filter dropdown
	public static final String filter_state_dropdown = "//select[@name='filter_published']";
	public static final String filter_category_dropdown = "//select[@name='filter_category_id']";
	public static final String filter_access_dropdown = "//select[@name='filter_access']";
	public static final String filter_language_dropdown = "//select[@name='filter_language']";
	
	//paging option
	public static final String paging_dropdown = "//select[@id='limit']";
	
	//table adminlist
	public static final String publish_status_icon = "//a[contains(text(),'%s')]/../following-sibling::td[1]/a/span";
	public static final String checkin_status_icon = "//a[contains(text(),'%s')]/preceding-sibling::a/span";
	
	/***************New/Edit Contact Page***************/
	//toolbar button
//	public static final String save_button = "//li[@id='toolbar-apply']";
//	public static final String save_close_button = "//li[@id='toolbar-save']";
//	public static final String save_new_button = "//li[@id='toolbar-save-new']";
//	public static final String cancel_button = "//li[@id='toolbar-cancel']";
		
	//Information option
	public static final String name_textbox = "//input[@id='jform_name']";
	public static final String alias_textbox = "//input[@id='jform_alias']";
	public static final String category_dropdown = "//select[@id='jform_catid']";
	public static final String state_dropdown = "//select[@id='jform_published']";
	public static final String access_dropdown = "//select[@id='jform_access']";
	public static final String language_dropdown = "//select[@id='jform_language']";
	public static final String featured_dropdown = "//select[@id='jform_featured']";
	public static final String image_button = "//div[@class='image']";
		
	// iframe
	public static final String otherinfo_iframe = "//iframe[@id='jform_misc_ifr']";
	public static final String frame_textbox ="//body[@id='tinymce']";
}
