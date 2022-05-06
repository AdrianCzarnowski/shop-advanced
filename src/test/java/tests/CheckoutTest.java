package tests;

import base.Pages;
import configuration.factory.UserFactory;
import configuration.handler.DataHandler;
import configuration.model.Product;
import configuration.model.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CheckoutTest extends Pages {
    private static Logger log = LoggerFactory.getLogger("CheckoutTest.class");

    @Test
    public void purchaseAndRegistrationProcessTest() {

        DataHandler dataHandler = new DataHandler();
        User user = new UserFactory().getRandomUser();

        menuPage
                .clickSingInButton();
        loginPage
                .clickNewAccount();
        registrationPage
                .fillForm(user);

        for (int i = 0; i < randomProductPage.numberOfRandomProduct - 1; i++) {
            homePage
                    .goToRandomCategory();
            randomProductPage
                    .clickRandomProduct()
                    .setRandomQuantityValue()
                    .clickAddToCartButton();
            productOrderDetailsPage.clickContinueShopping();
        }
        homePage
                .goToRandomCategory();
        randomProductPage
                .clickRandomProduct()
                .setRandomQuantityValue()
                .clickAddToCartButton();
        productOrderDetailsPage
                .clickProceedToCheckout();
        final List<Product> basketList = productOrderDetailsPage.setBasket().getBasketList();
        basketPage
                .clickProceedBtn();
        addressPage
                .fillAddressesForm();
        dataHandler
                .setShippingMethodName(shippingMethodPage.shippingMethodName().getShippingMethodName());

        shippingMethodPage
                .clickContinueBtn();
        dataHandler
                .setPaymentMethod(paymentPage.getPaymentMethodName());
        paymentPage
                .selectPayment()
                .checkTermsOfService()
                .selectTermsOfService()
                .clickPlaceOrderBtn();
        summaryPage
                .checkOrderDetails();

        dataHandler
                .setOrderReference(summaryPage.getOrderReference());
        dataHandler
                .setTotalAmount(summaryPage.getTotalAmount());

        assertAll(
                "heading",
                ()->assertEquals(dataHandler.getShippingMethodName(), summaryPage.getShippingMethod()),
                ()->assertEquals(dataHandler.getPaymentMethod(), System.getProperty("payment_method_payment_page")));
        log.info("Shipping and payments methods are correct");

        summaryPage
                .goToOrderHistory();

        assertAll(
                "heading",
                () -> assertEquals(dataHandler.getOrderReference(), orderHistoryPage.orderReference()),
                () -> assertEquals(BasePage.getTodayDate(), orderHistoryPage.orderDate()),
                () -> assertEquals(dataHandler.getTotalAmount(), orderHistoryPage.orderTotalPrice()),
                () -> assertEquals(System.getProperty("payment_order_history"), orderHistoryPage.orderPayment()),
                () -> assertTrue(orderHistoryPage.orderStatus().contains(dataHandler.getPaymentMethod())));
        log.info("Order reference, date, total cost, payment method and payment status are correct");


        orderHistoryPage
                .goToDetailsPage();
        orderDetailsPage
                .comparisionOfProductsFromCartToBasket(basketList);

        dataHandler
                .setAddress(orderDetailsPage.getDeliveryAddress());

        assertEquals(orderDetailsPage.getDeliveryAddress(), orderDetailsPage.getInvoiceAddress());
        
        orderDetailsPage
                .goToAddressPage();

        assertEquals(dataHandler.getAddress(), userAddressPage.userAddress());
        log.info("Address on invoice, delivery and account address are the same");
    }
}
