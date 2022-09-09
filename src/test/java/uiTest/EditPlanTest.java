package uiTest;

import com.github.javafaker.Faker;
import org.example.UI.dto.Plan;
import org.example.UI.pages.NewPlanModel;
import org.example.UI.pages.PlanDetailsPage;
import org.example.UI.pages.TestPlansPage;
import org.example.UI.steps.LoginSteps;
import org.example.UI.steps.PlanSteps;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.example.UI.pages.BasePage.DELETE;
import static org.example.UI.pages.BasePage.EDITE;
import static org.testng.Assert.assertEquals;

public class EditPlanTest extends BaseTest {

    Faker faker = new Faker();
    String title = faker.name().title();

    @BeforeMethod
    public void login() {
        new LoginSteps(driver).loginStandardUser();
        new PlanSteps(driver).createPlanWithoutDescription(title);
    }

    @Test
    public void addCasesByFilters() {

        String description = faker.name().title();

        new TestPlansPage(driver).clickOnDropdown(title)
                                 .choosingAnAction(EDITE);

        Plan plan = Plan.builder()
                        .fieldDescription(description)
                        .build();

        new NewPlanModel(driver).fillInNewPlanModel(plan)
                                .clickSavePlanBtn();

        Plan actualPlan = new PlanDetailsPage(driver).openDetails(title)
                                                     .getPlanDetails();
        Plan expectedPlan = Plan.builder()
                                .fieldTitle(title)
                                .fieldDescription(description)
                                .build();
        assertEquals(actualPlan, expectedPlan, "The plan does not updated");
    }

    @AfterMethod
    public void deletePlan() {
        new PlanSteps(driver).deletePlan(title, DELETE);
    }
}