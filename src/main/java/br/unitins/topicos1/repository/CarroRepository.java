package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Carro;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class CarroRepository implements PanacheRepository<Carro>{
    public List<Carro> findByNome(String nome) {
        return find("UPPER(nomeCarro) LIKE UPPER(?1) ", "%"+nome+"%").list();
    }
    public List<Carro> findAllEnable() {
        return find("desativado = false AND deletado = false").list();
    }

    public List<Carro> findByIdCarros(Long id){
        return find("id = (?1)", id).list();
    }

    public List<Carro> findCarrosAVenda(){
        return find("vendido = false ").list();
    }
}
