import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewAccountPage extends BasePage {

    public NewAccountPage(WebDriver driver) {
        super(driver);
    }
    private WebElement customerID , submit_button , account_type ,
    inital_deposit;



    public void locate_customer_id()
    {
        customerID = driver.findElement(By.xpath("//input[@name='cusid']"));
    }

    public void locate_submitButton()
    {
        submit_button = driver.findElement(By.xpath("//input[@name='AccSubmit']"));
    }
    public void locate_account_type()
    {
        account_type = driver.findElement(By.xpath("//select[@name='selaccount']"));
    }
    public void locateIntailDeposit()
    {
        inital_deposit = driver.findElement(By.name("inideposit"));
    }


    public void EnterCustomerID(String id)
    {
        clearElement(customerID);
        sendText(customerID,id);
        customerID.sendKeys(Keys.TAB);
    }
    public void EnterIntialDeposit(String d)
    {
        clearElement(inital_deposit);
        sendText(inital_deposit,d);
        inital_deposit.sendKeys(Keys.TAB);
    }
    public void EnterAccountTpye(String a)
    {
        selectOption(account_type,a);
    }

    public void ClickOn_submitButton()
    {
        clickElement(submit_button);
    }

}
