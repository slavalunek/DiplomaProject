package UITest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.UI.pages.CreateTestPlanPage;
import org.example.UI.pages.LoginPage;
import org.example.UI.pages.ProjectPage;
import org.example.UI.pages.TestPlansPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    ProjectPage projectPage;
    CreateTestPlanPage createTestPlanPage;
    TestPlansPage testPlansPage;

    @BeforeMethod()
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
        projectPage = new ProjectPage(driver);
        createTestPlanPage = new CreateTestPlanPage(driver);
        testPlansPage = new TestPlansPage(driver);
    }

   // @AfterMethod(alwaysRun = true)
   // public void tearDown() {
   //     if (driver != null) {
   //         driver.quit();
   //     }
   // }
}
