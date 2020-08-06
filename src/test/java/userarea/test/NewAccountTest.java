package userarea.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class NewAccountTest {
    WebDriver driver;

    @BeforeMethod
    public void setUpBrowser(){
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://shop.pragmatic.bg");
    }
    @Test
    public void userAccountCreationTest(){
        //driver.findElement(By.xpath("//i[@class='fa fa-user']")).click();
        //driver.findElement(By.xpath("//li[@class='dropdown open']")).click();

        // znam che ne se pravi taka, no zabih ujasno tuk, a mi se iska da produlja

        driver.get("http://shop.pragmatic.bg/index.php?route=account/register");
        //WebElement accountButton = driver.findElement(By.xpath("//li[@class='dropdown']//i]"));
        //accountButton.click();
        //driver.findElement(By.xpath("//li[@class='dropdown open']//li[1]")).click();

        String randomEmail = RandomStringUtils.randomAlphanumeric(7) + "@aol.com";

        driver.findElement(By.name("firstname")).sendKeys("Sandra");
        driver.findElement(By.name("lastname")).sendKeys("Aleksandrova");
        driver.findElement(By.name("email")).sendKeys(randomEmail);
        driver.findElement(By.name("telephone")).sendKeys("12364584870");
        driver.findElement(By.name("password")).sendKeys("ho1ho1");
        driver.findElement(By.name("confirm")).sendKeys("ho1ho1");
        driver.findElement(By.xpath("//input[@name='agree']")).click();
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        driver.findElement(By.xpath("//div[@class='pull-right']/a")).click();
        driver.findElement(By.xpath("//div[@id='content']//a[text()='Modify your address book entries']")).click();
        driver.findElement(By.xpath(" //div[@class='pull-right']/a[text()='New Address']")).click();

        driver.findElement(By.name("firstname")).sendKeys("Sandra");
        driver.findElement(By.name("lastname")).sendKeys("Aleksandrova");
        driver.findElement(By.name("address_1")).sendKeys("Dunav 7");
        driver.findElement(By.name("city")).sendKeys("Sofia");
        driver.findElement(By.name("postcode")).sendKeys("1000");
        Select country = new Select(this.driver.findElement(By.name("country_id")));
        country.selectByValue("33");
        Select region = new Select(this.driver.findElement(By.name("zone_id")));
        region.selectByValue("498");
        driver.findElement(By.xpath(" //div[@class='pull-right']/input[@type='submit']")).click();

        Assert.assertEquals(this.driver.findElement(By.xpath("//div[@class='table-responsive']")).getText(), "Sandra Aleksandrova\n" +
                        "Dunav 7\n" +
                        "Sofia 1000\n" +
                        "Sofia - town\n" +
                        "Bulgaria Edit   Delete");
                //div[@class='alert alert-success alert-dismissible'/i[text()='Your address has been successfully added']")).getText(),"Your address has been successfully added");



        //driver.findElement(By.linkText("Modify address book entries")).click();




    }


}
