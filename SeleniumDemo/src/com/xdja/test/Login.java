package com.xdja.test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Sleeper;

public class Login {

	public static String IE_DRIVER_PATH = "res/bin/IEDriverServer.exe";
	
    public static void main(String[] args) throws InterruptedException {
            	
    	System.setProperty("webdriver.ie.driver", IE_DRIVER_PATH);
    	
    	//为了解决IE浏览器保护模式问题，设置Driver参数
    	DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
    	ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
    			
        //如果火狐浏览器没有默认安装在C盘，需要制定其路径
        //System.setProperty("webdriver.firefox.bin", "D:/Program Files/Mozilla firefox/firefox.exe"); 
        WebDriver driver = new InternetExplorerDriver(ieCapabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://10.10.12.62:2281/pmc/login.do");       
        driver.manage().window().maximize();
        
        //开始进行页面模拟操作
        WebElement txtbox = driver.findElement(By.id("password"));
        txtbox.sendKeys("123456");
        
        WebElement pintext = driver.findElement(By.id("pin-pwd"));
        pintext.sendKeys("111111");
        
        WebElement loginbtn = driver.findElement(By.id("btn-login"));
        loginbtn.click();
        Thread.sleep(3000);
       
        
//      WebElement overidelink = driver.findElement(By.xpath("./"));
//      overidelink.click();
//      driver.close();

    }

}

