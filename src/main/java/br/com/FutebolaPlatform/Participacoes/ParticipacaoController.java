package br.com.FutebolaPlatform.Participacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/participacoes")
public class ParticipacaoController {

    @Autowired
    private ParticipacaoService participacaoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ParticipacaoModel> listar() {
        return participacaoService.listarTodos();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<ParticipacaoModel> buscarPorId(@PathVariable UUID id) {
        return participacaoService.buscarPorId(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ParticipacaoModel criar(@RequestBody ParticipacaoModel participacao) {
        return participacaoService.salvar(participacao);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ParticipacaoModel atualizar(@PathVariable UUID id, @RequestBody ParticipacaoModel participacao) {
        return participacaoService.atualizar(id, participacao);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        participacaoService.deletar(id);
    }
}
