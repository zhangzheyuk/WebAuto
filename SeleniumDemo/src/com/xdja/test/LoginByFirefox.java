package com.xdja.test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Sleeper;
import com.gargoylesoftware.htmlunit.javascript.host.file.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoginByFirefox {

	public static String IE_DRIVER_PATH = "res/bin/IEDriverServer.exe";
	
    public static void main(String[] argv) throws InterruptedException {
            	
        //如果火狐浏览器没有默认安装在C盘，需要制定其路径
        System.setProperty("webdriver.firefox.bin", "C:/Program Files (x86)/Mozilla Firefox/firefox.exe"); 
        //Set Firefox profile, 使用本机用户profile 
        ProfilesIni allProfiles = new ProfilesIni();  
        FirefoxProfile profile = allProfiles.getProfile("默认用户");  
        WebDriver driver = new FirefoxDriver(profile);
        
        //Driver的一些基本配置
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://10.10.12.62:2281/pmc/login.do");       
        driver.manage().window().maximize();
        System.out.println("webdriver start successfully....");
        
        //开始进行页面模拟操作        
        WebElement txtbox = driver.findElement(By.id("password"));
        txtbox.sendKeys("111111");
        
        WebElement pintext = driver.findElement(By.id("pin-pwd"));
        pintext.sendKeys("111111");
        
        WebElement loginbtn = driver.findElement(By.id("btn-login"));
        loginbtn.click();
        Thread.sleep(3000);
    }
}

