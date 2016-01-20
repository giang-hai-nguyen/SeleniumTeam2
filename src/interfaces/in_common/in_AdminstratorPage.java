package in_common;

import org.openqa.selenium.By;

public class in_AdminstratorPage {
	public static final String admin_icon = "//a[@class='admin-logo']";
	public static final String drop_open = "//div/ul[@class='nav nav-user pull-right']/li";
	public static final String logout_icon = "//li[@class='dropdown open']/ul/li/a[text()='Logout']";
	public static final String mainmenu="//ul[@id='menu']";
	public static final String messageDynamic = "//div/p[contains(text(),'%s')]";
	// Toolbar
	public static final String toolbar_new = "new";
	public static final String toolbar_edit = "edit";
	public static final String toolbar_publish = "publish";
	public static final String toolbar_unpublish = "unpublish";
	public static final String toolbar_archive = "archive";
	public static final String toolbar_trash = "trash";
	public static final String toolbar_delete = "delete";
	public static final String toolbar_checkin = "checkin";
	public static final String toolbar_save = "apply";
	public static final String toolbar_saveandclose = "save";
	public static final String toolbar_saveandnew = "save-new";
	public static final String toolbar_saveandcopy = "save-copy";
	public static final String toolbar_cancel = "cancel";
	public static final String toolbar_option = "popup-options";
	public static final String toolbar_help = "help";
	
	// filter
	public static final String filter_textbox = "//input[@id='filter_search']";
	public static final String search_button = "//button[@type='submit']";
	public static final String clear_button = "//button/span[@class='icon-remove']";
	
	//checkbox
	public static final String div_checkbox = "//td/div/a[contains(text(),'%s')]/../../../td/input[@type='checkbox']";
	public static final String without_div_checkbox = "//td/a[contains(text(),'%s')]/../../td/input[@type='checkbox']";
	public static final String page_title ="//div[@class='container-title']/h1";
	
	//table
	public static final String table_admin = "//form[@id='adminForm']//table/tbody";
	public static final String header_ID = "//form[@id='adminForm']//table/thead/tr//a[contains(text(),'ID')]";
	public static final String clear_btn = "//form[@id='adminForm']//button[contains(.,'Clear')]";
	public static final String header_ordering ="//table[@id='articleList']//tr/th/a/span";
	public static final String order_arrow_up = "//table[@id='articleList']//tr/th/a/span[@class='icon-arrow-up-3']";
	public static final String order_arrow_down = "//table[@id='articleList']//tr/th/a/span[@class='icon-arrow-down-3']";
	public static By dropdownlist_paginate = By.xpath("//select[@id='list_limit']");
	
}
