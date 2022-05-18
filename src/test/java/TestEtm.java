//import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestEtm{

  public RemoteWebDriver driver;
  //private static final String SELENIUM_URL = "http://localhost:4444/wd/hub";
  String SITE_URL="https://idev.etm.ru/ipro3";
  private static final String SELENIUM_URL = "http://jenkins.etm.corp:4444/wd/hub";
 //String SITE_URL="http://jenkins.etm.corp:85/";

  @BeforeEach
  public  void start() throws Exception{
    //WebDriverManager.chromedriver().setup();
    //ChromeOptions options= new ChromeOptions();
    //options.setHeadless(false);
    //driver= new ChromeDriver(options);
    driver = new RemoteWebDriver(
            new URL(SELENIUM_URL),
            new ChromeOptions()
    );

            driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @AfterEach
  public void finish(){
   driver.quit();
 }
  @Epic("Testing FOR https://idev.etm.ru/ipro3/ tasks ")
  @Feature(value= "Tests for task-basket functionality for market-user")
  @Severity(SeverityLevel.BLOCKER)
  @Description("проверка формы добавить товар-" +
          "1-вводим логин пароль-" +
          "2-переходим в каталог ,добавляем товар -10 шт" +
          "3-переходим в корзину" +
          "4-жмем ссылку условия предоставления скидок...закрываем поп-ап"+
          "5-кликаем оформить заказ "+
          "6-проверяем появление способа получения"+
          "7-выбираем СДЭк"+
          "8-выбираем точку получения"+
          "9-заполняем телеофн и ФИО-подтверждаем"+
          "10-Выбираем оплату при получении и жмем оформить заказ"+
          "11-переходим в заказы")
  @Test


  public void test12MakingAnOrderByCDEKPayUponReceipt(){
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
      WebElement button = driver.findElement(By.xpath("//a[@href='/catalog']"));
      button.click();
    }
    catch(org.openqa.selenium.StaleElementReferenceException ex)
    {
      WebElement button = driver.findElement(By.xpath("//a[@href='/catalog']"));
      button.click();
    }




    driver.findElement(By.xpath("(//div[@data-testid='catalog-item-product-1']//input[@value=''])[1]")).click();
    driver.findElement(By.xpath("(//div[@data-testid='catalog-item-product-1']//input[@value=''])[1]")).sendKeys("10");
    driver.findElement(By.xpath("//div[@data-testid='catalog-item-product-1']//span[@class='MuiButton-label']")).click();
    driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root jss4']")).click();
    driver.findElement(By.xpath("//p[@data-testid='go-to-basket']")).click();

    driver.findElement(By.xpath("//*[@data-testid='go-checkout-btn']")).click();
    waitForElementPresent(By.xpath("//div[contains(text(),'Самовывоз ЭТМ')]"),"the PickUp is not issued",5);
    driver.findElement(By.xpath("//*[contains(.,'Самовывоз ЭТМ')]")).click();
    //driver.findElement(By.xpath("//div[@data-testid='option-delivery']")).click();
    clickVisible(By.xpath("//div[contains(.,'пос. Шушары, Ленсоветовская дорога, д.12, корп.2, лит.Б')]") ,"not click delivery point", 5 );
    waitForElementPresent(By.xpath("//div[contains(text(),'пос. Шушары, Ленсоветовская дорога, д.12, корп.2, лит.Б')]"),"the delivery point is not issued",5);
    driver.findElement(By.xpath("//div[contains(text(),'пос. Шушары, Ленсоветовская дорога, д.12, корп.2, лит.Б')]")).click();
    driver.findElement(By.xpath("//div[contains(text(),'пос. Шушары, Ленсоветовская дорога, д.12, корп.2, лит.Б')]")).click();

    driver.findElement(By.xpath("//div[contains(text(),'При получении')]")).click();
    driver.findElement(By.xpath("//span[contains(.,'Оформить заказ')]")).click();
    waitForElementPresent(By.xpath("//p[contains(.,'Благодарим вас за использование системы iPRO!')]"),"the order is not issued",5);
    driver.findElement(By.xpath("//span[contains(.,'Перейти в Заказы')]")).click();

    driver.findElement(By.xpath("//button[@title='Выход']")).click();
  }
  @Test


  public void test13MakingAnOrderByPickUpByWriteScore(){
    driver.get(SITE_URL);
    driver.findElement(By.xpath("//span[contains(.,'Все понятно')]")).click();
    driver.findElement(By.xpath("//span[contains(.,'Все верно')]")).click();
    driver.findElement(By.xpath("//span[contains(.,'Вход / Регистрация')]")).click();
    driver.findElement(By.name("login")).clear();
    driver.findElement(By.name("login")).sendKeys("60004392kal");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("hzlc3549");
    driver.findElement(By.xpath("//span[contains(.,'Войти в систему')]")).click();

    try {
      WebElement button = driver.findElement(By.xpath("//a[@href='/catalog']"));
      button.click();
    }
    catch(org.openqa.selenium.StaleElementReferenceException ex)
    {
      WebElement button = driver.findElement(By.xpath("//a[@href='/catalog']"));
      button.click();
    }
   /* try {
      WebElement button = driver.findElement(By.xpath("//a[@data-testid='top-menu-catalog']"));
      button.click();
    }
    catch(org.openqa.selenium.StaleElementReferenceException ex)
    {
      WebElement button = driver.findElement(By.xpath("//a[@data-testid='top-menu-catalog']"));
      button.click();
    }*/



    driver.findElement(By.xpath("(//div[@data-testid='catalog-item-product-1']//input[@value=''])[1]")).click();
    driver.findElement(By.xpath("(//div[@data-testid='catalog-item-product-1']//input[@value=''])[1]")).sendKeys("10");
    driver.findElement(By.xpath("//div[@data-testid='add-basket-button-1']")).click();
    driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root jss4']")).click();
    driver.findElement(By.xpath("//p[@data-testid='go-to-basket']")).click();

    driver.findElement(By.xpath("//*[@data-testid='go-checkout-btn']")).click();
    waitForElementPresent(By.xpath("//div[contains(text(),'Самовывоз ЭТМ')]"),"the PickUp is not issued",5);
    //driver.findElement(By.xpath("//div[contains(text(),'Самовывоз ЭТМ')]")).click();
    /*driver.findElement(By.xpath("//img[@src='/assets/img/logo_etm_blue.png']")).click();
    driver.findElement(By.xpath("//img[@src='/assets/img/logo_etm_blue.png']")).click();
    clickVisible(By.xpath("//div[contains(.,'пос. Шушары, Ленсоветовская дорога, д.12, корп.2, лит.Б')]") ,"not click delivery point", 5 );
    waitForElementPresent(By.xpath("//div[contains(text(),'пос. Шушары, Ленсоветовская дорога, д.12, корп.2, лит.Б')]"),"the delivery point is not issued",5);
    driver.findElement(By.xpath("//div[contains(text(),'пос. Шушары, Ленсоветовская дорога, д.12, корп.2, лит.Б')]")).click();
    driver.findElement(By.xpath("//div[contains(text(),'пос. Шушары, Ленсоветовская дорога, д.12, корп.2, лит.Б')]")).click();*/

    driver.findElement(By.xpath("//div[@data-testid='option-payment-1']")).click();
    driver.findElement(By.xpath("//span[contains(.,'Оформить заказ')]")).click();
    driver.findElement(By.xpath("//input[@type='text']")).click();
    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("test");
    driver.findElement(By.xpath("//button[@data-testid='closePopup']")).click();
    waitForElementPresent(By.xpath("//p[contains(.,'Благодарим вас за использование системы iPRO!')]"),"the order is not issued",5);
    driver.findElement(By.xpath("//span[contains(.,'Перейти в Документы')]")).click();

    driver.findElement(By.xpath("//button[@title='Выход']")).click();
  }
  private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");

    return wait.until(
            ExpectedConditions.presenceOfElementLocated(by)
    );
  }
  /*private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
    element.click();

    return element;

  }
  private WebElement waitForElementAndClickable(By by, String error_message, long timeoutInSeconds){
    WebDriverWait wait=new WebDriverWait(driver, 10);
    wait.withMessage(error_message + "\n");
    return wait.until
            (ExpectedConditions.elementToBeClickable(by));

  }


  private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
    element.sendKeys(value);

    return element;

  }

  private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");

    return wait.until(
            ExpectedConditions.invisibilityOfElementLocated(by)
    );
  }

  private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
    element.clear();
    return element;
  }
  private WebElement waitForElementLocated(By by, String error_message, long timeoutInSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");

    return wait.until(
            ExpectedConditions.visibilityOfElementLocated(by)
    );
  }*/
  public void clickVisible(By by ,String error_message, long timeoutInSeconds ){
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.until(ExpectedConditions.elementToBeClickable(by));
    wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    final Actions actions = new Actions(driver);
    actions.moveToElement(driver.findElement(by)).click().perform();
  }
  }


