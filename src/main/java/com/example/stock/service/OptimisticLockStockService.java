package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OptimisticLockStockService {

    private final StockRepository stockRepository;

    public OptimisticLockStockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

    @Transactional
    public void decrease(Long id, Long quantity){
        //Optimistic Lock을 이용해 데이터를 가져옴
        Stock stock = stockRepository.findByIdWithOptimisticLock(id);

        //수량을 감소시킴
        stock.decrease(quantity);

        //데이터를 저장
        stockRepository.save(stock);
    }
}
