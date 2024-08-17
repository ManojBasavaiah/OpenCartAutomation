package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

    @Test
    public void verify_account_registration() {
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickRegister();

        AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
        regpage.setFirstname(randomString().toUpperCase());
        regpage.setLastname(randomString().toUpperCase());
        regpage.setEmail(randomString() + "@gmail.com");
        regpage.setTelephone(randomNumber());
        regpage.setPassword(randomAlphaNumber());
        regpage.setPrivacyPolicy();
        regpage.clickContinue();
        String confmsg = regpage.getConfirmationMsg();
        Assert.assertEquals(confmsg, "Your Account Has Been Created!");

    }

}
