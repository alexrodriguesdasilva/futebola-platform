package br.com.FutebolaPlatform.Pagamento;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<PagamentoModel> criarPagamento(@RequestBody PagamentoModel pagamento) {
        return ResponseEntity.ok(pagamentoService.salvar(pagamento));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<PagamentoModel>> listarPagamentos() {
        return ResponseEntity.ok(pagamentoService.listarTodos());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<PagamentoModel> buscarPagamentoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(pagamentoService.buscarPorId(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<PagamentoModel> atualizarPagamento(@PathVariable UUID id, @RequestBody PagamentoModel pagamento) {
        return ResponseEntity.ok(pagamentoService.atualizar(id, pagamento));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagamento(@PathVariable UUID id) {
        pagamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
