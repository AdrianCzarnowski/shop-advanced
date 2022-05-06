package pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;

public class LoginPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("LoginPage.class");
    @FindBy(css=".no-account a")
    public WebElement newAccountBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickNewAccount() {
        clickOnElement(newAccountBtn);
        return this;
    }
}
