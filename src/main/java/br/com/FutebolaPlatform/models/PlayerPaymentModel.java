package br.com.FutebolaPlatform.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.FutebolaPlatform.enums.PaymentCategoryEnum;
import br.com.FutebolaPlatform.enums.PaymentStatusEnum;
import br.com.FutebolaPlatform.enums.PaymentTypeEnum;
import jakarta.persistence.*;
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
    private Double amount;  // Valor pago

    @Column(nullable = false)
    private LocalDate paymentDate;  // Data do pagamento

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    private PaymentTypeEnum paymentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_category", nullable = false)
    private PaymentCategoryEnum paymentCategory;

    private String description;

    @ManyToOne(optional = false, fetch = FetchType.LAZY) // Relação obrigatória
    @JoinColumn(name = "player_id", nullable = false)
    private PlayerModel playerModel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatusEnum paymentStatus = PaymentStatusEnum.VALID; // valor padrão

    // FUTURO: Relacionamento com grupo (opcional)
    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_group_id")
    private PlayerGroupModel playerGroupModel;
    */

    // Um pagamento pode talvez está associado a uma partida
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private MatchModel matchModel;
}
