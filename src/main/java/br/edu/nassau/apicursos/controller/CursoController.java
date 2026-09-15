package br.edu.nassau.apicursos.controller;

import br.edu.nassau.apicursos.service.CursoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping("/mensagem")
    public String exibirMensagem() {
        return cursoService.gerarMensagem();
    }
}