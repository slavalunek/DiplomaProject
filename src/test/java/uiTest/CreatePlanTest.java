package uiTest;

import com.github.javafaker.Faker;
import org.example.UI.dto.Plan;
import org.example.UI.dto.TestCases;
import org.example.UI.pages.*;
import org.example.UI.steps.LoginSteps;
import org.example.UI.steps.PlanSteps;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.example.UI.pages.BasePage.DELETE;
import static org.testng.Assert.*;

public class CreatePlanTest extends BaseTest {

    Faker faker = new Faker();
    String title = faker.name().title();

    @BeforeMethod
    public void login() {
        new LoginSteps(driver).loginStandardUser();
    }

    @Test
    public void createPlan() {

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

        assertTrue(testPlansPage.isConfirmationMessageDisplayed(), "The plan was not made");
        Plan actualPlan = new PlanDetailsPage(driver).openDetails(title)
                                                     .getPlanDetails();
        assertEquals(actualPlan, plan, "The plan does not meet the expected");

        TestCases expectedTestCases = new PlanSteps(driver).testCasesInThePlan();
        TestCases actualTestCases = new PlanTestCasesPage(driver).openTestCases().getUserNameFromTestCases();
        assertEquals(actualTestCases, expectedTestCases, "The user name of the test case doesn't match");
    }

    @AfterMethod
    public void deletePlan(){
        new PlanSteps(driver).deletePlan(title,DELETE);
    }
}
