package pages.product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;
import pages.navigation.MenuPage;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ArtPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("CategoriesPage.class");
    private final int i = 2;
    MenuPage menuPage = new MenuPage(driver);
    @FindBy(css = "a.ui-slider-handle:nth-of-type(1)")
    private WebElement leftSlider;
    @FindBy(css = "a.ui-slider-handle:nth-of-type(2)")
    private WebElement rightSlider;
    @FindBy(css = ".faceted-slider p")
    private WebElement priceRange;
//    @FindBy(xpath = "//span[@class='price']")
//    private List<WebElement> displayedProductsPrice;

    public List<WebElement> getDisplayedProductPrice(){
        return driver.findElements(By.xpath("//span[@class='price']"));
    }
    @FindBy(xpath = "//div[@id='_desktop_search_filters_clear_all']/button")
    private WebElement clearFilterBtn;

    public ArtPage(WebDriver driver) {
        super(driver);
    }

    public ArtPage clickArtCategory() {
        clickOnElement(menuPage.categories.get(i));
        log.info("Category after click: " + menuPage.categories.get(i).getText());
        return this;
    }
    public int pageProductAmount = getDisplayedProductPrice().size();

    public ArtPage firstPriceFilter(String properties, int offset) {
        int amount = getDisplayedProductPrice().size();
        while (!priceRange.getText().endsWith(properties)) {
            waitForPageLoaded();
            clickAndHold(rightSlider);
            actions.moveByOffset(offset, 0).perform();
            log.info(getTextFromElement(priceRange));
        }
        releaseMouse(rightSlider);
        wait.until(c->getDisplayedProductPrice().size()<amount);
        return this;
    }

    public ArtPage secondPriceFilter(String properties, int offset) {
        int amount = getDisplayedProductPrice().size();

        while (!priceRange.getText().startsWith(properties)) {
            waitForPageLoaded();
            clickAndHold(leftSlider);
            actions.moveByOffset(offset, 0).perform();
            log.info(getTextFromElement(priceRange));
        }
        releaseMouse(leftSlider);
        wait.until(c->getDisplayedProductPrice().size()<amount);
        return this;
    }
    public int amountAfterFiltr() {
      return getDisplayedProductPrice().size();
    }

    public ArtPage countMatchedProducts(String properties) {
        List<String> products = new ArrayList<>();
        for (int j = 0; j < getDisplayedProductPrice().size(); j++) {
            waitForPageLoaded();
            waitToBeVisible(getDisplayedProductPrice().get(j));
            products.add(getDisplayedProductPrice().get(j).getText());
            log.info("Products: " + getTextFromElement(getDisplayedProductPrice().get(j)));
            String value = getDisplayedProductPrice().get(j).getText();
            softAssert.assertThat(value.contains(properties));
        }
        log.info("Number of matched products: " + getDisplayedProductPrice().size());
        return this;
    }

    public ArtPage clearFilters() {
        int amount = getDisplayedProductPrice().size();
        clickOnElement(clearFilterBtn);
        waitForPageLoaded();
        wait.until(c->getDisplayedProductPrice().size()>amount);
        return this;
    }
}



