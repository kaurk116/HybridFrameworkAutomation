package testcases;

import Pageobjects.AccountRegistrationPage;
import Pageobjects.HomePage;
import baseTest.BaseClass;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

//import pageObjects.AccountRegistrationPage;
//import pageObjects.HomePage;
//import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

	@Test(groups = "Regression")
	public void verify_account_registration() {
		try {
			logger.info("***** Starting TC001_AccountRegistrationTest  ****");
			logger.debug("This is a debug log message");
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount Link.. ");
			hp.clickRegister();
			logger.info("Clicked on Register Link.. ");
			logger.info("Providing customer details...");

			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			regpage.setFirstName(randomeString().toUpperCase());
			regpage.setLastName(randomeString().toUpperCase());
			regpage.setEmail(randomeString() + "@gmail.com");// randomly generated the email
			regpage.setTelephone(randomeNumber());

			String password = randomAlphaNumeric();

			regpage.setPassword(password);
			regpage.setConfirmPassword(password);

			regpage.setPrivacyPolicy();
			regpage.clickContinue();
			driver.navigate().refresh();

			logger.info("Validating expected message..");

			String confmsg = regpage.getConfirmationMsg();
			Assert.assertEquals(confmsg, "Your Account Has Been Created!", "Confirmation message mismatch");

			logger.info("Test passed");
		}
		catch(Exception e)
		{
			logger.error("Test failed: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
			logger.debug("debug",e);
		}
		finally
		{
			logger.info("***** Finished TC001_AccountRegistrationTest *****");
		}
	}
}








