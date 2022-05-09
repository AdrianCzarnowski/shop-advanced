package pages.base;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;


public class BasePage {
    protected static Random random = new Random();
    private static Logger log = LoggerFactory.getLogger("BasePage.class");
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor js;
    protected SoftAssertions softAssert;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(System.getProperty("waitTime"))));
        actions = new Actions(driver);
        js = ((JavascriptExecutor) driver);
        PageFactory.initElements(driver, this);
        softAssert = new SoftAssertions();
    }

    public static String getValueFromElement(WebElement element) {
        return element.getAttribute("value");
    }

    public static WebElement getRandomElement(List<WebElement> elements) {
        return elements.get(new Random().nextInt(elements.size()));
    }

    public static String getTodayDate() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return dateTimeFormatter.format(localDate);

    }

    public String getTextFromElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        wait.until(ExpectedConditions.visibilityOf(element));
        try {
            return element.getText();
        } catch (StaleElementReferenceException e) {
            return "";
        }
    }

    public void highLightenerMethod(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: lightred; border: 5px solid red;')", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickOnElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
//        highLightenerMethod(element);
        element.click();
    }

    public void selectCheckBox(WebElement element) {
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void sendKeys(WebElement element, String text, boolean clear) {
        if (clear) {
            highLightenerMethod(element);
            element.clear();
        }
        wait.until(ExpectedConditions.visibilityOf(element));
        highLightenerMethod(element);
        element.sendKeys(text);
    }

    public WebElement randomValueFromList(List<WebElement> elementList) {
        waitForPageLoaded();
        int size = new Random().nextInt(elementList.size());
        return elementList.get(size);
    }

    public void clickAndHold(WebElement element) {
        actions.clickAndHold(element);
    }

    public void releaseMouse(WebElement element) {
        actions.release(element).perform();
    }

    public void waitToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitToBeInvisible(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitToBeVisibleAllElements(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void waitForPageLoaded() {
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }
}
