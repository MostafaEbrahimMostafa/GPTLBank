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
@Feature("Edit Account Details For An Existing Account")
public class EditAccountPageTest extends BaseTest {


    EditAccountPage editAccountPage;


    @BeforeClass
    public void setUp_EditAccountPage() {
        // Navigate to the desired URL
        driver.get("https://demo.guru99.com/V1/html/Managerhomepage.php");

        // Wait for the account link to be present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement account = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[3]/div/ul/li[6]/a")));

        // Scroll the element into view using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", account);

        // Use JavaScript to click the account link
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", account);
        editAccountPage = new EditAccountPage(driver);
    }

    @Story("Edit Customer Account With Empty Account Number Field")
    @Description("Test Steps:\n\n1- Navigate Edit Account Page\n2- Leave Account Number Field Empty\n3- Click On Submit Button")
    @Test(priority = 1)
    public void validate_newAccount_with_empty_AccountNumber() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(100)); // Shortened wait time with faster polling

        try {
            // Locate the customer ID input field
            editAccountPage.locate_AccountNumber();

            // Enter an empty Customer ID
            editAccountPage.EnterAccountNumber("");

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
            Assert.fail("Expected error message or alert did not appear within the timeout: " + e.getMessage());
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }

    @Story("Edit Customer Account With Character in  Account Number Field ")
    @Description("Test Steps:\n\n1- Navigate Edit Account Page\n2- Enter Character in Account Number Field\n3- Click On Submit Button")
    @Test(priority = 2)
    public void validate_newAccount_with_characters_in_AccountNumber() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(100)); // Shortened wait time with faster polling

        try {
            // Locate the customer ID input field
            editAccountPage.locate_AccountNumber();

            // Enter an empty Customer ID
            editAccountPage.EnterAccountNumber("abc");

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
            Assert.fail("Expected error message or alert did not appear within the timeout: " + e.getMessage());
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }

    @Story("Edit Customer Account With Special Character in Account Number Field ")
    @Description("Test Steps:\n\n1- Navigate Edit Account Page\n2- Enter Special Character in Account Number Field\n3- Click On Submit Button")
    @Test(priority = 3)
    public void validate_newAccount_with_specialCharacter_in_AccountNumber() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(100)); // Shortened wait time with faster polling

        try {
            // Locate the customer ID input field
            editAccountPage.locate_AccountNumber();

            // Enter an empty Customer ID
            editAccountPage.EnterAccountNumber("#@1");

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
            Assert.fail("Expected error message or alert did not appear:" + e.getMessage());
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }

    @Story("Reset Functionality In Edit Account Page Is Working Correctly")
    @Description("Test Steps:\n\n1- Navigate Edit Account Page\n2- Enter Account Number Field\n3- Click On Reset Button")
    @Test(priority = 4)
    public void validate_that_reset_functionality_is_working_correctly() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // Reduced wait time

        // Locate and enter a Customer ID

        editAccountPage.locate_AccountNumber();

        // Enter an empty Customer ID
        editAccountPage.EnterAccountNumber("100");
        // Ensure the reset button is interactable and click it
        WebElement resetButton = driver.findElement(By.xpath("//input[@type='reset']")); // Corrected locator
        Assert.assertTrue(resetButton.isDisplayed() && resetButton.isEnabled(), "Reset button is not interactable");
        resetButton.click();

        // Validate that the Customer ID field is cleared
        WebElement account = driver.findElement(By.xpath("//input[@name='accountno']"));
        Assert.assertEquals(account.getAttribute("value"), "", "Account Number field was not reset");
    }

    @Story("Account Is Updated Successfully")
    @Description("Test Steps:\n\n1- Navigate Edit Account Page\n2- Enter Valid Account Number Field\n3- Click On Submit Button")
    @Test(priority = 5)
    public void validate_editCustomer_redirects_to_AccountForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Step 1: Locate and fill in the Customer ID input field
            editAccountPage.locate_AccountNumber();
            editAccountPage.EnterAccountNumber("123"); // Enter a valid Customer ID without leading/trailing spaces

            // Step 2: Locate and click the submit button
            editAccountPage.locate_submitButton();
            editAccountPage.ClickOn_submitButton();

            // Step 3: Wait for redirection or the new page to load
            wait.until(ExpectedConditions.urlContains("EditAccountForm.php"));

            // Step 4: Validate that we are on the Edit Customer Form page
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("EditAccountForm.php"), "Not redirected to Edit Customer Form!");

            // Optional: Check for specific elements on the form
            WebElement customerNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='selaccount']")));
            Assert.assertTrue(customerNameField.isDisplayed(), "Account Type field is not displayed!");

        } catch (TimeoutException e) {
            Assert.fail("Redirection to Account Form timed out: " + e.getMessage());
        }
    }
}



