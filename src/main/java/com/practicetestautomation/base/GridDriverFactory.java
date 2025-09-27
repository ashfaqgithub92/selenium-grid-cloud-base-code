package com.practicetestautomation.base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridDriverFactory {

	private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private String browser;
	private Logger log;

	public GridDriverFactory(String browser, Logger log) {
		this.browser = browser.toLowerCase();
		this.log = log;
	}

	public WebDriver createDriver() {
		log.info("Connecting to node with the browser : " + browser);

		DesiredCapabilities capabilities = new DesiredCapabilities();

		switch (browser) {
		case "chrome":
			capabilities.setBrowserName("chrome");
			break;

		case "firefox":
			capabilities.setBrowserName("firefox");
			break;

		default:
			log.debug("Do not know how to start: " + browser + ", starting chrome.");
			capabilities.setBrowserName("chrome");
			break;
		}

		URL url = null;

		try {
			url = new URL("https:localhost:4444");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
			
		driver.set(new RemoteWebDriver(url, capabilities));
		
		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
		return driver.get();
	}
}
