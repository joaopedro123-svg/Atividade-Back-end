package br.edu.nassau.apicursos.model;

public class Curso {

    private Long id;
    private String nome;
    private Integer cargaHoraria;

    public Curso() {
    }

    public Curso(String nome, Integer cargaHoraria) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    public Curso(Long id, String nome, Integer cargaHoraria) {
        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
}