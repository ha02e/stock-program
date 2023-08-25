package com.example.stock.facade;

import com.example.stock.service.OptimisticLockStockService;
import org.springframework.stereotype.Component;

@Component
public class OptimisticLockStockFacade {

    private final OptimisticLockStockService optimisticLockStockService;

    public OptimisticLockStockFacade(OptimisticLockStockService optimisticLockStockService){
        this.optimisticLockStockService = optimisticLockStockService;
    }

    public void decrease(Long id, Long quantity) throws InterruptedException{
        while (true){  //업데이트를 실패했을 때 재시도를 해야함
            try {
                optimisticLockStockService.decrease(id, quantity);

                //정상적으로 업데이트 되면 빠져나감
                break;
            } catch (Exception e) {
                //수량 감소 실패 시, 재시도
                Thread.sleep(50);
            }
        }
    }
}