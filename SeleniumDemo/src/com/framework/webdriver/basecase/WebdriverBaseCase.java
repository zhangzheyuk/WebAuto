package com.framework.webdriver.basecase;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.test.tools.DateTimeUtil;
import com.test.tools.PropUtil;
import com.framework.webdriver.testcase.InitWebdriverTest;

public class WebdriverBaseCase {
	protected static WebDriver driver;
	protected final String className = this.getClass().getName();
	protected static String capturePath;
	private long beforeSuiteStarts = 0;
	private long afterSuiteStops = 0;
	private long beforeClassStarts = 0;
	private long afterClassStops = 0;
	private long beforeTestStarts = 0;
	private long afterTestStops = 0;
	private PropUtil PropUtil = new PropUtil("config/FrameWork.properties");
	private Logger logger = LogManager.getLogger(WebdriverBaseCase.class);

	public void beforeSuite() {
		capturePath = PropUtil.get("CapturePath");
		driver = InitWebdriverTest.getWebDriver();
		String begins = DateTimeUtil.formatedTime("yyyy-MM-dd HH:mm:ss:SSS");
		beforeSuiteStarts = System.currentTimeMillis();
		logger.info("======" + begins + "：测试集开始======");
	}

	public void afterSuite() {
		String ends = DateTimeUtil.formatedTime("yyyy-MM-dd HH:mm:ss:SSS");
		afterSuiteStops = System.currentTimeMillis();
		logger.info("======" + ends + "：测试集结束======");
		logger.info("======本次测试集运行消耗时间 "
				+ (double)(afterSuiteStops - beforeSuiteStarts) / 1000 + " 秒！======");
	}

	public void beforeClass() {
		String begins = DateTimeUtil.formatedTime("yyyy-MM-dd HH:mm:ss:SSS");
		beforeClassStarts = System.currentTimeMillis();
		logger.info("======" + begins + "：测试【" + className + "】开始======");
	}

	public void afterClass() {
		String ends = DateTimeUtil.formatedTime("yyyy-MM-dd HH:mm:ss:SSS");
		afterClassStops = System.currentTimeMillis();
		logger.info("======" + ends + "：测试【" + className + "】结束======");
		logger.info("======本次测试运行消耗时间 " + (double)(afterClassStops - beforeClassStarts)
				/ 1000 + " 秒！======");
	}

	public void beforeTest(String methodName) {
		String begins = DateTimeUtil.formatedTime("yyyy-MM-dd HH:mm:ss:SSS");
		beforeTestStarts = System.currentTimeMillis();
		logger.info("======" + begins +  "：案例【" + className + "." + methodName
				+ "】开始======");
	}

	public String afterTest(String methodName, boolean isSucceed) {
		String ends = DateTimeUtil.formatedTime("yyyy-MM-dd HH:mm:ss:SSS");
		String captureName = "";
		if (isSucceed) {
			logger.info("案例 【" + className + "." + methodName + "】 运行通过！");
		} else {
			String dateTime = DateTimeUtil.formatedTime("-yyyyMMdd-HHmmssSSS");
			StringBuffer sb = new StringBuffer();
			captureName = sb.append(capturePath).append(className).append(".")
					.append(methodName).append(dateTime).append(".png")
					.toString();
			captureScreenshot(captureName);
			logger.error("案例 【" + className + "." + methodName
					+ "】 运行失败，请查看截图快照：" + captureName);
		}
		logger.info("======" + ends + "：案例【" + className + "." + methodName
				+ "】结束======");
		afterTestStops = System.currentTimeMillis();
		logger.info("======本次案例运行消耗时间 " + (double)(afterTestStops - beforeTestStarts)
				/ 1000 + " 秒！======");
		return captureName;
	}

	/**
	 * 截取屏幕截图并保存到指定路径
	 * 
	 * @param filepath
	 *            保存屏幕截图完整文件名称及路径
	 * @return 无
	 */
	public void capture(String name) {
		String dateTime = DateTimeUtil.formatedTime("-yyyyMMdd-HHmmssSSS");
		StringBuffer sb = new StringBuffer();
		String captureName = sb.append(capturePath).append(name)
				.append(dateTime).append(".png").toString();
		captureScreenshot(captureName);
		logger.debug("请查看截图快照：" + captureName);
	}

	/**
	 * 截取屏幕截图并保存到指定路径
	 * 
	 * @param name
	 *            保存屏幕截图名称
	 * @return 无
	 */
	public void captureScreenshot(String filepath) {
		File screenShotFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShotFile, new File(filepath));
		} catch (Exception e) {
			logger.error("保存屏幕截图失败，失败信息：", e);
		}
	}

	/**
	 * public method for handle assertions and screenshot.
	 * 
	 * @param isSucceed
	 *            if your operation success
	 * @throws RuntimeException
	 */
	public void operationCheck(String methodName, boolean isSucceed) {
		if (isSucceed) {
			logger.info("method 【" + methodName + "】 运行通过！");
		} else {
			logger.error("method 【" + methodName + "】 运行失败！");
		}
	}
}