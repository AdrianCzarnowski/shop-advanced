package base;

import org.junit.jupiter.api.BeforeEach;
import pages.base.BasePage;
import pages.navigation.FooterPage;
import pages.navigation.HomePage;
import pages.navigation.MenuPage;
import pages.order.*;
import pages.product.*;
import pages.user.LoginPage;
import pages.user.RegistrationPage;
import pages.user.UserAddressPage;

public class Pages extends TestBase {
    public static SearchPage searchPage;
    public static CategoryPage categoryPage;
    public static ArtPage artPage;
    public static FooterPage footerPage;
    public static PriceDropPage priceDropPage;
    public static ProductOnSalePage productPage;
    public static RandomProductPage randomProductPage;
    public static ProductOrderDetailsPage productOrderDetailsPage;
    public static HomePage homePage;
    public static BasketPage basketPage;
    public static MenuPage menuPage;
    public static LoginPage loginPage;
    public static RegistrationPage registrationPage;
    public static AddressPage addressPage;
    public static ShippingMethodPage shippingMethodPage;
    public static PaymentPage paymentPage;
    public static SummaryPage summaryPage;
    public static OrderHistoryPage orderHistoryPage;
    public static OrderDetailsPage orderDetailsPage;
    public static UserAddressPage userAddressPage;
    public static BasePage basePage;




    @BeforeEach
    public void testSetup() {
        searchPage = new SearchPage(driver);
        categoryPage = new CategoryPage(driver);
        artPage = new ArtPage(driver);
        footerPage = new FooterPage(driver);
        priceDropPage = new PriceDropPage(driver);
        productPage = new ProductOnSalePage(driver);
        randomProductPage = new RandomProductPage(driver);
        productOrderDetailsPage = new ProductOrderDetailsPage(driver);
        homePage = new HomePage(driver);
        basketPage = new BasketPage(driver);
        menuPage = new MenuPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        addressPage = new AddressPage(driver);
        shippingMethodPage = new ShippingMethodPage(driver);
        paymentPage = new PaymentPage(driver);
        summaryPage = new SummaryPage(driver);
        orderHistoryPage = new OrderHistoryPage(driver);
        orderDetailsPage = new OrderDetailsPage(driver);
        userAddressPage = new UserAddressPage(driver);
        basePage = new BasePage(driver);

    }
}
