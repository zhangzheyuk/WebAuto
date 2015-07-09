package com.test.pmc.page;

//import com.framework.util.HttpUtils;
import com.test.tools.PropUtil;
import com.framework.webdriver.baseapi.WebdriverBaseApi;

public class BasePage {
	protected WebdriverBaseApi driver;
	private PropUtil PropUtil = new PropUtil("config/FrameWork.properties");

	public BasePage(WebdriverBaseApi driver) {
		this.driver = driver;
	}

	public boolean isTextPresent(String expectedText) {
		return driver.isContentAppeared(expectedText);
	}

	public boolean isTextNotPresent(String expectedText) {
		return driver.isContentNotAppeared(expectedText);
	}

	/**
	 * 获取系统随机生成动态验证码
	 * 
	 * @param charset
	 * @return
	 */
//	public String getVercode(String charset) {
//		String url = PropUtil.get("getVercodeUrl");
//		String JSESSIONID = driver.getCookieNamed("JSESSIONID");
//		HttpUtils http = new HttpUtils();
//		String vercode = http.getVercode(url, null, "JSESSIONID=" + JSESSIONID,
//				charset);
//		return vercode;
//	}
}
