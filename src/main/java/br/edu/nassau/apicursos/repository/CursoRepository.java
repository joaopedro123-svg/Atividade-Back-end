package br.edu.nassau.apicursos.repository;

import br.edu.nassau.apicursos.model.Curso;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CursoRepository {

    private final List<Curso> cursos = new ArrayList<>();
    private Long proximoId = 1L;

    public Curso salvar(Curso curso) {
        curso.setId(proximoId);
        proximoId++;
        cursos.add(curso);
        return curso;
    }

    public List<Curso> listarTodos() {
        return new ArrayList<>(cursos);
    }

    public Optional<Curso> buscarPorId(Long id) {
        return cursos.stream()
                .filter(curso -> curso.getId().equals(id))
                .findFirst();
    }

    public boolean remover(Long id) {
        return cursos.removeIf(curso -> curso.getId().equals(id));
    }
}