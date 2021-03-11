package com.homework1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
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
    String BASE_URL = "http://158.101.173.161";;
    String LOGIN_NAME = "testadmin";
    String LOGIN_PASS = "R8MRDAYT_test";


    @BeforeEach
    public void startBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.manage().window().maximize();
        adminLogin();

    }

    @Test
    void menuNavigationTest() {
        By menuItemsLocator = By.className("app");
        By subMenuItemsLocator = By.className("doc");
        By panelHeadingLocator = By.className("panel-heading");

        for (int menuIterator = 1; menuIterator <= driver.findElements(menuItemsLocator).size(); menuIterator++){
            driver.findElement(By.xpath("//li[contains(@class, 'app')]["+menuIterator+"]")).click();
            Assertions.assertTrue(isElementPresent(panelHeadingLocator), "Heading not found");

            for (int submenuIterator = 1; submenuIterator <= driver.findElements(subMenuItemsLocator).size(); submenuIterator++)
            {
                driver.findElement(By.xpath("//li[contains(@class, 'doc')]["+submenuIterator+"]")).click();
                Assertions.assertTrue(isElementPresent(panelHeadingLocator), "Heading not found");


            }
        }
    }

    @AfterEach
    public void stop() {
        //driver.quit();
    }


    @Test
    void adminLogin() {


        By username = By.cssSelector("input[name='username']");
        By password = By.cssSelector("input[name='password']");
        By loginBtn = By.cssSelector("button[name=login");

        driver.get(BASE_URL + "/admin");

        if (isElementPresent(username)) {
            driver.findElement(username).sendKeys(LOGIN_NAME);
            driver.findElement(password).sendKeys(LOGIN_PASS);
            driver.findElement(loginBtn).click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.id("box-apps-menu")));
        pause(1);
    }


        boolean isElementPresent(By element) { return driver.findElements(element).size() > 0; }


        void pause(int sec) {
            try {
                Thread.sleep(sec*1000);
            }   catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

}
