package com.infoapi.Crawling_Use_Selenium.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tbl_laptop")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LaptopEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "realprice")
    private Integer realprice;

    @Column(name = "currency")
    private String currency;

    @Column(name = "screen")
    private String screen;

    @Column(name = "ram")
    private String ram;

    @Column(name = "cpu")
    private String cpu;

    @Column(name = "camera")
    private String camera;

    @Column(name = "vga")
    private String vga;

    @Column(name = "os")
    private String os;

    @Column(name = "weight")
    private float weight;

}
