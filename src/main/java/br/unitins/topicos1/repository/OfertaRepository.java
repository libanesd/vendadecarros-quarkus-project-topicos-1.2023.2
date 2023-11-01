package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Oferta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class OfertaRepository implements PanacheRepository<Oferta>{
    public List<Oferta> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1) ", "%"+nome+"%").list();
    }

    public List<Oferta> findByIdCliente(Long id) {
        return find("SELECT * FROM ofertas WHERE id_cliente = :id;", "%"+id+"%").list();
    }
}
