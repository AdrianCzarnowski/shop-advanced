package pages.order;

import configuration.factory.FakeDataFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;

public class AddressPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("LoginPage.class");
    @FindBy(xpath = "//input[@name='company']")
    private WebElement company;
    @FindBy(xpath = "//input[@name='address1']")
    private WebElement address;
    @FindBy(xpath = "//input[@name='city']")
    private WebElement city;
    @FindBy(xpath = "//input[@name='postcode']")
    private WebElement postcode;
    @FindBy(xpath = "//select[@name='id_state']")
    private WebElement states;
    @FindBy(xpath = "//select[@name='id_country']")
    private WebElement country;
    @FindBy(xpath = "//input[@name='phone']")
    private WebElement phone;
    @FindBy(xpath = "//button[@name='confirm-addresses']")
    private WebElement contiuneBtn;


    public AddressPage(WebDriver driver) {
        super(driver);
    }

    private void selectCountry() {
        Select select = new Select(country);
        select.selectByVisibleText(System.getProperty("country"));
    }


    public AddressPage fillAddressesForm() {
        sendKeys(company, FakeDataFactory.getCompany(), true);
        sendKeys(address, FakeDataFactory.getStreet(), true);
        sendKeys(city, FakeDataFactory.getCity(), true);
        selectCountry();
        sendKeys(postcode, FakeDataFactory.getZipCode(), true);
        sendKeys(phone, FakeDataFactory.getNumber(), true);
        clickOnElement(contiuneBtn);
        log.info("Address form is complete");
        return this;
    }
}
