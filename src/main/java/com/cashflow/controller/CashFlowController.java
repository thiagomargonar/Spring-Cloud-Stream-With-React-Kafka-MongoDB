package com.cashflow.controller;

import com.cashflow.dto.CashFlowDTO;
import com.cashflow.services.CashFlowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("/movement-cash")
@RestController
public class CashFlowController {

    private final CashFlowService cashFlowService;

    public CashFlowController(CashFlowService cashFlowService) {
        this.cashFlowService = cashFlowService;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> startCashFlow(@RequestBody CashFlowDTO cashFlow) {
        return Mono.just(cashFlow)
                .flatMap(cashFlowService::beginProcess)
                        .map(mp -> ResponseEntity.noContent().build());
    }
}
