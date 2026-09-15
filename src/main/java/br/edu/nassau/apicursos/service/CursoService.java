package br.edu.nassau.apicursos.service;

import br.edu.nassau.apicursos.model.Curso;
import br.edu.nassau.apicursos.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso cadastrar(Curso curso) {
        validarCurso(curso);
        curso.setNome(curso.getNome().trim());
        return cursoRepository.salvar(curso);
    }

    public List<Curso> listarTodos() {
        return cursoRepository.listarTodos();
    }

    public Optional<Curso> buscarPorId(Long id) {
        return cursoRepository.buscarPorId(id);
    }

    public Optional<Curso> atualizar(Long id, Curso novosDados) {
        validarCurso(novosDados);

        Optional<Curso> cursoEncontrado = cursoRepository.buscarPorId(id);

        if (cursoEncontrado.isPresent()) {
            Curso curso = cursoEncontrado.get();
            curso.setNome(novosDados.getNome().trim());
            curso.setCargaHoraria(novosDados.getCargaHoraria());
        }

        return cursoEncontrado;
    }

    public boolean remover(Long id) {
        return cursoRepository.remover(id);
    }

    private void validarCurso(Curso curso) {
        if (curso == null) {
            throw new IllegalArgumentException("Os dados do curso são obrigatórios.");
        }

        if (curso.getNome() == null || curso.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do curso é obrigatório.");
        }

        if (curso.getCargaHoraria() == null || curso.getCargaHoraria() <= 0) {
            throw new IllegalArgumentException(
                    "A carga horária deve ser maior que zero."
            );
        }
    }
}