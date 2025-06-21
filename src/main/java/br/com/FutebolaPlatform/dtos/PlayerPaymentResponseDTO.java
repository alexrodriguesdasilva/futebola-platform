package br.com.FutebolaPlatform.dtos;

import br.com.FutebolaPlatform.enums.PaymentCategoryEnum;
import br.com.FutebolaPlatform.enums.PaymentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PlayerPaymentResponseDTO {
    private UUID id;
    private Double amount;
    private LocalDate paymentDate;
    private PaymentTypeEnum paymentType;
    private String description;
    private UUID playerId;
    private String playerName;
    private PaymentCategoryEnum paymentCategory; 
}
