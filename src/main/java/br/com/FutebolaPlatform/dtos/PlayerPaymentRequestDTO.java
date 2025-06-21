package br.com.FutebolaPlatform.dtos;

import br.com.FutebolaPlatform.enums.PaymentCategoryEnum;
import br.com.FutebolaPlatform.enums.PaymentTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PlayerPaymentRequestDTO {

    @NotNull(message = "O valor do pagamento é obrigatório")
    private Double amount;
    
    @NotNull(message = "A data do pagamento é obrigatória")
    private LocalDate paymentDate;
    
    @NotNull(message = "O tipo de pagamento é obrigatório")
    private PaymentTypeEnum paymentType;
    
    @NotNull(message = "A categoria do pagamento é obrigatória")
    private PaymentCategoryEnum paymentCategory;

    private String description;

    @NotBlank(message = "O ID do jogador é obrigatório")
    @Pattern(
        regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
        message = "Formato de ID do jogador inválido (UUID esperado)"
    )
    private String playerId;
}
