package br.com.FutebolaPlatform.dtos;

import br.com.FutebolaPlatform.enums.MatchStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchResponseDTO {
    private UUID id;
    private LocalDateTime matchDate;
    private String location;
    private String teamA;
    private String teamB;
    private Integer scoreTeamA;
    private Integer scoreTeamB;
    private MatchStatusEnum status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

