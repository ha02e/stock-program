package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PessimisticLockStockService {

    private final StockRepository stockRepository;

    public PessimisticLockStockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

    @Transactional
    public void decrease(Long id, Long quantity){
        //Lock을 활용해서 데이터를 가져옴
        Stock stock = stockRepository.findByIdWithPessimisticLock(id);

        //재고를 감소시킴
        stock.decrease(quantity);

        //데이터를 저장
        stockRepository.save(stock);
    }

}
