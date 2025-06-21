package br.com.FutebolaPlatform.dtos;

import br.com.FutebolaPlatform.enums.PaymentTypeEnum;
import br.com.FutebolaPlatform.enums.PaymentCategoryEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PlayerPaymentRequestDTO {

    @NotNull
    private Double amount;

    @NotNull
    private LocalDate paymentDate;

    @NotNull
    private PaymentTypeEnum paymentType;

    @NotNull
    private PaymentCategoryEnum paymentCategory;

    private String description;

    @NotNull
    private String playerId; // UUID em String
}