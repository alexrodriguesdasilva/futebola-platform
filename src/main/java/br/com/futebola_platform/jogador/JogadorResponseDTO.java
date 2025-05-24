package br.com.futebola_platform.jogador;

public record JogadorResponseDTO(Long id, String nome, String apelido, String telefone, Integer idade) {
    public JogadorResponseDTO(Jogador jogador){
        this(jogador.getId(), jogador.getNome(), jogador.getApelido(), jogador.getTelefone(), jogador.getIdade());
    }
}