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
@Feature("Adding New Customer")
public class NewCustomerPageTest extends BaseTest {

    HomePage homePage;
    NewCustomerPage newCustomerPage;


    @BeforeClass
    public void setUp_NewCustomerPage() {
        homePage = new HomePage(driver);
        homePage.locate_newCustomer();
        homePage.ClickOn_newCustomer_button();
        newCustomerPage = new NewCustomerPage(driver);  // Initialize LoginPage object
    }

    private void validateFieldWithError(
            String fieldXPath,
            String inputValue,
            String expectedErrorMessage,
            String errorElementId,
            String alertMessageIfAny
    ) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate the field
        WebElement field = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(fieldXPath)));
        wait.until(ExpectedConditions.visibilityOf(field));
        wait.until(ExpectedConditions.elementToBeClickable(field));

        // Clear the field and enter the value
        field.clear();
        field.sendKeys(inputValue);

        // Simulate losing focus to trigger validation
        field.sendKeys(Keys.TAB);

        // Locate the error message element
        WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(errorElementId)));

        // Assert the error message
        Assert.assertEquals(errorMessageElement.getText(), expectedErrorMessage, "Error message does not match!");

        // Simulate alert if needed
        ((JavascriptExecutor) driver).executeScript("alert(arguments[0])", alertMessageIfAny);

        // Handle alert
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String actualAlertMessage = alert.getText();
        alert.accept();

        // Assert alert message
        Assert.assertEquals(actualAlertMessage, alertMessageIfAny, "Alert message does not match!");

        try {
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        submitButton.click();

        // Ensure alert appears
        Alert a = wait.until(ExpectedConditions.alertIsPresent());
        String alertMessage = a.getText();
        a.accept(); // Accept the alert
        Assert.assertEquals(alertMessage, "Please fill all fields", "Alert message does not match!");

    } catch (TimeoutException e) {
        Assert.fail("Expected error message or alert did not appear within the timeout: " + e.getMessage());
    } catch (Exception e) {
        Assert.fail("Test failed due to unexpected error: " + e.getMessage());
    }

    }


    //  4 Test Cases On Customer Name Field.
    @Story("New Customer With Empty Customer Name Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Leave Customer Name Field Empty\n3- Click On Submit Button")
    @Test(priority = 1)
    public void validate_newCustomer_with_empty_CustomerName() {
        validateFieldWithError(
                "//input[@name='name']",
                "",
                "Customer name must not be blank",
                "message",
                "Please fill all fields"
        );

    }

    @Story("New Customer With Numbers In Customer Name Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Enter Numbers In Customer Name Field \n3- Click On Submit Button")
    @Test(priority = 2)
    public void validate_NewCustomer_with_numbers_in_customerName()
    {
        validateFieldWithError(
                "//input[@name='name']",
                "12316",
                "Numbers are not allowed",
                "message",
                "Please fill all fields"
        );
    }

    @Story("New Customer With Special Character In Customer Name Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Enter Special Character In Customer Name Field \n3- Click On Submit Button")
    @Test(priority = 3)
    public void validate_newCustomer_with_specialCharacters_in_CustomerName() {
        validateFieldWithError(
                "//input[@name='name']",
                "Mostafa@kady!",
                "Special characters are not allowed",
                "message",
                "Please fill all fields"
        );
    }

    @Story("New Customer With First Character Have Space In Customer Name Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Enter First Character Have Space In Customer Name Field \n3- Click On Submit Button")
    @Test(priority = 4)
    public void validate_newCustomer_that_firstCharacter_cannot_Have_space_in_CustomerName() {
        validateFieldWithError(
                "//input[@name='name']",
                " mostafakady",
                "First character cannot be a space",
                "message",
                "Please fill all fields"
        );
    }


    //  4 Test Cases On Address Field.
    @Story("New Customer With Empty Address Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Leave Address Field Empty\n3- Click On Submit Button")
    @Test(priority = 5)
    public void validate_newCustomer_with_empty_Address() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
        WebElement addressField = driver.findElement(By.xpath("//textarea[@name='addr']"));
        newCustomerPage.locate_customerName();
        newCustomerPage.EnterCustomerName("MostafaIbrahim");
        wait.until(ExpectedConditions.visibilityOf(addressField));
        wait.until(ExpectedConditions.elementToBeClickable(addressField));
        // Clear the field and enter an address with special characters
        addressField.clear();
        addressField.clear();

        // Simulate losing focus to trigger validation
        addressField.sendKeys(Keys.TAB);

        // Ensure error message appears
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        Assert.assertEquals(errorMessage.getText() , "Address Field must not be blank", "Error message does not match!");

            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
            submitButton.click();

            // Ensure alert appears
            Alert a = wait.until(ExpectedConditions.alertIsPresent());
            String alertMessage = a.getText();
            a.accept(); // Accept the alert
            Assert.assertEquals(alertMessage, "Please fill all fields", "Alert message does not match!");

        } catch (TimeoutException e) {
            Assert.fail("Expected error message or alert did not appear within the timeout: " + e.getMessage());
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }

    @Story("New Customer With Special Character In Address Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Enter Special Character In Address Field \n3- Click On Submit Button")
    @Test(priority = 6)
    public void validate_newCustomer_with_specialCharacters_in_Address() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
       try {
           WebElement addressField = driver.findElement(By.xpath("//textarea[@name='addr']"));
           newCustomerPage.locate_customerName();
           newCustomerPage.EnterCustomerName("MostafaIbrahim");
        // Ensure the address field is visible and interactable
        wait.until(ExpectedConditions.visibilityOf(addressField));
        wait.until(ExpectedConditions.elementToBeClickable(addressField));

        // Clear the field and enter an address with special characters
        addressField.clear();
        addressField.sendKeys("123 Main St@!");

        // Simulate losing focus to trigger validation
        addressField.sendKeys(Keys.TAB);

        // Locate the error message element
        WebElement actualMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))); // Replace with actual ID

        // Assert the error message
        Assert.assertEquals(actualMessage.getText(), "Special characters are not allowed in address", "Error message does not match!");
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
            submitButton.click();

            // Ensure alert appears
            Alert a = wait.until(ExpectedConditions.alertIsPresent());
            String alertMessage = a.getText();
            a.accept(); // Accept the alert
            Assert.assertEquals(alertMessage, "Please fill all fields", "Alert message does not match!");

        } catch (TimeoutException e) {
            Assert.fail("Expected error message or alert did not appear: " + e.getMessage());
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }

    @Story("New Customer With First Character Have Space In Address Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Enter First Character Have Space In Address Field \n3- Click On Submit Button")
    @Test(priority = 7)
    public void validate_newCustomer_that_firstCharacter_cannot_Have_space_in_Address() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            WebElement addressField = driver.findElement(By.xpath("//textarea[@name='addr']"));
            newCustomerPage.locate_customerName();
            newCustomerPage.EnterCustomerName("MostafaIbrahim");
        // Ensure the address field is visible and interactable
        wait.until(ExpectedConditions.visibilityOf(addressField));
        wait.until(ExpectedConditions.elementToBeClickable(addressField));

        // Clear the field and enter an address starting with a space
        addressField.clear();
        addressField.sendKeys(" 123 Main Street");

        // Simulate losing focus to trigger validation
        addressField.sendKeys(Keys.TAB);

        // Locate the error message element
       WebElement actualMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))); // Replace with actual ID

        // Assert the error message
        Assert.assertEquals(actualMessage.getText(), "First character cannot be a space", "Error message does not match!");

            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
            submitButton.click();

            // Ensure alert appears
            Alert a = wait.until(ExpectedConditions.alertIsPresent());
            String alertMessage = a.getText();
            a.accept(); // Accept the alert
            Assert.assertEquals(alertMessage, "Please fill all fields", "Alert message does not match!");

        } catch (TimeoutException e) {
            Assert.fail("Expected error message or alert did not appear:" + e.getMessage());
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }


    //  4 Test Cases On City Field.
    @Story("New Customer With Empty City Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Leave City Field Empty\n3- Click On Submit Button")
    @Test(priority = 8)
    public void validate_newCustomer_with_empty_City() {
        validateFieldWithError(
                "//input[@name='city']",       // XPath of the city field
                "",                            // Input value (empty in this case)
                "City Field must be not blank", // Expected error message
                "message4",                    // ID of the error message element
                "Please fill all fields"       // Alert message
        );
    }

    @Story("New Customer With Numbers In City Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Enter Numbers In City Field \n3- Click On Submit Button")
    @Test(priority = 9)
    public void validate_newCustomer_with_numbers_in_city() {
        validateFieldWithError(
                "//input[@name='city']",       // XPath of the city field
                "1245",                        // Input value (numbers in this case)
                "Numbers are not allowed",     // Expected error message
                "message4",                    // ID of the error message element
                "Please fill all fields"       // Alert message
        );
    }

    @Story("New Customer With Special Character In City Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Enter Special Character In City Field \n3- Click On Submit Button")
    @Test(priority = 10)
    public void validate_newCustomer_with_specialCharacters_in_city() {
        validateFieldWithError(
                "//input[@name='city']",          // XPath of the city field
                "!@#$%^&*",                       // Input value (special characters)
                "Special characters are not allowed", // Expected error message
                "message4",                       // ID of the error message element
                "Please fill all fields"          // Alert message
        );
    }

    @Story("New Customer With First Character Have Space In City Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Enter First Character Have Space In City Field \n3- Click On Submit Button")
    @Test(priority = 11)
    public void validate_newCustomer_that_firstCharacter_cannot_Have_space_in_city() {
        validateFieldWithError(
                "//input[@name='city']",          // XPath of the city field
                " CityName",                      // Input value (space as the first character)
                "First character cannot have space", // Expected error message
                "message4",                       // ID of the error message element
                "Please fill all fields"          // Alert message
        );
    }


    //  4 Test Cases On State Field.
    @Story("New Customer With Empty State Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Leave State Field Empty\n3- Click On Submit Button")
    @Test(priority = 12)
    public void validate_newCustomer_with_empty_State() {
        validateFieldWithError(
                "//input[@name='state']",      // XPath of the state field
                "",                            // Input value (empty in this case)
                "State must not be blank",     // Expected error message
                "message5",                    // ID of the error message element
                "Please fill all fields"       // Alert message
        );
    }

    @Story("New Customer With Numbers In State Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Enter Numbers In State Field \n3- Click On Submit Button")
    @Test(priority = 13)
    public void validate_NewCustomer_with_numbers_in_state() {
        validateFieldWithError(
                "//input[@name='state']",      // XPath of the state field
                "1234",                        // Input value (numbers in this case)
                "Numbers are not allowed",     // Expected error message
                "message5",                    // ID of the error message element
                "Please fill all fields"       // Alert message
        );
    }

    @Story("New Customer With Special Character In State Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Enter Special Character In State Field \n3- Click On Submit Button")
    @Test(priority = 14)
    public void validate_newCustomer_with_specialCharacters_in_state() {
        validateFieldWithError(
                "//input[@name='state']",          // XPath of the state field
                "!@#$%^&*",                        // Input value (special characters)
                "Special characters are not allowed", // Expected error message
                "message5",                        // ID of the error message element
                "Please fill all fields"           // Alert message
        );
    }

    @Story("New Customer With First Character Have Space In State Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Enter First Character Have Space In State Field \n3- Click On Submit Button")
    @Test(priority = 15)
    public void validate_newCustomer_with_that_firstCharacter_cannot_Have_space_in_state() {
        validateFieldWithError(
                "//input[@name='state']",          // XPath of the state field
                " StateName",                      // Input value (space as the first character)
                "First character cannot have space", // Expected error message
                "message5",                        // ID of the error message element
                "Please fill all fields"           // Alert message
        );
    }


    // 5 Test Cases on PIN Field
    @Story("New Customer With Empty PIN Code Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Leave PIN Code Field Empty\n3- Click On Submit Button")
    @Test(priority = 16)
    public void validate_newCustomer_with_empty_PINCode() {
        validateFieldWithError(
                "//input[@name='pinno']",      // XPath of the PIN field
                "",                            // Input value (empty in this case)
                "PIN Code must not be blank",  // Expected error message
                "message6",                    // ID of the error message element
                "Please fill all fields"       // Alert message
        );
    }

    @Story("New Customer With Character In PIN Code Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Enter Character In PIN Code Field \n3- Click On Submit Button")
    @Test(priority = 17)
    public void validate_NewCustomer_with_characters_in_PINCode() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate the PIN field with explicit wait for presence
        WebElement PINField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[10]/td[2]/input")));

        // Ensure the element is visible and interactable
        wait.until(ExpectedConditions.visibilityOf(PINField));
        wait.until(ExpectedConditions.elementToBeClickable(PINField));

        // Scroll into view if needed (to handle elements outside the viewport)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", PINField);

        // Clear the field and enter invalid characters
        PINField.clear();
        PINField.sendKeys("kady");

        // Simulate losing focus from the input field to trigger validation
        PINField.sendKeys(Keys.TAB);

        // Locate the error message element with explicit wait
        WebElement actualMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message6")));

        // Assert the error message
        String errorMessage = actualMessage.getText();
        Assert.assertEquals(errorMessage, "Characters are not allowed", "Error message does not match!");

        // Retrieve the value of the PIN field
        String pin = PINField.getAttribute("value");

        // Check if the PIN field contains any characters
        boolean containsCharacters = !pin.matches("\\d*"); // Checks if PIN contains non-numeric characters

        if (containsCharacters) {
            // Assert the error message again to ensure it's displayed correctly
            Assert.assertEquals(errorMessage, "Characters are not allowed", "Error message does not match!");

            // Ensure submit button is interactable
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);

            // Click submit button
            submitButton.click();

            // Handle alert
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertMessage = alert.getText();
            alert.accept();

            // Assert alert message
            Assert.assertEquals(alertMessage, "Please fill all fields", "Alert message does not match!");
        } else {
            // Fail the test if the PIN does not contain characters (unexpected behavior)
            Assert.fail("PIN does not contain invalid characters, but the error message should be displayed.");

        }
    }

    @Story("New Customer With Invalid PIN Code Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Enter Invalid PIN Code Field \n3- Click On Submit Button")
    @Test(priority = 18)
    public void validate_NewCustomer_with_InvalidPINCode() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate the PIN code field with explicit wait for presence
        WebElement pinField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='pinno']")));

        // Ensure the PIN field is visible and interactable
        wait.until(ExpectedConditions.visibilityOf(pinField));
        wait.until(ExpectedConditions.elementToBeClickable(pinField));

        // Scroll into view if needed (to handle elements outside the viewport)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pinField);

        // Clear the field and enter an invalid PIN (e.g., 5 digits instead of 6)
        pinField.clear();
        pinField.sendKeys("12345"); // Invalid PIN (less than 6 digits)

        // Simulate losing focus from the input field to trigger validation
        pinField.sendKeys(Keys.TAB);

        // Locate the error message element with explicit wait
        WebElement actualMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message6"))); // Replace with correct ID or locator

        // Assert the error message
        String errorMessage = actualMessage.getText();
        Assert.assertEquals(errorMessage, "PIN Code must have 6 Digits", "Error message does not match!");

        // Retrieve the value entered in the PIN field
        String pinValue = pinField.getAttribute("value");

        // Check if the entered PIN has exactly 6 digits
        boolean isValidPIN = pinValue.matches("\\d{6}");

        if (!isValidPIN) {
            // Ensure submit button is interactable
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);

            // Attempt to click the submit button
            submitButton.click();

            // Handle alert (if the form prevents submission with invalid PIN)
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertMessage = alert.getText();
            alert.accept();

            // Assert alert message
            Assert.assertEquals(alertMessage, "Please fill all fields", "Alert message does not match!");
        } else {
            // Fail the test if the PIN length is valid but should be invalid
            Assert.fail("The PIN code is valid, but it should be invalid.");
        }
    }

    @Story("New Customer With Special Character In PIN Code Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Enter Special Character In PIN Code Field \n3- Click On Submit Button")
    @Test(priority = 19)
    public void validate_newCustomer_with_specialCharacters_in_pin() {
        validateFieldWithError(
                "//input[@name='pinno']",                    // XPath of the PIN field
                "1234@5678",                                 // Input value with special characters
                "Special characters are not allowed",        // Expected error message
                "message6",                                  // ID of the error message element
                "Please fill all fields"                    // Alert message
        );
    }

    @Story("New Customer With First Character Have Space In PIN Code Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Enter First Character Have Space In PIN Code Field \n3- Click On Submit Button")
    @Test(priority = 20)
    public void validate_NewCustomer_that_firstCharacter_cannot_Have_space_in_pin() {
        validateFieldWithError(
                "//input[@name='pinno']",          // XPath of the PIN field
                " 123456",                         // Input value (space as the first character)
                "First character cannot have space", // Expected error message
                "message6",                        // ID of the error message element
                "Please fill all fields"           // Alert message
        );
    }


    // 4 Test Cases for telephone number field
    @Story("New Customer With Empty Telephone Number Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Leave Telephone Number Field Empty\n3- Click On Submit Button")
    @Test(priority = 21)
    public void validate_newCustomer_with_empty_TelephoneNumber() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate the state field with explicit wait for presence
        WebElement TelephoneNumberField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[11]/td[2]/input")));

        // Ensure the element is visible and interactable
        wait.until(ExpectedConditions.visibilityOf(TelephoneNumberField));
        wait.until(ExpectedConditions.elementToBeClickable(TelephoneNumberField));

        // Scroll into view if needed (to handle elements outside the viewport)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", TelephoneNumberField);

        // Clear the field and enter an empty value
        TelephoneNumberField.clear();
        TelephoneNumberField.sendKeys("");
        TelephoneNumberField.sendKeys(Keys.TAB);

        // Locate the error message with explicit wait
        WebElement actualMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message7")));

        // Assert the error message
        Assert.assertEquals(actualMessage.getText(), "Telephone no must not be blank", "Error message does not match!");
        String tP = driver.findElement(By.xpath("//input[@name='telephoneno']")).getAttribute("value");
        if (tP.isEmpty()) {

            // Ensure submit button is interactable
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);

            // Click submit button
            submitButton.click();

            // Handle alert
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertMessage = alert.getText();
            alert.accept();

            // Assert alert message
            Assert.assertEquals(alertMessage, "Please fill all fields", "Alert message does not match!");
        } else {
            Assert.fail("No fields are empty, alert should not appear.");
        }
    }

    @Story("New Customer With Character In Telephone Number Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Enter Character In Telephone Number Field \n3- Click On Submit Button")
    @Test(priority = 22)
    public void validate_NewCustomer_with_characters_in_TelephoneNumber()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate the state field with explicit wait for presence
        WebElement TelephoneNumberField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[11]/td[2]/input")));

        // Ensure the element is visible and interactable
        wait.until(ExpectedConditions.visibilityOf(TelephoneNumberField));
        wait.until(ExpectedConditions.elementToBeClickable(TelephoneNumberField));

        // Scroll into view if needed (to handle elements outside the viewport)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", TelephoneNumberField);

        // Clear the field and enter an empty value
        TelephoneNumberField.clear();
        TelephoneNumberField.sendKeys("kady");
        TelephoneNumberField.sendKeys(Keys.TAB);

        // Locate the error message with explicit wait
        WebElement actualMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message7")));

        // Assert the error message
        String errorMessage = actualMessage.getText();
        Assert.assertEquals(errorMessage, "Characters are not allowed", "Error message does not match!");

        // Retrieve the value of the PIN field
        String t = TelephoneNumberField.getAttribute("value");

        // Check if the PIN field contains any characters
        boolean containsCharacters = !t.matches("\\d*"); // Checks if Telephone number contains non-numeric characters

        if (containsCharacters) {
            // Assert the error message again to ensure it's displayed correctly
            Assert.assertEquals(errorMessage, "Characters are not allowed", "Error message does not match!");

            // Ensure submit button is interactable
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);

            // Click submit button
            submitButton.click();

            // Handle alert
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertMessage = alert.getText();
            alert.accept();

            // Assert alert message
            Assert.assertEquals(alertMessage, "Please fill all fields", "Alert message does not match!");
        } else {
            // Fail the test if the PIN does not contain characters (unexpected behavior)
            Assert.fail("Telephone Number does not contain invalid characters, but the error message should be displayed.");

        }
    }

    @Story("New Customer With Special Character In Telephone Number Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Enter Special Character In Telephone Number Field \n3- Click On Submit Button")
    @Test(priority = 23)
    public void validate_newCustomer_with_specialCharacters_in_telephoneNumber() {
        validateFieldWithError(
                "//input[@name='telephoneno']",              // XPath of the telephone number field
                "123-456-7890",                              // Input value with special characters
                "Special characters are not allowed",        // Expected error message
                "message7",                                   // ID of the error message element
                "Please fill all fields"                     // Alert message
        );
    }

    @Story("New Customer With First Character Have Space In Telephone Number Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Enter First Character Have Space In Telephone Number Field \n3- Click On Submit Button")
    @Test(priority = 24)
    public void validate_NewCustomer_that_firstCharacter_cannot_Have_space_in_telephoneNumber() {
        validateFieldWithError(
                "//input[@name='telephoneno']",          // XPath of the telephone number field
                " 0123456789",                           // Input value (space as the first character)
                "First character cannot have space",     // Expected error message
                "message7",                              // ID of the error message element
                "Please fill all fields"                // Alert message
        );
    }



    // 3 Test Cases for Email Field
    @Story("New Customer With Empty Email Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Leave Email Field Empty\n3- Click On Submit Button")
    @Test(priority = 25)
    public void validate_newCustomer_with_empty_Email() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Increase timeout to 30 seconds

        // Locate the Email field with explicit wait for presence
        WebElement EmailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[12]/td[2]/input")));

        // Ensure the element is visible and interactable
        wait.until(ExpectedConditions.visibilityOf(EmailField));
        wait.until(ExpectedConditions.elementToBeClickable(EmailField));

        // Scroll into view if needed
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", EmailField);

        // Clear the field and enter an empty value
        EmailField.clear();
        EmailField.sendKeys("");


        // Wait for and locate the error message
        WebElement actualMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("message9")));
        WebElement act = wait.until(ExpectedConditions.visibilityOf(actualMessage));
        Assert.assertEquals(actualMessage.getText(), "Email-ID must not be blank", "Error message does not match!");

        // Validate the alert
        String email = EmailField.getAttribute("value");
        if (email.isEmpty()) {
            // Ensure the submit button is interactable
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);

            // Click the submit button
            submitButton.click();

            // Handle alert
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertMessage = alert.getText();
            alert.accept();

            // Assert alert message
            Assert.assertEquals(alertMessage, "Please fill all fields", "Alert message does not match!");
        } else {
            Assert.fail("No fields are empty, alert should not appear.");
        }
    }

    @Story("New Customer With Invalid Email Format Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Enter Invalid Email Format Field \n3- Click On Submit Button")
    @Test(priority = 26)
    public void validate_NewCustomer_with_InvalidEmail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate the email field with explicit wait for presence
        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='emailid']")));

        // Ensure the email field is visible and interactable
        wait.until(ExpectedConditions.visibilityOf(emailField));
        wait.until(ExpectedConditions.elementToBeClickable(emailField));

        // Scroll into view if needed (to handle elements outside the viewport)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", emailField);

        // Clear the field and enter an invalid email
        emailField.clear();
        emailField.sendKeys("Kady.com");

        // Simulate losing focus from the input field to trigger validation
        emailField.sendKeys(Keys.TAB);

        // Locate the error message element with explicit wait
        WebElement actualMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message9"))); // Replace with the correct ID or locator

        // Assert the error message
        String errorMessage = actualMessage.getText();
        Assert.assertEquals(errorMessage, "Email-ID is not valid", "Error message does not match!");

        // Retrieve the value entered in the email field
        String emailValue = emailField.getAttribute("value");

        // Check if the entered email matches a valid email format
        boolean isValidEmail = emailValue.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

        if (!isValidEmail) {
            // Ensure submit button is interactable
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);

            // Attempt to click the submit button
            submitButton.click();

            // Handle alert (if the form prevents submission with invalid email)
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertMessage = alert.getText();
            alert.accept();

            // Assert alert message
            Assert.assertEquals(alertMessage, "Please fill all fields", "Alert message does not match!");
        } else {
            // Fail the test if the email format is considered valid but it should be invalid
            Assert.fail("The email format is valid, but it should be invalid.");
        }
    }

    @Story("New Customer With First Character Have Space In Email Field")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Enter First Character Have Space In Email Field \n3- Click On Submit Button")
    @Test(priority = 27)
    public void validate_NewCustomer_that_firstCharacter_cannot_Have_space_in_Email() {
        validateFieldWithError(
                "//input[@name='emailid']",               // XPath of the email field
                " test@example.com",                      // Input value (space as the first character)
                "First character cannot have space",      // Expected error message
                "message7",                               // ID of the error message element
                "Please fill all fields"                 // Alert message
        );
    }


    // Test case on Empty fields
    @Story("New Customer With Empty Fields")
    @Description("Test Steps:\n\n1- Navigate New Customer Page\n2- Leave ALL Fields Empty\n3- Click On Submit Button")
    @Test(priority = 28)
    public void validate_that_submit_newCustomer_with_empty_fields() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Fill in form fields
        newCustomerPage.locate_customerName();
        newCustomerPage.EnterCustomerName("");
        newCustomerPage.locate_city();
        newCustomerPage.EnterCity("");
        newCustomerPage.locate_state();
        newCustomerPage.EnterState("");
        newCustomerPage.locate_pin();
        newCustomerPage.Enter_PIN("");
        newCustomerPage.locate_telephoneNumber();
        newCustomerPage.EnterTelephoneNumber("");
        newCustomerPage.locate_email();
        newCustomerPage.EnterEmail(""); // Leave email empty

        // Get field values
        String name = driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value");
        String city = driver.findElement(By.xpath("//input[@name='city']")).getAttribute("value");
        String state = driver.findElement(By.xpath("//input[@name='state']")).getAttribute("value");
        String pin = driver.findElement(By.xpath("//input[@name='pinno']")).getAttribute("value");
        String tP = driver.findElement(By.xpath("//input[@name='telephoneno']")).getAttribute("value");
        String email = driver.findElement(By.xpath("//input[@name='emailid']")).getAttribute("value");

        // Check for empty fields
        if (name.isEmpty() || city.isEmpty() || state.isEmpty() || pin.isEmpty() || tP.isEmpty() || email.isEmpty()) {

            // Ensure submit button is interactable
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);

            // Click submit button
            submitButton.click();

            // Handle alert
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertMessage = alert.getText();
            alert.accept();

            // Assert alert message
            Assert.assertEquals(alertMessage, "Please fill all fields", "Alert message does not match!");
        } else {
            Assert.fail("No fields are empty, alert should not appear.");
        }
    }


    // Test Case on Rest button
    @Story("Reset Functionality In New Customer Page Is Working Correctly")
    @Description("Test Steps:\n\n 1- Navigate New Customer Page\n2- Enter All Data Fields\n3- Click On Reset Button")
    @Test(priority = 29)
    public void validate_that_resetButton_is_working_correctly() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate all input fields
        WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='name']")));
        WebElement addressField = driver.findElement(By.xpath("//textarea[@name='addr']"));
        WebElement cityField = driver.findElement(By.xpath("//input[@name='city']"));
        WebElement stateField = driver.findElement(By.xpath("//input[@name='state']"));
        WebElement pinField = driver.findElement(By.xpath("//input[@name='pinno']"));
        WebElement emailField = driver.findElement(By.xpath("//input[@name='emailid']"));
        WebElement phoneField = driver.findElement(By.xpath("//input[@name='telephoneno']"));

        // Enter test data in all fields
        nameField.sendKeys("Test Name");
        addressField.sendKeys("123 Test Address");
        cityField.sendKeys("Test City");
        stateField.sendKeys("Test State");
        pinField.sendKeys("123456");
        emailField.sendKeys("test@example.com");
        phoneField.sendKeys("1234567890");

        // Locate and click the Reset button
        WebElement resetButton = driver.findElement(By.xpath("//input[@type='reset']"));
        resetButton.click();

        // Assert that all fields are cleared
        Assert.assertEquals(nameField.getAttribute("value"), "", "Name field is not cleared");
        Assert.assertEquals(addressField.getAttribute("value"), "", "Address field is not cleared");
        Assert.assertEquals(cityField.getAttribute("value"), "", "City field is not cleared");
        Assert.assertEquals(stateField.getAttribute("value"), "", "State field is not cleared");
        Assert.assertEquals(pinField.getAttribute("value"), "", "PIN field is not cleared");
        Assert.assertEquals(emailField.getAttribute("value"), "", "Email field is not cleared");
        Assert.assertEquals(phoneField.getAttribute("value"), "", "Phone field is not cleared");
    }


    // Test case on successfully created customer
    @Story("Customer Created Successfully")
    @Description("Test Steps:\n\n 1- Navigate New Customer Page\n2- Enter All Valid Data Fields\n3- Click On Submit Button")
    @Test(priority = 30)
    public void validate_that_create_newCustomer_is_successfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // customer name
        newCustomerPage.locate_customerName();
        newCustomerPage.EnterCustomerName("MostafaIbrahim");

        // gender
        newCustomerPage.locate_gender_male();
        newCustomerPage.EnterGenderMale();

        // date
        newCustomerPage.locate_date();
        newCustomerPage.EnterDate("01/05/2000");

        //address
        newCustomerPage.locate_address();
        newCustomerPage.EnterAddress("El marg / new cairo");


        // city
        newCustomerPage.locate_city();
        newCustomerPage.EnterCity("Cairo");

        // state
        newCustomerPage.locate_state();
        newCustomerPage.EnterState("Egypt");

        // pin code must be 6 digit
        newCustomerPage.locate_pin();
        newCustomerPage.Enter_PIN("123456");

        // telephone number
        newCustomerPage.locate_telephoneNumber();
        newCustomerPage.EnterTelephoneNumber("01000577258");

        newCustomerPage.locate_email();
        newCustomerPage.EnterEmail("mostafa.ibrahim@example.com");


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
            String expected = "Customer Createded Successfully!!!"; // Adjust based on actual behavior
            Assert.assertEquals(error, expected, "Alert message does not match expected.");
        } catch (org.openqa.selenium.TimeoutException e) {
            Assert.fail("No alert was present within the timeout period");
        }
    }

}









