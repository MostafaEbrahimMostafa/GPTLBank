import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {



    private WebElement user_nameElement , passwordElement , login_buttonElement ;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void locate_userNAme()
    {
        user_nameElement = driver.findElement(By.xpath("//input[@name='uid']"));
    }
    public void locate_password()
    {
        passwordElement = driver.findElement(By.xpath("//input[@name='password']"));
    }
    public void locate_login_button()
    {
        login_buttonElement = driver.findElement(By.xpath("//input[@type='submit']"));
    }

    public void setUser_nameElement(String name)
    {
        clearElement(user_nameElement);
        sendText(user_nameElement,name);
    }
    public void setPasswordElement(String password)
    {
        clearElement(passwordElement);
        sendText(passwordElement,password);
    }

    public void ClickOn_login_button()
    {
        clickElement(login_buttonElement);
    }





}
