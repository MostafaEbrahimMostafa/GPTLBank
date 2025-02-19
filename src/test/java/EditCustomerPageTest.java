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
@Feature("Edit For An Existing Customer")
public class EditCustomerPageTest extends BaseTest {

    HomePage homePage;
    EditCustomerPage editCustomerPage;



    @BeforeClass
    public void setUp_EditCustomerPage() {
        driver.get("https://demo.guru99.com/V1/html/Managerhomepage.php");
        homePage = new HomePage(driver);
        homePage.locate_editCustomer();
        homePage.ClickOn_editCustomer_button();
        editCustomerPage = new EditCustomerPage(driver);
    }


    @Story("Edit Customer With Empty Customer Id Field")
    @Description("Test Steps:\n\n1- Navigate Edit Customer Page\n2- Leave Customer Id Field Empty\n3- Click On Submit Button")
    @Test(priority = 1)
    public void validate_editCustomer_with_empty_CustomerID() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Locate the customer ID input field
            editCustomerPage.locate_customer_id();
            // Enter an empty Customer ID
            editCustomerPage.EnterCustomerID("");

            // Locate and click the submit button
            editCustomerPage.locate_submitButton();
            editCustomerPage.ClickOn_submitButton();

            // Wait for the alert to be present
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String actualAlertMessage = alert.getText();
            alert.accept(); // Accept the alert

            // Assert alert message
            Assert.assertEquals(actualAlertMessage, "Please fill all fields", "Alert message does not match!");

