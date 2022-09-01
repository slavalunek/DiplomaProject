package UITest;

import org.example.UI.pages.NewPlanModel;
import org.example.UI.pages.SelectTestCasesPage;
import org.example.UI.pages.TestPlansPage;
import org.example.UI.steps.LoginSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AddCasesByFiltersTest extends BaseTest {

    @BeforeMethod
    public void login() {
        new LoginSteps(driver).loginStandardUser();

    }

    @Test
    public void addCasesByFilters() {

        new TestPlansPage(driver).open()
                                 .clickCreatePlanBtn();

        new NewPlanModel(driver).clickAddCasesBtn()
                                .clickAddFilterButton()
                                .filterSelection("Severity")
                                .levelSelection("Major")
                                .clickProjectsCasesBtn();

        assertTrue(new SelectTestCasesPage(driver).isTheSelectionLevelIsConfirmed("Major"), "The level selection do not matches");
    }
}
