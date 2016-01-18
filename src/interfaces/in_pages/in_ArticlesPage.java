package in_pages;

import org.openqa.selenium.By;

public class in_ArticlesPage {
	
	/***************Main Article Page***************/	
	
	public static final String filter_list_dropdown = "//div/button[@data-original-title='Filter the list items.']";
	public static final String filter_state_dropdown = "//div[@id='filter_published_chzn']";
	public static final String filter_state_dropdown_values = "//div[@id='filter_published_chzn']/div/ul/li[text()='%s']";
	public static final String filter_category_dropdown = "//div[@id='filter_category_id_chzn']";
	public static final String filter_category_dropdown_values = "//div[@id='filter_category_id_chzn']/div/ul/li[text()='%s']";
	public static final String filter_access_dropdown = "//div[@id='filter_access_chzn']";
	public static final String filter_access_dropdown_values = "//div[@id='filter_access_chzn']/div/ul/li[text()='%s']";
	public static final String filter_language_dropdown = "//div[@id='filter_language_chzn']";
	public static final String filter_language_dropdown_values = "//div[@id='filter_language_chzn']/div/ul/li[text()='%s']";
	
	
	public static final String row_checkbox = "//a[contains(text(),'%s')]/../preceding-sibling::td/input";
	public static final String article_link = "//a[contains(text(),'%s')]";
	public static final String message_header = "//dl[@id='system-message']";
	public static final String publish_status_icon = "//td/div[a[contains(text(),'%s')]]/../../td/div/a/span";
	public static final String access_status = "//a[contains(text(),'%s')]/../following-sibling::td[5]";
	public static final String checkin_status_icon = "//a[contains(text(),'%s')]/preceding-sibling::a/span";
	
	
	/***************New/Edit Article Page***************/
	
	public static final String title_texbox ="//input[@id='jform_title']";
	public static final String category_dropdown = "//div[@id='jform_catid_chzn']";
	public static final String category_dropdown_values = "//div[@id='jform_catid_chzn']/div/ul/li[text()='%s']";
	public static final String state_dropdown = "//div[@id='jform_state_chzn']";
	public static final String state_dropdown_values = "//div[@id='jform_state_chzn']/div/ul/li[text()='%s']";
	public static final String access_dropdown = "//div[@id='jform_access_chzn']";
	public static final String access_dropdown_values = "//div[@id='jform_access_chzn']/div/ul/li[text()='%s']";
	public static final String language_dropdown = "//div[@id='jform_language_chzn']";
	public static final String language_dropdown_values = "//div[@id='jform_language_chzn']/div/ul/li[text()='%s']";
	public static final String featured = "//fieldset[@id='jform_featured']/label[text()='%s']";
	
	
	public static final String arttext_frame_textbox="//iframe[@id='jform_articletext_ifr']";
	public static final String frame_textbox="//body[@id='tinymce']";
	public static final String header_ID = "//*[@id='adminForm']//table/thead/tr//a[contains(text(),'ID')]";
	public static final String table_admin = "//*[@id='adminForm']//table/tbody";
	public static By dropdownlist_paginate = By.xpath("//select[@id='list_limit']");
	public static final String clear_btn = "//form[@id='adminForm']//button[contains(.,'Clear')]";
	public static final String header_ordering ="//table[@id='articleList']//tr/th/a/span";
	public static final String order_arrow_up = "//table[@id='articleList']//tr/th/a/span[@class='icon-arrow-up-3']";
	public static final String order_arrow_down = "//table[@id='articleList']//tr/th/a/span[@class='icon-arrow-down-3']";
	
	
}
