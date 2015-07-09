package com.test.pmc.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.framework.webdriver.baseapi.WebdriverBaseApi;

public class LoginPage extends BasePage {
	private String vercode;
	private Logger logger = LogManager.getLogger(LoginPage.class);

	public LoginPage(WebdriverBaseApi driver) {
		super(driver);
		logger.debug("running test in 【LoginPage】");
//		vercode = getVercode("GBK");
	}

	public void inputUserName(String userName) {
		driver.sendKeys(By.name("username"), userName);
	}

	public void inputPassWord(String passWord) {
		driver.sendKeys(By.id("password"), passWord);
	}
	
	public void inputSafecode(String safecode){
		driver.sendKeys(By.id("pin-pwd"), safecode);	
	}
	
	public void inputVercode(String vercode) {
		if (vercode != null && vercode.length() > 0) {
			driver.sendKeys(By.name("vercode"), vercode);
		} else if ("".equalsIgnoreCase(vercode)) {
			driver.sendKeys(By.name("vercode"), vercode);
		} else {
			driver.sendKeys(By.name("vercode"), this.vercode);
		}
	}

	public void clickLoginButton() {
		driver.click(By.xpath("//input[@type='button'][@value='个人登录']"));
	}
	
	public void login(String passWord, String safecode) {
		inputPassWord(passWord);
		inputSafecode(safecode);
		clickLoginButton();
	}

	/**
	 * 登录成功
	 * 
	 * @param userName
	 * @param passWord
	 * @param safecode
	 * @return
	 */
	public HomePage loginOK(String passWord, String safecode) {
		login(passWord, safecode);
		return new HomePage(driver);
	}

	/**
	 * 用户名错误，登录失败
	 * 
	 * @param userName
	 * @param passWord
	 * @param safecode
	 * @return
	 */
	public LoginPage loginByNameErr( String passWord,
			String safecode) {
		login( passWord, safecode);
		return this;
	}

	/**
	 * 密码错误，登录失败
	 * 
	 * @param userName
	 * @param passWord
	 * @param safecode
	 * @return
	 */
	public LoginPage loginByPwdErr(String passWord,
			String safecode) {
		login( passWord, safecode);
		return this;
	}

	/**
	 * 验证码错误，登录失败
	 * 
	 * @param userName
	 * @param passWord
	 * @param safecode
	 * @return
	 */
	public LoginPage loginBysafecodeErr(String passWord,
			String safecode) {
		login(passWord, safecode);
		return this;
	}

	public HomePage goHomePage() {
		driver.click(By.partialLinkText("返回首页"));
		return new HomePage(driver);
	}
}
