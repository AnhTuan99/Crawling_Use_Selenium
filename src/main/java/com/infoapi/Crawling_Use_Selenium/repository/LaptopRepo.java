package com.infoapi.Crawling_Use_Selenium.repository;

import com.infoapi.Crawling_Use_Selenium.entities.LaptopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepo extends JpaRepository<LaptopEntity, Long> {
}
