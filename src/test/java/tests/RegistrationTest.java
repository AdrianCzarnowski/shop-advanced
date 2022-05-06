package tests;

import base.Pages;
import configuration.factory.UserFactory;
import configuration.model.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest extends Pages {
    private static Logger log = LoggerFactory.getLogger("RegistrationTest.class");

    @Test
    public void registrationAlreadyRegisterUserData(){
        User user = new UserFactory().getAlreadyRegisterUser();

        menuPage
                .clickSingInButton();
        loginPage
                .clickNewAccount();
        registrationPage
                .fillForm(user);
        assertTrue(registrationPage.alertMsg.isDisplayed());

    }

}
