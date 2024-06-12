package br.unitins.topicos1.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "esqueceu_senha")
public class EsqueceuSenha extends DefaultEntity{
	@Column(length = 200, nullable = true)
    private String codigo;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	@Column(nullable = true)
	private LocalDateTime dataHoraLimite;
	@Column(nullable = true)
	private boolean utilizado;

	
	public boolean isUtilizado() {
		return utilizado;
	}

	public void setUtilizado(boolean utilizado) {
		this.utilizado = utilizado;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getDataHoraLimite() {
		return dataHoraLimite;
	}

	public void setDataHoraLimite(LocalDateTime dataHoraLimite) {
		this.dataHoraLimite = dataHoraLimite;
	}
	
	
}
