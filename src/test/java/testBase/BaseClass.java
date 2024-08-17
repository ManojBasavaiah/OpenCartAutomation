package testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseClass {
    public WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://tutorialsninja.com/demo/");
        driver.manage().window().maximize();
    }

    @AfterClass
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
}
