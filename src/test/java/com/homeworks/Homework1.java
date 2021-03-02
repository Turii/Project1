package com.homeworks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Homework1 {
    WebDriver driver;
    String URL;


    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        URL = "http://158.101.173.161/";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        //driver.quit();
    }

    @Test
    void DuckTestXPath() {
        driver.get(URL);

        //Task1 Manufacture dropdown
        driver.findElement(By.xpath("//*[@class='manufacturers dropdown']")).click();


        //Task2 Select yellow duck from 5 "recently viewed"
          //Preconditions for task2. pick up 5 ducks to "recently viewed"
        driver.findElement(By.xpath("//section[@id='box-popular-products']//a[@title='Blue Duck']")).click();
        driver.navigate().back();

        driver.findElement(By.xpath("//section[@id='box-popular-products']//a[@title='Green Duck']")).click();
        driver.navigate().back();

        driver.findElement(By.xpath("//section[@id='box-popular-products']//a[@title='Red Duck']")).click();
        driver.navigate().back();

        driver.findElement(By.xpath("//section[@id='box-popular-products']//a[title='Yellow Duck']")).click();
        driver.navigate().back();

        driver.findElement(By.xpath("//section[@id='box-popular-products']//a[@title='Purple Duck']")).click();
        driver.navigate().back();

        //The main step of Task2 Select yellow duck from 5 "recently viewed"
        driver.findElement(By.xpath("//section[@id='box-recently-viewed-products']//a[@title='Yellow Duck']")).click();
        driver.navigate().back();


        //Task3 Select green duck from 5 "Popular Products"
        driver.findElement(By.xpath("//section[@id='box-popular-products']//a[@title='Green Duck']")).click();
        driver.navigate().back();
    }

    @Test
    void SignInTestXPath() {
        driver.get(URL);

        //Task4 Click on Sign in
        driver.findElement(By.xpath("//ul[@class='nav navbar-nav navbar-right']/li[@class='account dropdown']")).click();

        driver.findElement(By.xpath("//ul[@class='dropdown-menu']//input[@*='email']")).click();
        driver.findElement(By.xpath("//ul[@class='dropdown-menu']//input[@*='email']")).clear();
        driver.findElement(By.xpath("//ul[@class='dropdown-menu']//input[@*='email']")).sendKeys("blablabla");

        driver.findElement(By.xpath("//ul[@class='dropdown-menu']//input[@*='password']")).click();
        driver.findElement(By.xpath("//ul[@class='dropdown-menu']//input[@*='password']")).clear();
        driver.findElement(By.xpath("//ul[@class='dropdown-menu']//input[@*='password']")).sendKeys("passowrd");

        driver.findElement(By.xpath(" //ul[@class='nav navbar-nav navbar-right']//ul[@class='dropdown-menu']//a[contains(text(),'New customers click here')]")).click();
    }

    @Test
    void DuckTestCSSselectors() {
        driver.get(URL);
        //Task1 Manufacture dropdown
        driver.findElement(By.cssSelector(".manufacturers.dropdown"));

        //Task2 Select yellow duck from 5 "recently viewed"
        //Preconditions for task2. pick up 5 ducks to "recently viewed"
        driver.findElement(By.cssSelector("section#box-popular-products a[title='Blue Duck']")).click();
        driver.navigate().back();

        driver.findElement(By.cssSelector("section#box-popular-products a[title='Green Duck']")).click();
        driver.navigate().back();

        driver.findElement(By.cssSelector("section#box-popular-products a[title='Red Duck']")).click();
        driver.navigate().back();

        driver.findElement(By.cssSelector("section#box-popular-products a[title='Yellow Duck']")).click();
        driver.navigate().back();

        driver.findElement(By.cssSelector("section#box-popular-products a[title='Purple Duck']")).click();
        driver.navigate().back();

        //The main step of Task2 Select yellow duck from 5 "recently viewed"
        driver.findElement(By.cssSelector("section#box-recently-viewed-products a[title='Yellow Duck']")).click();
        driver.navigate().back();

        //Task3 Select green duck from 5 "Popular Products"
        driver.findElement(By.cssSelector("section#box-popular-products a[title='Green Duck']")).click();
       // driver.navigate().back();
    }

    @Test
    void SignInTestCSSselectors() {
        driver.get(URL);

        //Task4 Click on Sign in
        driver.findElement(By.cssSelector("ul.nav.navbar-nav.navbar-right > li.account.dropdown")).click();

        driver.findElement(By.cssSelector("ul.dropdown-menu input[name='email']")).click();
        driver.findElement(By.cssSelector("ul.dropdown-menu input[name='email']")).clear();
        driver.findElement(By.cssSelector("ul.dropdown-menu input[name='email']")).sendKeys("blablabla");

        driver.findElement(By.cssSelector("ul.dropdown-menu input[name='password']")).click();
        driver.findElement(By.cssSelector("ul.dropdown-menu input[name='password']")).clear();
        driver.findElement(By.cssSelector("ul.dropdown-menu input[name='password']")).sendKeys("password");

        driver.findElement(By.cssSelector("ul.nav.navbar-nav.navbar-right ul.dropdown-menu a[href$='account']")).click();

    }



    }
