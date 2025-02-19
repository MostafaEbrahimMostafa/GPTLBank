import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {


    protected  static WebDriver driver;


    @BeforeTest
    public void setDriver()
    {
        if(driver == null) {
            driver = new ChromeDriver();
            driver.get("https://demo.guru99.com/V1/index.php");
            driver.manage().window().maximize();


        }
    }



    @AfterTest
    public void Quit()
    {
        if(driver != null)
        {
            driver.quit();
        }
    }



}
