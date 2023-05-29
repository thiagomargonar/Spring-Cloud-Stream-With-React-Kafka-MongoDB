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
public class InFlowSend implements CashSend {
    private static final Logger logger = LoggerFactory.getLogger(InFlowSend.class);

    private final Sinks.Many<CashFlowDTO> sink;

    public InFlowSend() {
        this.sink = Sinks.many().unicast().onBackpressureBuffer();
    }


    @Bean
    public Supplier<Flux<CashFlowDTO>> sendCashInReturn() {
        return () -> sink.asFlux()
                .doOnNext(m -> logger.debug("Sending creation account request message {}", m))
                .doOnError(t -> logger.error("Error sending creation account request message", t));
    }

    @Bean
    public Supplier<Flux<CashFlowDTO>> sendCashIn() {
        return () -> sink.asFlux()
                .doOnNext(m -> logger.debug("Sending cash with request document {}", m))
                .doOnError(t -> logger.error("Error sending cash account request message: ", t));
    }

    @Override
    public Mono<CashFlowDTO> sendMessageOf(CashFlowDTO request) {
        return Mono.just(sink.tryEmitNext(request))
                .doOnNext(emitResult -> logger.info("emiti result IN solicitado: {}", emitResult))
                .thenReturn(request);
    }

    @Override
    public TypeDTO getDestination() {
        return TypeDTO.IN;
    }
}
