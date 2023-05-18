package com.cashflow.services;


import com.cashflow.dto.CashFlowDTO;
import com.cashflow.message.ResolveClassToSendMessage;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CashFlowService {

    private static final Logger log = LoggerFactory.getLogger(CashFlowService.class);
    private final ResolveClassToSendMessage resolveClassToSendMessage;

    public CashFlowService(ResolveClassToSendMessage resolveClassToSendMessage) {
        this.resolveClassToSendMessage = resolveClassToSendMessage;
    }

    public Mono<CashFlowDTO> beginProcess(CashFlowDTO cashFlowDTO) {
        return Mono.just(cashFlowDTO)
                .flatMap(resolveClassToSendMessage::sendMessageProcess)
                .switchIfEmpty(logMessage(cashFlowDTO));
    }

    @NotNull
    private Mono<CashFlowDTO> logMessage(CashFlowDTO cashFlowDTO) {
        return Mono.just(cashFlowDTO)
                .flatMap(cashFlowDTO1 -> {
                    log.error("teste de erro, poderiamos colcoar quallquer tratamento aqui");
                    return Mono.error(Exception::new);
                });
    }
}
