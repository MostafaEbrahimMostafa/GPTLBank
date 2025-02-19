import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DeleteCustomerPage extends BasePage{

    public DeleteCustomerPage(WebDriver driver) {
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
