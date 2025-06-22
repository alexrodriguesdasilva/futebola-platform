package br.com.FutebolaPlatform.dtos;

import java.time.LocalDate;
import java.util.UUID;

import br.com.FutebolaPlatform.enums.PaymentCategoryEnum;
import br.com.FutebolaPlatform.enums.PaymentStatusEnum;
import br.com.FutebolaPlatform.enums.PaymentTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerPaymentPatchDTO {
    private Double amount;
    private LocalDate paymentDate;
    private String description;
    private PaymentTypeEnum paymentType;
    private PaymentCategoryEnum paymentCategory;
    private PaymentStatusEnum paymentStatus;
    private String groupId;
}
