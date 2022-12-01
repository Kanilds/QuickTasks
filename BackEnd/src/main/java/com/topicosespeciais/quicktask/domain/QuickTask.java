package com.topicosespeciais.quicktask.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class QuickTask implements Serializable {

    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String descricao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataParaFinalizar;
    private Boolean finalizado = false;

    public QuickTask() {
        super();
    }

    public QuickTask(Integer id, String titulo, String descricao, Date dataParaFinalizar, Boolean finalizado) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataParaFinalizar = dataParaFinalizar;
        this.finalizado = finalizado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataParaFinalizar() {
        return dataParaFinalizar;
    }

    public void setDataParaFinalizar(Date dataParaFinalizar) {
        this.dataParaFinalizar = dataParaFinalizar;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuickTask quickTask)) return false;
        return titulo.equals(quickTask.titulo) && descricao.equals(quickTask.descricao) && dataParaFinalizar.equals(quickTask.dataParaFinalizar) && finalizado.equals(quickTask.finalizado);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());

        return result;
    }
}
