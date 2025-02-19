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
@Feature("Delete An Account For A Customer")
public class DeleteAccountPageTest extends BaseTest{

    DeleteAccountPage deleteAccountPage;


    @BeforeClass
    public void setUp_DeleteAccountPage() {
        // Navigate to the desired URL
        driver.get("https://demo.guru99.com/V1/html/Managerhomepage.php");

        // Wait for the account link to be present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement delete_account = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[3]/div/ul/li[7]/a")));

        // Scroll the element into view using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", delete_account);

        // Use JavaScript to click the account link
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", delete_account);
        deleteAccountPage = new DeleteAccountPage(driver);
    }

    @Story("Delete Customer Account With Empty Account Number Field ")
    @Description("Test Steps:\n\n1- Navigate Delete Account Page\n2- Leave Account Number Field Empty\n3- Click On Submit Button")
    @Test(priority = 1)
    public void validate_DeleteAccount_with_empty_AccountNumber() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(100)); // Shortened wait time with faster polling

        try {
            // Locate the customer ID input field
            deleteAccountPage.locate_AccountNumber();

            // Enter an empty Customer ID
            deleteAccountPage.EnterAccountNumber("");

            // Ensure error message appears
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message2")));
            Assert.assertEquals(errorMessage.getText(), "Account Number must not be blank", "Error message does not match!");

            // Ensure submit button is interactable
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
            submitButton.click();

            // Ensure alert appears
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertMessage = alert.getText();
            alert.accept(); // Accept the alert
            Assert.assertEquals(alertMessage, "Please fill all fields", "Alert message does not match!");

        } catch (TimeoutException e) {
            Assert.fail("Expected error message or alert did not appear: " + e.getMessage());
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }

    @Story("Delete Customer Account With Character in Account Number Field ")
    @Description("Test Steps:\n\n1- Navigate Delete Account Page\n2- Enter Character in Account Number Field\n3- Click On Submit Button")
    @Test(priority = 2)
    public void validate_DeleteAccount_with_characters_in_AccountNumber()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(100)); // Shortened wait time with faster polling

        try {
            // Locate the customer ID input field
            deleteAccountPage.locate_AccountNumber();

            // Enter an empty Customer ID
            deleteAccountPage.EnterAccountNumber("abc");

            // Ensure error message appears
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message2")));
            Assert.assertEquals(errorMessage.getText(), "Characters are not allowed", "Error message does not match!");

            // Ensure submit button is interactable
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
            submitButton.click();

            // Ensure alert appears
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertMessage = alert.getText();
            alert.accept(); // Accept the alert
            Assert.assertEquals(alertMessage, "Please fill all fields", "Alert message does not match!");

        } catch (TimeoutException e) {
            Assert.fail("Expected error message or alert did not appear:" + e.getMessage());
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }

    @Story("Delete Customer Account With Special Character in Account Number Field ")
    @Description("Test Steps:\n\n1- Navigate Delete Account Page\n2- Enter Special Character in Account Number Field\n3- Click On Submit Button")
    @Test(priority = 3)
    public void validate_nDeleteAccount_with_specialCharacter_in_AccountNumber()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(100)); // Shortened wait time with faster polling

        try {
            // Locate the customer ID input field
            deleteAccountPage.locate_AccountNumber();

            // Enter an empty Customer ID
            deleteAccountPage.EnterAccountNumber("#@1");

            // Ensure error message appears
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message2")));
            Assert.assertEquals(errorMessage.getText(), "Special characters are not allowed", "Error message does not match!");

            // Ensure submit button is interactable
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
            submitButton.click();

            // Ensure alert appears
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertMessage = alert.getText();
            alert.accept(); // Accept the alert
            Assert.assertEquals(alertMessage, "Please fill all fields", "Alert message does not match!");

        } catch (TimeoutException e) {
            Assert.fail("Expected error message or alert did not appear: " + e.getMessage());
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }

    @Story("Reset Functionality In Delete Account Page Is Working Correctly")
    @Description("Test Steps:\n\n1- Navigate Delete Account Page\n2- Enter Account Number Field\n3- Click On Reset Button")
    @Test(priority = 4)
    public void validate_that_reset_functionality_is_working_correctly() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // Reduced wait time

        // Locate and enter a Customer ID

        deleteAccountPage.locate_AccountNumber();

        // Enter an empty Customer ID
        deleteAccountPage.EnterAccountNumber("100");
        // Ensure the reset button is interactable and click it
        WebElement resetButton = driver.findElement(By.xpath("//input[@type='reset']")); // Corrected locator
        Assert.assertTrue(resetButton.isDisplayed() && resetButton.isEnabled(), "Reset button is not interactable");
        resetButton.click();

        // Validate that the Customer ID field is cleared
        WebElement account = driver.findElement(By.xpath("//input[@name='accountno']"));
        Assert.assertEquals(account.getAttribute("value"), "", "Account Number field was not reset");
    }

    @Story("Account Is Deleted Successfully")
    @Description("Test Steps:\n\n1- Navigate Delete Account Page\n2- Enter Valid Account Number Field\n3- Click On Submit Button")
    @Test(priority = 5)
    public void validate_that_account_is_deleted_successfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Step 1: Locate and fill in the Customer ID input field
            deleteAccountPage.locate_AccountNumber();
            deleteAccountPage.EnterAccountNumber("123"); // Enter a valid Customer ID without leading/trailing spaces

            // Step 2: Locate and click the submit button
            deleteAccountPage.locate_submitButton();
            deleteAccountPage.ClickOn_submitButton();
            // Handle the success confirmation message or redirect

        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent()); // Wait until alert is present
            String error = alert.getText();
            alert.accept();

            // Assert the alert message
            String expected = "Account Deleted Successfully!!!"; // Adjust based on actual behavior
            Assert.assertEquals(error, expected, "Alert message does not match expected.");
        } catch (org.openqa.selenium.TimeoutException e) {
            Assert.fail("No alert was present");
        }
    }


}
