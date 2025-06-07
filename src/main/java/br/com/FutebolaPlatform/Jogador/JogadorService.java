package br.com.FutebolaPlatform.Jogador;

import br.com.FutebolaPlatform.Cadastro.CadastroModel;
import br.com.FutebolaPlatform.Cadastro.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private CadastroRepository cadastroRepository;

    public List<JogadorModel> listarTodos() {
        return jogadorRepository.findAll();
    }

    public Optional<JogadorModel> buscarPorId(UUID id) {
        return jogadorRepository.findById(id);
    }

    public JogadorModel salvar(JogadorRequestDTO dto) {
        CadastroModel cadastro = cadastroRepository.findById(dto.getCadastroId())
                .orElseThrow(() -> new RuntimeException("Cadastro não encontrado"));

        JogadorModel jogador = new JogadorModel();
        jogador.setPosicaoPreferida(dto.getPosicaoPreferida());
        jogador.setNivelHabilidade(dto.getNivelHabilidade());
        jogador.setPernaBoa(dto.getPernaBoa());
        jogador.setCadastro(cadastro);

        return jogadorRepository.save(jogador);
    }

    public JogadorModel atualizar(UUID id, JogadorRequestDTO dto) {
        return jogadorRepository.findById(id).map(jogador -> {
            jogador.setPosicaoPreferida(dto.getPosicaoPreferida());
            jogador.setNivelHabilidade(dto.getNivelHabilidade());
            jogador.setPernaBoa(dto.getPernaBoa());

            CadastroModel cadastro = cadastroRepository.findById(dto.getCadastroId())
                    .orElseThrow(() -> new RuntimeException("Cadastro não encontrado"));
            jogador.setCadastro(cadastro);

            return jogadorRepository.save(jogador);
        }).orElse(null);
    }

    public void deletar(UUID id) {
        jogadorRepository.deleteById(id);
    }
}
