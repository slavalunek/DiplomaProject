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
public class SelectTestCasesPage extends BasePage {

    public static final String PLAN_DEMO_CREATE = "/plan/DEMO/create";
    @FindBy(xpath = "//p[text()='Authorization']")
    private WebElement authorizationCasesBtn;
    @FindBy(xpath = "//span[text()='Select all']")
    private WebElement selectAllBtn;
    @FindBy(xpath = "//span[text()='Assign selected']")
    private WebElement assignSelectedBtn;
    @FindBy(xpath = "//div[text()='Unassigned']")
    private WebElement unassignedBtn;
    @FindBy(xpath = "//div[@class='modal-footer mt-2']//button[text()='Done']")
    private WebElement selectAssigneeDoneBtn;
    @FindBy(xpath = "//div[@class='modal-footer']//button[text()='Done']")
    private WebElement selectTestCasesDoneBtn;
    @FindBy(xpath = "//button[text()='+ Add filter']")
    private WebElement AddFilterBtn;
    @FindBy(xpath = "//div[@class='suite-left']//p[text()='Projects']")
    private WebElement projectsCasesBtn;

    public SelectTestCasesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isPageOpened() {
        waitForPageLoaded();
        By projectLocator = By.xpath("//h2[text()='Select test cases']");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(projectLocator));
            log.info("The page {} was opened successfully", "Home");
            return true;
        } catch (TimeoutException e) {
            log.info("The page {} was not opened because of error: {}", "Login", e.getMessage());
            return false;
        }
    }

    public SelectTestCasesPage open() {
        Properties properties = PropertiesLoader.loadProperties();
        driver.get(properties.getProperty("base.url") + PLAN_DEMO_CREATE);
        return this;
    }

    public SelectTestCasesPage clickAddFilterButton() {
        AddFilterBtn.click();
        return this;
    }

    public SelectTestCasesPage filterSelection(String fotmat) {
        By filterSelection = By.xpath(String.format("//div[@class='filters-menu WHRMzV']//button[text()='%s']", fotmat));
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterSelection));
        driver.findElement(filterSelection).click();
        return this;
    }

    public SelectTestCasesPage clickProjectsCasesBtn() {
        projectsCasesBtn.click();
        return this;
    }

    public SelectTestCasesPage levelSelection(String level) {
        By levelSelection = By.xpath(String.format("//span[text()='%s']//ancestor::div[@class='checkbox']" +
                "//span[contains(@class,'indicator')]", level));
        wait.until(ExpectedConditions.visibilityOfElementLocated(levelSelection));
        driver.findElement(levelSelection).click();
        return this;
    }
    public boolean isTheSelectionLevelIsConfirmed(String level) {
        By levelSelection = By.xpath(String.format("//div[@class='suitecase-params']//span[text()='%s']", level));
        wait.until(ExpectedConditions.visibilityOfElementLocated(levelSelection));
        return driver.findElement(levelSelection).isDisplayed();
    }

    public SelectTestCasesPage clickAuthorizationCasesBtn() {
        authorizationCasesBtn.click();
        By selectAllLocator = By.xpath("//span[text()='Select all']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectAllLocator));
        return this;
    }

    public SelectTestCasesPage clickSelectAllBtn() {
        selectAllBtn.click();
        return this;
    }

    public SelectTestCasesPage clickAssignSelectedBtn() {
        assignSelectedBtn.click();
        By UnassignedLocator = By.xpath("//div[text()='Unassigned']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(UnassignedLocator));
        return this;
    }

    public SelectTestCasesPage clickUnassignedBtn() {
        unassignedBtn.click();
        return this;
    }

    public SelectTestCasesPage clickAssignee() {
        Properties properties = PropertiesLoader.loadProperties();
        By assigneeLocator = By.xpath(String.format("//div[text()='%s']", properties.getProperty("userName")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(assigneeLocator));
        driver.findElement(assigneeLocator).click();
        return this;
    }

    public SelectTestCasesPage clickSelectAssigneeDoneBtn() {
        selectAssigneeDoneBtn.click();
        return this;
    }

    public CreateTestPlanPage clickSelectTestCasesDoneBtn() {
        selectTestCasesDoneBtn.click();
        return new CreateTestPlanPage(driver);
    }
}
