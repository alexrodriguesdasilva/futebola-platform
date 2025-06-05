package br.com.FutebolaPlatform.Cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CadastroService {

    @Autowired
    private CadastroRepository cadastroRepository;

    public List<CadastroModel> listarTodos() {
        return cadastroRepository.findAll();
    }

    public Optional<CadastroModel> buscarPorId(UUID id) {
        return cadastroRepository.findById(id);
    }

    public CadastroModel salvar(CadastroModel cadastro) {
        return cadastroRepository.save(cadastro);
    }

    public CadastroModel atualizar(UUID id, CadastroModel novoCadastro) {
        return cadastroRepository.findById(id)
                .map(c -> {
                    c.setNome(novoCadastro.getNome());
                    c.setApelido(novoCadastro.getApelido());
                    c.setTelefone(novoCadastro.getTelefone());
                    c.setEmail(novoCadastro.getEmail());
                    return cadastroRepository.save(c);
                }).orElse(null);
    }

    public void deletar(UUID id) {
        cadastroRepository.deleteById(id);
    }
}