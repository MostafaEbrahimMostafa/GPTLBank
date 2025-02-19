import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
@Feature("Logout Page")
public class LogoutPageTest extends BaseTest {


    HomePage homePage;
    LogoutPage logoutPage;



    @BeforeClass
    public void setUp_LogoutPage() {
        // Navigate to the desired URL
        driver.get("https://demo.guru99.com/V1/html/Managerhomepage.php");

        // Wait for the account link to be present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement min_statement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[3]/div/ul/li[8]/a")));
        // Scroll the element into view using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", min_statement);
        // Use JavaScript to click the account link
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", min_statement);
        logoutPage = new LogoutPage(driver);
    }

    @Story("Logout Feature Is Redirected Correctly To Login Page")
    @Description("Test Steps:\n\n 1- Navigate Home Page \n 2- Click On Logout Feature ")
    @Test
    public void validate_logout_button_is_working_correctly() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Locate and click the logout button
            logoutPage.locate_logoutButton();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutPage.getLogoutElement());

            // Wait for the alert to be present and capture its text
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();

            // Accept the alert
            alert.accept();

            // Validate the alert text
            String expectedAlertText = "You Have Succesfully Logged Out!!";
            Assert.assertEquals(alertText, expectedAlertText, "The alert text is not as expected.");

            // Validate redirection to the login page
            String expectedLoginUrl = "https://demo.guru99.com/V1/index.php"; // Update with the actual login URL
            wait.until(ExpectedConditions.urlToBe(expectedLoginUrl)); // Wait until the URL matches
            String actualUrl = driver.getCurrentUrl();
            Assert.assertEquals(actualUrl, expectedLoginUrl, "The URL after logout is not the login page.");

            // Optional: Validate a specific element on the login form
            WebElement loginForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("uid"))); // Example for username field
            Assert.assertTrue(loginForm.isDisplayed(), "Login form is not displayed after logout.");

        } catch (UnhandledAlertException e) {
            // Handle unexpected alerts gracefully
            Alert alert = driver.switchTo().alert();
            System.out.println("Unexpected alert: " + alert.getText());
            alert.accept();
            Assert.fail("Unexpected alert appeared: " + e.getMessage());
        } catch (TimeoutException e) {
            Assert.fail("Alert or redirection did not occur within the expected time: " + e.getMessage());
        }
    }

}
