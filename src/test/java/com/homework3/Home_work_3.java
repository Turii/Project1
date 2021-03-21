package com.homework3;

import com.parallel.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Home_work_3 extends TestBase {
    //WebDriver driver;
    //WebDriverWait wait;
    //WebDriver drv;
    //String BASE_URL = "http://158.101.173.161";;
    //String LOGIN_NAME = "testadmin";
    //String LOGIN_PASS = "R8MRDAYT_test";



    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
        //wait = new WebDriverWait(driver, 10);
        //event_driver = new EventFiringWebDriver(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();


        loginToAdminPane(driver);

    }


    @Test
    void openedWindow() {

        By elemLink = By.className("fa fa-external-link");
        driver.get(getBASE_URL() + "/admin/?app=countries&doc=edit_country");
        //driver.findElement(By.xpath(".//a[contains(text(), 'Wikipedia')] ")).click();

        for (WebElement linkArrow : driver.findElements(By.cssSelector(".fa.fa-external-link"))) {

            String originalW = driver.getWindowHandle();
            Set<String> existWs = driver.getWindowHandles();
            linkArrow.click();
            String newW = wait.until(anyWindowOtherThan(existWs));
            driver.switchTo().window(newW);

            assertThat(driver.getTitle(), containsString("Wikipedia"));

            driver.close();
            driver.switchTo().window(originalW);

            assertThat(driver.getTitle(), containsString("Add New Country"));

        }

    }


    public ExpectedCondition<String> anyWindowOtherThan(Set<String> windows) {
        return input -> {
            Set<String> handles = driver.getWindowHandles();
            handles.removeAll(windows);
            return handles.size() > 0 ? handles.iterator().next() : null;
        };
    }

}
