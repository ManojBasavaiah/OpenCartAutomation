package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.loginPage;
import testBase.BaseClass;
import utilities.DataProviders;


public class TC003_LoginDDT extends BaseClass {
    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "datadriven")/*as dataprovider is in other classs*/
    public void verify_loginDDT(String email, String pwd, String exp) {
        //Homepage
        logger.info("***** starting TC002_LoginTest ***** ");
        try {
            HomePage hp = new HomePage(driver);
            logger.info("Providing login details......");
            hp.clickMyAccount();
            logger.info("Clicked on MyAccount link.......");
            hp.clickLogin();
            logger.info("Clicked on Login link......");

            //login
            loginPage lp = new loginPage(driver);
            lp.setEmail(email);
            lp.setPassword(pwd);
            lp.clickLogin();

            //myaccount
            MyAccountPage myac = new MyAccountPage(driver);
            logger.info("Validating expected message.....");
            boolean targetPage = myac.isMyAccountPageExists();
        /*Data is valid --> login success --> logout
                  Data is valid --> login failed -->test fail
        Data is invalid --> login sucess --> test fail --> logout
            Data is invalid --> login failed --> test pass
         */
            if (exp.equalsIgnoreCase("Valid")) {
                if (targetPage == true) {
                    myac.clickLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            }
            if (exp.equalsIgnoreCase("Invalid")) {
                if (targetPage == true) {
                    Assert.assertTrue(false);
                    myac.clickLogout();

                } else {
                    Assert.assertTrue(true);
                }
            }
            Thread.sleep(3000);

        } catch (Exception e) {
            Assert.fail();
        }
        finally {
            logger.info("***** Finished TC003_LoginDDT *****");
        }


    }
}
