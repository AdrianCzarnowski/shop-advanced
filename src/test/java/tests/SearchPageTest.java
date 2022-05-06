package tests;

import base.Pages;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


public class SearchPageTest extends Pages {

    private static Logger log = LoggerFactory.getLogger("SearchResultTest.class");

    @Test
    public void searchResult_MatchTo_InputProductName() {
        searchPage
                .fillSearchBox()
                .clickSearchButton();
        log.info("Product name after search: " + searchPage.getProductNameAfterSearch());
        assertThat(searchPage.getProductNameAfterSearch(), containsString(searchPage.getProduct()));
    }

    @Test
    public void dropDownResult_MatchTo_InputProductName() {
        searchPage
                .fillSearchBox();
        log.info("Random product name: " + searchPage.getTextFromSearchField());
        assertThat(searchPage.getProductsNameFromDropDownList(), containsString(searchPage.getProduct()));
    }
}

