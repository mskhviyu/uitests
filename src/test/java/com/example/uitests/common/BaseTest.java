package com.example.uitests.common;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;


public class BaseTest {

	static final Logger LOG = LogManager.getLogger(BaseTest.class.getName());
	private static final String seleniumGridHubHostname = Config.getInstance().getGridHubHostName();

	@BeforeMethod()
	@Parameters({ "browser", "url" })
	public void setDriver(@Optional("chrome") String browser, @Optional("https://shop.mts.ru") String url) {
		Configuration.timeout = 8000;
		Configuration.startMaximized = true;
		Configuration.remote = "http://" + seleniumGridHubHostname + ":4444/wd/hub";
		Configuration.baseUrl = url;
		Configuration.browser = browser;
		Configuration.browserVersion = "90.0";
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("enableVNC", true);
		Configuration.browserCapabilities = dc;

//		Driver.setDriver(browser);
		LOG.info("Open url - " + url);
//		open(url);
	}	

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		Selenide.closeWebDriver();
//		WebDriverRunner.getWebDriver().quit();
	}
}
