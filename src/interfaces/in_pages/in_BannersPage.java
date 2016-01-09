package in_pages;

public class in_BannersPage 
{
	
	/***************Main Banners Page***************/	
	//search option
	public static final String filter_textbox = "//input[@id='filter_search']";
	public static final String search_button ="//button[text()='Search']";
	public static final String clear_button ="//button[text()='Clear']";
	
	//filter dropdown
	//public static final String filter_state_dropdown = "//select[@name='filter_state']";
	//public static final String filter_client_dropdown = "//select[@name='filter_client_id']";
	//public static final String filter_category_dropdown = "//select[@name='filter_category_id']";
	//public static final String filter_language_dropdown = "//select[@name='filter_language']";
	
	public static final String filter_list_dropdown = "//div/button[@data-original-title='Filter the list items.']";
	public static final String filter_state_dropdown = "//div[@id='filter_state_chzn']";
	public static final String filter_state_dropdown_values = "//div[@id='filter_state_chzn']/div/ul/li[text()='%s']";
	public static final String filter_category_dropdown = "//div[@id='filter_category_id_chzn']";
	public static final String filter_category_dropdown_values = "//div[@id='filter_category_id_chzn']/div/ul/li[text()='%s']";
	public static final String filter_language_dropdown = "//div[@id='filter_language_chzn']";
	public static final String filter_language_dropdown_values = "//div[@id='filter_language_chzn']/div/ul/li[text()='%s']";
	public static final String filter_client_dropdown = "//div[@id='filter_client_chzn']";
	public static final String filter_client_dropdown_values = "//div[@id='filter_client_chzn']/div/ul/li[text()='%s']";
	
	//paging option
	public static final String paging_dropdown = "//select[@id='limit']";
	
	//table adminlist
	public static final String publish_status_icon = "//a[contains(text(),'%s')]/../following-sibling::td[2]/a/span";
	public static final String checkin_status_icon = "//a[contains(text(),'%s')]/preceding-sibling::a/span";
	
	/***************New/Edit Banners Page***************/
		
	//Information option
	public static final String name_textbox = "//input[@id='jform_name']";
	public static final String alias_textbox = "//input[@id='jform_alias']";
	public static final String category_dropdown = "//div[@id='jform_catid_chzn']";
	public static final String category_dropdown_by_js = "//select[@id='jform_catid']";
	public static final String fillter_category_dropdown_value = "//div[@id='jform_catid_chzn']/div/ul/li[text()='%s']";
	public static final String state_dropdown = "//div[@id='jform_state_chzn']";
	public static final String state_dropdown_by_js = "//select[@id='jform_state']";
	public static final String fillter_state_dropdown_value = "//div[@id='jform_state_chzn']/div/ul/li[text()='%s']";
	public static final String description_textarea = "//textarea[@id='jform_description']";
	public static final String client_dropdown = "//div[@id='jform_cid_chzn']";
	public static final String client_dropdown_by_js = "//select[@id='jform_cid']";
	public static final String fillter_client_dropdown_value = "//div[@id='jform_cid_chzn']/div/ul/li[text()='%s']";
	
	
	/***************New/Edit Clients Page***************/
	public static final String client_name_textbox = "//input[@id='jform_name']";
	public static final String contact_name_textbox = "//input[@id='jform_contact']";
	public static final String contact_email_textbox = "//input[@id='jform_email']";
	
	/***************New/Edit Category Page***************/
	public static final String category_title_textbox = "//input[@id='jform_title']";
	public static final String toolbar_archive = "archive";
	public static final String lbl_message = "//div[@id='system-message-container']/div/p";
}
