package br.com.FutebolaPlatform.dtos;

import br.com.FutebolaPlatform.enums.DominantFootEnum;
import br.com.FutebolaPlatform.enums.PreferredPositionEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerRequestDTO {
    @NotNull(message = "O ID do usuário é obrigatório")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$", message = "Formato de UUID inválido")
    private String userId;


    @NotNull(message = "Posição preferida é obrigatória")
    private PreferredPositionEnum preferredPosition;

    @NotNull(message = "Pé dominante é obrigatório")
    private DominantFootEnum dominantFoot;

}
