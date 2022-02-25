import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class TestEtm {

  String SITE_URL="https://idev.etm.ru/ipro3";
  WebDriver driver;
  String close_form_add="//span[contains(.,'close')]";


  @BeforeEach
  public  void start(){
    WebDriverManager.chromedriver().setup();
    ChromeOptions options= new ChromeOptions();
    options.setHeadless(false);
    driver= new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }
  @AfterEach
  public void finish(){
    driver.quit();
  }
  @Epic("Testing FOR https://idev.etm.ru/ipro3/ tasks ")
  @Feature(value= "Tests for task-basket functionality for market-user")
  @Severity(SeverityLevel.MINOR)
  @Description("проверка формы добавить товар-" +
          "1-вводим логин пароль-" +
          "2-переходим в корзину-" +
          "3-кликаем на форму Добавить товар-" +
          "4-проверяем все поля формы на кликабельность"+
          "5-закрытие формы")
  @Test


  public void test1AproverkaKlikaFieldsOfFormi() {
    driver.get(SITE_URL);
    driver.findElement(By.xpath("//span[contains(.,'Все понятно')]")).click();
    driver.findElement(By.xpath("//span[contains(.,'Все верно')]")).click();
    driver.findElement(By.xpath("//span[contains(.,'Вход / Регистрация')]")).click();
    driver.findElement(By.name("login")).clear();
    driver.findElement(By.name("login")).sendKeys("9216572712");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("qakras123");
    driver.findElement(By.xpath("//span[contains(.,'Войти в систему')]")).click();

    try {
      WebElement button = driver.findElement(By.xpath("//p[@data-testid='go-to-basket']"));
      button.click();
    } catch (org.openqa.selenium.StaleElementReferenceException ex) {
      WebElement button = driver.findElement(By.xpath("//p[@data-testid='go-to-basket']"));
      button.click();
    }

    driver.findElement(By.xpath("//*[@data-testid ='action-item-1']")).click();
    driver.findElement(By.xpath("(//input[@value=''])[2]")).click();
    driver.findElement(By.xpath("(//input[@value=''])[3]")).click();
    driver.findElement(By.xpath("(//input[@value=''])[4]")).click();
    driver.findElement(By.xpath("//input[@value='1']")).click();
    driver.findElement(By.xpath(close_form_add)).click();
    driver.findElement(By.xpath("//button[@title='Выход']")).click();

  }
  }