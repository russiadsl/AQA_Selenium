package ru.netology.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestOrderApp {

    private WebDriver driver;

    @BeforeAll
    public void setUp(){
        System.setProperty("webdriver.chrome.driver" , "driver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @BeforeEach
    void setUp2(){
        driver = new ChromeDriver();
    }

    @AfterEach
    public void close(){
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldTestForm(){
        driver.get("http://localhost:9999");
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Евгений");
        elements.get(1).sendKeys("+79144131520");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();//Отправить;
        String text = driver.findElement(By.className("alert-success")).getText();
        assertEquals("Ваша заявка успешно отправлена!",text.trim());
    }
}
