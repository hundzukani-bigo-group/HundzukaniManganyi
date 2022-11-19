package za.co.finbond.assessment.client.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import za.co.finbond.assessment.client.entities.ClientEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ClientRepository extends R2dbcRepository<ClientEntity, Integer> {

    @Query("select * from client where username = :username")
    Mono<ClientEntity> findByUsername(String username);

}
