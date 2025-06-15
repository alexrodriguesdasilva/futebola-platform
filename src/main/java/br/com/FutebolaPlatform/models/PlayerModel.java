package br.com.FutebolaPlatform.models;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.FutebolaPlatform.enums.DominantFootEnum;
import br.com.FutebolaPlatform.enums.PreferredPositionEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_PLAYER")
@Getter
@Setter
public class PlayerModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    @JoinColumn(name = "user_id") // Relaciona a qual usuario esse cadastro de jogador pertence
    private UserModel user;

    @Enumerated(EnumType.STRING)
    private PreferredPositionEnum preferredPosition;

    @Enumerated(EnumType.STRING)
    private DominantFootEnum dominantFoot;

    // Lista de pagamentos realizados por este jogador
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Evita que a lista de pagamentos seja exibida em respostas JSON (ex: ao retornar um Player pela API)
    @OneToMany(mappedBy = "playerModel", fetch = FetchType.LAZY) // Um jogador pode ter varios Pagamentos
    private Set<PlayerPaymentModel> playerModels = new HashSet<>();

    // Participações do jogador em partidas
    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MatchPlayerModel> matchParticipations = new HashSet<>();

    // Relação entre Jogadores e Grupos 
    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    private Set<PlayerGroupMemberModel> groupMemberships = new HashSet<>();
}
