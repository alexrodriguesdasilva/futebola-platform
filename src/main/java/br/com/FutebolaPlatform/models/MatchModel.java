package br.com.FutebolaPlatform.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.FutebolaPlatform.enums.MatchStatusEnum;
import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table( name = "TB_MATCH")
public class MatchModel implements Serializable {
    private static final long serialVersionUID = 1L;

    // Identificador único da partida
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // Data e hora da partida
    @Column(name = "match_date", nullable = false)
    private LocalDateTime matchDate;

    // Local onde a partida acontecerá ou aconteceu
    @Column(nullable = false)
    private String location;

    // Nome ou identificação do time A
    @Column(name = "team_a", nullable = false)
    private String teamA;

    // Nome ou identificação do time B
    @Column(name = "team_b", nullable = false)
    private String teamB;

    // Placar do time A
    @Column(name = "score_team_a")
    private Integer scoreTeamA;

    // Placar do time B
    @Column(name = "score_team_b")
    private Integer scoreTeamB;

    // Status da partida: SCHEDULED, ONGOING, FINISHED, CANCELED
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MatchStatusEnum status;

    // Timestamp de criação do registro
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // Timestamp de última atualização do registro
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Uma partida pode ter vários pagamentos associados
    @OneToMany(mappedBy = "matchModel", fetch = FetchType.LAZY)
    private Set<PlayerPaymentModel> payments = new HashSet<>();

    // // Muitas partidas podem estar associadas a um grupo de jogadores (não obrigatório)
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "match_group_id", nullable = true) // FK opcional
    // private PlayerGroupModel playerGroupModel;

    // // Jogadores que participaram dessa partida
    // @OneToMany(mappedBy = "match", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // private Set<MatchPlayerModel> players = new HashSet<>();

    // Define valores padrão antes de persistir no banco
    @PrePersist
    public void setDefaults() {
        if (this.teamA == null || this.teamA.trim().isEmpty()) {
            this.teamA = "Time A";
        }
        if (this.teamB == null || this.teamB.trim().isEmpty()) {
            this.teamB = "Time B";
        }
        if (this.status == null) {
            this.status = MatchStatusEnum.SCHEDULED;
        }
    }
}