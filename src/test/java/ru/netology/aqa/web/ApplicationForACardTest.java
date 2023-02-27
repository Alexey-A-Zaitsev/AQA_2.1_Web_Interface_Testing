package ru.netology.aqa.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationForACardTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();;
    }

    @BeforeEach
    public void setUp() {
        //driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    // Задание 1. Позитивные тесты
    @Test
    public void shouldSuccessfulApplication() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("input[type = \"text\"]")).sendKeys("Симонов Иван");
        driver.findElement(By.cssSelector("input[type = \"tel\"]")).sendKeys("+79881234567");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.tagName("p")).getText();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldTakeADoubleName() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("input[type = \"text\"]")).sendKeys("Цезарь Гай Юлий");
        driver.findElement(By.cssSelector("input[type = \"tel\"]")).sendKeys("+79881234567");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.tagName("p")).getText();
        assertEquals(expected, actual);

    }

    @Test
    public void shouldAcceptAHyphenatedName() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("input[type = \"text\"]")).sendKeys("Петрова Анна-Мария");
        driver.findElement(By.cssSelector("input[type = \"tel\"]")).sendKeys("+79881234567");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.tagName("p")).getText();
        assertEquals(expected, actual);

    }

    @Test
    public void shouldTakeANameWithSymbolYo() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("input[type = \"text\"]")).sendKeys("Петров Фёдор");
        driver.findElement(By.cssSelector("input[type = \"tel\"]")).sendKeys("+79881234567");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.tagName("p")).getText();
        assertEquals(expected, actual);

    }

    @Test
    public void shouldTakeTheNameInLowerCase() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("input[type = \"text\"]")).sendKeys("зленко михаил");
        driver.findElement(By.cssSelector("input[type = \"tel\"]")).sendKeys("+79881234567");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.tagName("p")).getText();
        assertEquals(expected, actual);

    }

    @Test
    public void shouldTakeTheNameInUpperCase() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("input[type = \"text\"]")).sendKeys("ТАРАСОВ БОГДАН");
        driver.findElement(By.cssSelector("input[type = \"tel\"]")).sendKeys("+79881234567");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.tagName("p")).getText();
        assertEquals(expected, actual);

    }

    // Задание 1. Негативные тесты

    @Test
    public void invalidDataEntryInTheNameField1() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("input[type = \"text\"]")).sendKeys("Ivanov Ivan");
        driver.findElement(By.cssSelector("input[type = \"tel\"]")).sendKeys("+79881234567");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.className("input__sub")).getText();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryInTheNameField2() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("input[type = \"text\"]")).sendKeys("123456789 87654");
        driver.findElement(By.cssSelector("input[type = \"tel\"]")).sendKeys("+79881234567");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.className("input__sub")).getText();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryInTheNameField3() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("input[type = \"text\"]")).sendKeys("Кузнецов Юрий$$$");
        driver.findElement(By.cssSelector("input[type = \"tel\"]")).sendKeys("+79881234567");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.className("input__sub")).getText();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFillInTheNameField() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("input[type = \"text\"]")).sendKeys("");
        driver.findElement(By.cssSelector("input[type = \"tel\"]")).sendKeys("+79881234567");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.className("input__sub")).getText();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryInThePhoneField1() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("input[type = \"text\"]")).sendKeys("Зленко Михаил");
        driver.findElement(By.cssSelector("input[type = \"tel\"]")).sendKeys("+восемьвосемьсот");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Мобильный телефон\n" + "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.className("input_invalid")).getText();
        assertEquals(expected, actual);

    }

    @Test
    public void invalidDataEntryInThePhoneField2() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("input[type = \"text\"]")).sendKeys("Зленко Михаил");
        driver.findElement(By.cssSelector("input[type = \"tel\"]")).sendKeys("+12345678912345678");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Мобильный телефон\n" + "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.className("input_invalid")).getText();
        assertEquals(expected, actual);

    }

    @Test
    public void invalidDataEntryInThePhoneField3() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("input[type = \"text\"]")).sendKeys("Зленко Михаил");
        driver.findElement(By.cssSelector("input[type = \"tel\"]")).sendKeys("+7988123456&");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Мобильный телефон\n" + "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.className("input_invalid")).getText();
        assertEquals(expected, actual);

    }

    @Test
    public void invalidDataEntryInThePhoneField4() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("input[type = \"text\"]")).sendKeys("Зленко Михаил");
        driver.findElement(By.cssSelector("input[type = \"tel\"]")).sendKeys("+7988One23456");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Мобильный телефон\n" + "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.className("input_invalid")).getText();
        assertEquals(expected, actual);

    }

    @Test
    public void invalidDataEntryInThePhoneField5() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("input[type = \"text\"]")).sendKeys("Зленко Михаил");
        driver.findElement(By.cssSelector("input[type = \"tel\"]")).sendKeys("+7988123456");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Мобильный телефон\n" + "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.className("input_invalid")).getText();
        assertEquals(expected, actual);

    }

    @Test
    public void invalidDataEntryInThePhoneField6() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("input[type = \"text\"]")).sendKeys("Зленко Михаил");
        driver.findElement(By.cssSelector("input[type = \"tel\"]")).sendKeys("89881234567");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Мобильный телефон\n" + "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.className("input_invalid")).getText();
        assertEquals(expected, actual);

    }

    @Test
    public void invalidDataEntryInThePhoneField7() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("input[type = \"text\"]")).sendKeys("Зленко Михаил");
        driver.findElement(By.cssSelector("input[type = \"tel\"]")).sendKeys("");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Мобильный телефон\n" + "Поле обязательно для заполнения";
        String actual = driver.findElement(By.className("input_invalid")).getText();
        assertEquals(expected, actual);

    }

}
