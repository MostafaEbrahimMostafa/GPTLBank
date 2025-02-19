import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DeleteAccountPage extends BasePage{

    public DeleteAccountPage(WebDriver driver) {
        super(driver);
    }



    private WebElement account_number , submit_button ;


    public void locate_AccountNumber()
    {
        account_number = driver.findElement(By.xpath("//input[@name='accountno']"));
    }
    public void locate_submitButton()
    {
        submit_button = driver.findElement(By.xpath("//input[@name='AccSubmit']"));
    }

    public void EnterAccountNumber(String id)
    {
        clearElement(account_number);
        sendText(account_number,id);
        account_number.sendKeys(Keys.TAB);
    }
    public void ClickOn_submitButton()
    {
        clickElement(submit_button);
    }



}
