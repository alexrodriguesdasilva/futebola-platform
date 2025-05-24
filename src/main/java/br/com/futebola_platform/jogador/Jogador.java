package br.com.futebola_platform.jogador;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "jogador")
@Entity(name = "jogador")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String apelido;
    private String telefone;
    private Integer idade;

    public Jogador(JogadorRequestDTO data){
        this.nome = data.nome();
        this.apelido = data.apelido();
        this.telefone = data.telefone();
        this.idade = data.idade();
    }
}
