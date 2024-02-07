import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class GeneralLia {
    ChromeDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    Actions act = new Actions(driver);
    Random random = new Random();
    int genNum = random.nextInt(100);
    int genNum01 = random.nextInt(10);
    String SgenNum001 = String.valueOf(genNum01);
    String PolicyHolder = "Test ltd." + genNum;

    @BeforeTest()
    public void startAthos(){
    driver.navigate().to("https://staging-react.athosinsurance.com/");
    driver.manage().window().maximize();
    }
    @Test()
    public void populateGeneralLia() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/section/section[1]/nav/div/div[3]/ul/li[1]/a"))).click();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/section/section[1]/nav/div/div[3]/ul/li[1]/ul/li[2]/a")).click();
        driver.navigate().refresh();
        wait.until(ExpectedConditions.elementToBeClickable(By.name("policyHolderName"))).sendKeys(PolicyHolder);
        driver.findElement(By.name("address")).sendKeys("10020 west irma lane");
        driver.findElement(By.name("zipCode")).sendKeys("85382");
        driver.findElement(By.name("zipCode")).click();
        driver.findElement(By.name("city")).click();
        WebElement radioBtn1 = driver.findElement(By.name("isUSEntity"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", radioBtn1);
        Thread.sleep(3000);
        driver.findElement(By.name("firstName")).sendKeys("test"+SgenNum001);
        driver.findElement(By.name("lastName")).sendKeys("Auto");
        driver.findElement(By.name("email")).sendKeys("TestAuto@gmail.com");
        driver.findElement(By.name("phone")).sendKeys("5645648335");
        WebElement radioBtn2 = driver.findElement(By.name("havePriorInsurance"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", radioBtn2);
        Thread.sleep(1000);
        WebElement radioBtn3 = driver.findElement(By.name("haveClaimInLast5Year"));
        Thread.sleep(1000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", radioBtn3);
        WebElement radioBtn4 = driver.findElement(By.name("willProductionActivityOutsideUS"));
        Thread.sleep(1000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", radioBtn4);
        Thread.sleep(1000);
        driver.findElement(By.name("totalYearOfExperience")).sendKeys(SgenNum001);
    }
    @AfterTest()
    public void quitFirstCase() throws InterruptedException {
        //driver.quit();
    }
}

