package br.edu.nassau.apicursos.service;

import org.springframework.stereotype.Service;

@Service
public class CursoService {

    public String gerarMensagem() {
        return "API de cursos funcionando!";
    }
}