package br.com.FutebolaPlatform.Participacoes;

import br.com.FutebolaPlatform.Jogadores.JogadorModel;
import br.com.FutebolaPlatform.Partidas.PartidasModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class Participacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "jogador_id")
    private JogadorModel jogador;

    @ManyToOne
    @JoinColumn(name = "partida_id")
    private PartidasModel partida;

    private Boolean confirmado; // campo para indicar se o jogador confirmou presença

    private Integer notaPartida;


}