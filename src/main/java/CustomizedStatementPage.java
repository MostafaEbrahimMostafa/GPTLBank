import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomizedStatementPage extends BasePage {

    public CustomizedStatementPage(WebDriver driver) {
        super(driver);
    }

    private WebElement account_number , submit_button , from_date,
    to_date , amount_lower_limit , number_of_transaction;


    public void locate_AccountNumber()
    {
        account_number = driver.findElement(By.xpath("//input[@name='accountno']"));
    }
    public void locate_submitButton()
    {
        submit_button = driver.findElement(By.xpath("//input[@name='AccSubmit']"));
    }
    public void locate_fromDate()
    {
        from_date = driver.findElement(By.name("fdate"));
    }
    public void locate_toDate()
    {
        to_date = driver.findElement(By.name("tdate"));
    }
    public void locate_amount()
    {
        amount_lower_limit = driver.findElement(By.name("loweramt"));
    }
    public void locate_NofTransaction()
    {
        number_of_transaction = driver.findElement(By.name("tranno"));
    }

    public void EnterAccountNumber(String id)
    {
        clearElement(account_number);
        sendText(account_number,id);
        account_number.sendKeys(Keys.TAB);
    }
    public void EnterFromDate(String d)
    {
        clearElement(from_date);
        sendText(from_date,d);
    }
    public void EnterToDate(String d)
    {
        clearElement(to_date);
        sendText(to_date,d);
    }
    public void EnterAmount(String d)
    {
        clearElement(amount_lower_limit);
        sendText(amount_lower_limit,d);
        amount_lower_limit.sendKeys(Keys.TAB);
    }
    public void EnterNumberOfTransaction(String d)
    {
        clearElement(number_of_transaction);
        sendText(number_of_transaction,d);
        number_of_transaction.sendKeys(Keys.TAB);
    }
    public void ClickOn_submitButton()
    {
        clickElement(submit_button);
    }



}
