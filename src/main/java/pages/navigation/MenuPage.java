package pages.navigation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

public class MenuPage extends BasePage {

    @FindBy(xpath = "//a[@class='dropdown-item']")
    public List<WebElement> categories;
    @FindBy(css = "#search_filters")
    public WebElement sideMenu;
    @FindBy(css = "input.ui-autocomplete-input")
    public WebElement searchBox;
    @FindBy(css = "form > button ")
    public WebElement searchButton;
    @FindBy(xpath = "//a[@title='Log in to your customer account']")
    public WebElement singInBtn;

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    public MenuPage clickSingInButton() {
        clickOnElement(singInBtn);
        return this;
    }
}
