package br.edu.nassau.apicursos.repository;

import br.edu.nassau.apicursos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}