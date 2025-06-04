package br.com.FutebolaPlatform.Partidas;

import br.com.FutebolaPlatform.Participacoes.ParticipacaoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table (name = "tb_partidas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PartidasModel {

    @Id
    @GeneratedValue()
    private Long id;
    private String data;
    private String hora;
    private String jogadorConfirmado;

    //  @OneToMany Uma partida pode ter varios Jogadores
    @OneToMany(mappedBy = "partida")
    private List<ParticipacaoModel> participacoes;
}
