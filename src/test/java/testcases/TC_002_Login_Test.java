package testcases;

import Pageobjects.AccountLoginPage;
import Pageobjects.HomePage;
import Pageobjects.MyAccountPage;
import baseTest.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TC_002_Login_Test extends BaseClass {
    @Test(groups = "Regression")
    public void VerifyAccountLogin() throws IOException {

        try {
            FileReader file =new FileReader("./src/test/resources/config.properties");
            p=new Properties();
            p.load(file);
       logger.info("***** Starting TC001_AccountRegistrationTest  ****");
        //Home page
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        logger.info("Clicked on MyAccount Link.. ");
        hp.ClickLogin();
        logger.info("Clicked on Register Link.. ");
        //logger.info("Providing customer details...");

       //Account Login
        AccountLoginPage lp=new AccountLoginPage(driver);
        lp.setEmail(p.getProperty("Email"));
        lp.setPassword_field(p.getProperty("Password"));
        lp.clicklogin();

        //My Account Page
        MyAccountPage myAccountpage=new MyAccountPage(driver);
        //compare the text value
            String confmsg= myAccountpage.getMyAccountmsg();
            Assert.assertEquals(confmsg, p.getProperty("MyAccountmsg", "Confirmation message mismatch"));
            logger.info("Test passed");
            // Boolean true and false value
            boolean targetPage=myAccountpage.isMyAccountPageExists();
            Assert.assertEquals(targetPage, true,"Login failed");
        }
        catch(Exception e) {
            Assert.fail();
        }
		finally
    {
        logger.info("***** Finished TC001_AccountRegistrationTest *****");
    }
    }
}
