package com.example.stock.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Stock {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long produceId;

    private Long quantity;

    public Stock(){

    };

    public Stock(Long produceId, Long quantity){
        this.produceId = produceId;
        this.quantity = quantity;
    }

    public Long getQuantity() {
        return quantity;
    }
}
