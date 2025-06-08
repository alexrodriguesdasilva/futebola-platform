package br.com.FutebolaPlatform.Turmas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TurmasService {

    @Autowired
    private TurmasRepository turmasRepository;

    public List<TurmasModel> listarTodos() {
        return turmasRepository.findAll();
    }

    public Optional<TurmasModel> buscarPorId(UUID id) {
        return turmasRepository.findById(id);
    }

    public TurmasModel salvar(TurmasModel turma) {
        return turmasRepository.save(turma);
    }

    public TurmasModel atualizar(UUID id, TurmasModel turmaAtualizada) {
        return turmasRepository.findById(id)
                .map(turma -> {
                    turma.setNome(turmaAtualizada.getNome());
                    turma.setDiaSemana(turmaAtualizada.getDiaSemana());
                    turma.setHoraInicio(turmaAtualizada.getHoraInicio());
                    turma.setHoraFim(turmaAtualizada.getHoraFim());
                    return turmasRepository.save(turma);
                }).orElse(null);
    }

    public void deletar(UUID id) {
        turmasRepository.deleteById(id);
    }
}
