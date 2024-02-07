import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;


public class BaseCode {
    ChromeDriver driver = new ChromeDriver();
    Random random = new Random();
    Actions act = new Actions(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    String Firstname = "Testuser" + random.nextInt(900);
    String Lastname = "Last" + random.nextInt(900);
    String Password = "Abcd@1234";
    String Email = Firstname + Lastname + "@mailinator.com";
    String CompanyName = "Comp" + random.nextInt(100) + "ltd";
    String TeamMember = "Teammember" + random.nextInt(100);
    String TeamMember1 = "Teammember" + random.nextInt(100);
    String TeamMember2 = "Teammember" + random.nextInt(100);
    String TeamMemberEmail = TeamMember + "@mailinator.com";
    String TeamMemberEmail1 = TeamMember1 + "@mailinator.com";
    String TeamMemberEmail2 = TeamMember2 + "@mailinator.com";
    @BeforeTest()
    public void navigateToURL(){
        driver.get("https://cutango-stagging.azurewebsites.net/");
        driver.manage().window().maximize();
            }
    @Test(priority = 1)
    public void registration(){
        driver.findElement(By.xpath("/html/body/app-root/login/html/body/div/div/div[1]/a")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("FirstName"))).sendKeys(Firstname);
        driver.findElement(By.id("LastName")).sendKeys(Lastname);
        driver.findElement(By.id("Useremail")).sendKeys(Email);
        driver.findElement(By.id("country")).click();
        driver.findElement(By.xpath("//*[@id=\"country\"]/option[11]")).click();
        driver.findElement(By.id("Password")).sendKeys(Password);
        WebElement ele1 = driver.findElement(By.id("confirmpassword"));
        ele1.sendKeys(Password);
        driver.findElement(By.id("termsandconditions")).click();
        driver.findElement(By.xpath("/html/body/app-root/app-signup/html/body/div/div/form/div[5]/div/button")).click();
    }
    @Test(priority = 2)
    public void subscription() throws InterruptedException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-subscription/html/body/div[1]/div/div[3]/div/div[2]/div/button"))).click();
            Thread.sleep(3000);
        }
        catch (ElementClickInterceptedException exception3){
            int i;
            for (i=0; i<5;i++) {
                driver.findElement(By.xpath("/html/body/app-root/app-subscription/html/body/div[1]/div/div[3]/div/div[2]/div/button")).click();
            }
            System.out.println("Exception is " + exception3);
        }

    }
    @Test(priority = 3)
    public void emailConfirmation(){
        driver.get("https://www.mailinator.com/");
        driver.navigate().refresh();
       try {
           wait.until(ExpectedConditions.elementToBeClickable(By.id("search"))).sendKeys(Email);
       }
        catch (TimeoutException exception)
        {
           driver.findElement(By.xpath("//*[@id=\"site-header\"]/div[1]/div/div/div[2]/div/a")).click();
           driver.findElement(By.id("inbox_field")).sendKeys(Email);
           System.out.println("Exception is " + exception);
        }
       driver.findElement(By.xpath("//*[@id=\"site-header\"]/div[1]/div/div/div[1]/div/button")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div[2]/div[3]/div/div[4]/div/div/table/tbody/tr"))).click();
        WebElement ele2 = driver.findElement(By.id("html_msg_body"));
        driver.switchTo().frame(ele2);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/a"))).click();
    }

    @Test(priority = 4)
    public void companyReg() throws InterruptedException {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        wait.until(ExpectedConditions.elementToBeClickable(By.name("CompanyName"))).sendKeys(CompanyName);
        driver.findElement(By.xpath("/html/body/app-root/app-addcompany/html/body/div/div[2]/form/div[2]/div/div[2]/button")).click();
        Thread.sleep(2000);
        driver.navigate().refresh();
    }

    @Test(priority = 5)
    public void projectCreation() throws InterruptedException {
        Thread.sleep(3000);
        driver.navigate().refresh();
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"container_target-1\"]/div[2]/div/app-project/div[1]/div[2]/button[2]/i"))).click();
        }
        catch (ElementClickInterceptedException exception)
            {
                driver.findElement(By.xpath("//*[@id=\"container_target-1\"]/div[2]/div/app-project/div[1]/div[2]/button[2]/i")).click();
            }
            Thread.sleep(3000);
            driver.findElement(By.id("ProjectName")).sendKeys("Proj100");
            driver.findElement(By.id("CountryId")).click();
            driver.findElement(By.xpath("//*[@id=\"CountryId\"]/option[216]")).click();
            driver.findElement(By.id("TimeZone")).click();
            driver.findElement(By.xpath("//*[@id=\"TimeZone\"]/option[3]")).click();
            driver.findElement(By.id("btnNextToTeam")).click();
    }
    @Test(priority = 6)
    public void teamMemberAdd() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("email0"))).sendKeys(TeamMemberEmail);
        driver.findElement(By.id("role0")).click();
        driver.findElement(By.xpath("//*[@id=\"role0\"]/option[3]")).click();
        driver.findElement(By.xpath("//*[@id=\"manageTeam\"]/div[2]/div/div/div/div[1]/div/div[2]/a")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("email0"))).sendKeys(TeamMemberEmail1);
        driver.findElement(By.id("role0")).click();
        driver.findElement(By.xpath("//*[@id=\"role0\"]/option[4]")).click();
        driver.findElement(By.xpath("//*[@id=\"manageTeam\"]/div[2]/div/div/div/div[1]/div/div[2]/a")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("email0"))).sendKeys(TeamMemberEmail2);
        driver.findElement(By.id("role0")).click();
        driver.findElement(By.xpath("//*[@id=\"role0\"]/option[2]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/layout/div[1]/div[2]/div/app-newproject/div[2]/div/div/div/form/div[2]/div[3]/div/div/button[2]"))).click();
        Thread.sleep(3000);
    }
    @Test(priority = 7)
    public void playbookCreation(){
        driver.navigate().refresh();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"playlink\"]/a"))).click();
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"tab-first\"]/div[1]/div/div/button[2]"))).click();
        }catch (ElementClickInterceptedException exception6){
            System.out.println("Exception is " + exception6);
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.id("PlayBookName"))).sendKeys("TestAutoPB"+ random.nextInt(10));
        driver.findElement(By.id("btnNextToTeam")).click();
    }
    @Test(priority = 8)
    public void taskCreation()throws InterruptedException{
        int j = 1;
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"myTable\"]/tbody/tr/td[1]"))).click();
        for (int i=0; i<8;i++) {
            String TaskName = "Task" + j;
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/layout/div[1]/div[2]/div/app-playbook/div[2]/div[2]/div[2]/div[1]/div/div[1]/span[1]/i"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("IsActivity"))).sendKeys(TaskName);
            driver.findElement(By.xpath("//*[@id=\"task_\"]/td[7]/input")).sendKeys("100");
            if (i== 2){
                driver.findElement(By.id("ActivityType")).click();
                driver.findElement(By.xpath("//*[@id=\"ActivityType\"]/option[2]")).click();
            }

            if (i == 7){
                try {
                    driver.findElement(By.id("ActivityType")).click();
                    driver.findElement(By.xpath("//*[@id=\"ActivityType\"]/option[6]")).click();
                    Thread.sleep(5000);
//                    driver.findElement(By.xpath("//*[@id=\"task_\"]/td[10]/angular2-multiselect/div/div[1]/div/span[1]")).click();
//                    WebElement ele7 = driver.findElement(By.xpath("/html/body/app-root/layout/div[1]/div[2]/div/app-playbook/div[2]/div[2]/div[2]/div[2]/div/div/div[1]/table/tbody/tr[1]/td[10]/angular2-multiselect/div/div[2]/div[3]/div[1]/input"));
//                    wait.until(ExpectedConditions.elementToBeClickable(ele7)).click();
                    driver.findElement(By.xpath("//*[@id=\"milestonemodal\"]/div[2]/div/form/div/div/div[3]/div/button[1]/i")).click();
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/layout/div[1]/div[2]/div/app-playbook/div[2]/div[2]/div[2]/div[2]/div/div/div[1]/table/tbody/tr[1]/td[12]/input"))).sendKeys("07-02-2024");

                }
                catch (ElementClickInterceptedException exception8){
                    System.out.println("Exception is " + exception8);
                }
            }
            driver.findElement(By.xpath("//*[@id=\"task_\"]/td[21]/span/button[1]")).click();
            j++;
        }

    }
}
