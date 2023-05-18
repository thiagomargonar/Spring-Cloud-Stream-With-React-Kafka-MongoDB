package com.cashflow.message;

import com.cashflow.dto.CashFlowDTO;
import com.cashflow.dto.TypeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Component
public class OutFlowSend implements CashSend {
    private static final Logger logger = LoggerFactory.getLogger(OutFlowSend.class);

    private final Sinks.Many<CashFlowDTO> sink;

    public OutFlowSend() {
        this.sink = Sinks.many().unicast().onBackpressureBuffer();
    }


    @Bean
    public Sinks.Many<CashFlowDTO> sendCashOutReturn() {
        return sink;
    }

    @Bean
    public Supplier<Flux<CashFlowDTO>> sendCashOut() {
        return () -> sink.asFlux()
                .doOnNext(m -> logger.debug("Sending cash with request document {}", m.getDocument()))
                .doOnError(t -> logger.error("Error sending cash account request message: ", t));
    }
    @Override
    public Mono<CashFlowDTO> sendMessageOf(CashFlowDTO request) {
        return Mono.just(sink.tryEmitNext(request)).thenReturn(request);
    }

    @Override
    public TypeDTO getDestination() {
        return TypeDTO.OUT;
    }
}
