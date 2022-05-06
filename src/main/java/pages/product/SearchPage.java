package pages.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;
import pages.navigation.HomePage;
import pages.navigation.MenuPage;


public class SearchPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("SearchPage.class");
    HomePage homePage = new HomePage(driver);
    MenuPage menuPage = new MenuPage(driver);
    String product = homePage.randomProductNameFromHomePage();
    @FindBy(css = "div.product-description")
    private WebElement searchResultProductName;
    @FindBy(css = "#ui-id-1")
    private WebElement dropDownResult;
    @FindBy(css = "#products")
    private WebElement listOfSearchProduct;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public SearchPage fillSearchBox(String value) {
        sendKeys(menuPage.searchBox, value, false);
        return this;
    }

    public void clickSearchButton() {
        clickOnElement(menuPage.searchButton);
    }

    public String getTextFromSearchField() {
        return searchResultProductName.getText();
    }

    public String getProductNameAfterSearch() {
        wait.until(ExpectedConditions.visibilityOf(listOfSearchProduct));
        return searchResultProductName.getText();
    }

    public String getProductsNameFromDropDownList() {
        wait.until(ExpectedConditions.visibilityOf(dropDownResult));
        return dropDownResult.getText();
    }

    public SearchPage fillSearchBox() {
        log.info("Random product name: " + product);
        fillSearchBox(product);
        return this;
    }

    public String getProduct() {
        return product;
    }

}
