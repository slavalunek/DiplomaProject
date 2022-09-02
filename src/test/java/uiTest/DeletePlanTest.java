package uiTest;

import com.github.javafaker.Faker;
import org.example.UI.pages.*;
import org.example.UI.steps.LoginSteps;
import org.example.UI.steps.PlanSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.example.UI.pages.BasePage.DELETE;
import static org.testng.Assert.assertTrue;

public class DeletePlanTest extends BaseTest{

    Faker faker = new Faker();
    String title = faker.name().title();

    @BeforeMethod
    public void login() {
        new LoginSteps(driver).loginStandardUser();
        new PlanSteps(driver).createPlan(title);
    }

    @Test
    public void deletePlan() {
       new TestPlansPage(driver).clickOnDropdown(title)
                                .choosingAnAction(DELETE)
                                .clickOnDeletePlan();
       assertTrue(new TestPlansPage(driver).isConfirmationMessageDisplayedDeletedPlan(title),"The plan was not deleted");
    }
}
