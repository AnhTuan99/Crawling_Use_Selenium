package com.infoapi.Crawling_Use_Selenium;

import com.infoapi.Crawling_Use_Selenium.models.AutoComment;
import com.infoapi.Crawling_Use_Selenium.models.Crawler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrawlingUseSeleniumApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrawlingUseSeleniumApplication.class, args);
        try {
//			Crawler crawler = new Crawler();
//			crawler.setMainURL("https://fptshop.com.vn/");
//			crawler.crawlProducts();
//			crawler.close();

            AutoComment autoComment = new AutoComment();
			autoComment.setMainURL("https://www.facebook.com");
            autoComment.crawType("daotuan1712@gmail.com", "Haxinhtuoi","https://www.facebook.com/photo/?fbid=595534358408946&set=gm.496139991573935","Xuất xắc <3");
            //autoComment.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
