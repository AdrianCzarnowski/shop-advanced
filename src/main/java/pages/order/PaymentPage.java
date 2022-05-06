package pages.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;

public class PaymentPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("PaymentPage.class");
    @FindBy(xpath = "//*[@id='conditions_to_approve[terms-and-conditions]']")
    private WebElement termsCheckbox;
    @FindBy(xpath = "//*[@id='payment-option-2']")
    private WebElement payByBankWire;
    @FindBy(css = "#payment-option-2-container > label > span")
    private WebElement payByBankWireText;
    @FindBy(xpath = "//a[@id='cta-terms-and-conditions-0']")
    private WebElement termsOfServiceLink;
    @FindBy(xpath = "//*[@class='js-modal-content']")
    private WebElement modalMsg;
    @FindBy(css = "#modal > div > div > button > span")
    private WebElement closeModal;
    @FindBy(xpath = "//*[@id='payment-confirmation']/div[1]/button")
    private WebElement placeOrderBtn;

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public PaymentPage selectTermsOfService() {
        selectCheckBox(termsCheckbox);
        return this;
    }

    public PaymentPage selectPayment() {
        selectCheckBox(payByBankWire);
        return this;
    }


    public PaymentPage checkTermsOfService() {
        clickOnElement(termsOfServiceLink);
        String msg = getTextFromElement(modalMsg);
        if (!(msg == null)) {
            clickOnElement(closeModal);
            log.info("Terms are not empty");
        }
        return this;
    }

    public PaymentPage clickPlaceOrderBtn() {
        clickOnElement(placeOrderBtn);
        return this;
    }

    public String getPaymentMethodName() {
        String paymentMethod = getTextFromElement(payByBankWireText).replace("Pay by ", "").trim();
        log.info(paymentMethod);
        return paymentMethod;
    }
}
