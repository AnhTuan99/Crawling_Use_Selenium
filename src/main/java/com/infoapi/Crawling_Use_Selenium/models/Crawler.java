package com.infoapi.Crawling_Use_Selenium.models;

import com.infoapi.Crawling_Use_Selenium.entities.PhoneEntity;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Crawler {
    private WebDriver driver;
    private String mainURL;

    public Crawler() {
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

    public void crawType(String url, Class objClass, Class functionClass, String itemsString, String itemNameString, String itemPriceString, String itemRealPriceString, String itemTskt) {
        try {
            this.setMainURL(url);
            driver.findElement(By.xpath("//span[@class='icon-th-list']")).click();
            String text = "";
            while (!driver.findElements(By.xpath("//div[@class='cdt-product--loadmore']")).isEmpty()) {
                WebElement webElement = driver.findElement(By.xpath("//div[@class='cdt-product--loadmore']"));
                webElement.click();
                driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
            }
            List<WebElement> types = driver.findElements(By.xpath(itemsString));

            for (WebElement e : types) {
                WebElement lpilname = e.findElement(By.xpath(itemNameString));
                String name = lpilname.findElement(By.xpath("./h3/a")).getText().trim();
                System.out.println(name);
                if (lpilname.findElements(By.xpath(itemRealPriceString)).isEmpty()) {
                    if (!lpilname.findElements(By.xpath(itemPriceString + "/div[@class='progress pdiscount2']")).isEmpty())
                        System.out.println(lpilname.findElement(By.xpath(itemPriceString + "/div[@class='progress pdiscount2']")).getText().trim());
                    else if (!lpilname.findElements(By.xpath(itemPriceString + "/div[@class='price']")).isEmpty()) {
                        System.out.println(lpilname.findElement(By.xpath(itemPriceString + "/div[@class='price']")).getText().trim());
                    } else
                        System.out.println(lpilname.findElement(By.xpath(itemPriceString + "/div[@class='progress']")).getText().trim());
                    System.out.println(lpilname.findElement(By.xpath(itemPriceString + "/div[@class='strike-price']")).getText().trim());
                } else {
                    if (!lpilname.findElements(By.xpath(itemRealPriceString + "/div[@class='tcdm text-left']/div")).isEmpty())
                        System.out.println(lpilname.findElement(By.xpath(itemRealPriceString + "/div[@class='tcdm text-left']/div")).getText().trim());
                }

                List<WebElement> lpilTskts = lpilname.findElements(By.xpath(itemTskt));
                for (WebElement a : lpilTskts) {
                    String test = a.getText().toLowerCase().trim();
                    System.out.println(test);
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void crawlProducts() {
        Map<String, String> categories = this.getMainLinks();
        for (Map.Entry<String, String> entry : categories.entrySet()) {
            String key = entry.getKey();
            String itemsString;
            String itemNameString;
            String itemPriceString;
            String itemRealPriceString;
            String itemTskt;
            switch (key) {
                case "ĐIỆN THOẠI":
                    itemsString = "//div[@class='cdt-product  ']";
                    itemNameString = "./div[@class='cdt-product__info']";
                    itemPriceString = "./div[@class='cdt-product__show-promo']";
                    itemRealPriceString = "./*[@class='cdt-product__price']";
                    itemTskt = "./div[@class='cdt-product__config list-layout']/div[@class='cdt-product__config__param']/span";
                    crawType(entry.getValue(), PhoneEntity.class, PhoneEntity.class, itemsString, itemNameString, itemPriceString, itemRealPriceString, itemTskt);
                    break;
                case "LAPTOP":
                    itemsString = "//div[@class='cdt-product  ']";
                    itemNameString = "./div[@class='cdt-product__info']";
                    itemPriceString = "./div[@class='cdt-product__show-promo']";
                    itemRealPriceString = "./*[@class='cdt-product__price']";
                    itemTskt = "./div[@class='cdt-product__config list-layout']/div[@class='cdt-product__config__param']/span";
                    crawType(entry.getValue(), PhoneEntity.class, PhoneEntity.class, itemsString, itemNameString, itemPriceString, itemRealPriceString, itemTskt);
                    break;
            }
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
