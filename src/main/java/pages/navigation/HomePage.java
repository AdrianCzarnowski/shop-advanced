package pages.navigation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;
import pages.product.CategoryPage;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private static Logger log = LoggerFactory.getLogger("HomePage.class");
    @FindBy(css = "div.product-description > h3")
    public List<WebElement> productsNameList;
    @FindBy(css = ".category a")
    private List<WebElement> categoriesAndSubCategories;

    private List<String> allCategoriesAndSubCategories = new ArrayList<String>();




    public String randomProductNameFromHomePage() {
        return randomValueFromList(productsNameList).getText();
    }

    CategoryPage categoryPage = new CategoryPage(driver);





    public void getCategories() {
        for (WebElement category : categoriesAndSubCategories) {
            String URL = category.getAttribute("href");
            allCategoriesAndSubCategories.add(URL);
        }
    }

    public HomePage goToRandomCategory(){
        getCategories();
        String URL = String.valueOf(allCategoriesAndSubCategories.get(random.nextInt(allCategoriesAndSubCategories.size())));
        driver.get(URL);
        waitForPageLoaded();
        log.info("Category page: " + categoryPage.pageTitle.getText());
        return this;
    }
}
