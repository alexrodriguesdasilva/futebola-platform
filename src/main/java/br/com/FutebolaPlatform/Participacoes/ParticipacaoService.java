package br.com.FutebolaPlatform.Participacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParticipacaoService {

    @Autowired
    private ParticipacaoRepository participacaoRepository;

    public List<ParticipacaoModel> listarTodos() {
        return participacaoRepository.findAll();
    }

    public Optional<ParticipacaoModel> buscarPorId(UUID id) {
        return participacaoRepository.findById(id);
    }

    public ParticipacaoModel salvar(ParticipacaoModel participacao) {
        return participacaoRepository.save(participacao);
    }

    public ParticipacaoModel atualizar(UUID id, ParticipacaoModel atualizada) {
        return participacaoRepository.findById(id)
                .map(participacao -> {
                    participacao.setJogador(atualizada.getJogador());
                    participacao.setPartida(atualizada.getPartida());
                    participacao.setConfirmado(atualizada.getConfirmado());
                    participacao.setNotaPartida(atualizada.getNotaPartida());
                    participacao.setTipoPagamento(atualizada.getTipoPagamento());
                    return participacaoRepository.save(participacao);
                }).orElse(null);
    }

    public void deletar(UUID id) {
        participacaoRepository.deleteById(id);
    }
}
