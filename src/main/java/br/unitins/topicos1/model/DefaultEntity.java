package br.unitins.topicos1.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@MappedSuperclass
public class DefaultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date dataCriacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;
    
    @Column(name = "deletado")
    private Boolean deletado;

    @Column(name = "desativado")
    private Boolean desativado;

    @PrePersist
    protected void onCreate() {
        dataCriacao = new Date(); // Define a data de criação como a data atual
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
    public boolean isDesativado() {
        return desativado;
    }

    public void setDesativado(boolean desativado) {
        this.desativado = desativado;
    }
    public boolean isDeletado() {
        return deletado;
    }

    public void setDeletado(boolean deletado) {
        this.deletado = deletado;
    }
}
