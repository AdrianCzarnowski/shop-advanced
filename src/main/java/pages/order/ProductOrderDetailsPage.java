package pages.order;

import configuration.model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

import static configuration.model.Product.productList;


public class ProductOrderDetailsPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("ProductOrderDetailsPage.class");
    @FindBy(css = ".product-container [itemprop=name]")
    private static WebElement productName;
    @FindBy(css = "[itemprop=price]")
    private static WebElement productPrice;
    @FindBy(css = "#quantity_wanted")
    private static WebElement productQuantity;
    public Product newProduct;
    @FindBy(css = "div.col-md-7 > div > div > button")
    private WebElement continueShoppingBtn;
    @FindBy(xpath = "//div[@class='cart-content-btn']/a")
    private WebElement proceedBtn;
    @FindBy(css = ".product-line-grid")
    private List<WebElement> productRows;

    private List<Product> basketList = new ArrayList<>();

    public ProductOrderDetailsPage(WebDriver driver) {
        super(driver);
    }

    public Product newProductBuilder() {
        String productNameText = getTextFromElement(productName);
        highLightenerMethod(productPrice);
        String productPriceText = getTextFromElement(productPrice).replace("$", "");
        highLightenerMethod(productQuantity);
        String productQuantityText = getValueFromElement(productQuantity);


        Product product = new Product(productNameText, Double.parseDouble(productPriceText),
                Integer.parseInt(productQuantityText));
        log.info("Created item:" + product);
        return product;
    }

    public ProductOrderDetailsPage clickContinueShopping() {
        clickOnElement(continueShoppingBtn);
        return this;
    }

    public ProductOrderDetailsPage clickProceedToCheckout() {
        clickOnElement(proceedBtn);
        return this;
    }

    public void checkCartOfProducts() {
        newProduct = newProductBuilder();


        if (productList.contains(newProduct)) {
            Product productFromList = productList.get(productList.indexOf(newProduct));
            log.info("List contains item");
            productFromList.addQuantity((int) newProduct.getQuantity());
            log.info("Quantity updated " + newProduct.getProductName() + " quantity after updated: " + newProduct.getQuantity());

        } else {
            log.warn("List doesn't contains item");
            productList.add(newProduct);
            log.info("New product added: " + newProduct.getProductName());
        }
        log.info("Products in list: ");
        productList.forEach(product -> log.info("\t- " + product.toString() + "\n"));
    }

    public ProductOrderDetailsPage setBasket() {

        for (WebElement productRow : productRows) {
            String name = productRow.findElement(By.cssSelector(".product-line-info:nth-child(1)")).getText();
            double price = Double.parseDouble(productRow.findElement(By.cssSelector(".product-line-info .price")).getText().replace("$", ""));
            int quantity = Integer.parseInt(productRow.findElement(By.name("product-quantity-spin")).getAttribute("value"));
            Product product = new Product(name, price, quantity);
            basketList.add(product);
        }
        return this;
    }

    public List<Product> getBasketList() {
        return basketList;
    }
}

