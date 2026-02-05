package baseTest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import com.google.j2objc.annotations.Property;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.apache.logging.log4j.LogManager;//log4j
import org.apache.logging.log4j.Logger;   //log4j
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	//public WebDriver driver;
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	@BeforeClass(groups = { "Master", "Sanity", "Regression" }) //Step8 groups added
	@Parameters({"os", "browser"})
	public void setup(@Optional("windows") String os, @Optional("edge") String br) throws IOException {
		//log4j
		logger=LogManager.getLogger(this.getClass());//Log4j
		//config file reader
		FileReader file =new FileReader("./src/test/java/resources/config.properties");
		p=new Properties();
		p.load(file);

		//Add the selenium grid
        if(p.getProperty("execution_env").equalsIgnoreCase("remote")){
			DesiredCapabilities capabilities=new DesiredCapabilities();

			//os
			if(os.equalsIgnoreCase("Linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else if (os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}

			//browser
			switch(br.toLowerCase())
			{
				case "chrome": capabilities.setBrowserName("chrome"); break;
				case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
				case "firefox": capabilities.setBrowserName("FireFox"); break;
				default: System.out.println("No matching browser"); return;
			}

			//run selenium grid server
			https://www.selenium.dev/downloads/
//karam@karam-laptop:~/Documents/Projects$ java -jar selenium-server-4.40.0.jar standalone  run this selenium server jar
// Run Jenkins
// https://www.jenkins.io/doc/book/installing/war-file/
// http://localhost:9090/
// karam@karam-laptop:~/Documents/Projects$ java -jar jenkins.war --httpPort=9090



			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}

		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{

			switch(br.toLowerCase())
			{
				case "chrome" : driver=new ChromeDriver(); break;
				case "edge" : driver=new EdgeDriver(); break;
				case "firefox": driver=new FirefoxDriver(); break;
				default : System.out.println("Invalid browser name.."); return;
			}
		}
		/*switch(br.toLowerCase())
		{
			case "chrome": driver=new ChromeDriver(); break;
			case "edge": driver=new EdgeDriver(); break;
			default: System.out.println("No matching browser..");
				return;
		}*/
        driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("URL"));
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups = { "Master", "Sanity", "Regression" })
	public void tearDown()
	{
		driver.close();
	}
	

	public String randomeString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomeNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
	public String randomAlphaNumeric()
	{
		String str=RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(3);
		
		return (str+"@"+num);
	}

	//code for take screenshot

	public static String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir")
				+ File.separator + "screenshots"
				+ File.separator + tname + "_" + timeStamp + ".png";

		File target = new File(targetFilePath);

		FileUtils.copyFile(source, target);   // IMPORTANT

		return targetFilePath;
	}
}
