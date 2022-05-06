package pages.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;
import pages.navigation.MenuPage;

import java.util.ArrayList;
import java.util.List;

public class CategoryPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("CategoriesPage.class");
    @FindBy(xpath = "//div[@itemprop='itemListElement']")
    public List<WebElement> productList;
    @FindBy(css = "#js-product-list-header > div > h1")
    public WebElement pageTitle;
    MenuPage menuPage = new MenuPage(driver);
    @FindBy(css = "div.col-md-6.hidden-sm-down.total-products")
    private WebElement productQuantity;
    @FindBy(xpath = "//ul[@class='category-sub-menu']//a")
    private List<WebElement> subCategories;
    private List<String> categoryNameDisplayed = new ArrayList<>();

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    private void getCategoryName() {
        String categoryName = getTextFromElement(pageTitle);
        log.info("Category after click: " + categoryName);
    }

    public CategoryPage checkCategories_AndSubcategories() {
        for (int i = 0; i < menuPage.categories.size(); i++) {
            categoryNameDisplayed.add(getTextFromElement(menuPage.categories.get(i)));
            log.info("Selected category: " + getTextFromElement(menuPage.categories.get(i)));
            clickOnElement(menuPage.categories.get(i));
            amountOfProducts_AndSideMenuIsDisplayed();

            actions.moveToElement(menuPage.categories.get(i)).build().perform();
            for (int j = 0; j < subCategories.size(); j++) {
                clickOnElement(subCategories.get(j));
                amountOfProducts_AndSideMenuIsDisplayed();
                clickOnElement(menuPage.categories.get(i));
            }
        }
        return this;
    }

    private boolean isDisplayedMenu() {
        if (menuPage.sideMenu.isDisplayed()) {
            log.info("Side menu is displayed");
            return true;
        } else {
            log.info("Side menu is not displayed");
            return false;
        }
    }

    private boolean countProductList() {
        if (getTextFromElement(productQuantity).contains(String.valueOf(productList.size()))) {
            log.info("Products on this page is: " + productList.size());
            return true;
        } else {
            return false;
        }
    }

    private void amountOfProducts_AndSideMenuIsDisplayed() {
        getCategoryName();
        countProductList();
        isDisplayedMenu();
    }
}


    
 













