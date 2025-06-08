package br.com.FutebolaPlatform.Pagamento;

import br.com.FutebolaPlatform.Jogador.JogadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final JogadorRepository jogadorRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, JogadorRepository jogadorRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.jogadorRepository = jogadorRepository;
    }

    public PagamentoModel salvar(PagamentoModel pagamento) {
        // Validação opcional
        if (!jogadorRepository.existsById(pagamento.getJogador().getId())) {
            throw new RuntimeException("Jogador não encontrado");
        }
        return pagamentoRepository.save(pagamento);
    }

    public List<PagamentoModel> listarTodos() {
        return pagamentoRepository.findAll();
    }

    public PagamentoModel buscarPorId(UUID id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }

    public PagamentoModel atualizar(UUID id, PagamentoModel novoPagamento) {
        PagamentoModel existente = buscarPorId(id);

        if (!jogadorRepository.existsById(novoPagamento.getJogador().getId())) {
            throw new RuntimeException("Jogador não encontrado");
        }

        existente.setJogador(novoPagamento.getJogador());
        existente.setValor(novoPagamento.getValor());
        existente.setDataPagamento(novoPagamento.getDataPagamento());
        existente.setTipoPagamento(novoPagamento.getTipoPagamento());
        existente.setReferenciaMesAno(novoPagamento.getReferenciaMesAno());

        return pagamentoRepository.save(existente);
    }

    public void deletar(UUID id) {
        PagamentoModel pagamento = buscarPorId(id);
        pagamentoRepository.delete(pagamento);
    }
}
