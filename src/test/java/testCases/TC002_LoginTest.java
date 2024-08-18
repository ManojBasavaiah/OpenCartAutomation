package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.loginPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
    @Test(groups = {"Sanity", "Master"})
    public void verify_login(){
        logger.info("***** starting TC002_LoginTest ***** ");
        try {
            //Homepage
            HomePage hp = new HomePage(driver);
            logger.info("Providing login details......");
            hp.clickMyAccount();
            logger.info("Clicked on MyAccount link.......");
            hp.clickLogin();
            logger.info("Clicked on Login link......");

            //login
            loginPage lp = new loginPage(driver);
            lp.setEmail(p.getProperty("email"));
            lp.setPassword(p.getProperty("password"));
            lp.clickLogin();

            //myaccount
            MyAccountPage myac = new MyAccountPage(driver);
            logger.info("Validating expected message.....");
            boolean targetPage = myac.isMyAccountPageExists();

            //assertion
            Assert.assertEquals(targetPage, true, "login failed");
        }catch (Exception e){
            Assert.fail();
        }
        //or Assert.assertTrue(targetPage);
        logger.info("***** Finished TC002_LoginTest *****");
    }
}
