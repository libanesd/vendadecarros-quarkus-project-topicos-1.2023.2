package br.unitins.topicos1.model;

import java.util.List;

import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioDadosBasicosJwtDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioIdDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioInsertDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioInsertUserDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioJwtDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioResponseDTO;
import br.unitins.topicos1.dto.UsuarioDTORepository.UsuarioSemSenhaDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuario")
public class Usuario extends DefaultEntity{

    @Column(length = 200, nullable = true)
    private String nome;
    @Column(length = 200, nullable = true)
    private String login;
    @Column(length = 700, nullable = true)
    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoDeUsuario tipodeusuario;

    @Column(length = 12, nullable = true)
    private String cpf;
    @Column(length = 200, nullable = true)
    private String endereco;
    @Column(length = 200, nullable = true)
    private String telefone;
    @Column(length = 200, nullable = true)
    private String email;

    @ManyToMany
    @JoinTable(name = "usuario_oferta",
               joinColumns = @JoinColumn(name = "usuario_id"),
               inverseJoinColumns = @JoinColumn(name = "oferta_id"))
    private  List<Oferta> ofertas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<MovimentacaoFinanceira> vendas;

    public static Usuario valueOfUsuarioDTO(UsuarioDTO usuario){
        Usuario usuarioCast = new Usuario();
        usuarioCast.setTipodeusuario(usuario.tipodeusuario());
        usuarioCast.setEndereco(usuario.endereco());
        usuarioCast.setCpf(usuario.cpf());
        usuarioCast.setTelefone(usuario.telefone());
        usuarioCast.setEmail(usuario.email());
        usuarioCast.setVendas(usuario.vendas()
                .stream()
                .map(t -> MovimentacaoFinanceira.valueOfVendaUsuarioDTO(t)).toList());
        usuarioCast.setOfertas(usuario.ofertas()
                .stream()
                .map(t -> Oferta.valueOfOfertaUsuarioDTO(t)).toList());
        return usuarioCast;
    }

    public static Usuario valueOfUsuarioResponseDTO(UsuarioResponseDTO usuario){
        Usuario usuarioCast = new Usuario();
        usuarioCast.setId(usuario.id());
        usuarioCast.setTipodeusuario(usuario.tipodeusuario());
        usuarioCast.setEndereco(usuario.endereco());
        usuarioCast.setCpf(usuario.cpf());
        usuarioCast.setTelefone(usuario.telefone());
        usuarioCast.setEmail(usuario.email());
        usuarioCast.setVendas(usuario.vendas()
                .stream()
                .map(t -> MovimentacaoFinanceira.valueOfVendaUsuarioDTO(t)).toList());
        usuarioCast.setOfertas(usuario.ofertas()
                .stream()
                .map(t -> Oferta.valueOfOfertaUsuarioDTO(t)).toList());
        return usuarioCast;
    }

    public static Usuario valueOfUsuarioJwtDTO(UsuarioJwtDTO usuario){
        Usuario usuarioCast = new Usuario();
        usuarioCast.setId(usuario.id());
        usuarioCast.setNome(usuario.nome());
        usuarioCast.setLogin(usuario.login());
        usuarioCast.setEndereco(usuario.endereco());
        usuarioCast.setTelefone(usuario.telefone());
        usuarioCast.setTipodeusuario(usuario.tipodeusuario());
        usuarioCast.setOfertas(usuario.ofertas()
                .stream()
                .map(t -> Oferta.valueOfOfertaIdDTO(t)).toList());
        return usuarioCast;
    }

    public static Usuario valueOfUsuarioDadosBasicosJwtDTO(UsuarioDadosBasicosJwtDTO usuario){
        Usuario usuarioCast = new Usuario();
        usuarioCast.setId(usuario.id());
        usuarioCast.setLogin(usuario.login());
        usuarioCast.setNome(usuario.nome());
        usuarioCast.setEndereco(usuario.endereco());
        usuarioCast.setTelefone(usuario.telefone());
        return usuarioCast;
    }

    public static Usuario valueOfUsuarioInsertDTO(UsuarioInsertDTO usuario){
        Usuario usuarioCast = new Usuario();
        usuarioCast.setTipodeusuario(usuario.tipodeusuario());
        usuarioCast.setEndereco(usuario.endereco());
        usuarioCast.setNome(usuario.nome());
        usuarioCast.setCpf(usuario.cpf());
        usuarioCast.setTelefone(usuario.telefone());
        usuarioCast.setEmail(usuario.email());
        usuarioCast.setSenha(usuario.senha());
        usuarioCast.setLogin(usuario.login());
        return usuarioCast;
    }

    public static Usuario valueOfUsuarioSemSenhaDTO(UsuarioSemSenhaDTO usuario){
        Usuario usuarioCast = new Usuario();
        usuarioCast.setCpf(usuario.cpf());
        usuarioCast.setNome(usuario.nome());
        usuarioCast.setLogin(usuario.login());
        usuarioCast.setEndereco(usuario.endereco());
        usuarioCast.setTelefone(usuario.telefone());
        usuarioCast.setEmail(usuario.email());
        return usuarioCast;
    }

    public static Usuario valueOfUsuarioInsertUserDTO(UsuarioInsertUserDTO usuario){
        Usuario usuarioCast = new Usuario();
        usuarioCast.setEndereco(usuario.endereco());
        usuarioCast.setNome(usuario.nome());
        usuarioCast.setCpf(usuario.cpf());
        usuarioCast.setTelefone(usuario.telefone());
        usuarioCast.setEmail(usuario.email());
        usuarioCast.setSenha(usuario.senha());
        usuarioCast.setLogin(usuario.login());
        return usuarioCast;
    }
    
    public static Usuario valueOfUsuarioIdDTO(UsuarioIdDTO usuario){
        Usuario usuarioCast = new Usuario();
        usuarioCast.setId(usuario.id());
        return usuarioCast;
    }

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
    
    public List<MovimentacaoFinanceira> getVendas() {
        return vendas;
    }
    public void setVendas(List<MovimentacaoFinanceira> vendas) {
        this.vendas = vendas;
    }
    public List<Oferta> getOfertas() {
        return ofertas;
    }
    public void setOfertas(List<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
}
