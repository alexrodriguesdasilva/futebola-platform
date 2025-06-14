package br.com.FutebolaPlatform.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_MATCH_PLAYER")
@Getter
@Setter
public class MatchPlayerModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // Jogador participante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private PlayerModel player;

    // Partida associada
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = false)
    private MatchModel match;

    // Exemplo de dado adicional: presença confirmada
    @Column(name = "confirmed", nullable = false)
    private boolean confirmed = false;

    // Timestamp de registro
    @Column(name = "joined_at")
    private LocalDateTime joinedAt = LocalDateTime.now();
}
