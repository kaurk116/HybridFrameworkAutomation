package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
    public MyAccountPage(WebDriver driver)
    {
        super(driver);
    }
    @FindBy(xpath = "//h2[normalize-space()='My Account']")
    WebElement MyAccount;

    @FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")
    WebElement logout;

//get text value form locator
    public String getMyAccountmsg() {
        try {
            return (MyAccount.getText());
        } catch (Exception e) {
            return (e.getMessage());
        }
    }
    public void accountLogout(){
        logout.click();
    }

    public boolean isMyAccountPageExists()   // MyAccount Page heading display status
    {
        try {
            return (MyAccount.isDisplayed());
        } catch (Exception e) {
            return (false);
        }
    }


}
