package tests;

import base.Pages;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PriceDropPageTest extends Pages {
    private static Logger log = LoggerFactory.getLogger("SearchResultTest.class");

    @Test
    public void saleProducts() {
        footerPage
                .clickPriceDropButton();
        priceDropPage
                .checkOnSalePageAreLoaded()
                .checkVisibilityOfPricesAndDiscount()
                .calculateAndCheckDiscountPrice()
                .openOneProductPage();
        productPage
                .checkVisibilityOfSaveLabel()
                .checkCalculateDiscount();
    }
}
