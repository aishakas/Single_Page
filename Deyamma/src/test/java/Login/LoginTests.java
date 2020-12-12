package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTests{
    // import the selenium webDriver
    private WebDriver driver;
    // import chromeDriver
    @BeforeClass
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "resources/chromeDriver.exe");
        driver = new ChromeDriver();
        // Input project URL = Deydam login URL
        driver.get("https://dev.d2rxvhrryr2bkn.amplifyapp.com/login");
        // waiting for globally
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // maximize window
        driver.manage().window().maximize();
        /* get page title */
        System.out.println(driver.getTitle());
        // Locate username field
        driver.findElement(By.id("username")).sendKeys("kasali_aisha");
        // Locate password field
        driver.findElement(By.id("password")).sendKeys("@Aishak28262");
        // Click on the sign in button
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div/div/div[2]/div/div/form/button")).click();
    }
    @Test(priority = 0 )
    public void testSuccessfulLogin(){
        if (driver.getCurrentUrl ().contains ("https://dev.d2rxvhrryr2bkn.amplifyapp.com/app/feed"))
            //Pass
            System.out.println ("The Page URL contains /app/feed");
        else
            //Fail
            System.out.println ("The Page URL does not contain /app/feed");
    }

    @Test(priority = 1)
    public void testLogout() throws InterruptedException {
        //click on the profile button
        driver.findElement (By.xpath ("//*[@id=\"root\"]/div/div/div/main/div/div[1]/div[1]/div/div[2]/div[3]")).click ();
        Thread.sleep (2000);
        //click on the logout button
        driver.findElement (By.xpath ("//*[@id=\"root\"]/div/div/div/main/div/div[1]/div[1]/div/div[2]/div[3]/div/div/a[3]/p")).click ();
        //printout response based on status
        if(driver.getCurrentUrl ().contains ("https://dev.d2rxvhrryr2bkn.amplifyapp.com/login\""))
            //Pass
            System.out.println ("The Login page URL contains /login");
        else
            //Fail
            System.out.println ("The Login URL does not contain /login");
    }

    @Test(priority = 1)
    public void testNegativeLogin(){
        driver.findElement (By.id ("username")).sendKeys ("akasali");
        driver.findElement (By.id ("password")).sendKeys ("@Aishak28262");
        driver.findElement (By.xpath ("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div/div/div[2]/div/div/form/button")).click ();
        String expectedErrorMessage = "Login Failed: Invalid username or password";
        String actualErrorMessage = driver.findElement(By.xpath ("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div/div/div[2]/div/div/form/div[4]/div/div/p")).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    // initiate the test run command
        public static void main (String args[]) throws InterruptedException {
            LoginTests test = new LoginTests();
            test.setup();
        }
        @AfterTest
        public void closeBrowser () throws InterruptedException{
        Thread.sleep(10000);
        driver.quit();
    }

}
