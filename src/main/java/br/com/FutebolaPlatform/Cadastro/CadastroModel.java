package br.com.FutebolaPlatform.Cadastro;

import br.com.FutebolaPlatform.Participacoes.ParticipacaoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

// Entity ele transforma uma classe em uma entidade do BD
// JPA = Java Persistence API
@Entity
@Table(name = "tb_cadastro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CadastroModel { //Representa qualquer pessoa cadastrada (pode ou não ser jogador).

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;
    private String apelido;
    private String telefone;
    private String email;
}
