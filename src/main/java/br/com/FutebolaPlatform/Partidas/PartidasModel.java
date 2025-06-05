package br.com.FutebolaPlatform.Partidas;

import br.com.FutebolaPlatform.Participacoes.ParticipacaoModel;
import br.com.FutebolaPlatform.Turmas.TurmaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_partidas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartidasModel { //Representa uma partida única, pontual ou vinculada a um grupo.

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDate data;
    private LocalTime hora;

    private Integer numeroJogadoresPrevistos;

    @OneToMany(mappedBy = "partida")
    private List<ParticipacaoModel> participacoes;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private TurmaModel turma; // se a partida for recorrente
}
