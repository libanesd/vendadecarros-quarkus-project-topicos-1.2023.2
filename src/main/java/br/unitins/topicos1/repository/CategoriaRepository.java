package br.unitins.topicos1.repository;
import java.util.List;

import br.unitins.topicos1.model.Categoria;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class CategoriaRepository implements PanacheRepository<Categoria>{
    public List<Categoria> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1) ", "%"+nome+"%").list();
    }
    public List<Categoria> findAllEnable() {
        return find("desativado = false AND deletado = false").list();
    }
}


