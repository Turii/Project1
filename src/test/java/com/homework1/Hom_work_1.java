package com.homework1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Hom_work_1 {
    WebDriver driver;
    WebDriverWait wait;
    //WebDriver drv;
    String URL;


    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        URL = "http://158.101.173.161/admin";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();


    }

    @AfterEach
    public void stop() {
        //driver.quit();
    }


    @Test
    void AdminLogin() {
        driver.get(URL);


        driver.findElement(By.cssSelector("input[name='username']")).click();
        driver.findElement(By.cssSelector("input[name='username']")).clear();
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("testadmin");

        driver.findElement(By.cssSelector("input[name='password']")).click();
        driver.findElement(By.cssSelector("input[name='password']")).clear();
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("R8MRDAYT_test");

        driver.findElement(By.cssSelector("button[name$='login']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("ul#box-apps-menu li[data-code=appearance]")));


        /*List<WebElement> elements = driver.findElements(By.xpath("//span[@class='name']"));
        System.out.println("Number of elements:" +elements.size());

        String[] getelems = new String[elements.size()];*/
        String[] titlelems = new String[18];
        for (int i=0; i<17;i++){
            titlelems[i] = driver.getTitle();
            //getelems[i] = "//li[@class='app'][" + i + "+1]";
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='app'][" + i + "+1]")));
            driver.findElement(By.xpath("//li[@class='app'][" + i + "+1]")).click();
            //driver.findElement(By.xpath(".//li[@class='doc']")).click();

            String title = driver.getTitle();

            if (isElementPresent(driver, By.xpath("//div[@class='panel-heading']"))) {
                //driver.findElement(By.xpath(".//*[@class='panel-heading']")).click();

                System.out.println(title + " is present");
            } else {
                System.out.println(title + " is absent");
            }
            //driver.navigate().back();

            System.out.println("Elements found:" + titlelems[i]);
        }


        /*driver.findElement(By.cssSelector("ul#box-apps-menu li[data-code=appearance]")).click();
        String title = driver.getTitle();

        if (isElementPresent(driver, By.xpath("//div[@class='panel-heading']"))) {

            System.out.println(title + " is present");
        } else {
            System.out.println(title + " is absent");
        }*/
        driver.navigate().back();
    }


    public boolean isElementPresent(WebDriver driver, By locator) {

        return driver.findElements(locator).size() > 0;
    }

}
