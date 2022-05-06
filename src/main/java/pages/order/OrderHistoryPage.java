package pages.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;

public class OrderHistoryPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("OrderHistoryPage.class");
    @FindBy(css = "tbody > tr > th")
    private WebElement orderReference;
    @FindBy(css = "tbody > tr > td:nth-child(2)")
    private WebElement date;

    @FindBy(css = "tbody > tr > td:nth-child(3)")
    private WebElement totalPrice;

    @FindBy(css = "tbody > tr > td:nth-child(4)")
    private WebElement payment;

    @FindBy(css = "tbody > tr > td:nth-child(5)")
    private WebElement status;

    @FindBy(xpath = ".//a[contains(text(), 'Details')]")
    private WebElement detailsBtn;

    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }

    public String orderReference() {
        String order = getTextFromElement(orderReference);
        return order;
    }

    public String orderDate() {
        String orderDate = getTextFromElement(date);
        return orderDate;
    }

    public double orderTotalPrice() {
        double total = Double.parseDouble(getTextFromElement(totalPrice).replace("$", ""));
        return total;
    }

    public String orderPayment() {
        String paymentType = getTextFromElement(payment);
        return paymentType;
    }

    public String orderStatus() {
        String orderStatus = getTextFromElement(status);
        return orderStatus;
    }

    public OrderHistoryPage goToDetailsPage() {
        clickOnElement(detailsBtn);
        return this;
    }
}

