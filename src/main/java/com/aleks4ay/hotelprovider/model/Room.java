package com.aleks4ay.hotelprovider.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "Room")
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoomId")
    private Long roomId;

    @Column(name = "HotelId")
    private Long hotelId;

    @Column(name = "RoomNumber", unique = true)
    private Integer roomNumber;

    @Column(name = "Category")
    @Enumerated(EnumType.ORDINAL)
    private Category category;

    @Column(name = "Capacity")
    private Integer capacity;

    @Column(name = "Description")
    private String description;

    @JoinColumn(name = "ImgId")
    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    @Column(name = "Price")
    private Integer price;
}
