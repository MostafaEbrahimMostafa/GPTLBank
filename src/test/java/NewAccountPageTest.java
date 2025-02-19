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

@Feature("New Account For An Existing Customer")
public class NewAccountPageTest extends BaseTest {

    HomePage homePage;
    NewAccountPage newAccountPage;



    @BeforeClass
    public void setUp_NewAccountPage() {
        // Navigate to the desired URL
        driver.get("https://demo.guru99.com/V1/html/Managerhomepage.php");

        // Wait for the account link to be present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement account = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[3]/div/ul/li[5]/a")));

        // Scroll the element into view using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", account);

        // Use JavaScript to click the account link
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", account);
        newAccountPage = new NewAccountPage(driver);
    }



    @Story("New Customer Account With Empty Customer Id Field")
    @Description("Test Steps:\n\n1- Navigate New Account Page\n2- Leave Customer Id Field Empty\n3- Click On Submit Button")
    @Test(priority = 1)
    public void validate_newAccount_with_empty_CustomerID() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(100)); // Shortened wait time with faster polling

        try {
            // Locate the customer ID input field
            newAccountPage.locate_customer_id();

            // Enter an empty Customer ID
            newAccountPage.EnterCustomerID("");

            // Ensure error message appears
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message14")));
            Assert.assertEquals(errorMessage.getText(), "Customer ID is required", "Error message does not match!");

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

    @Story("New Customer Account With Character in Customer Id Field ")
    @Description("Test Steps:\n\n1- Navigate New Account Page\n2- Enter Character in Customer Id Field\n3- Click On Submit Button")
    @Test(priority = 2)
    public void validate_newAccount_with_characters_in_CustomerID()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(100)); // Shortened wait time with faster polling

        try {
            // Locate the customer ID input field
            newAccountPage.locate_customer_id();

            // Enter an empty Customer ID
            newAccountPage.EnterCustomerID("abc");

            // Ensure error message appears
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message14")));
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

    @Story("New Customer Account With Special Character in  Customer Id Field ")
    @Description("Test Steps:\n\n1- Navigate New Account Page\n2- Enter Special Character in Customer Id Field\n3- Click On Submit Button")
    @Test(priority = 3)
    public void validate_newAccount_with_specialCharacter_in_CustomerID()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(100)); // Shortened wait time with faster polling

        try {
            // Locate the customer ID input field
            newAccountPage.locate_customer_id();

            // Enter an empty Customer ID
            newAccountPage.EnterCustomerID("");

            // Ensure error message appears
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message14")));
            Assert.assertEquals(errorMessage.getText(), "Special character are not allowed", "Error message does not match!");

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

    @Story("New Customer Account With First Character Have Space In Customer Id Field")
    @Description("Test Steps:\n\n1- Navigate New Account Page\n2- Enter First Character Have Space In Customer Id Field \n3- Click On Submit Button")
    @Test(priority = 6)
    public void validate_newAccount_that_firstCharacter_cannot_Have_space_in_CustomerID() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(100)); // Shortened wait time with faster polling

        try {
            // Locate the customer ID input field
            newAccountPage.locate_customer_id();

            // Enter an empty Customer ID
            newAccountPage.EnterCustomerID("@@#");

            // Ensure error message appears
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message14")));
            Assert.assertEquals(errorMessage.getText(), "First character cannot have space", "Error message does not match!");

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


    @Story("New Customer Account With Invalid Initial Deposit Field ")
    @Description("Test Steps:\n\n1- Navigate New Account Page\n2- Enter Invalid Initial Deposit Field\n3- Click On Submit Button")
    @Test(priority = 4)
    public void validate_initial_Deposit_is_not_less_than_500LE()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(100)); // Shortened wait time with faster polling

        try {
            // Locate the customer ID input field
            newAccountPage.locateIntailDeposit();
            // Enter an empty Customer ID
            newAccountPage.EnterIntialDeposit("400");

            // Ensure error message appears
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message14")));
            Assert.assertEquals(errorMessage.getText(), "Initial deposit must be at least 500", "Error message does not match!");

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

    @Story("Reset Functionality In New Account Page Is Working Correctly")
    @Description("Test Steps:\n\n1- Navigate New Account Page\n2- Enter All Data Fields\n3- Click On Reset Button")
    @Test(priority = 5)
    public void validate_that_reset_functionality_is_working_correctly() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // Reduced wait time
        newAccountPage.locate_customer_id();
        newAccountPage.EnterCustomerID("100");

        // gender
        newAccountPage.locate_account_type();
        newAccountPage.EnterAccountTpye("current");


        newAccountPage.locateIntailDeposit();
        newAccountPage.EnterIntialDeposit("600");


        // Ensure the reset button is interactable and click it
        WebElement resetButton = driver.findElement(By.xpath("//input[@type='reset']")); // Corrected locator
        Assert.assertTrue(resetButton.isDisplayed() && resetButton.isEnabled(), "Reset button is not interactable");
        resetButton.click();

        // Validate that the Customer ID field is cleared
        WebElement customerIdField = driver.findElement(By.xpath("//input[@name='cusid']"));
        Assert.assertEquals(customerIdField.getAttribute("value"), "", "Customer ID field was not reset");
    }

    @Story("Account Is Created Successfully")
    @Description("Test Steps:\n\n1- Navigate New Account Page\n2- Enter All Valid Data Fields\n3- Click On Submit Button")
    @Test(priority = 7)
    public void validate_that_Account_created_successfully()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // customer name
        newAccountPage.locate_customer_id();
        newAccountPage.EnterCustomerID("100");

        // gender
        newAccountPage.locate_account_type();
        newAccountPage.EnterAccountTpye("current");


        newAccountPage.locateIntailDeposit();
        newAccountPage.EnterIntialDeposit("600");


        // Ensure submit button is interactable
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);

        // Click submit button
        submitButton.click();

        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent()); // Wait until alert is present
            String error = alert.getText();
            alert.accept();
            // Assert the alert message
            String expected = "Account Created Successfully!!!"; // Adjust based on actual behavior
            Assert.assertEquals(error, expected, "Alert message does not match expected.");
        } catch (org.openqa.selenium.TimeoutException e) {
            Assert.fail("No alert was present");
        }
    }









}
