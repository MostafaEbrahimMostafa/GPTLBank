import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogoutPage extends BasePage {

    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    private WebElement logoutElement ;
    public void locate_logoutButton()
    {
        logoutElement = driver.findElement(By.xpath("//body/div[3]/div/ul/li[10]/a"));
    }

    public WebElement getLogoutElement() {
        return logoutElement;
    }

}
