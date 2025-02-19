import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
@Feature("Delete A Customer")
public class DeleteCustomerPageTest extends BaseTest {


    HomePage homePage;
    DeleteCustomerPage deleteCustomerPage;


    @BeforeClass
    public void setUp_DeleteCustomerPage() {
        driver.get("https://demo.guru99.com/V1/html/Managerhomepage.php");
        homePage = new HomePage(driver);
        homePage.locate_deleteCustomer();
        homePage.ClickOn_deleteCustomer_button();
        deleteCustomerPage = new DeleteCustomerPage(driver);
    }


    @Story("Delete Customer With Empty Customer Id Field")
    @Description("Test Steps:\n\n1- Navigate Delete Customer Page\n2- Leave Customer Id Field Empty\n3- Click On Submit Button")
    @Test(priority = 1)
    public void validate_deleteCustomer_with_empty_CustomerID() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Locate the customer ID input field
            deleteCustomerPage.locate_customer_id();
            // Enter an empty Customer ID
            deleteCustomerPage.EnterCustomerID("");

            // Locate and click the submit button
            deleteCustomerPage.locate_submitButton();
            deleteCustomerPage.ClickOn_submitButton();

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

    @Story("Delete Customer With Character In Customer Id Field")
    @Description("Test Steps:\n\n1- Navigate Delete Customer Page\n2- Enter Character In Customer Id Field \n3- Click On Submit Button")
    @Test(priority = 2)
    public void validate_deleteCustomer_with_characters_in_CustomerID()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Locate the customer ID input field
            deleteCustomerPage.locate_customer_id();

            deleteCustomerPage.EnterCustomerID("abc");

            // Locate and click the submit button
            deleteCustomerPage.locate_submitButton();
            deleteCustomerPage.ClickOn_submitButton();

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

    @Story("Delete Customer With Special Character In Customer Id Field")
    @Description("Test Steps:\n\n1- Navigate Delete Customer Page\n2- Enter Special Character In Customer Id Field \n3- Click On Submit Button")
    @Test(priority = 3)
    public void validate_deleteCustomer_with_specialCharacter_in_CustomerID()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Locate the customer ID input field
            deleteCustomerPage.locate_customer_id();

            deleteCustomerPage.EnterCustomerID("@#1");

            // Locate and click the submit button
            deleteCustomerPage.locate_submitButton();
            deleteCustomerPage.ClickOn_submitButton();

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

    @Story("Reset Functionality In Delete Customer Page Is Working Correctly")
    @Description("Test Steps:\n\n1- Navigate Delete Customer Page\n2- Enter Customer Id Field\n3- Click On Reset Button")
    @Test(priority = 4)
    public void validate_that_rest_functionality_is_working_correctly()
    {
        deleteCustomerPage.locate_customer_id();
        deleteCustomerPage.EnterCustomerID("100");
        deleteCustomerPage.locate_restButton();
        deleteCustomerPage.ClickOn_restButton();
        WebElement actual = driver.findElement(By.xpath("//input[@name='cusid']"));
        Assert.assertEquals(actual.getAttribute("value"),"");
    }

    @Story("Delete Customer With First Character Have Space In Customer Id Field")
    @Description("Test Steps:\n\n1- Navigate Delete Customer Page\n2- Enter First Character Have Space In Customer Id Field \n3- Click On Submit Button")
    @Test(priority = 5)
    public void validate_deleteCustomer_that_firstCharacter_cannot_Have_space_in_CustomerID() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Locate the customer ID input field
            deleteCustomerPage.locate_customer_id();

            // Enter a Customer ID with a leading space
            deleteCustomerPage.EnterCustomerID(" 123");

            // Locate and click the submit button
            deleteCustomerPage.locate_submitButton();
            deleteCustomerPage.ClickOn_submitButton();

            // Wait for the alert to be present
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String actualAlertMessage = alert.getText();
            alert.accept(); // Accept the alert

            // Assert alert message
            Assert.assertEquals(actualAlertMessage, "Please fill all fields", "Error message does not match!");

            // Wait for the error message to be displayed on the page (if applicable after alert)
            WebElement actualMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message14")));
            Assert.assertEquals(actualMessage.getText(), "First character cannot have space", "Error message does not match!");

        } catch (org.openqa.selenium.TimeoutException e) {
            Assert.fail("Expected error message And alert did not appear:");
        }

    }

    @Story("Customer Is Deleted Successfully")
    @Description("Test Steps:\n\n1- Navigate Delete Customer Page\n2- Enter Valid Customer Id Field\n3- Click On Submit Button")
    @Test(priority = 6)
    public void validate_that_DeleteCustomer_is_working_correctly() {
        driver.navigate().to("https://demo.guru99.com/V1/html/DeleteCustomerInput.php");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Locate the customer ID input field
            deleteCustomerPage.locate_customer_id();

            // Enter a Customer ID with a leading space
            deleteCustomerPage.EnterCustomerID(" 123");

            // Locate and click the submit button
            deleteCustomerPage.locate_submitButton();
            deleteCustomerPage.ClickOn_submitButton();

            // Wait for the alert to be present
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String actualAlertMessage = alert.getText();
            alert.accept(); // Accept the alert

            // Assert alert message
            Assert.assertEquals(actualAlertMessage, "Customer Deleted Successfully", "Alert message does not match!");
        } catch (org.openqa.selenium.TimeoutException e) {
            Assert.fail("Expected error message And alert did not appear:");
        }
    }
}
