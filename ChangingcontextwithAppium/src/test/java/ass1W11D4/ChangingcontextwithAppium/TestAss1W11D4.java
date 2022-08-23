package ass1W11D4.ChangingcontextwithAppium;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class TestAss1W11D4 {
	private AndroidDriver driver;

	@BeforeSuite
	public void setUp() throws MalformedURLException, InterruptedException {

		// Setting up desire caps using DesireCapabilities class
		// Create an object for Desired Capabilities
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

		// Set capabilities
		desiredCapabilities.setCapability("appium:app",
				"C:\\Users\\lo0ol\\eclipse-workspace\\ChangingcontextwithAppium\\ApiDemos-debug.apk");
		desiredCapabilities.setCapability("appium:deviceName", "23b9cb400c1c7ece");
		desiredCapabilities.setCapability("appium:platformName", "Android");
		desiredCapabilities.setCapability("appium:platformVersion", "10");
		desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
		desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);
		desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
		// Java package of the Android app you want to run
		desiredCapabilities.setCapability("appium:appPackage", "io.appium.android.apis");
		// Activity name for the Android activity you want to launch from your package
		desiredCapabilities.setCapability("appium:appActivity", "io.appium.android.apis.ApiDemos");

		System.out.println("Finshed: desiredCapabilities");

		// Initialize the driver object with the URL to Appium Server and
		// passing the capabilities
		URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver(remoteUrl, desiredCapabilities);
		System.out.println("Finshed: driver");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@Test
	public void Test1() throws InterruptedException {

		// Perform the action on the element
		// click on "View"
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		System.out.println("Finshed: Views");
		Thread.sleep(5000);

		// scroll down to element
		((JavascriptExecutor) driver).executeScript("mobile: scroll",
				ImmutableMap.builder()
				.put("direction", "down")
				.put("strategy", "accessibility id")
				.put("selector", "WebView2")
				.put("percent", 3.0)
				.build());
		System.out.println("Finshed: scroll");
		Thread.sleep(5000);

		// click on "WebView2" using Actions
		WebElement element = driver.findElement(AppiumBy.accessibilityId("WebView2"));
		Actions action = new Actions(driver);
		action.moveToElement(element).click().build().perform();
		System.out.println("Finshed: action");
		Thread.sleep(5000);

		// click on TextField
		WebElement textField = driver.findElement(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View/android.widget.EditText"));
		textField.click(); // it will open the keyboard
		System.out.println("Finshed: click on TextField");
		Thread.sleep(5000);

		// send text
		textField.sendKeys("Mawaddah");
		System.out.println("Finshed: sendKeys");
		Thread.sleep(5000);

		// hide keyboard
		driver.hideKeyboard();
		System.out.println("Finshed: hideKeyboard");
		Thread.sleep(5000);

		// click on Text link
		WebElement link = driver
				.findElement(By.xpath("//android.view.View[@content-desc=\"i am a link\"]/android.widget.TextView"));
		link.click();
		System.out.println("Finshed: click on link");
		Thread.sleep(5000);

		// get all contexts
		Set<String> contexts = driver.getContextHandles();

		for (String context : contexts) {
			System.out.println("getContextHandles =" + context);
		}

		// get current context
		String context1 = driver.getContext();
		System.out.println("Finshed: the current context is = " + context1);
		Thread.sleep(5000);

		// change context, back to native app
		driver.context("NATIVE_APP");
		String context2 = driver.getContext();
		System.out.println("Finshed: change context to NATIVE_APP");
		System.out.println("Finshed: current context is = " + context2);
		Thread.sleep(5000);

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		System.out.println("Finshed: quit");
	}

}
