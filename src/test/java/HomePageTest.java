import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
@Feature("Manager Home Page")
public class HomePageTest extends BaseTest {

    HomePage homePage;
    WebDriverWait wait;

    @BeforeClass
    public void setUp_HomePage() {
        homePage = new HomePage(driver); // Initialize HomePage object
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait
    }

    // Helper method to click on a button and verify the result
    private void clickAndVerifyResult(WebElement button, String xpathToVerify) {
        // Wait for the element to be clickable and click using JavaScript
        wait.until(ExpectedConditions.elementToBeClickable(button));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);

        // Verify if the result is as expected
        WebElement actual = driver.findElement(By.xpath(xpathToVerify));
        Assert.assertTrue(actual.isDisplayed(), "The expected element is not displayed.");
    }


    @Story("Verify New Customer Feature Is Redirected Correctly To New Customer Page")
    @Description("Test Steps:\n\n1- Navigate Home Page\n2- Click On New Customer Feature")
    @Test(priority = 1)
    public void validate_newCustomer_button_is_working_correctly() {
        homePage.locate_newCustomer();
        clickAndVerifyResult(homePage.getNew_customerElement(), "/html/body/table/tbody/tr/td/table/tbody");
    }

    @Story("Verify Edit Customer Feature Is Redirected Correctly To Edit Customer Page")
    @Description("Test Steps:\n\n1- Navigate Home Page\n2- Click On Edit Customer Feature")
    @Test(priority = 2)
    public void validate_editCustomer_button_is_working_correctly() {
        homePage.locate_editCustomer();
        clickAndVerifyResult(homePage.getEdit_customerElement(), "/html/body/table/tbody/tr/td/table/tbody");
    }

    @Story("Verify Delete Customer Feature Is Redirected Correctly To Delete Customer Page")
    @Description("Test Steps:\n\n1- Navigate Home Page\n2- Click On Delete Customer Feature")
    @Test(priority = 3)
    public void validate_deleteCustomer_button_is_working_correctly() {
        homePage.locate_deleteCustomer();
        clickAndVerifyResult(homePage.getDelete_customerElement(), "/html/body/table/tbody/tr/td/table/tbody");
    }

    @Story("Verify New Account Feature Is Redirected Correctly To New Account Page")
    @Description("Test Steps:\n\n1- Navigate Home Page\n2- Click On New Account Feature")
    @Test(priority = 4)
    public void validate_newAccount_button_is_working_correctly() {
        homePage.locate_newAccount();
        clickAndVerifyResult(homePage.getNew_accountElement(), "/html/body/table/tbody/tr/td/table/tbody");
    }

    @Story("Verify Edit Account Feature Is Redirected Correctly To Edit Account Page")
    @Description("Test Steps:\n\n1- Navigate Home Page\n2- Click On Edit Account Feature")
    @Test(priority = 5)
    public void validate_editAccount_button_is_working_correctly() {
        homePage.locate_editAccount();
        clickAndVerifyResult(homePage.getEdit_accountElement(), "/html/body/table/tbody/tr/td/table/tbody");
    }

    @Story("Verify Min Statement Feature Is Redirected Correctly To Min Statement Page")
    @Description("Test Steps:\n\n1- Navigate Home Page\n2- Click On Min Statement Feature")
    @Test(priority = 7)
    public void validate_minStatement_button_is_working_correctly() {
        homePage.locate_minStatement();
        clickAndVerifyResult(homePage.getMin_statementElement(), "/html/body/table/tbody/tr/td/table/tbody");
    }

    @Story("Verify Customized Statement Feature Is Redirected Correctly To Customized  Statement Page")
    @Description("Test Steps:\n\n1- Navigate Home Page\n2- Click On Customized  Statement Feature")
    @Test(priority = 8)
    public void validate_customizedStatement_button_is_working_correctly() {
        homePage.locate_customizedStatement();
        clickAndVerifyResult(homePage.getCustomised_statementElement(), "/html/body/table/tbody/tr/td/table/tbody");
    }


}
