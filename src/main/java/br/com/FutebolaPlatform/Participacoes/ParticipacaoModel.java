package br.com.FutebolaPlatform.Participacoes;

import br.com.FutebolaPlatform.Jogador.JogadorModel;
import br.com.FutebolaPlatform.Partidas.PartidasModel;
import br.com.FutebolaPlatform.TipoPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Table(name = "tb_participacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "jogador_id")
    private JogadorModel jogador;

    @ManyToOne
    @JoinColumn(name = "partida_id")
    private PartidasModel partida;

    private Boolean confirmado;
    private Integer notaPartida;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento; // MENSAL, AVULSO
}