package br.com.futebola_platform.controller;

import br.com.futebola_platform.jogador.Jogador;
import br.com.futebola_platform.jogador.JogadorRepository;
import br.com.futebola_platform.jogador.JogadorRequestDTO;
import br.com.futebola_platform.jogador.JogadorResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("jogador")
public class JogadorController {

    @Autowired
    private JogadorRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveJogador(@RequestBody JogadorRequestDTO data){
        Jogador jogadorData = new Jogador(data);
        repository.save(jogadorData);
        return;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<JogadorResponseDTO> getAll(){
        List<JogadorResponseDTO> jogadorList = repository.findAll().stream().map(JogadorResponseDTO::new).toList();
        return jogadorList;
    }
}
