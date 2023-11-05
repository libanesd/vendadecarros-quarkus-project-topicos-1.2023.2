package br.unitins.topicos1.model;


import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Usuario extends DefaultEntity {
    @Column(length = 200, nullable = true)
    private String nome;
    @Column(length = 200, nullable = true)
    private String login;
    @Column(length = 700, nullable = true)
    private String senha;



    @Enumerated(EnumType.STRING)
    private TipoDeUsuario tipodeusuario;

    public TipoDeUsuario getTipodeusuario() {
        return tipodeusuario;
    }

    public void setTipodeusuario(TipoDeUsuario tipodeusuario) {
        this.tipodeusuario = tipodeusuario;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
