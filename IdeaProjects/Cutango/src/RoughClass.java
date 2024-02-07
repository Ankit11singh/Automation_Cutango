import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.Objects;

public class RoughClass {
    BaseCode reg = new BaseCode();

        @Test(priority = 1)
    public void login() throws InterruptedException {
        reg.driver.get("https://cutango-stagging.azurewebsites.net/");
        reg.driver.manage().window().maximize();
        reg.driver.findElement(By.id("Username")).sendKeys("testowner1++@maildrop.cc");
        reg.driver.findElement(By.id("password")).sendKeys("Abcd@1234");
        reg.driver.findElement(By.xpath("/html/body/app-root/login/html/body/div/div/form/button")).click();
        Thread.sleep(3000);
    }
    @Test(priority = 2)
    public void testProjectAndTaskCreation() throws InterruptedException {
            reg.playbookCreation();
            reg.taskCreation();
    }
//    @Test(priority = 4)
//    public void logOut() throws InterruptedException {
//        Thread.sleep(5000);
//        String CurrentURL = driver.getCurrentUrl();
//        String URL = "https://cutango-stagging.azurewebsites.net/project";
//        if (Objects.equals(CurrentURL, URL)){
//            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"adminlink\"]/a"))).click();
//        } else {
//            driver.findElement(By.xpath("//*[@id=\"prolink\"]/a")).click();
//        }
//        WebElement ele5 = driver.findElement(By.xpath("/html/body/app-root/layout/div[1]/div[2]/ul/li[5]/div/button"));
//        ele5.click();
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/layout/div[1]/div[2]/ul/li[5]/div/ul/li[2]/a"))).click();
//    }
}
