package com.example.exchange_service;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="exchange")
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "exchange_from")
    String from;
    @Column(name = "exchange_to")
    String to;
    @Column(name = "exchange_value")
    BigDecimal exchangeValue;
    @Transient
    String port;
}
