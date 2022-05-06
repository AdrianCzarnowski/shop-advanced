package pages.user;

import configuration.model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;

import java.util.List;

public class RegistrationPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("RegistrationPage.class");

    @FindBy(css=".custom-radio")
    private List<WebElement> gender;
    @FindBy(xpath = "//input[@name='firstname']")
    private WebElement firstName;
    @FindBy(xpath = "//input[@name='lastname']")
    private WebElement lastName;
    @FindBy(xpath = "//input[@name='email']")
    private WebElement email;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;
    @FindBy(xpath = "//input[@name='birthday']")
    private WebElement birthday;

    @FindBy (css="input[type=checkbox]")
    private List<WebElement> checkboxes;

    @FindBy(css = "footer > button")
    private WebElement saveBtn;

    @FindBy(xpath = "//div[@class='help-block']")
    public WebElement alertMsg;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void selectCheckBoxes(){
        for (WebElement checkbox : checkboxes){
            selectCheckBox(checkbox);
        }
    }

    public RegistrationPage fillForm(User user) {
        clickOnElement(getRandomElement(gender));
        sendKeys(firstName, user.getFirstName(), true);
        sendKeys(lastName, user.getLastName(), true);
        sendKeys(email, user.getEmail(), true);
        sendKeys(password, user.getPassword(), false);
        sendKeys(birthday, user.getBirthDate(), true);
        selectCheckBoxes();
        clickOnElement(saveBtn);
        log.info("New user created");
        return this;
    }
}
