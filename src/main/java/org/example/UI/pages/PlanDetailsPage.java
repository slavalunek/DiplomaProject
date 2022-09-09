package org.example.UI.pages;

import lombok.extern.log4j.Log4j2;
import org.example.UI.dto.Plan;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class PlanDetailsPage extends BasePage{

    @FindBy(xpath = "//div[@class='plan-view-header-title']/h1")
    private WebElement planTitle;
    @FindBy(xpath = "//div[@class='toastui-editor-contents']//p")
    private WebElement planDescription;

    public PlanDetailsPage(WebDriver driver) {
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

    public PlanDetailsPage openDetails(String title) {
        driver.findElement(By.xpath(String.format("//a[text()='%s']",title))).click();
        return this;
    }

    public Plan getPlanDetails() {
        Plan plan = Plan.builder()
                .fieldTitle(getPlanTitle())
                .fieldDescription(getPlanDescription()).build();
        return plan;
    }

    public String getPlanTitle() {
        return planTitle.getText();
    }

    public String getPlanDescription() {
        return planDescription.getText();
    }
}