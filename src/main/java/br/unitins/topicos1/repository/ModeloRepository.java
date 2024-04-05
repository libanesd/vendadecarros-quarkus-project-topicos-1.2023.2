package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Modelo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ModeloRepository implements PanacheRepository<Modelo> {
    public List<Modelo> findByNome(String nomemodelo) {
        return find("UPPER(nomeModelo) LIKE UPPER(?1) ", "%"+nomemodelo+"%").list();
    }

    public List<Modelo> findByIdModelos(Long id){
        return find("id = (?1)", id).list();
    }
}
