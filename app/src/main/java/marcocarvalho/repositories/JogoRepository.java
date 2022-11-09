package marcocarvalho.repositories;

import org.springframework.data.repository.CrudRepository;

import marcocarvalho.models.Jogo;

public interface JogoRepository extends CrudRepository<Jogo, Integer> {
    
}