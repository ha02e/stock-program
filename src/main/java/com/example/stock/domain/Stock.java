package com.example.stock.domain;

import javax.persistence.*;

@Entity
public class Stock {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long produceId;

    private Long quantity;

    @Version
    private Long version;


    public Stock(){

    };

    public Stock(Long produceId, Long quantity){
        this.produceId = produceId;
        this.quantity = quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    //재고 감소 메서드
    public void decrease(Long quantity){
        if(this.quantity - quantity < 0){
            throw new RuntimeException("재고는 0개 미만이 될 수 없습니다.");
        }
        this.quantity -= quantity;
    }

}
