package tests;

import base.Pages;
import configuration.model.Product;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasketTest extends Pages {

    private static Logger log = LoggerFactory.getLogger("SearchResultTest.class");


    @Test
    public void addRandomProduct() {
        for (int i = 0; i < randomProductPage.numberOfAdditionToCart; i++) {
            homePage
                    .goToRandomCategory();
            randomProductPage
                    .clickRandomProduct()
                    .setRandomQuantityValue()
                    .clickAddToCartButton();
            productOrderDetailsPage
                    .clickContinueShopping()
                    .checkCartOfProducts();

        }
    }

    @Test
    public void basketTest() {

        for (int i = 0; i < randomProductPage.numberOfRandomProduct; i++) {
            homePage
                    .goToRandomCategory();
            randomProductPage
                    .clickRandomProduct()
                    .clickAddToCartButton();
            productOrderDetailsPage
                    .clickContinueShopping()
                    .checkCartOfProducts();
        }
        randomProductPage
                .clickBasketBtn();
        basketPage
                .checkTotalCost();
        assert ((String.valueOf(Product.getAllOrderCost())).contains(basketPage.checkTotalCost()));
        log.info("Order value are the same");

        randomProductPage
                .setFirstProductQuantity();
        basketPage
                .checkCostAfterChange()
                .clickUpArrowRandomProduct()
                .checkCostAfterChange()
                .clickDownArrowRandomProduct()
                .checkCostAfterChange()
                .clickTrashBtn();
    }
}
