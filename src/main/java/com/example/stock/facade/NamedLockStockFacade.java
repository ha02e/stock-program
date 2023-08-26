package com.example.stock.facade;

import com.example.stock.repository.LockRepository;
import com.example.stock.repository.StockRepository;
import com.example.stock.service.StockService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class NamedLockStockFacade {
    //Lock 획득을 위해
    private final LockRepository lockRepository;

    //재고 감소를 할 수 있게 하기 위해
    private final StockService stockService;

    public NamedLockStockFacade(LockRepository lockRepository, StockService stockService){
        this.lockRepository = lockRepository;
        this.stockService = stockService;
    }

    //decrease 메서드
    @Transactional
    public void decrease(Long id, Long quantity){
        try {
            //lock 획득
            lockRepository.getLock(id.toString());

            //재고 감소
            stockService.decrease(id, quantity);
        }finally {
            //모든 로직이 종료되었을 때, lock 해제
            lockRepository.releaseLock(id.toString());
        }
    }
}
