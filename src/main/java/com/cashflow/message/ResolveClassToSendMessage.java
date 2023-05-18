package com.cashflow.message;

import com.cashflow.dto.CashFlowDTO;
import com.cashflow.dto.TypeDTO;
import com.cashflow.message.CashSend;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.*;

@Component
public class ResolveClassToSendMessage {
    private final Map<TypeDTO, CashSend> customCashSend;

    public ResolveClassToSendMessage(List<CashSend> customExceptionsInstance) {
        customCashSend = new IdentityHashMap<>();
        customExceptionsInstance.forEach(cashSend -> this.customCashSend.put(cashSend.getDestination(), cashSend));
    }

    public Mono<CashFlowDTO> sendMessageProcess(CashFlowDTO cashFlowDTO) {
        return Mono.just(cashFlowDTO)
                .flatMap(dto -> process(customCashSend.get(dto.getType()), dto))
                .thenReturn(cashFlowDTO);
    }

    private Mono<Void> process(CashSend cashSend, CashFlowDTO cashFlowDTO) {
        return cashSend.sendMessageOf(cashFlowDTO).then();
    }
}
