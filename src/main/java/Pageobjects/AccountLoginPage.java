package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountLoginPage extends BasePage{
    public AccountLoginPage(WebDriver driver)
    {
        super(driver);
    }
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email_Field;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement password_field;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement Login_Button;


    public void setEmail(String email) {
        email_Field.sendKeys(email);
    }
    public void  setPassword_field(String password){
        password_field.sendKeys(password);

    }
    public void clicklogin(){
        Login_Button.click();
    }

}
