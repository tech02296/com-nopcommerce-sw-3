package browserfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;

    public void openBrowser(String baseUrl){
        // we are launching the Chrome browser
        driver = new ChromeDriver();// ChromeDrive is class comes from selenium library
        // open the URL into Browser
        driver.get(baseUrl);
        // Maximise Browser
        driver.manage().window().maximize();
        // we give Implicit wait to Driver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void closBrowser(){
        driver.quit();
    }
}

