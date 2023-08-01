package com.example.datn.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tour_image")
@Entity
public class TourImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tour_id")
    private Long tourId;

    @Column(name = "image100")
    private String image100;

    @Column(name = "image50")
    private String image50;

    @Column(name = "image251")
    private String image251;

    @Column(name = "image252")
    private String image252;
}
