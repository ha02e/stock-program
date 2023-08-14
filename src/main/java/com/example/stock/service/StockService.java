package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public  StockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

    @Transactional
    //재고 감소 메서드
    public void decrease(Long id, Long quantity){
        // Stock 조회
        Stock stock = stockRepository.findById(id).orElseThrow();

        // 재고를 감소
        stock.decrease(quantity);

        // 갱신된 값을 저장
        stockRepository.saveAndFlush(stock);
    }
}
