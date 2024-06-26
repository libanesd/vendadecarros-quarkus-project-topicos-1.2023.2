package br.unitins.topicos1.application;

import java.io.InputStream;
import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.PasswordAuthentication;

public class Email {

    private String usuario;
	private String senha;
	private String assunto;
	private String emailDestino;
	private String mensagem;
	
	public static void main(String[] args) {
		Email email = new Email("janiojunior@gmail.com", "Teste 2", "Novo teste");
		//System.out.println(email.enviar());
	}
	
	public Email(String emailDestino, String assunto, String mensagem) {
		this.emailDestino = emailDestino;
		this.assunto = assunto;
		this.mensagem = mensagem;
		
		Properties prop = new Properties();
		try (InputStream is = this.getClass().getResourceAsStream("/config/email.properties")){
			prop.load(is);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\n\n\n\n NÃO FOI ENCONTRADO O ARQUIVO email.properties na pasta config. \n\n\n\n");
		}
		
        setUsuario(prop.getProperty("usuario"));
        setSenha(prop.getProperty("senha"));
	}
	
	
	public boolean enviar(String email, String codigo) {
		
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS
		System.out.println(getUsuario());
		System.out.println(getSenha());

		Session session = Session.getInstance(prop, 
				new jakarta.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(getUsuario(), getSenha());
					}
				});
				System.out.println(session.getDebug());
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("leonardozeaim@gmail.br"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject("Recuperar senha:");
			message.setText("Seu codigo para recuperar senha é: " + codigo);

			// enviando o email
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}		
	}

	private String getUsuario() {
		return usuario;
	}

	private void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	private String getSenha() {
		return senha;
	}

	private void setSenha(String senha) {
		this.senha = senha;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getEmailDestino() {
		return emailDestino;
	}

	public void setEmailDestino(String emailDestino) {
		this.emailDestino = emailDestino;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
	
}
