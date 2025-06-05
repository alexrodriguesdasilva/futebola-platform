package br.com.FutebolaPlatform.Cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cadastros")
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;

    @GetMapping
    public List<CadastroModel> listar() {
        return cadastroService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<CadastroModel> buscarPorId(@PathVariable UUID id) {
        return cadastroService.buscarPorId(id);
    }

    @PostMapping
    public CadastroModel criar(@RequestBody CadastroModel cadastro) {
        return cadastroService.salvar(cadastro);
    }

    @PutMapping("/{id}")
    public CadastroModel atualizar(@PathVariable UUID id, @RequestBody CadastroModel cadastro) {
        return cadastroService.atualizar(id, cadastro);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        cadastroService.deletar(id);
    }
}
