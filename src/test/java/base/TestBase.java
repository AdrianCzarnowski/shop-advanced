package base;

import configuration.factory.DriverFactory;
import configuration.properties.App;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;


public class TestBase {
    private static App app;
    private static DriverFactory driverFactory = new DriverFactory();
    protected WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        app = new App();
    }

    @BeforeEach
    void beforeEach() {
        driver = driverFactory.getDriver(app.setBrowserFromYaml());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}


