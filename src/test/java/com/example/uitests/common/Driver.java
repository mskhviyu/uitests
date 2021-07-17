package com.example.uitests.common;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Driver {
	private static final String FIREFOX = "firefox";
	private static final String CHROME = "chrome";
	private static final String IE = "ie";

	private static final String seleniumGridHubHostname = Config.getInstance().getGridHubHostName();
	private static final boolean IS_USE_SELENIUM_GRID = Config.getInstance().isGridUsed();
	private static final String WINDOWS_CHROME_DRIVER_PATH = "src/test/resources/drivers/chromedriver.exe";
	private static final String WINDOWS_IE_DRIVER_PATH = "src/test/resources/drivers/IEDriverServer.exe";

	static final Logger LOG = LogManager.getLogger(Driver.class.getName());

	public static void setDriver(String browser) {
		Configuration.timeout = 8000;
		Configuration.startMaximized = true;
		Configuration.remote = "http://" + seleniumGridHubHostname + ":4444/wd/hub/";
		Configuration.baseUrl = "https://shop.mts.ru/";
		Configuration.browser = browser;

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("enableVNC", true);



		LOG.info("Selenium grid is ON");
//		setUpGrid(browser);

		WebDriverRunner.getWebDriver().manage().window().maximize();
	}

	public static void setUpGrid(String browser) {
		DesiredCapabilities dc = new DesiredCapabilities();
		switch (browser) {
		case FIREFOX:
			FirefoxProfile fp = new FirefoxProfile();
			dc.setCapability(FirefoxDriver.PROFILE, fp);
			dc.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
			// FOR VNC - now work in only FF 46.0
			dc.setVersion("46.0");
			dc.setCapability("enableVNC", true);
			break;

		case IE:
			dc = DesiredCapabilities.internetExplorer();
			dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, false);

		case CHROME:
		default:
			Configuration.browser = "chrome";
			dc.setCapability("enableVNC", true);

			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			options.addArguments("--disable-save-password-bubble");
			dc.setCapability(ChromeOptions.CAPABILITY, options);
		}
		dc.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
		dc.setJavascriptEnabled(true);
		dc.setPlatform(Platform.LINUX);
		try {
			String url = "http://" + seleniumGridHubHostname + ":4444/wd/hub/";
			LOG.debug("URL = " + url);
//			WebDriverRunner.setWebDriver(new RemoteWebDriver(new URL(url), dc));

		} catch (Exception e) {
			throw new RuntimeException("HUB is not running on server '" + seleniumGridHubHostname + "'");
		}
	}

}
