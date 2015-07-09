package com.test.pmc.testcase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


//import com.auction.bean.UserInfo;
import com.test.pmc.page.LoginPage;
import com.test.pmc.page.HomePage;
//import com.framework.util.FrameworkDao;
import com.framework.webdriver.baseapi.WebdriverBaseApi;
import com.framework.webdriver.basecase.WebdriverBaseCase;

@Test(groups = { "LoginTest" })

public class LoginTest extends WebdriverBaseCase {
	protected WebdriverBaseApi webDriver;
	private LoginPage loginPage;
	private HomePage homePage;
	private Logger logger = LogManager.getLogger(LoginTest.class);

	@BeforeClass(alwaysRun = true)
	public void beforeClassTest(){
		beforeClass();
		try{
			 webDriver = new WebdriverBaseApi(driver);
			 
		}catch (Exception e) {
			logger.error("beforeClassTest error：", e);
		}
	}
	
	@Test(enabled = true, alwaysRun = true)
	public void testLogin() {
		boolean flag = false;
		
		beforeTest("testLogin");
		try {
//			UserInfo = (UserInfo) FrameworkDao.getRandomObjectByParam(
//					"UserInfo.getUserByParam", "testLogin");
//			HomePage = new HomePage(webDriver);
//			LoginPage = HomePage.goLoginPage();
			loginPage = new LoginPage(webDriver);
//			HomePage = LoginPage.loginOK(UserInfo.getUsername(),
//					UserInfo.getPassword(), null);
			homePage = loginPage.loginOK("123456", "111111");
			flag = homePage.isTextPresent("手机管控"); 
		} catch (Exception e) {
			logger.error("testLogin error：", e);
		}
		logger.debug("flag==" + flag);
		String captureName = afterTest("testLogin", flag);
		Assert.assertTrue(flag, className + ".testLogin failed!capture:"
				+ captureName);
	}
	
	@Test(dependsOnMethods = { "testLogin" }, enabled = true, alwaysRun = true)
	public void testLogout() {
		boolean flag = false;
		beforeTest("testLogout");
		try {
			loginPage = homePage.logout();
			flag = loginPage.isTextPresent("欢迎来到个人中心");
//			homePage = loginPage.goHomePage();
		} catch (Exception e) {
			logger.error("testLogout error：", e);
		}
		logger.debug("flag==" + flag);
		String captureName = afterTest("testLogout", flag);
		Assert.assertTrue(flag, className + ".testLogout failed!capture:"
				+ captureName);
	}

//	@Test(dependsOnMethods = { "testLogout" }, enabled = true, alwaysRun = true)
//	public void testLoginByNameErr() {
//		boolean flag = false;
//		beforeTest("testLoginByNameErr");
//		try {
//			LoginPage = HomePage.goLoginPage();
//			LoginPage.loginByNameErr("mysql22", "mysql", null);
//			flag = LoginPage.isTextPresent("用户名/密码不匹配");
//			HomePage = LoginPage.goHomePage();
//		} catch (Exception e) {
//			logger.error("testLoginByNameErr error：", e);
//		}
//		logger.debug("flag==" + flag);
//		String captureName = afterTest("testLoginByNameErr", flag);
//		Assert.assertTrue(flag, className
//				+ ".testLoginByNameErr failed!capture:" + captureName);
//	}
//
//	@Test(dependsOnMethods = { "testLoginByNameErr" }, enabled = true, alwaysRun = true)
//	public void testLoginByPwdErr() {
//		boolean flag = false;
//		beforeTest("testLoginByPwdErr");
//		try {
//			LoginPage = HomePage.goLoginPage();
//			LoginPage.loginByPwdErr("mysql", "mysql22", null);
//			flag = LoginPage.isTextPresent("用户名/密码不匹配");
//			HomePage = LoginPage.goHomePage();
//		} catch (Exception e) {
//			logger.error("testLoginByPwdErr error：", e);
//		}
//		logger.debug("flag==" + flag);
//		String captureName = afterTest("testLoginByPwdErr", flag);
//		Assert.assertTrue(flag, className
//				+ ".testLoginByPwdErr failed!capture:" + captureName);
//	}
//
//	@Test(dependsOnMethods = { "testLoginByPwdErr" }, enabled = true, alwaysRun = true)
//	public void testLoginByVercodeErr() {
//		boolean flag = false;
//		beforeTest("testLoginByVercodeErr");
//		try {
//			LoginPage = HomePage.goLoginPage();
//			LoginPage.loginByVercodeErr("mysql", "mysql", "666888");
//			flag = LoginPage.isTextPresent("验证码不匹配");
//		} catch (Exception e) {
//			logger.error("testLoginByVercodeErr error：", e);
//		}
//		logger.debug("flag==" + flag);
//		String captureName = afterTest("testLoginByVercodeErr", flag);
//		Assert.assertTrue(flag, className
//				+ ".testLoginByVercodeErr failed!capture:" + captureName);
//	}

	@AfterClass(alwaysRun = true)
	public void afterClassTest() {
		try {
//			DbUnitUtil.tearDown();
		} catch (Exception e) {
			logger.error("afterClassTest error：", e);
		}
		afterClass();
	}
	
	
}
