package br.edu.nassau.apicursos.service;

import br.edu.nassau.apicursos.model.Curso;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final List<Curso> cursos = new ArrayList<>();
    private Long proximoId = 1L;

    public Curso cadastrar(Curso curso) {
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

    public Optional<Curso> atualizar(Long id, Curso novosDados) {
        Optional<Curso> cursoEncontrado = buscarPorId(id);

        if (cursoEncontrado.isPresent()) {
            Curso curso = cursoEncontrado.get();
            curso.setNome(novosDados.getNome());
            curso.setCargaHoraria(novosDados.getCargaHoraria());
        }

        return cursoEncontrado;
    }

    public boolean remover(Long id) {
        return cursos.removeIf(curso -> curso.getId().equals(id));
    }
}