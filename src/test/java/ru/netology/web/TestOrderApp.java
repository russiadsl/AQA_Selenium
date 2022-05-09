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

    private static WebDriver driver;

    @BeforeAll
    public static void setUp(){
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
    public void shouldTestCorrectData(){
        driver.get("http://localhost:9999");
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Евгений -Безуглый ");
        elements.get(1).sendKeys("+79144131520");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();//Отправить;
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.",text.trim());
    }

    @Test
    public void shouldTestFieldName(){
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type=text]")).sendKeys("Евгений_Безуглый*F");
        driver.findElement(By.cssSelector("[type=tel]")).sendKeys("+79144131520");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();//Отправить;
        String text = driver.findElement(By.className("input__sub")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.",text);
    }

//    @Test
//    public void shouldTestFieldPhone(){
//        driver.get("http://localhost:9999");
//        driver.findElement(By.cssSelector("[type=text]")).sendKeys("Евгений Безуглый");
//        driver.findElement(By.cssSelector("[type=tel]")).sendKeys("-9144131520");
//        driver.findElement(By.className("checkbox__box")).click();
//        driver.findElement(By.tagName("button")).click();//Отправить;
//        String text = driver.findElement(By.cssSelector("[input_invalid]")).getText();
//        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.",text);
//    }
}
