package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.EsqueceuSenha;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.persistence.NoResultException;

public class EsqueceuSenhaRepository implements PanacheRepository<EsqueceuSenha>{
    public EsqueceuSenha findCodigo(String codigo)  {
        try {
            return find("codigo = ?1 ", codigo ).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
}
