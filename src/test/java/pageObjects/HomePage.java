package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement lnkMyaccount;
    @FindBy(linkText = "Register")
    WebElement lnkRegister;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickMyAccount() {
        lnkMyaccount.click();
    }

    public void clickRegister() {
        lnkRegister.click();
    }
}

