package pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class UserAddressPage extends BasePage {

    @FindBy(css = "div.address-body > address")
    private WebElement address;

    public UserAddressPage(WebDriver driver) {
        super(driver);
    }

    public String userAddress() {
        String userAddress = getTextFromElement(address).trim();
        return userAddress;
    }
}
