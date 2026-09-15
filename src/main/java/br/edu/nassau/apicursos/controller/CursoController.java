package br.edu.nassau.apicursos.controller;

import br.edu.nassau.apicursos.model.Curso;
import br.edu.nassau.apicursos.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listarTodos() {
        return ResponseEntity.ok(cursoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable Long id) {
        return cursoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Curso> cadastrar(@RequestBody Curso curso) {
        Curso cursoCadastrado = cursoService.cadastrar(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoCadastrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar(
            @PathVariable Long id,
            @RequestBody Curso curso) {

        return cursoService.atualizar(id, curso)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        boolean removido = cursoService.remover(id);

        if (removido) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}