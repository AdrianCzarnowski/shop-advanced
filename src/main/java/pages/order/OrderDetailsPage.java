package pages.order;

import configuration.model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.navigation.FooterPage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderDetailsPage extends BasketPage {

    private static Logger log = LoggerFactory.getLogger("OrderHistoryPage.class");
    FooterPage footerPage = new FooterPage(driver);

    @FindBy(xpath = "//table[@id='order-products']//tbody//tr")
    private List<WebElement> productRows;
    @FindBy(css = "#delivery-address > address")
    private WebElement deliveryAddress;
    @FindBy(css = "#invoice-address > address")
    private WebElement invoiceAddress;



    public OrderDetailsPage(WebDriver driver) {
        super(driver);
    }

    public List<Product> checkOrderDetails() {
        List<Product> productLi = new ArrayList<>();

        for (WebElement productRow : productRows) {
            WebElement productName = productRow.findElement(By.xpath(".//td[1]"));
            WebElement productQuantity = productRow.findElement(By.xpath(".//td[2]"));
            WebElement productPrice = productRow.findElement(By.xpath(".//td[3]"));

            String name = getTextFromElement(productName);
            double price = Double.parseDouble(getTextFromElement(productPrice).substring(1));
            int quantinty = Integer.parseInt(getTextFromElement(productQuantity));
            Product product = new Product(name, price, quantinty);
            productLi.add(product);
        }
        return productLi;
    }

    public OrderDetailsPage comparisionOfProductsFromCartToBasket(List<Product> basketList) {

        List<Product> product = checkOrderDetails();

        assertTrue(product.size() == basketList.size());

        for (int i = 0; i < product.size(); i++) {
            String basketProductName = basketList.get(i).getProductName();
            String orderProductName = product.get(i).getProductName();
            int basketProductQuantity = basketList.get(i).getQuantity();
            int quantityOrderPage = product.get(i).getQuantity();
            double basketProductPrice = basketList.get(i).getProductPrice();
            double priceOrderPage = product.get(i).getProductPrice();

            log.info("Product in basket: " + basketProductName);
            log.info("Product on order page: " + orderProductName);

            assertTrue(orderProductName.contains(basketProductName));
            assertTrue(String.valueOf(basketProductQuantity).contains(String.valueOf(quantityOrderPage)));
            assertTrue(String.valueOf(basketProductPrice).contains(String.valueOf(priceOrderPage)));
        }
        log.info("The products in the cart and the products on the OrderDetailsPage are the same");
        return this;
    }

    public String getDeliveryAddress() {
        String delivery = getTextFromElement(deliveryAddress).trim();
        return delivery;
    }

    public String getInvoiceAddress() {
        String invoice = getTextFromElement(invoiceAddress).trim();
        return invoice;
    }

    public OrderDetailsPage goToAddressPage() {
        scrollToElement(footerPage.addressBtn);
        clickOnElement(footerPage.addressBtn);
        return this;
    }
}
