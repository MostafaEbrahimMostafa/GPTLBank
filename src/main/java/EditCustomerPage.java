import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditCustomerPage extends BasePage {

    public EditCustomerPage(WebDriver driver) {
        super(driver);
    }


    private WebElement customerID , submit_button , rest_button ;



    public void locate_customer_id()
    {
        customerID = driver.findElement(By.xpath("//input[@name='cusid']"));
    }

    public void locate_submitButton()
    {
        submit_button = driver.findElement(By.xpath("//input[@name='AccSubmit']"));
    }
    public void locate_restButton()
    {
        rest_button = driver.findElement(By.xpath("//input[@name='res']"));
    }

    public void EnterCustomerID(String id)
    {
        clearElement(customerID);
        sendText(customerID,id);
        customerID.sendKeys(Keys.TAB);
    }

    public void ClickOn_submitButton()
    {
        clickElement(submit_button);
    }
    public void ClickOn_restButton()
    {
        clickElement(rest_button);
    }

}
