package br.com.FutebolaPlatform.Partidas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/partidas")
public class PartidasController {

    @Autowired
    private PartidasService partidasService;

    @GetMapping
    public List<PartidasModel> listar() {
        return partidasService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<PartidasModel> buscarPorId(@PathVariable UUID id) {
        return partidasService.buscarPorId(id);
    }

    @PostMapping
    public PartidasModel criar(@RequestBody PartidasModel partida) {
        return partidasService.salvar(partida);
    }

    @PutMapping("/{id}")
    public PartidasModel atualizar(@PathVariable UUID id, @RequestBody PartidasModel partida) {
        return partidasService.atualizar(id, partida);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        partidasService.deletar(id);
    }
}
