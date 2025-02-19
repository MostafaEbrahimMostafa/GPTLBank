import com.google.common.base.Verify;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

@Feature("Login Page")
public class LoginPageTest extends BaseTest {

    LoginPage loginPage;

    @BeforeClass
    public void setUp_LoginPage() {
        driver.get("https://demo.guru99.com/V1/index.php");
        loginPage = new LoginPage(driver);
    }

    @Story("Login With Empty Email")
    @Description("Test Steps:\n\n1- Navigate Login Page\n2- Leave Email Field Empty\n3- Click On Submit Button")
    @Test(priority = 1)
    public void validate_login_with_emptyEmail() {
        loginPage.locate_userNAme();
        loginPage.locate_password();
        loginPage.setUser_nameElement("");
        loginPage.setPasswordElement("AjebYnE");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message23")));
        Assert.assertTrue(errorID.isDisplayed());

    }

    @Story("Login With Empty Password")
    @Description("Test Steps:\n\n1- Navigate Login Page\n2- Leave Password Field Empty\n3- Click On Submit Button")
    @Test(priority = 2)
    public void validate_login_with_emptyPassword() {
        loginPage.locate_userNAme();
        loginPage.locate_password();
        loginPage.setUser_nameElement("mngr602965");
        loginPage.setPasswordElement("");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message18")));
        Assert.assertTrue(errorID.isDisplayed());
    }

    @Story("Login With Invalid Email And Valid Password")
    @Description("Test Steps:\n\n1- Navigate Login Page\n2- Enter Invalid Email\n3- Enter Valid Password\n4- Click On Submit Button")
    @Test(priority = 3)
    public void validate_login_with_InvalidEmail_and_validPassword() {
        loginPage.locate_userNAme();
        loginPage.locate_password();
        loginPage.locate_login_button();
        loginPage.setUser_nameElement("mngr6");
        loginPage.setPasswordElement("AjebYnE");
        loginPage.ClickOn_login_button();
        Alert alert = driver.switchTo().alert();
        String error = alert.getText();
        alert.accept();
        String expected = "User is not valid";
        Assert.assertEquals(error, expected);
    }

    @Story("Login With Valid Email And Invalid Password")
    @Description("Test Steps:\n\n1- Navigate Login Page\n2- Enter Valid Email\n3- Enter Invalid Password\n4- Click On Submit Button")
    @Test(priority = 4)
    public void validate_login_with_validEmail_and_InvalidPassword() {
        loginPage.locate_userNAme();
        loginPage.locate_password();
        loginPage.locate_login_button();
        loginPage.setUser_nameElement("mngr602965");
        loginPage.setPasswordElement("A456");
        loginPage.ClickOn_login_button();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String error = alert.getText();
            alert.accept();
            String expected = "User is not valid";
            Assert.assertEquals(error, expected, "Alert message does not match expected.");
        } catch (org.openqa.selenium.TimeoutException e) {
            Assert.fail("No alert was present within the timeout period.");
        }
    }

    @Story("Reset Functionality In Login Page Is Working Correctly")
    @Description("Test Steps:\n\n1- Navigate Login Page\n2- Enter Email\n3- Enter Password\n4- Click On reset Button")
    @Test(priority = 5)
    public void validate_that_reset_functionality_is_working_correctly() {
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // Reduced wait time

        loginPage.locate_userNAme();
        loginPage.locate_password();
        loginPage.locate_login_button();
        loginPage.setUser_nameElement("mngr6");
        loginPage.setPasswordElement("AjebYnE");
        // Ensure the reset button is interactable and click it
        WebElement resetButton = driver.findElement(By.xpath("//input[@type='reset']")); // Corrected locator
        Assert.assertTrue(resetButton.isDisplayed() && resetButton.isEnabled(), "Reset button is not interactable");
        resetButton.click();

        // Validate that the Customer ID field is cleared
        WebElement name =  driver.findElement(By.xpath("//input[@name='uid']"));
        WebElement pass =  driver.findElement(By.xpath("//input[@name='password']"));
        Assert.assertEquals(name.getAttribute("value"), "", "User Id field was not reset");
        Assert.assertEquals(pass.getAttribute("value"), "", "Password field was not reset");
    }

    @Story("Login With Valid Email And Valid Password")
    @Description("Test Steps:\n\n1- Navigate Login Page\n2- Enter Valid Email\n3- Enter Valid Password\n4- Click On Submit Button")
    @Test(priority = 6)
    public void validate_login_with_valid_data() {
        loginPage.locate_userNAme();
        loginPage.locate_password();
        loginPage.locate_login_button();
        loginPage.setUser_nameElement("mngr602965");
        loginPage.setPasswordElement("AjebYnE");
        loginPage.ClickOn_login_button();
        WebElement actual = driver.findElement(By.xpath("//marquee[@class='heading3']"));
        Assert.assertTrue(actual.isDisplayed());

    }
}
