package br.com.FutebolaPlatform.Jogador;

import lombok.Data;

import java.util.UUID;

@Data
public class JogadorRequestDTO {
    private String posicaoPreferida;
    private Integer nivelHabilidade;
    private String pernaBoa;
    private UUID cadastroId; // apenas o ID do cadastro
}
