package br.com.FutebolaPlatform.Jogador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @GetMapping
    public List<JogadorModel> listar() {
        return jogadorService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<JogadorModel> buscarPorId(@PathVariable UUID id) {
        return jogadorService.buscarPorId(id);
    }

    @PostMapping
    public JogadorModel criar(@RequestBody JogadorRequestDTO dto) {
        return jogadorService.salvar(dto);
    }

    @PutMapping("/{id}")
    public JogadorModel atualizar(@PathVariable UUID id, @RequestBody JogadorRequestDTO dto) {
        return jogadorService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        jogadorService.deletar(id);
    }
}
