package com.cashflow.message;

import com.cashflow.dto.CashFlowDTO;
import com.cashflow.dto.TypeDTO;
import reactor.core.publisher.Mono;

public interface CashSend {
    TypeDTO getDestination();
    Mono<CashFlowDTO> sendMessageOf(CashFlowDTO request);
}
