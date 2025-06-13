package br.com.FutebolaPlatform.models;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.FutebolaPlatform.enums.DominantFootEnum;
import br.com.FutebolaPlatform.enums.PreferredPositionEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
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
}
