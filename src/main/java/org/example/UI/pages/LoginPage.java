package org.example.UI.pages;

import lombok.extern.log4j.Log4j2;
import org.example.UI.utils.PropertiesLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Properties;

@Log4j2
public class LoginPage extends BasePage{

    @FindBy(id = "inputEmail")
    private WebElement userEmailInput;

    @FindBy(id = "inputPassword")
    private WebElement passwordInput;

    @FindBy(id = "btnLogin")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isPageOpened() {
        waitForPageLoaded();
        By accountsLocator = By.id("Symbols");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(accountsLocator));
            log.info("The page {} was opened successfully","Home");
            return true;
        } catch (TimeoutException e) {
            log.info("The page {} was not opened because of error: {}","Login",e.getMessage());
            return false;
        }
    }

    public LoginPage open() {
        Properties properties = PropertiesLoader.loadProperties();
        driver.get(properties.getProperty("base.url") + LOGIN_REDIRECT_PATH_2_F);
        return this;
    }

    public LoginPage fillInUserName(String userName) {
        userEmailInput.sendKeys(userName);
        return this;
    }

    public LoginPage fillInPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public ProjectPage submitForm() {
        loginButton.submit();
        By locator = By.xpath("//h1[text()='Projects']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return new ProjectPage(driver);
    }
}