            // Wait for the error message to be displayed on the page (if applicable after alert)
            WebElement actualMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message14")));
            Assert.assertEquals(actualMessage.getText(), "Customer ID is required", "Error message does not match!");

        } catch (UnhandledAlertException e) {
            // Handle unexpected alert if one occurs
            Alert alert = driver.switchTo().alert();
            System.out.println("Unhandled Alert: " + alert.getText());
            alert.accept();
            Assert.fail("Test failed due to unexpected alert: " + e.getMessage());
        }
    }

    @Story("Edit Customer With Character In Customer Id Field")
    @Description("Test Steps:\n\n1- Navigate Edit Customer Page\n2- Enter Character In Customer Id Field \n3- Click On Submit Button")
    @Test(priority = 2)
    public void validate_editCustomer_with_characters_in_CustomerID()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Locate the customer ID input field
            editCustomerPage.locate_customer_id();

            editCustomerPage.EnterCustomerID("abc");

            // Locate and click the submit button
            editCustomerPage.locate_submitButton();
            editCustomerPage.ClickOn_submitButton();

            // Wait for the alert to be present
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String actualAlertMessage = alert.getText();
            alert.accept(); // Accept the alert

            // Assert alert message
            Assert.assertEquals(actualAlertMessage, "Please fill all fields", "Alert message does not match!");

            // Wait for the error message to be displayed on the page (if applicable after alert)
            WebElement actualMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message14")));
            Assert.assertEquals(actualMessage.getText(), "Characters are not allowed", "Error message does not match!");

        } catch (UnhandledAlertException e) {
            // Handle unexpected alert if one occurs
            Alert alert = driver.switchTo().alert();
            System.out.println("Unhandled Alert: " + alert.getText());
            alert.accept();
            Assert.fail("Test failed due to unexpected alert: " + e.getMessage());
        }
    }

    @Story("Edit Customer With Special Character In Customer Id Field")
    @Description("Test Steps:\n\n1- Navigate Edit Customer Page\n2- Enter Special Character In Customer Id Field \n3- Click On Submit Button")
    @Test(priority = 3)
    public void validate_editCustomer_with_specialCharacter_in_CustomerID()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Locate the customer ID input field
            editCustomerPage.locate_customer_id();

            editCustomerPage.EnterCustomerID("@#1");

            // Locate and click the submit button
            editCustomerPage.locate_submitButton();
            editCustomerPage.ClickOn_submitButton();

            // Wait for the alert to be present
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String actualAlertMessage = alert.getText();
            alert.accept(); // Accept the alert

            // Assert alert message
            Assert.assertEquals(actualAlertMessage, "Please fill all fields", "Alert message does not match!");

            // Wait for the error message to be displayed on the page (if applicable after alert)
            WebElement actualMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message14")));
            Assert.assertEquals(actualMessage.getText(), "Special characters are not allowed", "Error message does not match!");

        } catch (UnhandledAlertException e) {
            // Handle unexpected alert if one occurs
            Alert alert = driver.switchTo().alert();
            System.out.println("Unhandled Alert: " + alert.getText());
            alert.accept();
            Assert.fail("Test failed due to unexpected alert: " + e.getMessage());
        }
    }

    @Story("Rest Functionality In Edit Customer Page Is Working Correctly")
    @Description("Test Steps:\n\n1- Navigate Edit Customer Page\n2- Enter Customer Id Field\n3- Click On Reset Button")
    @Test(priority = 4)
    public void validate_that_rest_functionality_is_working_correctly()
    {
        editCustomerPage.locate_customer_id();
        editCustomerPage.EnterCustomerID("100");
        editCustomerPage.locate_restButton();
        editCustomerPage.ClickOn_restButton();
        WebElement actual = driver.findElement(By.xpath("//input[@name='cusid']"));
        Assert.assertEquals(actual.getAttribute("value"),"");
    }

    @Story("Edit Customer With First Character Have Space In Customer Id Field")
    @Description("Test Steps:\n\n1- Navigate Edit Customer Page\n2- Enter First Character Have Space In Customer Id Field \n3- Click On Submit Button")
    @Test(priority = 5)
    public void validate_editCustomer_that_firstCharacter_cannot_Have_space_in_CustomerID() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Locate the customer ID input field
            editCustomerPage.locate_customer_id();

            // Enter a Customer ID with a leading space
            editCustomerPage.EnterCustomerID(" 123");

            // Locate and click the submit button
            editCustomerPage.locate_submitButton();
            editCustomerPage.ClickOn_submitButton();

            // Wait for the alert to be present
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String actualAlertMessage = alert.getText();
            alert.accept(); // Accept the alert

            // Assert alert message
            Assert.assertEquals(actualAlertMessage, "Please fill all fields", "Alert message does not match!");

            // Wait for the error message to be displayed on the page (if applicable after alert)
            WebElement actualMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message14")));
            Assert.assertEquals(actualMessage.getText(), "First character cannot have space", "Error message does not match!");

        } catch (TimeoutException e) {
            Assert.fail("Expected error message or alert did not appear:" + e.getMessage());
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }

    }

    @Story("Customer Is Updated Successfully")
    @Description("Test Steps:\n\n1- Navigate Edit Customer Page\n2- Enter Valid Customer Id Field\n3- Click On Submit Button")
    @Test(priority = 7)
    public void validate_editCustomer_redirects_to_editCustomerForm() {
        driver.navigate().to("https://demo.guru99.com/V1/html/EditCustomer.php");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Step 1: Locate and fill in the Customer ID input field
            editCustomerPage.locate_customer_id();
            editCustomerPage.EnterCustomerID("123"); // Enter a valid Customer ID without leading/trailing spaces

            // Step 2: Locate and click the submit button
            editCustomerPage.locate_submitButton();
            editCustomerPage.ClickOn_submitButton();

            // Step 3: Wait for redirection or the new page to load
            wait.until(ExpectedConditions.urlContains("EditCustomerForm.php"));

            // Step 4: Validate that we are on the Edit Customer Form page
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("EditCustomerForm.php"), "Not redirected to Edit Customer Form!");

            // Optional: Check for specific elements on the form
            WebElement customerNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
            Assert.assertTrue(customerNameField.isDisplayed(), "Customer Name field is not displayed!");
        }
            catch (TimeoutException e) {
                Assert.fail("Redirection to Account Form timed out: " + e.getMessage());
            }
    }





}
