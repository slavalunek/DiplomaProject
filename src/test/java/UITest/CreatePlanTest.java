package UITest;

import com.github.javafaker.Faker;
import org.example.UI.pages.CreateTestPlanPage;
import org.example.UI.pages.SelectTestCasesPage;
import org.example.UI.pages.TestPlansPage;
import org.example.UI.steps.LoginSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreatePlanTest extends BaseTest {

    @BeforeMethod
    public void login() {
        new LoginSteps(driver).loginStandardUser();
    }

    @Test
    public void CreatePlan() {
        Faker faker = new Faker();

        new TestPlansPage(driver).open()
                                 .clickCreatePlanBtn();

        new CreateTestPlanPage(driver).fillInTheFieldTitle(faker.name().title())
                                      .fillInTheFieldDescription(faker.name().title())
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
}
