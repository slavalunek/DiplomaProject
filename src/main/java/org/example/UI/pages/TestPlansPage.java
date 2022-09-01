package org.example.UI.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class TestPlansPage extends BasePage {

    @FindBy(id = "createButton")
    private WebElement createPlanButton;
    @FindBy(xpath = "//span[text()='Test plan was created successfully!']")
    private WebElement confirmationMessage;
    By testPlans = By.xpath("//h1[text()='Test plans']");

    public TestPlansPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isPageOpened() {
        waitForPageLoaded();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(testPlans));
            log.info("The page {} was opened successfully", "Home");
            return true;
        } catch (TimeoutException e) {
            log.info("The page {} was not opened because of error: {}", "Login", e.getMessage());
            return false;
        }
    }

    public TestPlansPage open() {
        driver.get("https://app.qase.io/plan/DEMO");
        wait.until(ExpectedConditions.visibilityOfElementLocated(testPlans));
        return this;
    }

    public CreateTestPlanPage clickCreatePlanBtn() {
        createPlanButton.click();
        By planDetails = By.xpath("//div[text()='Plan details']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(planDetails));
        return new CreateTestPlanPage(driver);
    }

    public Boolean isConfirmationMessageDisplayed() {
        By element = By.xpath("//span[text()='Test plan was created successfully!']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return confirmationMessage.isDisplayed();
    }

    public Boolean isConfirmationMessageDisplayedDeletedPlan(String title) {
        By element = By.xpath(String.format("//span[text()='Test plan %s was deleted successfully!']", title));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return confirmationMessage.isDisplayed();
    }

    public TestPlansPage clickOnDropdown(String title) {
        By dropdown = By.xpath(String.format("//a[text()='%s']" +
                "//ancestor::tr[@class='project-row']//a[@aria-expanded='false']", title));
        wait.until(ExpectedConditions.visibilityOfElementLocated(dropdown));
        driver.findElement(dropdown).click();
        return this;
    }

    public TestPlansPage choosingAnAction(String action) {
        By choosingAnAction = By.xpath(String.format("//a[@aria-expanded='true']//ancestor::tr[@class='project-row']//a[text()='%s']", action));
        wait.until(ExpectedConditions.visibilityOfElementLocated(choosingAnAction));
        driver.findElement(choosingAnAction).click();
        return this;
    }

    public TestPlansPage clickOnDeletePlan() {
        By deletePlan = By.xpath("//button[text()=' Delete plan']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(deletePlan));
        driver.findElement(deletePlan).click();
        return this;
    }
}
