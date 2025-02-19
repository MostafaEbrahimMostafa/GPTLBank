import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NewCustomerPage extends BasePage {


    private WebElement customer_name , gender_male , date ,
    address , city , state , pin , telephone_number , email , submit_button ;

    public NewCustomerPage(WebDriver driver) {
        super(driver);
    }


    public WebElement getCity() {
        return city;
    }

    public WebElement getState() {
        return state;
    }

    public WebElement getPin() {
        return pin;
    }



    public WebElement getEmail() {
        return email;
    }

    public void locate_customerName()
    {
        customer_name = driver.findElement(By.xpath("//input[@name='name']"));
        clearElement(customer_name);
    }
    public void locate_gender_male()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        gender_male = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='rad1'][1]")));
    }
    public void locate_date()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        date = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='dob']")));
        clearElement(date);
    }
    public void locate_address()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        address = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@align='center']/tbody/tr/td/table/tbody/tr[7]/td[2]/textarea")));
        clearElement(address);
    }
    public void locate_city()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        city = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@align='center']/tbody/tr/td/table/tbody/tr[8]/td[2]/input")));
        clearElement(city);
    }

    public void locate_state()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        state = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@align='center']/tbody/tr/td/table/tbody/tr[9]/td[2]/input")));
        clearElement(state);
    }
    public void locate_pin()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        pin =wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@align='center']/tbody/tr/td/table/tbody/tr[10]/td[2]/input")));
        clearElement(pin);
    }
    public void locate_telephoneNumber()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        telephone_number = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@align='center']/tbody/tr/td/table/tbody/tr[11]/td[2]/input")));
        clearElement(telephone_number);
    }
    public void locate_email()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        email = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@align='center']/tbody/tr/td/table/tbody/tr[12]/td[2]/input")));
        clearElement(email);
    }
    public void locate_submitButton()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        submit_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submit_button);
    }


    public void EnterCustomerName(String name)
    {
        sendText(customer_name,name);
        customer_name.sendKeys(Keys.TAB);
    }
    public void EnterGenderMale()
    {
       clickElement(gender_male);
    }

    public void EnterDate(String dat)
    {
        sendText(date,dat);
    }
    public void EnterAddress(String add)
    {
        sendText(address,add);
    }
    public void EnterCity(String c)
    {
        sendText(city,c);
        city.sendKeys(Keys.TAB);
    }
    public void EnterState(String s)
    {
        sendText(state,s);

    }
    public void Enter_PIN(String p)
    {
        sendText(pin,p);

    }
    public void EnterTelephoneNumber(String t)
    {
        sendText(telephone_number,t);

    }
    public void EnterEmail(String e)
    {
        sendText(email,e);
        city.sendKeys(Keys.TAB);
    }

    public void ClickOn_submitButton()
    {
        clearElement(submit_button);
    }








}
