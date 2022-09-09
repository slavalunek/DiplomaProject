package org.example.UI.steps;

import com.github.javafaker.Faker;
import org.example.UI.dto.Plan;
import org.example.UI.dto.TestCases;
import org.example.UI.pages.NewPlanModel;
import org.example.UI.pages.SelectTestCasesPage;
import org.example.UI.pages.TestPlansPage;
import org.example.UI.utils.PropertiesLoader;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class PlanSteps {

    WebDriver driver;
    Properties properties = PropertiesLoader.loadProperties();

    Faker faker = new Faker();

    public PlanSteps(WebDriver driver) {
        this.driver = driver;
    }

    public void createPlan(String title) {
        new TestPlansPage(driver).open()
                                 .clickCreatePlanBtn();

        Plan plan = Plan.builder()
                        .fieldTitle(title)
                        .fieldDescription(faker.name().title())
                        .build();

        new NewPlanModel(driver).fillInNewPlanModel(plan)
                                .clickAddCasesBtn();

        new SelectTestCasesPage(driver).clickAuthorizationCasesBtn()
                                       .clickSelectAllBtn()
                                       .clickAssignSelectedBtn()
                                       .clickUnassignedBtn()
                                       .clickAssignee()
                                       .clickSelectAssigneeDoneBtn()
                                       .clickSelectTestCasesDoneBtn()
                                       .clickCreatePlanBtn();
    }

    public void createPlanWithoutDescription(String title) {
        new TestPlansPage(driver).open()
                                 .clickCreatePlanBtn();

        Plan plan = Plan.builder()
                        .fieldTitle(title)
                        .build();

        new NewPlanModel(driver).fillInNewPlanModel(plan)
                                .clickAddCasesBtn();

        new SelectTestCasesPage(driver).clickAuthorizationCasesBtn()
                                       .clickSelectAllBtn()
                                       .clickSelectTestCasesDoneBtn()
                                       .clickCreatePlanBtn();
    }

    public TestCases testCasesInThePlan() {
        TestCases testCases = TestCases.builder()
                                       .authorization1(properties.getProperty("userName"))
                                       .signUp(properties.getProperty("userName"))
                                       .passwordRestore(properties.getProperty("userName"))
                                       .signUpWithInviteLink(properties.getProperty("userName"))
                                       .authorization2(properties.getProperty("userName")).build();
        return testCases;
    }

    public void deletePlan(String title, String action) {
        new TestPlansPage(driver).open()
                                 .clickOnDropdown(title)
                                 .choosingAnAction(action)
                                 .clickOnDeletePlan();
    }
}