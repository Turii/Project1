package com.homework3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Home_work_3 {
    WebDriver driver;
    WebDriverWait wait;
    //WebDriver drv;
    String BASE_URL = "http://158.101.173.161";;
    String LOGIN_NAME = "testadmin";
    String LOGIN_PASS = "R8MRDAYT_test";


    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        //event_driver = new EventFiringWebDriver(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        adminLogin();
    }


    @Test
    void openedWindow() {

        By elemLink = By.className("fa fa-external-link");

        driver.navigate().to("http://158.101.173.161/admin/?app=countries&doc=countries");
        driver.findElement(By.xpath(".//a[contains(text(), 'Afghanistan')] ")).click();

        String originalW = driver.getWindowHandle();
        Set<String> existWs = driver.getWindowHandles();

        for (int iterator = 1; iterator <= driver.findElements(elemLink).size(); iterator++) {
            driver.findElement(By.xpath("elemLink")).click();
            String newW = wait.until(anyWindowOtherThan(existWs));
            driver.switchTo().window(newW);
            driver.close();
            driver.switchTo().window(originalW);
        }

    }


    public ExpectedCondition<String> anyWindowOtherThan(Set<String> windows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver input) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(windows);

                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
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
    @AfterEach
    public void stop() {
        driver.close();
        driver.quit();
    }
}
