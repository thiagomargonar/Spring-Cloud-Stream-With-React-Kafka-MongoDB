package com.cashflow.dto;

import com.cashflow.annotations.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CashFlowDTO {

    @NotNull
    @Document
    private String document;
    private TypeDTO type;
    private BigDecimal value;

    private CashFlowDTO(Builder builder) {
        document = builder.document;
        type = builder.typeDTO;
        value = builder.value;
    }

    public static Builder builder() {
        return new Builder();
    }


    public String getDocument() {
        return document;
    }

    public TypeDTO getType() {
        return type;
    }

    public BigDecimal getValue() {
        return value;
    }

    private CashFlowDTO() {
    }


    public static final class Builder {
        private String document;
        private TypeDTO typeDTO;
        private BigDecimal value;

        private Builder() {
        }

        public Builder document(String val) {
            document = val;
            return this;
        }

        public Builder type(TypeDTO val) {
            typeDTO = val;
            return this;
        }

        public Builder value(BigDecimal val) {
            value = val;
            return this;
        }

        public CashFlowDTO build() {
            return new CashFlowDTO(this);
        }
    }
}
