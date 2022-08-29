package UITest;

import org.example.UI.pages.LoginPage;
import org.example.UI.utils.PropertiesLoader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Properties;

public class LoginTest extends BaseTest {

    @Test
    public void loginUser() {
        Properties properties = PropertiesLoader.loadProperties();
        new LoginPage(driver).open()
                             .fillInUserName(properties.getProperty("username"))
                             .fillInPassword(properties.getProperty("password"))
                             .submitForm();
        Assert.assertTrue(projectPage.isProjectPageOpened(), "the user is not logged in");
    }
}
