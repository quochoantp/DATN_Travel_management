package com.example.datn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_rate")
@Entity
public class UserRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rate_id")
    private Long rateId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "rate_user")
    private Integer rateUser;

    @Column(name = "comment")
    private String comment;

}
