package br.com.FutebolaPlatform.dtos;

import br.com.FutebolaPlatform.enums.MatchStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MatchPatchDTO {
    private LocalDateTime matchDate;
    private String location;
    private String teamA;
    private String teamB;
    private Integer scoreTeamA;
    private Integer scoreTeamB;
    private MatchStatusEnum status;
}
