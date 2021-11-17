package com.infoapi.Crawling_Use_Selenium;

import com.infoapi.Crawling_Use_Selenium.models.Crawler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrawlingUseSeleniumApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrawlingUseSeleniumApplication.class, args);
		try {
			Crawler crawler = new Crawler();
			crawler.setMainURL("https://fptshop.com.vn/");
			crawler.crawlProducts();
			//crawler.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
