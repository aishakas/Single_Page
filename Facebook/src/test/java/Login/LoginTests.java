package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTests {
    // import the selenium webDriver
    private WebDriver driver;
    // import chromeDriver
    @BeforeClass
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "resources/chromeDriver.exe");
        driver = new ChromeDriver();
        // Input project URL = facebook login URL
        driver.get("https://www.facebook.com/login");
        // waiting for globally
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // maximize window
        driver.manage().window().maximize();
        // get page title
        System.out.println(driver.getTitle());
        // Locate username field
        driver.findElement(By.id("email")).sendKeys("+2349056317135");
        // Locate password field
        driver.findElement(By.id("pass")).sendKeys("@aishak28262");
        // Click on the sign in button
        driver.findElement(By.xpath("//*[@id=\"loginbutton\"]")).click();
        driver.navigate().refresh();
        Thread.sleep(1000);
    }

    @Test
    public void testSuccessfulLogin(){
        if (driver.getCurrentUrl ().contains ("https://www.facebook.com/login/app/feed"))
            //Pass
            System.out.println ("The Page URL contains /app/feed");
        else
            //Fail
            System.out.println ("The Page URL does not contain /app/feed");
    }

    @Test
    public void testLogout() throws InterruptedException {
        driver.navigate().refresh();
        //click on the profile button
        driver.findElement (By.xpath ("//*[@id=\"mount_0_0\"]/div/div[1]/div[1]/div[2]/div[4]/div[1]/span/div")).click ();
        Thread.sleep (2000);
        //click on the logout button
        driver.findElement (By.xpath ("//*[@id=\"mount_0_0\"]/div/div[1]/div[1]/div[2]/div[4]/div[2]/div/div[2]/div[1]/div[1]/div/div/div/div/div/div/div/div/div[1]/div/div[3]/div/div[4]/div/div[1]/div[2]/div/div/div/div")).click ();
        //printout response based on status
        if(driver.getCurrentUrl ().contains ("https://www.facebook.com/login/login"))
            //Pass
            System.out.println ("The Login page URL contains /login");
        else
            //Fail
            System.out.println ("The Login URL does not contain /login");
    }

    // initiate the test run command
    public static void main(String args[]) throws InterruptedException {
        LoginTests test = new LoginTests();
        test.setup();
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
        driver.quit();
    }
}