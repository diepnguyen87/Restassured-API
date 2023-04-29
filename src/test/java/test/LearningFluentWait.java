package test;

import model.RequestCapility;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class LearningFluentWait implements RequestCapility {

    private WebDriver driver;
    private FluentWait<WebElement> fluentWaitWebElement;
    private FluentWait<WebDriver> fluentWaitDriver;

    @BeforeTest
    public void BeforeTest() {
        driver = new ChromeDriver();
        driver.get("https://automationfc.github.io/fluent-wait/");
    }

    @Test
    public void TC_01(){
        WebElement countdownTime = driver.findElement(By.id("javascript_countdown_time"));
        fluentWaitWebElement = new FluentWait<>(countdownTime);
        fluentWaitWebElement.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .until((x) -> {
                    String text = x.getText();
                    System.out.println("Time: " + text);
                    return text.endsWith("00");
                });
    }

    @Test
    public void TC_02(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();

        fluentWaitDriver = new FluentWait<>(driver);
        fluentWaitDriver.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .until((driver) -> {
                    WebElement element = driver.findElement(By.cssSelector("div#finish>h4"));
                    return element.getText().equalsIgnoreCase(">Hello World!");
                });
    }

    @Test
    public void TC_03(){
        driver.get("https://admin-demo.nopcommerce.com");
    }

    @AfterTest(alwaysRun = true)
    public void AfterTest() {
        if(driver != null){
            driver.quit();
        }
    }
}
