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
public class SelectTestCasesPage extends BasePage{

    @FindBy(xpath = "//p[text()='Authorization']")
    private WebElement authorizationCasesBtn;
    @FindBy(xpath = "//button[text()='Select all']")
    private WebElement selectAllBtn;
    @FindBy(xpath = "//button[text()='Assign selected']")
    private WebElement assignSelectedBtn;
    @FindBy(xpath = "//div[text()='Unassigned']")
    private WebElement unassignedBtn;
    @FindBy(xpath = "//div[text()='вячеслав']")
    private WebElement assignee;
    @FindBy(xpath = "//div[@class='modal-footer mt-2']//button[text()='Done']")
    private WebElement selectAssigneeDoneBtn;
    @FindBy(xpath = "//div[@class='modal-footer']//button[text()='Done']")
    private WebElement selectTestCasesDoneBtn;

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

    public SelectTestCasesPage clickAuthorizationCasesBtn() {
        authorizationCasesBtn.click();
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
        By assigneeLocator = By.xpath("//div[text()='вячеслав']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(assigneeLocator));
        assignee.click();
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
