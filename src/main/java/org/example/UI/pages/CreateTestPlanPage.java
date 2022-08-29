package org.example.UI.pages;

import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CreateTestPlanPage extends BasePage {

    @FindBy(id = "title")
    private WebElement fieldTitle;
    @FindBy(id = "save-plan")
    private WebElement createPlanBtn;
    @FindBy(xpath = "//p[@class='empty-node']")
    private WebElement fieldDescription;
    @FindBy(xpath = "//button[text()=' Add cases']")
    private WebElement addCasesBtn;

    public CreateTestPlanPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isPageOpened() {
        waitForPageLoaded();
        By projectLocator = By.xpath("//div[text()='Plan details']");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(projectLocator));
            log.info("The page {} was opened successfully", "Home");
            return true;
        } catch (TimeoutException e) {
            log.info("The page {} was not opened because of error: {}", "Login", e.getMessage());
            return false;
        }
    }

    public CreateTestPlanPage fillInTheFieldTitle(String title) {
        fieldTitle.sendKeys(title);
        return this;
    }

    public CreateTestPlanPage fillInTheFieldDescription(String Description) {
        fieldDescription.sendKeys(Description);
        return this;
    }

    public SelectTestCasesPage clickAddCasesBtn() {
        addCasesBtn.click();
        return new SelectTestCasesPage(driver);
    }

    public TestPlansPage clickCreatePlanBtn() {
        createPlanBtn.click();
        return new TestPlansPage(driver);
    }

}
