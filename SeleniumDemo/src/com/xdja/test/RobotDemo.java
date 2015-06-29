package com.xdja.test;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class RobotDemo {
	public static void main(String argv[]) throws AWTException, IOException{
		Robot rbt = new Robot();
		//Delay
		rbt.delay(1000);
		
		//Open your application in WINNT system
		Runtime.getRuntime().exec("notepad");
		
		//For linux
		//Runtime.getRuntime().exec("gedit");
		
		rbt.delay(5000);
		
		//Mouse operation
		rbt.mouseMove(10,10);
		rbt.mousePress(InputEvent.BUTTON1_MASK);
		rbt.mouseRelease(InputEvent.BUTTON1_MASK);
		
		//KeyBoard operation
		rbt.keyPress(KeyEvent.VK_1);
		
	}

}
