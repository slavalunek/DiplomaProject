package org.example.UI.pages;

import org.example.UI.dto.Plan;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewPlanModel extends BasePage {

    @FindBy(xpath = "//button[text()=' Add cases']")
    private WebElement addCasesBtn;
    @FindBy(id = "save-plan")
    private WebElement savePlanBtn;

    public NewPlanModel(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isPageOpened() {
        return false;
    }

    public NewPlanModel fillInNewPlanModel(Plan plan) {
        if (plan.getFieldTitle() != null) {
            new CreateTestPlanPage(driver).fillInTheFieldTitle(plan.getFieldTitle());
        }

        if (plan.getFieldDescription() != null) {
            new CreateTestPlanPage(driver).fillInTheFieldDescription(plan.getFieldDescription());
        }
        return this;
    }

    public SelectTestCasesPage clickAddCasesBtn() {
        addCasesBtn.click();
        return new SelectTestCasesPage(driver);
    }

    public TestPlansPage clickSavePlanBtn() {
        savePlanBtn.click();
        return new TestPlansPage(driver);
    }
}