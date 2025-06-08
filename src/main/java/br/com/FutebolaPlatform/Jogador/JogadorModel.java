package br.com.FutebolaPlatform.Jogador;

import br.com.FutebolaPlatform.Cadastro.CadastroModel;
import br.com.FutebolaPlatform.Participacoes.ParticipacaoModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_jogadores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogadorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String posicaoPreferida;
    private Integer nivelHabilidade;
    private String pernaBoa;

    @OneToOne
    @JoinColumn(name = "cadastro_id")
    private CadastroModel cadastro;

    @OneToMany(mappedBy = "jogador", cascade = CascadeType.ALL)
    @JsonManagedReference("jogador-participacoes")
    private List<ParticipacaoModel> participacoes;
}
