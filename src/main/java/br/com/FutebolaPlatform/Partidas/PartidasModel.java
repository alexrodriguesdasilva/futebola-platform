package br.com.FutebolaPlatform.Partidas;

import br.com.FutebolaPlatform.Participacoes.ParticipacaoModel;
import br.com.FutebolaPlatform.Turmas.TurmasModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class PartidasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDate data;
    private LocalTime hora;
    private Integer numeroJogadoresPrevistos;

    @OneToMany(mappedBy = "partida", cascade = CascadeType.ALL)
    @JsonManagedReference("partida-participacoes")
    private List<ParticipacaoModel> participacoes;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private TurmasModel turma;
}

