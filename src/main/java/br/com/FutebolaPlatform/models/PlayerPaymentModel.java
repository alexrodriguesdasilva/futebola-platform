package br.com.FutebolaPlatform.models;

import java.time.LocalDate;
import java.util.UUID;

import br.com.FutebolaPlatform.enums.PaymentTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne // Um pagamento pode ser feito por apenas 1 jogador
    @JoinColumn(name = "player_id") // Referencia de qual chave estrangeira para referenciar que um pagamento é de um jogador
    private PlayerModel playerModel; 

    // Vários pagamentos podem estar associados a um grupo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_group_id", nullable = true) // FK opcional
    private PlayerGroupModel playerGroupModel; // Grupo relacionado ao pagamento (se houver)

    // Muitos Pagamentos pode estar associado a uma partida (opcional)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = true)
    private MatchModel matchModel;
}
