package QAMavenProject.Mavenjava;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinks {
	static WebDriver driver;
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");//parent windiow
		List<WebElement> links= driver.findElements(By.tagName("a"));
		System.out.println("No. of LInks are :"+links.size());
		List<String> urlList= new ArrayList<String>();
		for(WebElement e:links) {
			String url=e.getAttribute("href");
			urlList.add(url);
//			checkBrokenLink(url);
		}
		long stTime=System.currentTimeMillis();
		urlList.parallelStream().forEach(e->checkBrokenLink(e));
		long enTime=System.currentTimeMillis();
		System.out.println("totla time take :"+(enTime-stTime));
		driver.quit();
	}
public static void checkBrokenLink(String linkURL) {
	try {
	URL url = new URL(linkURL);
	HttpURLConnection httpUrlCOnnection= (HttpURLConnection) url.openConnection();
	httpUrlCOnnection.setConnectTimeout(5000);
	httpUrlCOnnection.connect();
	if(httpUrlCOnnection.getResponseCode()>=400) {
		System.out.println(linkURL+"------->"+httpUrlCOnnection.getResponseMessage()+"is a broken link");
	}else {
		System.out.println(linkURL+"------->"+httpUrlCOnnection.getResponseMessage());
	}
	}
	catch (Exception e) {
		// TODO: handle exception
	}
}
}
