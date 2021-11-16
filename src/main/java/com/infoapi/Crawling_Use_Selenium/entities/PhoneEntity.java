package com.infoapi.Crawling_Use_Selenium.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tbl_phone")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PhoneEntity {

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

    @Column(name = "cell")
    private String cell;

    @Column(name = "cpu")
    private String cpu;

    @Column(name = "camera")
    private String camera;

    @Column(name = "ram")
    private String ram;

    @Column(name = "os")
    private String os;

}
