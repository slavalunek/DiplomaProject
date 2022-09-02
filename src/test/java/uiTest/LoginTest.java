package uiTest;

import org.example.UI.pages.LoginPage;
import org.example.UI.utils.PropertiesLoader;
import org.testng.annotations.Test;

import java.util.Properties;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    @Test
    public void loginUser() {
        Properties properties = PropertiesLoader.loadProperties();
        new LoginPage(driver).open()
                             .fillInUserName(properties.getProperty("email"))
                             .fillInPassword(properties.getProperty("password"))
                             .submitForm();
        assertTrue(projectPage.isProjectPageOpened(), "the user is not logged in");
    }
}
