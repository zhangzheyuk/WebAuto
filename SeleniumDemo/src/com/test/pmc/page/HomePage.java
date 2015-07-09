package com.test.pmc.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.framework.webdriver.baseapi.WebdriverBaseApi;
//import com.test.pmc.page.ManageKind.ManageKindPage;

public class HomePage extends BasePage {
	private Logger logger = LogManager.getLogger(HomePage.class);

	public HomePage(WebdriverBaseApi driver) {
		super(driver);
		logger.debug("running test in 【HomePage】");
	}

	/**
	 * 进入登录页面
	 * 
	 * @return
	 */
	public LoginPage goLoginPage() {
		driver.click(By.partialLinkText("登录"));
		return new LoginPage(driver);
	}

//	/**
//	 * 进入管理种类页面
//	 * 
//	 * @return
//	 */
//	public ManageKindPage goManageKindPage() {
//		driver.click(By.partialLinkText("管理种类"));
//		return new ManageKindPage(driver);
//	}

	/**
	 * 退出登录进入登录页面
	 * 
	 * @return
	 */
	public LoginPage logout() {
		driver.click(By.partialLinkText("退出"));
		return new LoginPage(driver);
	}
}
