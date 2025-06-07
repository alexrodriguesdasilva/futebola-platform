package br.com.FutebolaPlatform.Partidas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PartidasService {

    @Autowired
    private PartidasRepository partidasRepository;

    public List<PartidasModel> listarTodos() {
        return partidasRepository.findAll();
    }

    public Optional<PartidasModel> buscarPorId(UUID id) {
        return partidasRepository.findById(id);
    }

    public PartidasModel salvar(PartidasModel partida) {
        return partidasRepository.save(partida);
    }

    public PartidasModel atualizar(UUID id, PartidasModel partidaAtualizada) {
        return partidasRepository.findById(id)
                .map(partida -> {
                    partida.setData(partidaAtualizada.getData());
                    partida.setHora(partidaAtualizada.getHora());
                    partida.setNumeroJogadoresPrevistos(partidaAtualizada.getNumeroJogadoresPrevistos());
                    partida.setParticipacoes(partidaAtualizada.getParticipacoes());
                    partida.setTurma(partidaAtualizada.getTurma());
                    return partidasRepository.save(partida);
                }).orElse(null);
    }

    public void deletar(UUID id) {
        partidasRepository.deleteById(id);
    }
}
