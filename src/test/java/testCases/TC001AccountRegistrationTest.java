package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001AccountRegistrationTest extends BaseClass {
    @Test(groups = {"Master", "Regression"}, invocationCount = 1)
    public void verify_account_registration() {
        logger.info("***** starting TC001AccountRegistrationTest *****");
        try {
            // Navigate to the homepage
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on MyAccount link.......");
            hp.clickRegister();
            logger.info("Clicked on Register link......");

// Create an instance of the AccountRegistrationPage
            AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
            logger.info("Providing customer details......");

// Fill in the registration form with random data
            regpage.setFirstname(randomString().toUpperCase());
            regpage.setLastname(randomString().toUpperCase());
            regpage.setEmail(randomString() + "@gmail.com");
            regpage.setTelephone(randomNumber());
            regpage.setPassword(randomAlphaNumber());
            regpage.setPrivacyPolicy();
            regpage.clickContinue();
            logger.info("Validating expected result......");

// Validate the expected result
// ...


            String confmsg = regpage.getConfirmationMsg();
            String actcongmsg = p.getProperty("actMsg");
            //here you change value for failing
            if (confmsg.equals(actcongmsg)) {
                Assert.assertTrue(true);
            } else {
                logger.error("Test failed.....");
                logger.debug("Debug logs....");
                Assert.fail();
            }
            // Assert.assertEquals(confmsg, "Your Account Has ");
        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("***** Finished TC001AccountRegistrationTest *****");
    }

}
