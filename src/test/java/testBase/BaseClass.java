package testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups = {"Master", "datadriven"})
    @Parameters({"os","browser"})
    public void setup(String os, String br) throws IOException {
        //loading config.properties file
        //you can use FileReader class as well instead of Fileinputstram
        FileInputStream file=new FileInputStream("src/test/resources/config.properties");
        p=new Properties();
        p.load(file);
        logger = LogManager.getLogger(this.getClass());
        switch (br.toLowerCase()){
            case "chrome" : WebDriverManager.chromedriver().setup(); driver=new ChromeDriver(); break;
            case "edge": WebDriverManager.edgedriver().setup(); driver = new EdgeDriver(); break;
            case "firefox": WebDriverManager.firefoxdriver().setup(); driver= new FirefoxDriver(); break;
            default:
                System.out.println("Invalid browser name....");
                return;
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("baseURL")); //reading url from config file
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"Master", "datadriven"})
    public void tearDown() {
        driver.quit();
    }

    public String randomString() {
        String generatestring = RandomStringUtils.randomAlphabetic(6);
        return generatestring;
    }

    public String randomNumber() {
        String generateNumber = RandomStringUtils.randomNumeric(10);
        return generateNumber;
    }

    public String randomAlphaNumber() {
        String generateString = RandomStringUtils.randomAlphabetic(5);
        String generateNumber = RandomStringUtils.randomNumeric(4);
        return (generateString + "@" + generateNumber);
    }
    public String captureScreen(String tname) throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
        File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
        String targetFilePath=System.getProperty("user.dir")+"screenshots"+tname+"_"+timeStamp;
        File targetFile=new File(targetFilePath);
        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }
}
