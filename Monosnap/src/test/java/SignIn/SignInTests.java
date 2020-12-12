package SignIn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SignInTests {
    private WebDriver driver;

    // import chromeDriver
    @BeforeClass
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "resources/chromeDriver.exe");
        driver = new ChromeDriver();
        // Input project URL = monosnap login URL
        driver.get("https://monosnap.com/");
        // waiting for globally
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // maximize window
        driver.manage().window().maximize();
        // get page title
        System.out.println(driver.getTitle());
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[3]/div[1]/div/div[2]/div[3]")).click();
        // Locate email field
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div[1]/div/div[2]/form/div[2]/input[1]")).sendKeys("ak.kasali@yahoo.com");
        // Locate password field
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div[1]/div/div[2]/form/div[2]/input[2]")).sendKeys("aishak28262");
        //click accept cookies
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/div[1]/div")).click();
        // Click on the sign in button
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div[1]/div/div[2]/form/button")).click();
        //click zoom exit button
        driver.findElement(By.xpath("//*[@id=\"store\"]/div[1]/div[1]/div")).click();
        Thread.sleep(1000);
    }

    @Test(priority = 0)
    public void testSuccessfulLogin() throws InterruptedException {
        if (driver.getCurrentUrl ().contains ("https://monosnap.com/app/feed"))
            //Pass
            System.out.println ("The Page URL contains /app/feed");
        else
            //Fail
            System.out.println ("The Page URL does not contain /app/feed");
        Thread.sleep(5000);
    }

    @Test(priority = 1)
    public void testLogout() throws InterruptedException {
        //click on the settings button
        driver.findElement (By.xpath ("//*[@id=\"store\"]/div[4]/div/div[1]/div/div[3]/div[3]")).click ();
        Thread.sleep (2000);
        //click on the logout button
        driver.findElement (By.xpath ("//*[@id=\"store\"]/div[4]/div/div[1]/div/div[1]/div[2]")).click ();
        //printout response based on status
        if(driver.getCurrentUrl ().contains ("https://monosnap.com/SignIn"))
            //Pass
            System.out.println ("The Login page URL contains /SignIn");
        else
            //Fail
            System.out.println ("The Login URL does not contain /SignIn");
        Thread.sleep(5000);
        driver.navigate().refresh();
    }

    @Test(priority = 1)
    public void testNegativeLogin (){
        driver.navigate().refresh();
        driver.findElement (By.xpath ("//*[@id=\"root\"]/div[2]/div/div[3]/div[1]/div/div[2]/div[3]")).click();
        driver.findElement (By.xpath ("//*[@id=\"root\"]/div[2]/div/div[1]/div[1]/div/div[2]/form/div[2]/input[1]")).sendKeys("invalid@yahoo.com");
        driver.findElement (By.xpath ("//*[@id=\"root\"]/div[2]/div/div[1]/div[1]/div/div[2]/form/div[2]/input[2]")).sendKeys ("admin123.");
        //click accept cookies
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/div[1]/div")).click();
        driver.findElement (By.xpath ("//*[@id=\"root\"]/div[2]/div/div[1]/div[1]/div/div[2]/form/button")).click();
        String expectedErrorMessage = "";
        String actualErrorMessage = driver.findElement (By.xpath ("//*[@id=\"root\"]/div[1]/div/div/div/div")).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }


    // initiate the test run command
    public static void main(String args[]) throws InterruptedException {
        SignInTests test = new SignInTests();
        test.setup();
    }

    @AfterTest
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(10000);
        driver.quit();
    }

}
