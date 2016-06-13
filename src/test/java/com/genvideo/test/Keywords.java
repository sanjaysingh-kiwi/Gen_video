package com.genvideo.test;

import org.apache.commons.io.FileUtils;
import org.apache.commons.validator.Var;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.genvideo.test.DriverScript.*;
import static com.genvideo.test.GetOSName.OsUtils.isWindows;
import static com.genvideo.test.GetOSName.OsUtils.isMac;

@SuppressWarnings("ALL")
public class Keywords {

	// public AppiumDriver<MobileElement> driver1;

	public WebDriver driver;
	public String campaign_title;
	Date date = new Date();
	SimpleDateFormat dt = new SimpleDateFormat("MMM d, yyyy");

	public String openBrowser(String object, String data) {

		// Chrome Driver Path

		System.setProperty("webdriver.chrome.driver",
				"ChromeDriver/chromedriver.exe");

		// Internet Explorer Path
		if (isWindows()) {
			File file = new File("IEDriver/IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		}

		APP_LOGS.debug("Opening browser");
		if (data.equals("Mozilla"))

			driver = new FirefoxDriver();

		else if (data.equals("IE"))

			driver = new InternetExplorerDriver();
		else if (data.equals("Chrome"))
			driver = new ChromeDriver();

		long implicitWaitTime = Long.parseLong(CONFIG
				.getProperty("implicitwait"));
		driver.manage().timeouts()
				.implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
		return Constants.KEYWORD_PASS;

	}

	public String navigate(String object, String data) {
		APP_LOGS.debug("Navigating to URL");
		try {
			Thread.sleep(10000l);
			driver.manage().window().maximize();
			driver.navigate().to(data);

		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " -- Not able to navigate";
		}
		return Constants.KEYWORD_PASS;
	}

	/*
	 * public String LaunchApplication(String object,String data) throws
	 * IOException, InterruptedException{
	 * APP_LOGS.debug("Executing LunchApplication");
	 * 
	 * 
	 * 
	 * //AppiumDriverLocalService service =
	 * AppiumDriverLocalService.buildDefaultService(); //service.start();
	 * //Thread.sleep(25000L); Runtime.getRuntime().exec(
	 * "open -a Terminal /Users/kiwitech/Downloads/Selenium30_sept_2015/startappium.command"
	 * ); Thread.sleep(25000L); //File appDir = new File
	 * ("/Users/kiwitech/Desktop/apk");
	 * 
	 * File app = new File("/Users/kiwitech/Desktop/mahaalo/Mahaalo.app");
	 * DesiredCapabilities capabilities = new DesiredCapabilities();
	 * capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.4");
	 * capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "iOS");
	 * //capabilities
	 * .setCapability("udid","6d4e9b4d63092e8cd77e44ab13ee26399cf43866");
	 * //capabilities
	 * .setCapability("udid","b251c1abdffdc25a7010f0e86bf7864c8a132662");
	 * //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
	 * "iphone05"); capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
	 * "iPhone 6");
	 * capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
	 * //capabilities.setCapability("bundleID","com.kiwitech.mahaalo");
	 * capabilities
	 * .setCapability(MobileCapabilityType.APP,app.getAbsolutePath()); driver1 =
	 * new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub") ,
	 * capabilities); driver1.manage().timeouts().implicitlyWait(30,
	 * TimeUnit.SECONDS);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * return Constants.KEYWORD_PASS;
	 * 
	 * }
	 */

	public String clickLink(String object, String data) {
		APP_LOGS.debug("Clicking on link ");
		try {
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " -- Not able to click on link"
					+ e.getMessage();
		}
		return Constants.KEYWORD_PASS;
	}

	public String clickSubmitButton(String object, String data) {
		APP_LOGS.debug("Clicking on Submit button ");
		try {
			// ((JavascriptExecutor)
			// driver).executeScript("window.scrollTo(0,500)");
			driver.findElement(By.xpath(OR.getProperty(object))).click();
			Thread.sleep(2000L);
			// driver.navigate().refresh();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " -- Not able to click on link"
					+ e.getMessage();
		}

		return Constants.KEYWORD_PASS;
	}

	public String clickLinkCss(String object, String data) {
		APP_LOGS.debug("Clicking on link ");
		try {
			driver.findElement(By.cssSelector(OR.getProperty(object))).click();
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " -- Not able to click on link"
					+ e.getMessage();
		}

		return Constants.KEYWORD_PASS;
	}

	public String clickLink_linkText(String object, String data) {
		APP_LOGS.debug("Clicking on link ");
		driver.findElement(By.linkText(OR.getProperty(object))).click();

		return Constants.KEYWORD_PASS;
	}

	public String clickLink_name(String object, String data) {
		APP_LOGS.debug("Clicking on link ");
		driver.findElement(By.name(OR.getProperty(object))).click();

		return Constants.KEYWORD_PASS;
	}

	public String verifyLinkText(String object, String data) {
		APP_LOGS.debug("Verifying link Text");
		try {
			String actual = driver
					.findElement(By.xpath(OR.getProperty(object))).getText();
			String expected = data;

			if (actual.equals(expected))
				return Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL + " -- Link text not verified";

		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " -- Link text not verified"
					+ e.getMessage();

		}

	}

	public String clickButton(String object, String data) {
		APP_LOGS.debug("Clicking on Button");
		try {
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " -- Not able to click on Button"
					+ e.getMessage();
		}

		return Constants.KEYWORD_PASS;
	}

	public String clickButtonCss(String object, String data) {
		APP_LOGS.debug("Clicking on Button");
		try {
			driver.findElement(By.cssSelector(OR.getProperty(object))).click();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " -- Not able to click on Button"
					+ e.getMessage();
		}
		return Constants.KEYWORD_PASS;
	}

	public String verifyButtonText(String object, String data) {
		APP_LOGS.debug("Verifying the button text");
		try {
			String actual = driver
					.findElement(By.xpath(OR.getProperty(object))).getText();
			String expected = data;

			if (actual.equals(expected))
				return Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL + " -- Button text not verified "
						+ actual + " -- " + expected;
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Object not found "
					+ e.getMessage();
		}

	}

	public String selectList(String object, String data) {
		APP_LOGS.debug("Selecting from list");
		try {
			if (!data.equals(Constants.RANDOM_VALUE)) {
				driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(
						data);
			} else {
				// logic to find a random value in list
				WebElement droplist = driver.findElement(By.xpath(OR
						.getProperty(object)));
				List<WebElement> droplist_cotents = droplist.findElements(By
						.tagName("EXPO"));
				Random num = new Random();
				int index = num.nextInt(droplist_cotents.size());
				String selectedVal = droplist_cotents.get(index).getText();

				driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(
						selectedVal);
			}
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	public String verifyAllListElements(String object, String data) {
		APP_LOGS.debug("Verifying the selection of the list");
		try {
			WebElement droplist = driver.findElement(By.xpath(OR
					.getProperty(object)));
			List<WebElement> droplist_cotents = droplist.findElements(By
					.tagName("option"));

			// extract the expected values from OR. properties
			String temp = data;
			String allElements[] = temp.split(",");
			// check if size of array == size if list
			if (allElements.length != droplist_cotents.size())
				return Constants.KEYWORD_FAIL + "- size of lists do not match";

			for (int i = 0; i < droplist_cotents.size(); i++) {
				if (!allElements[i].equals(droplist_cotents.get(i).getText())) {
					return Constants.KEYWORD_FAIL + "- Element not found - "
							+ allElements[i];
				}
			}
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	public String verifyListSelection(String object, String data) {
		APP_LOGS.debug("Verifying all the list elements");
		try {
			String expectedVal = data;
			// System.out.println(driver.findElement(By.xpath(OR.getProperty(object))).getText());
			WebElement droplist = driver.findElement(By.xpath(OR
					.getProperty(object)));
			List<WebElement> droplist_cotents = droplist.findElements(By
					.tagName("option"));
			String actualVal = null;
			for (int i = 0; i < droplist_cotents.size(); i++) {
				String selected_status = droplist_cotents.get(i).getAttribute(
						"selected");
				if (selected_status != null)
					actualVal = droplist_cotents.get(i).getText();
			}

			if (!actualVal.equals(expectedVal))
				return Constants.KEYWORD_FAIL + "Value not in list - "
						+ expectedVal;

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not find list. "
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}

	public String selectRadio(String object, String data) {
		APP_LOGS.debug("Selecting a radio button");
		try {
			String temp[] = object.split(Constants.DATA_SPLIT);
			driver.findElement(
					By.xpath(OR.getProperty(temp[0]) + data
							+ OR.getProperty(temp[1]))).click();
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + "- Not able to find radio button";

		}

		return Constants.KEYWORD_PASS;

	}

	public String verifyRadioSelected(String object, String data) {
		APP_LOGS.debug("Verify Radio Selected");
		try {
			String temp[] = object.split(Constants.DATA_SPLIT);
			String checked = driver.findElement(
					By.xpath(OR.getProperty(temp[0]) + data
							+ OR.getProperty(temp[1]))).getAttribute("checked");
			if (checked == null)
				return Constants.KEYWORD_FAIL + "- Radio not selected";

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + "- Not able to find radio button";

		}

		return Constants.KEYWORD_PASS;

	}

	public String checkCheckBox(String object, String data) {
		APP_LOGS.debug("Checking checkbox");
		try {
			// true or null
			String checked = driver.findElement(
					By.xpath(OR.getProperty(object))).getAttribute("checked");
			if (checked == null)// checkbox is unchecked
				driver.findElement(By.xpath(OR.getProperty(object))).click();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " - Could not find checkbo";
		}
		return Constants.KEYWORD_PASS;

	}

	public String unCheckCheckBox(String object, String data) {
		APP_LOGS.debug("Unchecking checkBox");
		try {
			String checked = driver.findElement(
					By.xpath(OR.getProperty(object))).getAttribute("checked");
			if (checked != null)
				driver.findElement(By.xpath(OR.getProperty(object))).click();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " - Could not find checkbox";
		}
		return Constants.KEYWORD_PASS;

	}

	public String verifyCheckBoxSelected(String object, String data) {
		APP_LOGS.debug("Verifying checkbox selected");
		try {
			String checked = driver.findElement(
					By.xpath(OR.getProperty(object))).getAttribute("checked");
			if (checked != null)
				return Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL + " - Not selected";

		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " - Could not find checkbox";

		}

	}

	public String verifyText(String object, String data) {
		APP_LOGS.debug("Verifying the text");
		try {
			String actual = driver
					.findElement(By.xpath(OR.getProperty(object))).getText();
			String expected = data;

			if (actual.equals(expected))
				return Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL + " -- text not verified "
						+ actual + " -- " + expected;
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Object not found "
					+ e.getMessage();
		}

	}

	public String writeInInput(String object, String data) {
		APP_LOGS.debug("Writing in text box");

		try {
			if (object.equals("campaign_title")) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				data = data.concat(dateFormat.format(date).toString());
				campaign_title = data;
			}
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Unable to write "
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}

	public void waitForSpinner() {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("//div[@class='spinner-container short ng-hide']")));
	}

	public String setCampaignStatus(String object, String data) {
		APP_LOGS.debug("Setting Campaign Status");
		try {
			waitForSpinner();
			WebElement dropdown = driver.findElement(By.xpath(OR
					.getProperty(object)));
			Select select = new Select(dropdown);
			select.selectByVisibleText(data);
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Unable to write "
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;
	}

	public String writeInInputCss(String object, String data) {
		APP_LOGS.debug("Writing in text box");

		try {
			driver.findElement(By.cssSelector(OR.getProperty(object)))
					.sendKeys(data);
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Unable to write "
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}

	public String verifyTextinInput(String object, String data) {
		APP_LOGS.debug("Verifying the text in input box");
		try {
			String actual = driver
					.findElement(By.xpath(OR.getProperty(object)))
					.getAttribute("value");
			String expected = data;

			if (actual.equals(expected)) {
				return Constants.KEYWORD_PASS;
			} else {
				return Constants.KEYWORD_FAIL + " Not matching ";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Unable to find input box "
					+ e.getMessage();

		}
	}

	public String clickImage() {
		APP_LOGS.debug("Clicking the image");

		return Constants.KEYWORD_PASS;
	}

	public String verifyFileName() {
		APP_LOGS.debug("Verifying inage filename");

		return Constants.KEYWORD_PASS;
	}

	public String verifyTitle(String object, String data) {
		APP_LOGS.debug("Verifying title");
		try {
			String actualTitle = driver.getTitle();
			String expectedTitle = data;
			if (actualTitle.equals(expectedTitle))
				return Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL + " -- Title not verified "
						+ expectedTitle + " -- " + actualTitle;
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Error in retrieving title";
		}
	}

	public String exist(String object, String data) {
		APP_LOGS.debug("Checking existance of element");
		try {
			driver.findElement(By.xpath(OR.getProperty(object)));
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Object doest not exist";
		}

		return Constants.KEYWORD_PASS;
	}

	public String click(String object, String data) {
		APP_LOGS.debug("Clicking on any element");
		try {
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Not able to click";
		}
		return Constants.KEYWORD_PASS;
	}

	public String clickCss(String object, String data) {
		APP_LOGS.debug("Clicking on any element");
		try {
			driver.findElement(By.cssSelector(OR.getProperty(object))).click();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Not able to click";
		}
		return Constants.KEYWORD_PASS;
	}

	public String synchronize(String object, String data) {
		APP_LOGS.debug("Waiting for page to load");
		((JavascriptExecutor) driver)
				.executeScript("function pageloadingtime()" + "{"
						+ "return 'Page has completely loaded'" + "}"
						+ "return (window.onload=pageloadingtime());");

		return Constants.KEYWORD_PASS;
	}

	public String waitForElementVisibility(String object, String data) {
		APP_LOGS.debug("Waiting for an element to be visible");
		int start = 0;
		int time = (int) Double.parseDouble(data);
		try {
			while (time == start) {
				if (driver.findElements(By.xpath(OR.getProperty(object)))
						.size() == 0) {
					Thread.sleep(1000L);
					start++;
				} else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL
					+ "Unable to close browser. Check if its open"
					+ e.getMessage();
		}
		return Constants.KEYWORD_PASS;
	}

	public String closeBrowser(String object, String data) {
		APP_LOGS.debug("Closing the browser");
		try {
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL
					+ "Unable to close browser. Check if its open"
					+ e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}

	public String deleteAllCookies(String object, String data) {
		APP_LOGS.debug("Deleting all the Browser cookies");
		try {
			driver.manage().deleteAllCookies();
			driver.navigate().refresh();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL
					+ "Unable delete all the cookies from Browser"
					+ e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}

	public String quitBrowser(String object, String data) {
		APP_LOGS.debug("Closing the browser");
		try {
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL
					+ "Unable to close browser. Check if its open"
					+ e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}

	public String pause(String object, String data)
			throws NumberFormatException, InterruptedException {
		long time = (long) Double.parseDouble(object);
		Thread.sleep(time * 1000L);
		return Constants.KEYWORD_PASS;
	}

	/************************ APPLICATION SPECIFIC KEYWORDS ********************************/
	public String workspaceDropList(String object, String data) {
		APP_LOGS.debug("Selecting Expo");
		try {
			// driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[3]/div[1]")).sendKeys("EXPO");
			// Thread.sleep(5000l);
			// driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[3]/div[1]")).click();
			Select dropdown = new Select(
					driver.findElement(By
							.xpath("html/body/div[1]/div[2]/div/div/div[3]/div[1]/select")));
			dropdown.selectByIndex(17);
			Thread.sleep(5000);
			/*
			 * driver.findElement(By.className("customLoginSelect")).sendKeys("EXPO"
			 * ); driver.findElement(By.className("customLoginSelect")).click();
			 * List<WebElement> list =
			 * driver.findElements(By.className("ng-valid")); for(int
			 * i=0;i<list.size();i++){
			 * 
			 * System.out.println(list.get(i).getText());
			 * 
			 * }
			 */
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	public String brandDropList(String object, String data) {
		APP_LOGS.debug("Selecting Expo");
		try {
			// driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[3]/div[2]")).sendKeys("EXPO");
			// Thread.sleep(5000l);
			// driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[3]/div[2]")).click();
			Select dropdown = new Select(
					driver.findElement(By
							.xpath("html/body/div[1]/div[2]/div/div/div[3]/div[2]/select")));
			dropdown.selectByIndex(2);
			Thread.sleep(5000);
			/*
			 * driver.findElement(By.className("ng-valid")).sendKeys("EXPO");
			 * driver.findElement(By.className("ng-valid")).click();
			 * List<WebElement> list =
			 * driver.findElements(By.className("ng-valid")); for(int
			 * i=0;i<list.size();i++){
			 * System.out.println(list.get(i).getText());
			 * 
			 * }
			 */

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	public String groupDropList(String object, String data) {
		APP_LOGS.debug("Selecting Admin");
		try {
			Select dropdown = new Select(
					driver.findElement(By
							.xpath("html/body/div[1]/div[2]/div/div/div[3]/div[3]/select")));
			dropdown.selectByIndex(1);
			Thread.sleep(5000);
			// driver.findElement(By.className("hasCustomSelect")).sendKeys("Admin");
			// driver.findElement(By.className("hasCustomSelect")).click();
			//
			// List<WebElement> list =
			// driver.findElements(By.className("customLoginSelect"));
			// for(int i=0;i<list.size();i++){
			// System.out.println(list.get(i).getText());
			//
			// }

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	public String selectactive(String object, String data) {
		APP_LOGS.debug("Selecting active");
		try {
			driver.findElement(
					By.xpath(".//*[@id='engagement-orders-builder']/div/div[3]/div/section[1]/div/div/form/div[2]/div[1]/div[5]/div/div/button"))
					.click();
			driver.findElement(
					By.xpath(".//*[@id='engagement-orders-builder']/div/div[3]/div/section[1]/div/div/form/div[2]/div[1]/div[5]/div/div/ul/li[2]/a"))
					.click();

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	public String selectdate(String object, String data) {
		APP_LOGS.debug("select date");
		try {

			driver.findElement(By.xpath(".//*[@id='startdate']")).click();
			driver.findElement(
					By.xpath(".//*[@id='engagement-orders-builder']/div/div[3]/div/section[1]/div/div/form/div[2]/div[1]/div[3]/div[1]/div[1]/table/tbody/tr[5]/td[7]/button"))
					.click();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL
					+ "Unable to go back, Check if its open" + e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}

	public String campaigndate(String object, String data) {
		APP_LOGS.debug("campaigndate");
		try {
			driver.findElement(
					By.xpath(".//*[@id='wrap']/div/div[1]/div/div/div/div/div[2]/div[2]/div/div[1]/form/div[1]/div/div/div/div/ng-form/div[2]/div/input"))
					.click();
			driver.findElement(
					By.xpath(".//*[@id='wrap']/div/div[1]/div/div/div/div/div[2]/div[2]/div/div[1]/form/div[1]/div/div/div/div/ng-form/div[2]/div/div[1]/table/tbody/tr[6]/td[1]/button"))
					.click();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL
					+ "Unable to go back, Check if its open" + e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}

	public String uploadcampaignimagenot(String object, String data) {
		APP_LOGS.debug("Not uploadcampaignimage");
		try {
			WebElement upload = driver
					.findElement(By
							.xpath(".//*[@id='wrap']/div/div[1]/div/div/div/div/div[2]/div[2]/div/div[1]/form/div[1]/div/div/div/div/ng-form/div[4]/image-upload"));
			upload.click();
			Runtime.getRuntime().exec("C:/Users/user/Desktop/upload1.exe");
			// upload.sendKeys("/Users/kiwitech/Desktop/test.jpg");
			// WebElement element= driver.findElement(By.name("image-upload"));
			// element.sendKeys("//Users//kiwitech//Desktop//test.jpg");
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL
					+ "Unable to go back, Check if its open" + e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}

	public String uploadcampaignimagenot1(String object, String data) {
		APP_LOGS.debug("Not uploadcampaignimage");
		try {
			WebElement upload = driver
					.findElement(By
							.xpath(".//*[@id='wrap']/div/div[1]/div/div/div/div/div[2]/div[2]/div/div[1]/form/div[1]/div/div/div/div/ng-form/div[4]/image-upload"));
			upload.click();
			Thread.sleep(5000l);
			StringSelection ss = new StringSelection(
					"C:\\Users\\user\\Desktop\\test1.jpg");
			Toolkit.getDefaultToolkit().getSystemClipboard()
					.setContents(ss, null);

			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL
					+ "Unable to go back, Check if its open" + e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}

	public String uploadcampaignimage(String object, String data) {
		APP_LOGS.debug("uploadcampaignimage");
		try {
			WebElement upload = driver
					.findElement(By
							.xpath(".//*[@id='wrap']/div/div[1]/div/div/div/div/div[2]/div[2]/div/div[1]/form/div[1]/div/div/div/div/ng-form/div[4]/image-upload"));
			upload.click();
			Runtime.getRuntime().exec("C:/Users/user/Desktop/upload.exe");

		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL
					+ "Unable to go back, Check if its open" + e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}

	public String uploadcampaignimage1(String object, String data) {
		APP_LOGS.debug("uploadcampaignimage");
		try {
			WebElement upload = driver
					.findElement(By
							.xpath(".//*[@id='wrap']/div/div[1]/div/div/div/div/div[2]/div[2]/div/div[1]/form/div[1]/div/div/div/div/ng-form/div[4]/image-upload"));
			upload.click();
			Thread.sleep(5000l);
			StringSelection ss = new StringSelection(
					"C:\\Users\\user\\Desktop\\test.jpg");
			Toolkit.getDefaultToolkit().getSystemClipboard()
					.setContents(ss, null);

			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL
					+ "Unable to go back, Check if its open" + e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}

	public String expdate(String object, String data) {
		APP_LOGS.debug("Selecting expdate");

		try {
			driver.findElement(By.xpath("addnewOrder_expdate")).sendKeys(
					"2016-06-30");

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	public String selectiphone(String object, String data) {
		APP_LOGS.debug("Selecting iphone");

		try {
			driver.findElements(By.className("ui-menu-item")).get(14).click();

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;
	}

	public String campaignDueDate(String object, String data) {
		APP_LOGS.debug("campaignDueDate");
		try {
			waitForSpinner();
			Calendar calender = new GregorianCalendar();
			calender.setTime(date);
			calender.add(Calendar.DATE, 30);
			Date newDate = calender.getTime();
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			driver.findElement(By.xpath("//div[@class='input-group']/input"))
					.sendKeys(format.format(newDate));
			Thread.sleep(2000L);
			driver.findElement(By.xpath("//div[@class='input-group']/input"))
					.click();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + "Unable to enter campaign due date"
					+ e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}

	public String selectCreditCard(String object, String data) {
		APP_LOGS.debug("Selecting Credit Card");

		int num = Integer.parseInt(data);

		try {
			List<WebElement> selectSize = driver.findElements(By.xpath(OR
					.getProperty(object)));
			selectSize.get(num).click();

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	public String enter(String object, String data) {
		APP_LOGS.debug("Going back one page");
		try {
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(
					Keys.ENTER);
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL
					+ "Unable to go back, Check if its open" + e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}

	public String tab(String object, String data) {
		APP_LOGS.debug("Going back one page");
		try {
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(
					Keys.TAB);
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL
					+ "Unable to go back, Check if its open" + e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}

	public String enterCss(String object, String data) {
		APP_LOGS.debug("Going back one page");
		try {
			driver.findElement(By.cssSelector(OR.getProperty(object)))
					.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL
					+ "Unable to go back, Check if its open" + e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}

	public String windowHandler(String object, String data) {
		APP_LOGS.debug("Handling multiple windows");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		try {
			String mainWindowHandle = driver.getWindowHandle();
			driver.findElement(By.xpath(OR.getProperty(object))).click();
			Set<String> window = driver.getWindowHandles();
			Iterator<String> iterator = window.iterator();

			while (iterator.hasNext()) {
				String popupHandle = iterator.next().toString();
				if (!popupHandle.contains(mainWindowHandle)) {
					driver.switchTo().window(popupHandle);
				}
			}

			// Back to main window
			// driver.switchTo().window(mainWindowHandle);

		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL
					+ "Unable to handle windows, See log4j report for more info"
					+ e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}

	public String click_JS(String object, String data) {
		APP_LOGS.debug("Clicking on any element using JavaScript");
		try {
			((JavascriptExecutor) driver)
					.executeScript("document.getElementById('" + object
							+ "').click()");
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL
					+ "Unable to click, Check if its open" + e.getMessage();
		}
		return Constants.KEYWORD_PASS;
	}

	public String writeInInput_JS(String object, String data) {
		APP_LOGS.debug("Writing in text box using JavaScript");
		try {
			((JavascriptExecutor) driver)
					.executeScript("document.getElementById('" + object
							+ "').value='" + data + "'");
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL
					+ "Unable to write, Check if its open" + e.getMessage();
		}
		return Constants.KEYWORD_PASS;
	}

	// << Go back one page
	public String goBack(String object, String data) {
		APP_LOGS.debug("Going back one page");
		try {
			driver.navigate().back();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL
					+ "Unable to go back, Check if its open" + e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}

	// >> Go to forward one page
	public String goForward(String object, String data) {
		APP_LOGS.debug("Going back one page");
		try {
			driver.navigate().forward();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL
					+ "Unable to go back, Check if its open" + e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}

	// Verify list of items after clicking on drop-down list like Newborn, Shoes
	// etc.
	public String verifyAllItems(String object, String data) {
		APP_LOGS.debug("Verifying link Text");
		try {
			for (int i = 0; i <= 150; i++) {
				List<WebElement> gridBoxes = driver.findElements(By
						.className("events-div"));
				System.out.println("Number of boxes " + gridBoxes.size());
				// Pick random link box
				Random gridnum = new Random();
				int grids = gridnum.nextInt(gridBoxes.size());
				WebElement grid = gridBoxes.get(grids);
				// WebElement box = link_boxes.get(0);
				List<WebElement> ItemBoxes = grid.findElements(By
						.className("product-image"));
				System.out.println("Total links are -- " + ItemBoxes.size());
				// Pick random item
				Random itemnum = new Random();
				int items = itemnum.nextInt(ItemBoxes.size());
				WebElement item = ItemBoxes.get(items);
				item.click();
				Thread.sleep(4000L);
				System.out.println(driver.getTitle());
				driver.navigate().back();

			}
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " -- Link text not verified"
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;
	}

	// Credit Card functionality at the CheckOut
	public String selectExpMonth(String object, String data) {
		APP_LOGS.debug("Selecting Expiration Month");
		try {
			WebElement states = driver.findElement(By.xpath(OR
					.getProperty(object)));
			List<WebElement> state = states.findElements(By.tagName("option"));
			Random num = new Random();
			int index = num.nextInt(state.size());
			state.get(index).click();
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	// Credit Card functionality at the CheckOut
	public String selectExpYear(String object, String data) {
		APP_LOGS.debug("Selecting Expiration Year");
		try {
			WebElement states = driver.findElement(By.xpath(OR
					.getProperty(object)));
			List<WebElement> state = states.findElements(By.tagName("option"));
			Random num = new Random();
			int index = num.nextInt(state.size());
			state.get(index).click();
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	// Credit Card functionality at the CheckOut
	public String selectAmex(String object, String data) {
		APP_LOGS.debug("Selecting American Express Card");
		try {
			WebElement size = driver.findElement(By.xpath(OR
					.getProperty(object)));
			List<WebElement> selectSize = size.findElements(By
					.tagName("option"));
			selectSize.get(0).click();

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	// Credit Card functionality at the CheckOut
	public String selectVisa(String object, String data) {
		APP_LOGS.debug("Selecting Visa Credit Card");
		try {
			WebElement size = driver.findElement(By.xpath(OR
					.getProperty(object)));
			List<WebElement> selectSize = size.findElements(By
					.tagName("option"));
			selectSize.get(1).click();

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	// Credit Card functionality at the CheckOut
	public String selectMasterCard(String object, String data) {
		APP_LOGS.debug("Selecting MasterCard");
		try {
			WebElement size = driver.findElement(By.xpath(OR
					.getProperty(object)));
			List<WebElement> selectSize = size.findElements(By
					.tagName("option"));
			selectSize.get(2).click();

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	public String selectRandomState(String object, String data) {
		APP_LOGS.debug("Selecting Random States");
		try {
			WebElement states = driver.findElement(By.xpath(OR
					.getProperty(object)));
			List<WebElement> state = states.findElements(By.tagName("option"));
			Random num = new Random();
			int index = num.nextInt(state.size());
			state.get(index++).click();
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	public String selectEvent(String object, String data) {
		APP_LOGS.debug("Selecting Random event");
		try {
			WebElement eventList = driver.findElement(By
					.xpath("//*[@id='events-live']/ul"));
			List<WebElement> events = eventList.findElements(By
					.xpath("//*[@class='event-link']"));

			int time = Integer.parseInt(object);

			if (events.size() == 0) {
				APP_LOGS.debug("There's no events on the page");

			} else {
				events.get(time).click();
			}

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	public String selectRandomEvent(String object, String data) {
		APP_LOGS.debug("Selecting Random event");
		try {
			List<WebElement> events = driver.findElements(By
					.xpath("//*[@id='events-live']/ul/li[not(@style)]/a"));
			Random num = new Random();
			int index = num.nextInt(events.size());
			WebElement menu = events.get(index);
			Actions builder = new Actions(driver);
			builder.moveToElement(menu).build().perform();
			menu.click();

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	public String selectProduct(String object, String data) {
		APP_LOGS.debug("Selecting Random Product");

		int time = Integer.parseInt(object);

		try {
			List<WebElement> items = driver.findElements(By
					.xpath("//*[@class='product-image']"));
			if (items.size() == 0) {
				APP_LOGS.debug("There's no product on the page");
				driver.navigate().back();
				selectRandomEvent(object, data);
				selectRandomProduct(object, data);

			} else {
				items.get(time).click();
			}
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	public String selectRandomProduct(String object, String data) {
		APP_LOGS.debug("Selecting Random Product");
		try {
			List<WebElement> items = driver.findElements(By
					.xpath("//*[@class='thumbnail'][not(@status_sold_out)]/a"));
			Random num = new Random();
			int index = num.nextInt(items.size());
			items.get(index).click();

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	public String selectRandomColor(String object, String data) {
		APP_LOGS.debug("Selecting Random Color");
		try {
			WebElement color = driver.findElement(By
					.xpath("//*[@id='attribute85']"));
			List<WebElement> selectColor = color.findElements(By
					.tagName("option"));
			selectColor.get(1).click();

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	public String selectRandomSize(String object, String data) {
		APP_LOGS.debug("Selecting Random Size");
		try {
			WebElement size = driver.findElement(By
					.xpath("//*[@id='attribute169']"));
			List<WebElement> selectSize = size.findElements(By
					.tagName("option"));
			selectSize.get(1).click();

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	public String chrodKeys(String object, String data) {
		APP_LOGS.debug("Selecting in text box");

		try {
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(
					Keys.chord(data));
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Unable to select "
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}

	public String chrodKeysCss(String object, String data) {
		APP_LOGS.debug("Selecting in text box");

		try {
			driver.findElement(By.cssSelector(OR.getProperty(object)))
					.sendKeys(Keys.chord(data));
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Unable to select "
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}

	public String clearInputText(String object, String data) {
		APP_LOGS.debug("Clearing input text box");

		try {
			driver.findElement(By.xpath(OR.getProperty(object))).clear();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Unable to clear input text "
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}

	public String clearInputText_css(String object, String data) {
		APP_LOGS.debug("Clearing input text box");

		try {
			driver.findElement(By.cssSelector(OR.getProperty(object))).clear();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Unable to clear input text "
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}

	public String mouseHover(String object, String data) {
		APP_LOGS.debug("Mouse Hovering to the element");
		try {

			Thread.sleep(3000L);
			WebElement menu = driver.findElement(By.xpath(OR
					.getProperty(object)));
			Actions builder = new Actions(driver);
			builder.moveToElement(menu).build().perform();

		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + "Unable to mouse hover"
					+ e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}

	public String mouseHoverCss(String object, String data) {
		APP_LOGS.debug("Mouse Hovering to the element");
		try {

			Thread.sleep(3000L);
			WebElement menu = driver.findElement(By.cssSelector(OR
					.getProperty(object)));
			Actions builder = new Actions(driver);
			builder.moveToElement(menu).build().perform();

		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + "Unable to mouse hover"
					+ e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}

	public String doubleClick(String object, String data) {
		APP_LOGS.debug("Mouse Hovering to the element");
		try {

			Thread.sleep(3000L);
			WebElement menu = driver.findElement(By.xpath(OR
					.getProperty(object)));
			Actions builder = new Actions(driver);
			builder.doubleClick(menu).build().perform();

		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + "Unable to mouse hover"
					+ e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}

	public String validateLogin(String object, String data) {
		// object of the current test XLS
		// name of my current test case
		System.out.println("xxxxxxxxxxxxxxxxxxxxx");
		String data_flag = currentTestSuiteXLS.getCellData(currentTestCaseName,
				"Data_correctness", currentTestDataSetID);

		try {

			String expected = driver.findElement(
					By.xpath(OR.getProperty("myAccount_link"))).getText();
			String unexpected = driver.findElement(
					By.cssSelector(OR.getProperty("login_signIn_button_css")))
					.getText();

			if (expected.equals(null))
				if (!data_flag.equals(Constants.POSITIVE_DATA))
					return Constants.KEYWORD_PASS;
				else
					return Constants.KEYWORD_FAIL;

		} catch (Exception e) {

		}

		// check for page title
		if (data_flag.equals(Constants.POSITIVE_DATA))
			return Constants.KEYWORD_PASS;
		else
			return Constants.KEYWORD_FAIL;
	}

	/*
	 * public String validateLogin(String object, String data){ // object of the
	 * current test XLS // name of my current test case
	 * System.out.println("xxxxxxxxxxxxxxxxxxxxx"); String
	 * data_flag=currentTestSuiteXLS.getCellData(currentTestCaseName,
	 * "Data_correctness",currentTestDataSetID );
	 * while(driver.findElements(By.xpath
	 * (OR.getProperty("image_login_process"))).size() !=0 ){ try{ String
	 * visiblity
	 * =driver.findElement(By.xpath(OR.getProperty("image_login_process"
	 * ))).getAttribute("style");
	 * System.out.println("System Processing request - "+ visiblity);
	 * if(visiblity.indexOf("hidden") != -1){ // error message on screen // YOUR
	 * WORK String
	 * actualErrMsg=driver.findElement(By.xpath(OR.getProperty("error_login"
	 * ))).getText(); //String expected=OR;
	 * if(data_flag.equals(Constants.POSITIVE_DATA)) return
	 * Constants.KEYWORD_FAIL; else return Constants.KEYWORD_PASS; }
	 * 
	 * }catch(Exception e){
	 * 
	 * } }
	 * 
	 * // check for page title if(data_flag.equals(Constants.POSITIVE_DATA))
	 * return Constants.KEYWORD_PASS; else return Constants.KEYWORD_FAIL; }
	 */

	public String verifyLaptops(String object, String data) {
		APP_LOGS.debug("Verifying the laptops in app");
		// brand
		String brand = currentTestSuiteXLS.getCellData(currentTestCaseName,
				"Brand", currentTestDataSetID).toLowerCase();
		for (int i = 1; i <= 4; i++) {
			String text = driver
					.findElement(
							By.xpath(OR.getProperty("laptop_name_link_start")
									+ i
									+ OR.getProperty("laptop_name_link_end")))
					.getText().toLowerCase();
			if (text.indexOf(brand) == -1) {
				return Constants.KEYWORD_FAIL + " Brand not there in - " + text;
			}

		}

		return Constants.KEYWORD_PASS;
	}

	public String verifySearchResults(String object, String data) {
		APP_LOGS.debug("Verifying the Search Results");
		try {
			data = data.toLowerCase();
			for (int i = 3; i <= 5; i++) {
				String text = driver
						.findElement(
								By.xpath(OR
										.getProperty("search_result_heading_start")
										+ i
										+ OR.getProperty("search_result_heading_end")))
						.getText().toLowerCase();
				if (text.indexOf(data) == -1) {
					return Constants.KEYWORD_FAIL + " Got the text - " + text;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + "Error -->" + e.getMessage();
		}

		return Constants.KEYWORD_PASS;

	}

	public String pickerwheel(String object, String data) {
		APP_LOGS.debug("Executing pickerwheel Keyword");
		// extract the test data

		try {
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(
					data + "\n");

		} catch (Throwable t) {
			// report error
			return Constants.KEYWORD_FAIL
					+ " -- Not able to enter picker wheel keyword";
		}

		return Constants.KEYWORD_PASS;
	}

	public String sleep(String object, String data) {
		APP_LOGS.debug("Executing sleep Keyword");

		try {

			Thread.sleep(10000l);
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " -- Not able to sleep";
		}

		return Constants.KEYWORD_PASS;
	}

	public String ExplicitWait(String object, String data)
			throws NumberFormatException, InterruptedException {
		APP_LOGS.debug("Executing Explicitwait Keyword");

		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath(OR.getProperty(object))));
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " -- Not able to wait";
		}
		return Constants.KEYWORD_PASS;

	}

	public String writeInInputbyclassname(String object, String data) {
		APP_LOGS.debug("Writing in text box");

		try {
			List<WebElement> element = driver.findElements(By
					.className("form-control"));
			for (int i = 0; i < element.size(); i++) {
				System.out.println(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Unable to write "
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}

	public String Enterproductiphone(String object, String data) {
		APP_LOGS.debug("Writing in text box");

		try {
			driver.findElement(By.xpath(".//*[@id='searchInput']")).sendKeys(
					"iPhone");
			Thread.sleep(5000l);
			driver.findElement(By.className("ui-menu-item")).click();

		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Unable to write "
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}

	/*
	 * public String closeApp(String object,String data){
	 * APP_LOGS.debug("Executing closeApp Keyword"); try{
	 * 
	 * driver1.closeApp(); //driver1.resetApp(); }catch(Exception e){
	 * e.printStackTrace();return
	 * Constants.KEYWORD_FAIL+" -- Not able to close app";
	 * 
	 * } return Constants.KEYWORD_PASS; }
	 * 
	 * @SuppressWarnings("unchecked") public String launchApp(){
	 * APP_LOGS.debug("Executing launchApp Keyword");
	 * 
	 * try{
	 * 
	 * driver1.launchApp(); }catch(Exception e){ e.printStackTrace();return
	 * Constants.KEYWORD_FAIL+" -- Not able to launchapp"; }
	 * 
	 * return Constants.KEYWORD_PASS; } public String Quit(String object,String
	 * data){ APP_LOGS.debug("Executing closeApp Keyword"); try{
	 * 
	 * 
	 * driver1.quit(); }catch(Exception e){ e.printStackTrace();return
	 * Constants.KEYWORD_FAIL+" -- Not able to quit"; }
	 * 
	 * return Constants.KEYWORD_PASS; } public String scrollTo(String
	 * object,String data){ APP_LOGS.debug("Executing scrollTo Keyword");
	 * 
	 * try{
	 * 
	 * driver1.scrollTo(OR.getProperty(object)); }catch(Exception e){
	 * e.printStackTrace();return
	 * Constants.KEYWORD_FAIL+" -- Not able to scroll"; } return
	 * Constants.KEYWORD_PASS; }
	 */
	public String gmailwindow(String object, String data) {
		APP_LOGS.debug("gmail window");

		try {
			String parentHandle = driver.getWindowHandle(); // get the current
															// window handle
			for (int i = 0; i < 10; i++) {
				driver.findElement(By.xpath(OR.getProperty(object))).click();
				Thread.sleep(5000l);
				driver.findElement(By.xpath(OR.getProperty(object))).click();
			}
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle); // switch focus of
														// WebDriver to the next
														// found window handle
														// (that's your newly
														// opened window)
			}

			// code to do something on new window
			driver.findElement(By.cssSelector("#Email")).sendKeys(
					"genvideotest5@gmail.com");
			driver.findElement(By.cssSelector("#next")).click();
			driver.findElement(By.cssSelector("#Passwd")).sendKeys(
					"genvideotest");
			driver.findElement(By.cssSelector("#signIn")).click();
			Thread.sleep(10000l);
			driver.findElement(By.cssSelector("#submit_approve_access"))
					.click();
			// driver.close(); // close newly opened window when done with it
			Thread.sleep(10000l);
			driver.switchTo().window(parentHandle); // switch back to the
													// original window

		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Unable to write "
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}

	public String gmailwindow1(String object, String data) {
		APP_LOGS.debug("gmail window");

		try {
			String parentHandle = driver.getWindowHandle(); // get the current
															// window handle
			for (int i = 0; i < 10; i++) {
				driver.findElement(By.xpath(OR.getProperty(object))).click();
				Thread.sleep(5000l);
				driver.findElement(By.xpath(OR.getProperty(object))).click();
			}
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle); // switch focus of
														// WebDriver to the next
														// found window handle
														// (that's your newly
														// opened window)
			}

			// code to do something on new window
			driver.findElement(By.cssSelector("#Email")).sendKeys(
					"genvideotest5@gmail.com");
			driver.findElement(By.cssSelector("#next")).click();
			driver.findElement(By.cssSelector("#Passwd")).sendKeys(
					"genvideotest");
			driver.findElement(By.cssSelector("#signIn")).click();
			Thread.sleep(10000l);
			driver.findElement(By.cssSelector("#submit_approve_access"))
					.click();
			// driver.close(); // close newly opened window when done with it
			Thread.sleep(10000l);
			driver.switchTo().window(parentHandle); // switch back to the
													// original window
			Thread.sleep(10000l);
			driver.findElement(
					By.xpath("(//button[@class='btn btn-lg btn-warning2'])[2]"))
					.click();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Unable to write "
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}

	// not a keyword


	public String clickWrenchOnCurrentCampaign(String object, String data) {
		APP_LOGS.debug("Current Campaign");
		try {
			Thread.sleep(15000L);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			String xpath = "//div[@class='campaign-content']//h3[contains(text(),'"
					+ dt.format(date)
					+ "')]/ancestor::div[@class='campaign-content']//h3[contains(text(),'"
					+ campaign_title.trim()
					+ "')]//i[@class='fa fa-wrench pr-5']";
			wait.until(ExpectedConditions.elementToBeClickable(driver
					.findElement(By
							.xpath(xpath))));
			WebElement element = driver.findElement(By.xpath(xpath));
					element.click();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Unable to write "
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}
	
	public String clickWrenchOnLiveCampaign(String object, String data) {
		APP_LOGS.debug("Current Live Campaign");
		try {
			Thread.sleep(15000L);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			String xpath = "//div[@class='status live']/parent::div//div[@class='campaign-content']//h3[contains(text(),'"
					+ campaign_title.trim()
					+ "')]//i[@class='fa fa-wrench pr-5']";
			wait.until(ExpectedConditions.elementToBeClickable(driver
					.findElement(By
							.xpath(xpath))));
			WebElement element = driver.findElement(By.xpath(xpath));
					element.click();
		} catch (Exception e) {
			return clickWrenchOnCurrentCampaign("", "");
		}
		return Constants.KEYWORD_PASS;

	}

	public void captureScreenshot(String filename,
			String keyword_execution_result) throws IOException {
		// take screen shots
		if (CONFIG.getProperty("screenshot_everystep").equals("Y")) {
			// capturescreen

			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")
					+ "//screenshots//" + filename + ".jpg"));

		} else if (keyword_execution_result.startsWith(Constants.KEYWORD_FAIL)
				&& CONFIG.getProperty("screenshot_error").equals("Y")) {
			// capture screenshot
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")
					+ "//screenshots//" + filename + ".jpg"));
		}
	}
}
