package com.framework.webdriver.testcase;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.test.tools.PropUtil;
import com.framework.webdriver.basecase.WebdriverBaseCase;

public class InitWebdriverTest extends WebdriverBaseCase {
	private String IEDriverServer = System.getProperty("user.dir")
			+ "/resource/IEDriverServer.exe";
	private String chromedriver = System.getProperty("user.dir")
			+ "/resource/chromedriver.exe";
	private PropUtil PropUtil = new PropUtil("config/FrameWork.properties");
	private int pageLoadTimeout = Integer.parseInt(PropUtil
			.get("pageLoadTimeout"));
	private int waitTimeout = Integer.parseInt(PropUtil.get("waitTimeout"));
	private int scriptTimeout = Integer.parseInt(PropUtil.get("scriptTimeout"));
	private Logger logger = LogManager.getLogger(InitWebdriverTest.class);

	public void printPath(){
		System.out.println("path = " + PropUtil.class.getClassLoader().getResource("config/FrameWork.properties").getPath());
	}
	
	@BeforeSuite(alwaysRun = true)
	@Parameters({ "browser", "browserDir", "baseURL" })
	public void beforeTestSuite(String browser, String browserDir,
			String baseURL) {
		logger.debug("browser : " + browser);
		logger.debug("browserDir : " + browserDir);
		logger.debug("baseURL : " + baseURL);

		if ("iexplore".equalsIgnoreCase(browser)) {
			startInternetExplorerDriver(baseURL);
		} else if ("firefox".equalsIgnoreCase(browser)) {
			startFirefoxDriver(browserDir, baseURL);
		} else if ("googlechrome".equalsIgnoreCase(browser)) {
			startChromeDriver(browserDir, baseURL);
		} else {
			startInternetExplorerDriver(baseURL);
		}
		beforeSuite();
	}

	@AfterSuite(alwaysRun = true)
	public void afterTestSuite() {
		afterSuite();
		stopWebDriver();
	}

	public void startInternetExplorerDriver(String baseUrl) {
		System.setProperty("webdriver.ie.driver", IEDriverServer);
		DesiredCapabilities capabilities = DesiredCapabilities
				.internetExplorer();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		capabilities.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
		try {
			driver = new InternetExplorerDriver(capabilities);
			logger.info("start InternetExplorerDriver");
			driver.manage().timeouts()
					.pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
			logger.debug("set pageLoadTimeout : " + pageLoadTimeout);
			driver.manage().timeouts()
					.implicitlyWait(waitTimeout, TimeUnit.SECONDS);
			logger.debug("set waitTimeout : " + waitTimeout);
			driver.manage().timeouts()
					.setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);
			logger.debug("set scriptTimeout : " + scriptTimeout);
			driver.manage().window().maximize();
			get(baseUrl, 3);
		} catch (Exception e) {
			logger.error("执行startInternetExplorerDriver()方法发生异常，异常信息：", e);
		}
	}

	public void startFirefoxDriver(String firefox_dir, String baseUrl) {
		System.setProperty("webdriver.firefox.bin", firefox_dir);
		ProfilesIni allProfiles = new ProfilesIni();  
	    FirefoxProfile profile = allProfiles.getProfile("default");  
	    driver = new FirefoxDriver(profile);
		try {
//			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//			driver = new FirefoxDriver(capabilities);
			
			logger.info("start FirefoxDriver");
			driver.manage().timeouts()
					.pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
			logger.debug("set pageLoadTimeout : " + pageLoadTimeout);
			driver.manage().timeouts()
					.implicitlyWait(waitTimeout, TimeUnit.SECONDS);
			logger.debug("set waitTimeout : " + waitTimeout);
			driver.manage().timeouts()
					.setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);
			logger.debug("set scriptTimeout : " + scriptTimeout);
			driver.manage().window().maximize();
			get(baseUrl, 3);
		} catch (Exception e) {
			logger.error("执行startFirefoxDriver()方法发生异常，异常信息：", e);
		}
	}

	public void startChromeDriver(String chrome_dir, String baseUrl) {

		System.setProperty("webdriver.chrome.driver", chromedriver);
		System.setProperty("webdriver.chrome.bin", "chrome_dir");

		try {

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new ChromeDriver(capabilities);
			logger.info("start ChromeDriver");
			driver.manage().timeouts()
					.pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
			logger.debug("set pageLoadTimeout : " + pageLoadTimeout);
			driver.manage().timeouts()
					.implicitlyWait(waitTimeout, TimeUnit.SECONDS);
			logger.debug("set waitTimeout : " + waitTimeout);
			driver.manage().timeouts()
					.setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);
			logger.debug("set scriptTimeout : " + scriptTimeout);
			driver.manage().window().maximize();
			get(baseUrl, 3);
		} catch (Exception e) {
			logger.error("执行startChromeDriver()方法发生异常，异常信息：", e);
		}
	}

	private void stopWebDriver() {
		try {
			driver.quit();
			logger.info("stop Driver");
		} catch (Exception e) {
			logger.error("执行stopWebDriver()方法发生异常，异常信息：", e);
		}

	}

	/**
	 * rewrite the get method, adding user defined log</BR>
	 * 地址跳转方法，使用WebDriver原生get方法，加入失败重试的次数定义。
	 * 
	 * @param url
	 *            the url you want to open.
	 * @param actionCount
	 *            retry times when load timeout occuers.
	 */
	public void get(String url, int actionCount) {
		boolean isSucceed = false;
		for (int i = 0; i < actionCount; i++) {
			try {
				driver.get(url);
				logger.debug("navigate to url [ " + url + " ]");
				isSucceed = true;
				break;
			} catch (Exception e) {
				logger.error(e);
			}
		}
		operationCheck("get", isSucceed);
	}

	public static WebDriver getWebDriver() {
		return driver;
	}

	/**
	 * 截取保存屏幕截图的路径
	 * 
	 * @param 无
	 * @return 保存屏幕截图的路径
	 */
	public static String getCapturePath() {
		return capturePath;
	}
}
