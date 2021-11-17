package com.infoapi.Crawling_Use_Selenium.models;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Crawler {
    private WebDriver driver;
    private String mainURL;

    public Crawler() {
        this.driver = new ChromeDriver();
    }

    public void setMainURL(String mainURL) {
        this.mainURL = mainURL;
        this.driver.get(mainURL);
    }

    public void close() {
        this.driver.close();
    }
}
