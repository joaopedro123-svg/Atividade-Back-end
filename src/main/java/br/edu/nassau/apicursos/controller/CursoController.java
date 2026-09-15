package br.edu.nassau.apicursos.controller;

import java.util.Map;
import br.edu.nassau.apicursos.model.Curso;
import br.edu.nassau.apicursos.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> tratarDadosInvalidos(
            IllegalArgumentException exception
    ) {
        return ResponseEntity.badRequest()
                .body(Map.of("erro", exception.getMessage()));
    }
}