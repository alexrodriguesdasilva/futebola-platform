package br.com.FutebolaPlatform.Turmas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/turmas")
public class TurmasController {

    @Autowired
    private TurmasService turmasService;

    @GetMapping
    public List<TurmasModel> listar() {
        return turmasService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<TurmasModel> buscarPorId(@PathVariable UUID id) {
        return turmasService.buscarPorId(id);
    }

    @PostMapping
    public TurmasModel criar(@RequestBody TurmasModel turma) {
        return turmasService.salvar(turma);
    }

    @PutMapping("/{id}")
    public TurmasModel atualizar(@PathVariable UUID id, @RequestBody TurmasModel turma) {
        return turmasService.atualizar(id, turma);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        turmasService.deletar(id);
    }
}
