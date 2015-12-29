package in_pages;

public class in_ArticlesPage {
	
	/***************Main Article Page***************/	

	public static final String state_filter_dropdown = "//select[@name='filter_published']";
	public static final String category_filter_dropdown = "//select[@name='filter_category_id']";
	public static final String access_filter_dropdown = "//select[@name='filter_access']";
	public static final String author_filter_dropdown = "//select[@name='filter_author_id']";
	
	public static final String row_checkbox = "//a[contains(text(),'%s')]/../preceding-sibling::td/input";
	public static final String article_link = "//a[contains(text(),'%s')]";
	public static final String message_header = "//dl[@id='system-message']";
	public static final String publish_status_icon = "//a[contains(text(),'%s')]/../following-sibling::td[1]/a/span";
	public static final String access_status = "//a[contains(text(),'%s')]/../following-sibling::td[5]";
	
	
	/***************New/Edit Article Page***************/
	
	public static final String title_texbox ="//input[@id='jform_title']";
	public static final String category_dropdown="//select[@id='jform_catid']";
	public static final String status_dropdown ="//select[@id='jform_state']";
	public static final String access_dropdown ="//select[@id='jform_access']";
	public static final String save_button ="//div[@id='toolbar']//span[@class='icon-32-apply']";
	public static final String saveclose_button="//li[@id='toolbar-save']/a/span";
	public static final String savenew_button="//li[@id='toolbar-save-new']/a/span";
	public static final String cancel_button="//li[@id='toolbar-cancel']/a/span";
	
	
	public static final String arttext_frame_textbox="//iframe[@id='jform_articletext_ifr']";
	public static final String frame_textbox="//body[@id='tinymce']";
}
