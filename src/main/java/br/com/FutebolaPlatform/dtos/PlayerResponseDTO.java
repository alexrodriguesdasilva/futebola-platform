package br.com.FutebolaPlatform.dtos;

import java.util.UUID;

import br.com.FutebolaPlatform.enums.DominantFootEnum;
import br.com.FutebolaPlatform.enums.PreferredPositionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class PlayerResponseDTO {

    private UUID id;
    private UUID userId;
    private String userName;
    private PreferredPositionEnum preferredPosition;
    private DominantFootEnum dominantFoot;

}