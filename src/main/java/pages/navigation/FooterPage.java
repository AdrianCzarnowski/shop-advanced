package pages.navigation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class FooterPage extends BasePage {
    @FindBy(css = "#link-product-page-prices-drop-1")
    private WebElement priceDropButton;

    @FindBy(css = "#footer_account_list > li:nth-child(4) > a")
    public WebElement addressBtn;

    public FooterPage(WebDriver driver) {
        super(driver);
    }

    public FooterPage clickPriceDropButton() {
        clickOnElement(priceDropButton);
        return this;
    }
}
