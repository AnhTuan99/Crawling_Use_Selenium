package com.infoapi.Crawling_Use_Selenium.models;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AutoComment {
    private WebDriver driver;
    private String mainURL;

    public AutoComment() {
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
        this.driver = new ChromeDriver(ops);
    }

    public void setMainURL(String mainURL) {
        this.mainURL = mainURL;
        this.driver.get(mainURL);
    }

    public void close() {
        this.driver.close();
    }

    public Map<String, String> getMainLinks() {
        Map<String, String> result = new HashMap<>();

        List<WebElement> list = driver.findElements(By.xpath("//ul[@class='fs-mnul clearfix']/li/a"));
        list.forEach((WebElement e) -> {
            String title = e.getText().trim();
            if (title.equals("ĐIỆN THOẠI") || title.equals("LAPTOP")) {
                result.put(title, e.getAttribute("href"));
            }
        });
        return result;
    }

    public void crawType(String email, String password, String url, String key) {
        try {
            driver.findElement(By.xpath("//input[@class='inputtext _55r1 _6luy']")).sendKeys(email);
            driver.findElement(By.xpath("//input[@class='inputtext _55r1 _6luy _9npi']")).sendKeys(password);
            driver.findElement(By.xpath("//button[@class='_42ft _4jy0 _6lth _4jy6 _4jy1 selected _51sy']")).click();
            driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
            this.setMainURL(url);
            driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//div[@class='oo9gr5id lzcic4wl jm1wdb64 l9j0dhe7 gsox5hk5 mdldhsdk ii04i59q notranslate']")).sendKeys(key);
            driver.findElement(By.xpath("//div[@class='oo9gr5id lzcic4wl jm1wdb64 l9j0dhe7 gsox5hk5 mdldhsdk ii04i59q notranslate']")).sendKeys(Keys.ENTER);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean waitForJStoLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }

        };
        // wait for JavaScript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }


        };
        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }
}
