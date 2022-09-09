package org.example.UI.steps;

import org.example.UI.pages.LoginPage;
import org.example.UI.pages.ProjectPage;
import org.example.UI.utils.PropertiesLoader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Properties;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;
    ProjectPage projectPage;

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
        this.projectPage = new ProjectPage(driver);
    }

    public void loginStandardUser() {
        Properties properties = PropertiesLoader.loadProperties();
        new LoginPage(driver).open()
                             .fillInUserName(properties.getProperty("email"))
                             .fillInPassword(properties.getProperty("password"))
                             .submitForm();
        Assert.assertTrue(projectPage.isProjectPageOpened(), "the user is not logged in");
    }
}