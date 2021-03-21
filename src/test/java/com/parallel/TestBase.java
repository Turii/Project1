package com.parallel;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class TestBase extends TestSettings {
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public WebDriver driver;
    public WebDriverWait wait;

    By popularProducts = By.cssSelector("#box-popular-products .product-column");

    Random random = new Random();
    protected By itemsCountLabel = By.cssSelector(".badge.quantity");
    By addToCartBtn = By.cssSelector("[name=add_cart_product]");
    By openCart = By.cssSelector("#cart");
    By deleteItemBtn = By.cssSelector("[name='remove_cart_item']");
    By noItemTxt = By.xpath("//*[text()='There are no items in your cart.']");
    By cartItemsTable = By.cssSelector(".items.list-unstyled");
    String popularProductxPath = "//*[@id='box-popular-products']//*[@class='product-column'][%s]";


    @BeforeEach
    public void start() {

        WebDriverManager.chromedriver().setup();

        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, 10);
            return;
        }

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);

        tlDriver.set(driver);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    driver.quit();
                    driver = null;
                }));
    }


    public boolean isElementPresent(By element) {
        return driver.findElements(element).size() > 0;
    }


    public void addremoveItems() {
        int itemsCount = 3;
        addProductToTheCart(itemsCount);
        assertThat(driver.findElement(itemsCountLabel).getText(), is(String.valueOf(itemsCount)));

        cleanUpCart();
    }




    private void addProductToTheCart(Integer itemsCount) {

        for (int i = 1; i <= itemsCount; i++) {
            addRandomProduct();
            wait.until(elementToBeClickable(addToCartBtn)).click();
            wait.until(textToBePresentInElementLocated(itemsCountLabel, String.valueOf(i)));
            driver.get(getBASE_URL());
        }

    }


    private void addRandomProduct() {
        int items = driver.findElements(popularProducts).size();
        WebElement item = wait.until(elementToBeClickable((By.xpath(String.format(popularProductxPath, random.nextInt(items) + 1)))));
        new Actions(driver).moveToElement(item).pause(500).click(item).perform();
    }

    private void cleanUpCart() {
        wait.until(elementToBeClickable(openCart)).click();

        while (!isElementPresent(noItemTxt)) {
            WebElement table = wait.until(elementToBeClickable(cartItemsTable));
            wait.until(elementToBeClickable(deleteItemBtn)).click();
            wait.until(stalenessOf(table));
        }

        driver.get(getBASE_URL());
        isBasketEmpty();

    }


    public boolean isBasketEmpty() {

        System.out.println("1233456677");
        return wait.until(ExpectedConditions.textToBe(By.cssSelector(".badge.quantity"), ""));

    }
}