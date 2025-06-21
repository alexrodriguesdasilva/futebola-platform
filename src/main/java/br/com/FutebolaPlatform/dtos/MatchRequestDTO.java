package br.com.FutebolaPlatform.dtos;

import br.com.FutebolaPlatform.enums.MatchStatusEnum;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MatchRequestDTO {

    @NotNull(message = "A data e hora da partida é obrigatória")
    private LocalDateTime matchDate;

    @NotBlank(message = "O local da partida é obrigatório")
    private String location;

    private String teamA;
    private String teamB;

    private Integer scoreTeamA;
    private Integer scoreTeamB;

    private MatchStatusEnum status;
}
