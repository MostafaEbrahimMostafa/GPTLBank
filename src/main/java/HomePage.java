import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {


    private WebElement new_customerElement , edit_customerElement , delete_customerElement,
    new_accountElement , edit_accountElement ,
    min_statementElement , customised_statementElement ;

    public HomePage(WebDriver driver) {
        super(driver);
    }


    public WebElement getNew_customerElement() {
        return new_customerElement;
    }

    public WebElement getEdit_customerElement() {
        return edit_customerElement;
    }

    public WebElement getDelete_customerElement() {
        return delete_customerElement;
    }

    public WebElement getMin_statementElement() {
        return min_statementElement;
    }

    public WebElement getCustomised_statementElement() {
        return customised_statementElement;
    }

    public WebElement getNew_accountElement() {
        return new_accountElement;
    }

    public WebElement getEdit_accountElement() {
        return edit_accountElement;
    }



    public void locate_newCustomer()
    {
        new_customerElement = driver.findElement(By.xpath("//body/div[3]/div/ul/li[2]/a"));
    }
    public void locate_editCustomer()
    {
        edit_customerElement = driver.findElement(By.xpath("//body/div[3]/div/ul/li[3]/a"));
    }
    public void locate_deleteCustomer()
    {
        delete_customerElement = driver.findElement(By.xpath("//body/div[3]/div/ul/li[4]/a"));
    }
    public void locate_newAccount()
    {
        new_accountElement = driver.findElement(By.xpath("//body/div[3]/div/ul/li[5]/a"));
    }
    public void locate_editAccount()
    {
        edit_accountElement = driver.findElement(By.xpath("//body/div[3]/div/ul/li[6]/a"));
    }

    public void locate_minStatement()
    {
        min_statementElement = driver.findElement(By.xpath("//body/div[3]/div/ul/li[8]/a"));
    }
    public void locate_customizedStatement()
    {
        customised_statementElement = driver.findElement(By.xpath("//body/div[3]/div/ul/li[9]/a"));
    }


    public void ClickOn_newCustomer_button()
    {
        clickElement(new_customerElement);
    }
    public void ClickOn_editCustomer_button()
    {
        clickElement(edit_customerElement);
    }
    public void ClickOn_deleteCustomer_button()
    {
        clickElement(delete_customerElement);
    }





}
