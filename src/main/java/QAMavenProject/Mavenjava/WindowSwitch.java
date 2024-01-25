package QAMavenProject.Mavenjava;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
public class WindowSwitch {
	static WebDriver driver;
	public static void main(String[] args) throws Exception {
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");//parent windiow
		String parentWIndowID= driver.getWindowHandle();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[3]/div[1]/a[1]")).click();
		driver.findElement(By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[3]/div[1]/a[2]")).click();
		driver.findElement(By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[3]/div[1]/a[3]")).click();
		driver.findElement(By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[3]/div[1]/a[4]")).click();
		Set<String> handle= driver.getWindowHandles();
		List<String> hList= new ArrayList<String>(handle);
		if(switchToRightWindowHandle("OrangeHRM - World's Most Popular Opensource HRIS | Secaucus NJ | Facebook", hList)) {
			System.out.println(driver.getCurrentUrl()+" : "+driver.getTitle());
		}
		switchToParentWIndowId(parentWIndowID);
		System.out.println(driver.getCurrentUrl()+" : "+driver.getTitle());
		takeScreenshot();
		WebElement inputField=driver.findElement(By.xpath("//input[@name='username']"));
		inputField.sendKeys(Keys.TAB);
			driver.quit();
	}
	public static void switchToParentWIndowId(String parentWIndowID) {
		driver.switchTo().window(parentWIndowID);
	}
	
	public static void takeScreenshot() throws IOException  {
		TakesScreenshot screenShot=(TakesScreenshot)driver;
		File sercFile=screenShot.getScreenshotAs(OutputType.FILE);
		File desFile=new File("C:\\Users\\Admin\\eclipse-workspace\\Mavenjava\\target\\Screenshot");
		FileUtils.copyFileToDirectory(sercFile, desFile);
	}
	public static boolean switchToRightWindowHandle(String windowTitle,List<String> hList) {
		for(String e: hList) {
			String title=driver.switchTo().window(e).getTitle();
			if(title.contains(windowTitle)) {
				System.out.println("found the right window.......");
				return true;
			}
		}
		return false;
	}
}
