package com.homework1;

import com.parallel.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Random;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Home_work_2 extends TestBase {

    //Random random = new Random();



    //By itemsCountLabel = By.cssSelector(".badge.quantity");
    /*By popularProducts = By.cssSelector("#box-popular-products .product-column");

    By addToCartBtn = By.cssSelector("[name=add_cart_product]");
    By openCart = By.cssSelector("#cart");
    By deleteItemBtn = By.cssSelector("[name='remove_cart_item']");
    By noItemTxt = By.xpath("//*[text()='There are no items in your cart.']");
    By cartItemsTable = By.cssSelector(".items.list-unstyled");
    String popularProductxPath = "//*[@id='box-popular-products']//*[@class='product-column'][%s]";*/


    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver.get(getBASE_URL());

            if (isElementPresent(By.name("decline_cookies"))) {
                driver.findElement(By.name("decline_cookies")).click();
            }


    }




    @Test
    void addRemoveItemsTest() {
        addremoveItems();


        driver.get(getBASE_URL());
        isBasketEmpty();
        assertThat(driver.findElement(itemsCountLabel).getText(), is(""));
    }





}
