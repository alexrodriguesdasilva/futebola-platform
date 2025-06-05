package br.com.FutebolaPlatform.Turmas;

import br.com.FutebolaPlatform.Partidas.PartidasModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_turmas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurmaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;
    private DayOfWeek diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;

    @OneToMany(mappedBy = "turma")
    private List<PartidasModel> partidas;
}