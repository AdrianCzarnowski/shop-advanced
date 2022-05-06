package pages.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class SummaryPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("BasePage.class");
    List<String> productList = new ArrayList<>();
    @FindBy(xpath = "//*[@id=\"order-items\"]/div[2]/div")
    private List<WebElement> products;
    @FindBy(css = "#order-details > ul > li:nth-child(1)")
    private WebElement orderNumber;
    @FindBy(css = "#order-details > ul > li:nth-child(3)")
    private WebElement shippingMethod;

    @FindBy(css = "#order-details > ul > li:nth-child(2)")
    private WebElement paymentMethod;

    @FindBy(css = "[title='Orders']")
    private WebElement orderHistory;

    @FindBy(xpath = "//tr[@class='total-value font-weight-bold']")
    private WebElement totalAmount;


    public SummaryPage(WebDriver driver) {
        super(driver);
    }

    public SummaryPage checkOrderDetails() {

        for (int i = 0; i < products.size(); i++) {
            waitToBeVisibleAllElements(products);
            productList.add(getTextFromElement(products.get(i)));
        }
        return this;
    }

    public String getOrderReference() {
        String orderReference = getTextFromElement(orderNumber).replace("Order reference: ", "");
        return orderReference;
    }

    public String getShippingMethod() {
        String shippingMethodName = getTextFromElement(shippingMethod).replace("Shipping method: ", "").replace("Pick up in-store", "");
        log.info("Shipping method name: " + getTextFromElement(shippingMethod).replace("Shipping method: ", "").replace("Pick up in-store", ""));
        return shippingMethodName.trim();
    }



    public double getTotalAmount() {
        double totalAm = Double.parseDouble(getTextFromElement(totalAmount).replace("TOTAL  $", "").trim());
        log.info("total amount is: " + totalAm);
        return totalAm;
    }

    public SummaryPage goToOrderHistory() {
        scrollToElement(orderHistory);
        clickOnElement(orderHistory);
        return this;
    }
}
