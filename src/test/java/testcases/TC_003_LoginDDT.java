package testcases;

import baseTest.BaseClass;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Pageobjects.HomePage;

import Pageobjects.AccountLoginPage;
import Pageobjects.MyAccountPage;
import utilities.DataProviders;


/*Data is valid  - login success - test pass  - logout
Data is valid -- login failed - test fail

Data is invalid - login success - test fail  - logout
Data is invalid -- login failed - test pass
*/

public class TC_003_LoginDDT extends BaseClass {
    //@Test(dataProvider = "LoginData2")  dataProviderClass = DataProviders.class
    @Test(dataProvider="LoginData",dataProviderClass = DataProviders.class, groups = "Sanity")
    public void verify_loginDDT(String email, String password, String exp) {
        logger.info("**** Starting TC_003_LoginDDT *****");

        try {

            //Home page
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.ClickLogin(); //Login link under MyAccount

            //Login page
            AccountLoginPage lp = new AccountLoginPage(driver);
            lp.setEmail(email);
            lp.setPassword_field(password);
            lp.clicklogin(); //Login button

            //My Account Page
            MyAccountPage macc = new MyAccountPage(driver);
            boolean targetPage = macc.isMyAccountPageExists();
            System.out.println("Print the value login or not " +targetPage);
            if (exp.equalsIgnoreCase("Valid")) {
                if (targetPage == true) {
                    macc.accountLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            }

            if (exp.equalsIgnoreCase("Invalid")) {
                if (targetPage == true) {
                    macc.accountLogout();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
            }
        } catch (Exception e) {
            Assert.fail("An exception occurred: " + e.getMessage());
        }

       logger.info("**** Finished TC_003_LoginDDT *****");
    }

   /* @DataProvider(name = "LoginData2")
    public String[][] getData() {
        // Each row must have 3 columns: email, password, and expected result
        return new String[][]{
                {"user@mail.com", "pass123", "Valid"},
                {"wrong@mail.com", "abc", "Invalid"}
        };

    }*/
}