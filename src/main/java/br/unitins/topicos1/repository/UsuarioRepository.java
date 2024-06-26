package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario>{
    public List<Usuario> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1) ", "%"+nome+"%").list();
    }

    public Usuario findByLogin(String login) {
        try {
            System.out.println(login);
            var u = find("login LIKE ?1 ", login).singleResult();
            System.out.println(u);
            return u;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public Usuario findByLoginAndSenha(String login, String senha) {
        try {
            return find("login = ?1 AND senha = ?2 ", login, senha).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public List<Usuario> findAllEnable() {
        return find("desativado = false AND deletado = false").list();
    }
}
