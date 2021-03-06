package com.infoapi.Crawling_Use_Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class CrawlingUseSeleniumApplicationTests {

    private ChromeDriver driver;

    @BeforeClass
    public void beforeClass() {
        // Khởi tạo trình duyệt Firefox
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--disable-notifications");
        ops.addArguments("start-maximized");
        ops.addArguments("disable-infobars");
        // ops.addArguments("--user-data-dir=F:\\Profile");  //đường đẫn đến profile
        ops.addArguments("profile-directory=Profile 8");
        //    ops.addArguments("headless");               // chạy ngầm
        // ops.addArguments("window-size=1200x600"); // set kích thước

        driver = new ChromeDriver(ops);

    }

    @Test
    public void openBrowser1() {
        driver.navigate().to("http://google.com.vn");
        Dimension size = new Dimension(1024, 768);
        driver.manage().window().setSize(size);
    }

    @Test
    public void openBrowser2() {
        driver.navigate().to("https://vi-vn.facebook.com");
        Dimension size = new Dimension(1280, 768);
        driver.manage().window().setSize(size);
    }

    @Test
    public void openBrowser3() throws InterruptedException {
        driver.navigate().to("http://google.com.vn");
        driver.manage().window().maximize();

    }

    @Test
    public void openBrowser4() throws InterruptedException {
        driver.navigate().to("https://www.howkteam.vn/search");
        //driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);

        driver.navigate().refresh();
//        driver.findElement(By.xpath("//button[@class='btn-block-option']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[@data-action='header_search_on']")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.name("q")).sendKeys("android");
        List<WebElement> driverList = driver.findElements(By.xpath("//button[@class='btn btn-secondary']"));
        System.out.println(driverList.size() + "----------");
        driverList.get(1).click();

    }

    @Test
    public void openBrowser5() throws InterruptedException {
        driver.navigate().to("https://vnexpress.net/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        ((JavascriptExecutor)
                driver).executeScript("window.scrollTo( 0, document.body.scrollHeight)");

    }

    @Test
    public void openAddmefast() throws InterruptedException {

        driver.navigate().to("http://addmefast.com/pinterest-folllowers-pinterest-repins-likes");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


        WebElement input1 = driver.findElement(By.xpath("//input[@name='email'][@class='email']"));


        input1.sendKeys("camxuctroll@gmail.com");

        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("okvippro");

        driver.findElement(By.xpath("//input[@name='login_button']")).click();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        System.out.println(driver.findElement(By.className("points_count")).getText());
        driver.navigate().to("http://addmefast.com/free_points/facebook_likes");


        Thread.sleep(3000);

        // WebElement buttonClick = driver.findElement(By.xpath("//a[@class='single_like_button btn3-wrap']"));

        Actions actions = new Actions(driver);

        while (true) {

            Thread.sleep(2000);
            WebElement buttonClick = driver.findElement(By.xpath("//a[@class='single_like_button btn3-wrap']"));
            actions.moveToElement(buttonClick).click().perform();


            Set<String> windowId = driver.getWindowHandles();    // get  window id of current window
            Iterator<String> itererator = windowId.iterator();

            String mainWinID = itererator.next();
            String newAdwinID = itererator.next();

            driver.switchTo().window(newAdwinID);
            Thread.sleep(3000);
            WebElement buttonLike = driver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div/div[1]/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div[1]/div"));


            actions.moveToElement(buttonLike).click().perform();
            Thread.sleep(2000);
            driver.close();

            driver.switchTo().window(mainWinID);

        }
    }


    @Test
    public void openFacebook() throws InterruptedException {

        driver.navigate().to("https://fb.com");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


    }

    @AfterTest
    public void close() {
        driver.close();
    }
}
