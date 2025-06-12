package br.com.FutebolaPlatform.models;

import java.time.LocalDate;
import java.util.UUID;

import br.com.FutebolaPlatform.enums.PaymentTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_PLAYER_PAYMENT")
@Getter
@Setter
public class PlayerPaymentModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Double amount;  // valor pago

    @Column(nullable = false)
    private LocalDate paymentDate;  // data do pagamento

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentTypeEnum paymentType;

    private String description;
}
