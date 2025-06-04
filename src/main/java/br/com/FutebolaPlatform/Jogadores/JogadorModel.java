package br.com.FutebolaPlatform.Jogadores;

import jakarta.persistence.*;

// Entity ele transforma uma classe em uma entidade do BD
// JPA = Java Persistence API
@Entity
@Table(name = "tb_cadastro")
public class JogadorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;// TODO implementar como UUID
    private String nome;
    private String apelido;
    private String telefone;
    private String email;
    private String posicaoPreferida;
    private String nivelHabilidade;
    private String pernaBoa;
    private List<PartidasModel> partidas;
    //dataNascimento (LocalDate) TODO estudar como implementar
    // grupoId ou List<Grupo> TODO Jogador pode pertencer a um ou mais grupos. Relacionamento importante.
    // presencas (ex: List<PresencaModel>) TODO Histórico de presença em partidas — pode ser útil no futuro.


    public JogadorModel() {
    }

    public JogadorModel(String nome, String apelido, String email, String telefone, String posicaoPreferida, String nivelHabilidade, String pernaBoa) {
        this.nome = nome;
        this.apelido = apelido;
        this.email = email;
        this.telefone = telefone;
        this.posicaoPreferida = posicaoPreferida;
        this.nivelHabilidade = nivelHabilidade;
        this.pernaBoa = pernaBoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosicaoPreferida() {
        return posicaoPreferida;
    }

    public void setPosicaoPreferida(String posicaoPreferida) {
        this.posicaoPreferida = posicaoPreferida;
    }

    public String getNivelHabilidade() {
        return nivelHabilidade;
    }

    public void setNivelHabilidade(String nivelHabilidade) {
        this.nivelHabilidade = nivelHabilidade;
    }

    public String getPernaBoa() {
        return pernaBoa;
    }

    public void setPernaBoa(String pernaBoa) {
        this.pernaBoa = pernaBoa;
    }
}
