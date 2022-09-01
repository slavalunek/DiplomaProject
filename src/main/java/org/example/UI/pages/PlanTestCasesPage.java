package org.example.UI.pages;

import lombok.extern.log4j.Log4j2;
import org.example.UI.dto.TestCases;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class PlanTestCasesPage extends BasePage {

    public PlanTestCasesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isPageOpened() {
        waitForPageLoaded();
        By projectLocator = By.id("tab-details");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(projectLocator));
            log.info("The page {} was opened successfully", "Home");
            return true;
        } catch (TimeoutException e) {
            log.info("The page {} was not opened because of error: {}", "Login", e.getMessage());
            return false;
        }
    }

    public PlanTestCasesPage openTestCases() {
        driver.findElement(By.id("tab-test-cases")).click();
        return this;
    }

    public TestCases getUserNameFromTestCases() {
        TestCases testCases = TestCases.builder()
                                       .authorization1(getAssigneeInTestCase("Authorization"))
                                       .signUp(getAssigneeInTestCase("Sign up"))
                                       .passwordRestore(getAssigneeInTestCase("Password restore"))
                                       .signUpWithInviteLink(getAssigneeInTestCase("Sign up with invite link"))
                                       .authorization2(getAssigneeInTestCase("Authorization")).build();
        return testCases;
    }

    public String getAssigneeInTestCase(String testCaseName) {
        By fullLocator = By.xpath(String.format("//p[text()='%s']//ancestor::tbody//span", testCaseName));
        return driver.findElement(fullLocator).getText();
    }
}
