package br.com.FutebolaPlatform.Jogadores;

import br.com.FutebolaPlatform.Participacoes.ParticipacaoModel;
import br.com.FutebolaPlatform.Partidas.PartidasModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entity ele transforma uma classe em uma entidade do BD
// JPA = Java Persistence API
@Entity
@Table(name = "tb_cadastro")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JogadorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;// TODO implementar como UUID
    private String nome;
    private String apelido;
    private String telefone;
    private String email;
    private String posicaoPreferida;
    private Integer nivelHabilidade;
    private String pernaBoa;

    // @ManyToOne
    @OneToMany(mappedBy = "jogador")
    private List<ParticipacaoModel> participacoes;

    //dataNascimento (LocalDate) TODO estudar como implementar
    // grupoId ou List<Grupo> TODO Jogador pode pertencer a um ou mais grupos. Relacionamento importante.
    // presencas (ex: List<PresencaModel>) TODO Histórico de presença em partidas — pode ser útil no futuro.



}
