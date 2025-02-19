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

@Feature("Customized statement allows you to filter and display transactions in an account based on date, transaction value")
public class CustomizedStatementPageTest extends BaseTest{


    HomePage homePage;
    CustomizedStatementPage customizedStatementPage;


    @BeforeClass
    public void setUp_CustomizedPage() {
        // Navigate to the desired URL
        driver.get("https://demo.guru99.com/V1/html/Managerhomepage.php");

        // Wait for the account link to be present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement min_statement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[3]/div/ul/li[9]/a")));

        // Scroll the element into view using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", min_statement);

        // Use JavaScript to click the account link
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", min_statement);
        customizedStatementPage = new CustomizedStatementPage(driver);
    }

    @Story("Customized Statement With Empty Account Number Field")
    @Description("Test Steps:\n\n 1- Navigate Customized Statement Page\n2- Leave Account Number Field Empty\n3- Click On Submit Button")
    @Test(priority = 1)
    public void validate_customizedStatement_with_empty_AccountNumber() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(100)); // Shortened wait time with faster polling

        try {
            // Locate the customer ID input field
            customizedStatementPage.locate_AccountNumber();

            // Enter an empty Customer ID
            customizedStatementPage.EnterAccountNumber("");

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

    @Story("Customized Statement With Character in Account Number Field")
    @Description("Test Steps:\n\n 1- Navigate Customized Statement Page\n2- Enter Character in Account Number Field\n3- Click On Submit Button")
    @Test(priority = 2)
    public void validate_customizedStatement_with_characters_in_AccountNumber()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(100)); // Shortened wait time with faster polling

        try {
            // Locate the customer ID input field
            customizedStatementPage.locate_AccountNumber();

            // Enter an empty Customer ID
            customizedStatementPage.EnterAccountNumber("abc");

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

    @Story("Customized Statement With Special Character in Account Number Field")
    @Description("Test Steps:\n\n 1- Navigate Customized Statement Page\n2- Enter Special Character in Account Number Field\n3- Click On Submit Button")
    @Test(priority = 3)
    public void validate_customizedStatement_with_specialCharacter_in_AccountNumber()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(100)); // Shortened wait time with faster polling

        try {
            // Locate the customer ID input field
            customizedStatementPage.locate_AccountNumber();

            // Enter an empty Customer ID
            customizedStatementPage.EnterAccountNumber("#@1");

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
            Assert.fail("Expected error message or alert did not appear within the timeout: " + e.getMessage());
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }




    @Story("Customized Statement With Empty Amount Lower Limit Field")
    @Description("Test Steps:\n\n 1- Navigate Customized Statement Page\n2- Leave Amount Lower Limit Field Empty\n3- Click On Submit Button")
    @Test(priority = 4)
    public void validate_customizedStatement_with_empty_AmountLimit() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(100)); // Shortened wait time with faster polling

        try {
            customizedStatementPage.locate_AccountNumber();
            customizedStatementPage.EnterAccountNumber("100");
            customizedStatementPage.locate_fromDate();
            customizedStatementPage.locate_toDate();
            customizedStatementPage.EnterFromDate("12/12/2024");
            customizedStatementPage.EnterToDate("11/12/2024");
            customizedStatementPage.locate_amount();
            customizedStatementPage.EnterAmount("");
            customizedStatementPage.locate_NofTransaction();
            customizedStatementPage.EnterNumberOfTransaction("4");

            // Ensure error message appears
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message2")));
            Assert.assertEquals(errorMessage.getText(), "Amount Lower Limit must not be blank", "Error message does not match!");

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
            Assert.fail("Expected error message And alert did not appear : " + e.getMessage());
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }

    @Story("Customized Statement With Character in Amount Lower Limit Field")
    @Description("Test Steps:\n\n 1- Navigate Customized Statement Page\n2- Enter Character in Amount Lower Limit Field\n3- Click On Submit Button")
    @Test(priority = 5)
    public void validate_customizedStatement_with_characters_in_AmountLimit()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(100)); // Shortened wait time with faster polling

        try {
            customizedStatementPage.locate_AccountNumber();
            customizedStatementPage.EnterAccountNumber("100");
            customizedStatementPage.locate_fromDate();
            customizedStatementPage.locate_toDate();
            customizedStatementPage.EnterFromDate("01/05/2000");
            customizedStatementPage.EnterToDate("01/05/2002");
            customizedStatementPage.locate_amount();
            customizedStatementPage.EnterAmount("abc");
            customizedStatementPage.locate_NofTransaction();
            customizedStatementPage.EnterNumberOfTransaction("4");

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
            Assert.fail("Expected error message And alert did not appear : " + e.getMessage());
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }

    @Story("Customized Statement With Special Character in Amount Lower Limit Field")
    @Description("Test Steps:\n\n 1- Navigate Customized Statement Page\n2- Enter Special Character in Amount Lower Limit Field\n3- Click On Submit Button")
    @Test(priority = 6)
    public void validate_customizedStatement_with_specialCharacter_in_AmountLimit()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(100)); // Shortened wait time with faster polling

        try {
            customizedStatementPage.locate_AccountNumber();
            customizedStatementPage.EnterAccountNumber("100");
            customizedStatementPage.locate_fromDate();
            customizedStatementPage.locate_toDate();
            customizedStatementPage.EnterFromDate("01/05/2000");
            customizedStatementPage.EnterToDate("01/05/2002");
            customizedStatementPage.locate_amount();
            customizedStatementPage.EnterAmount("@#100");
            customizedStatementPage.locate_NofTransaction();
            customizedStatementPage.EnterNumberOfTransaction("4");

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
            Assert.fail("Expected error message And alert did not appear" + e.getMessage());
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }

    @Story("Customized Statement With Empty Number Of Transaction Field")
    @Description("Test Steps:\n\n 1- Navigate Customized Statement Page\n2- Leave Number Of Transaction Field Empty\n3- Click On Submit Button")
    @Test(priority = 7)
    public void validate_customizedStatement_with_empty_Number_Of_Transaction() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(100)); // Shortened wait time with faster polling

        try {
            customizedStatementPage.locate_AccountNumber();
            customizedStatementPage.EnterAccountNumber("100");
            customizedStatementPage.locate_fromDate();
            customizedStatementPage.locate_toDate();
            customizedStatementPage.EnterFromDate("01/05/2000");
            customizedStatementPage.EnterToDate("01/05/2002");
            customizedStatementPage.locate_amount();
            customizedStatementPage.EnterAmount("5000");
            customizedStatementPage.locate_NofTransaction();
            customizedStatementPage.EnterNumberOfTransaction("");

            // Ensure error message appears
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message2")));
            Assert.assertEquals(errorMessage.getText(), "Number of Transaction must not be blank", "Error message does not match!");

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
            Assert.fail("Expected error message And alert did not appear: " + e.getMessage());
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }

    @Story("Customized Statement With Character in Number Of Transaction Field")
    @Description("Test Steps:\n\n 1- Navigate Customized Statement Page\n2- Enter Character in Number Of Transaction Field\n3- Click On Submit Button")
    @Test(priority = 8)
    public void validate_customizedStatement_with_characters_in_Number_Of_Transaction()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(100)); // Shortened wait time with faster polling

        try {
            customizedStatementPage.locate_AccountNumber();
            customizedStatementPage.EnterAccountNumber("100");
            customizedStatementPage.locate_fromDate();
            customizedStatementPage.locate_toDate();
            customizedStatementPage.EnterFromDate("01/05/2000");
            customizedStatementPage.EnterToDate("01/05/2002");
            customizedStatementPage.locate_amount();
            customizedStatementPage.EnterAmount("5000");
            customizedStatementPage.locate_NofTransaction();
            customizedStatementPage.EnterNumberOfTransaction("abc");

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
            Assert.fail("Expected error message And alert did not appear : " + e.getMessage());
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }

    @Story("Customized Statement With Special Character in Number Of Transaction Field")
    @Description("Test Steps:\n\n 1- Navigate Customized Statement Page\n2- Enter Special Character in Number Of Transaction Field\n3- Click On Submit Button")
    @Test(priority = 9)
    public void validate_customizedStatement_with_specialCharacter_in_Number_Of_Transaction()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(100)); // Shortened wait time with faster polling

        try {
            customizedStatementPage.locate_AccountNumber();
            customizedStatementPage.EnterAccountNumber("100");
            customizedStatementPage.locate_fromDate();
            customizedStatementPage.locate_toDate();
            customizedStatementPage.EnterFromDate("01/05/2000");
            customizedStatementPage.EnterToDate("01/05/2002");
            customizedStatementPage.locate_amount();
            customizedStatementPage.EnterAmount("5000");
            customizedStatementPage.locate_NofTransaction();
            customizedStatementPage.EnterNumberOfTransaction("@#4");

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
            Assert.fail("Expected error message And alert did not appear: " + e.getMessage());
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }

    @Story("Reset Functionality In Customized Statement Page Is Working Correctly")
    @Description("Test Steps:\n\n 1- Navigate Customized Statement Page\n2- Enter All Data Fields\n3- Click On Reset Button")
    @Test(priority = 10)
    public void validate_that_reset_functionality_is_working_correctly() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // Reduced wait time

        customizedStatementPage.locate_AccountNumber();
        customizedStatementPage.EnterAccountNumber("100");
        customizedStatementPage.locate_fromDate();
        customizedStatementPage.EnterFromDate("01/05/2000");
        customizedStatementPage.locate_toDate();
        customizedStatementPage.EnterToDate("01/05/2002");
        customizedStatementPage.locate_amount();
        customizedStatementPage.EnterAmount("5000");
        customizedStatementPage.locate_NofTransaction();
        customizedStatementPage.EnterNumberOfTransaction("4");
        // Ensure the reset button is interactable and click it
        WebElement resetButton = driver.findElement(By.xpath("//input[@type='reset']")); // Corrected locator
        Assert.assertTrue(resetButton.isDisplayed() && resetButton.isEnabled(), "Reset button is not interactable");
        resetButton.click();

        // Validate that the Customer ID field is cleared
        WebElement account = driver.findElement(By.xpath("//input[@name='accountno']"));
        Assert.assertEquals(account.getAttribute("value"), "", "Account Number field was not reset");
    }

    @Story("Filtering And Displaying Transactions Are Working Correctly")
    @Description("Test Steps:\n\n 1- Navigate Customized Statement Page\n2- Enter All Valid Data Fields\n3- Click On Submit Button")
    @Test(priority = 11)
    public void validate_that_customizedStatement_functionality_is_working_correctly()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        customizedStatementPage.locate_AccountNumber();
        customizedStatementPage.EnterAccountNumber("100");
        customizedStatementPage.locate_fromDate();
        customizedStatementPage.locate_toDate();
        customizedStatementPage.EnterFromDate("01/05/2000");
        customizedStatementPage.EnterToDate("01/05/2002");
        customizedStatementPage.locate_amount();
        customizedStatementPage.EnterAmount("5000");
        customizedStatementPage.locate_NofTransaction();
        customizedStatementPage.EnterNumberOfTransaction("4");

        // Step 2: Locate and click the submit button
        customizedStatementPage.locate_submitButton();
        customizedStatementPage.ClickOn_submitButton();

        // Step 3: Wait for redirection or the new page to load
        wait.until(ExpectedConditions.urlContains("BalanceEnquiry.php"));

        // Step 4: Validate that we are on the Edit Customer Form page
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("BalanceEnquiry.php"), "Not redirected to Balance Enquiry!");
    }







}
