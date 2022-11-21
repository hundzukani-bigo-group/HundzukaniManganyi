package za.co.finbond.assessment.hundzukani.manganyi.repository;

import org.springframework.data.r2dbc.repository.Query;
import reactor.core.publisher.Mono;
import za.co.finbond.assessment.hundzukani.manganyi.entities.ClientEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ClientRepository extends R2dbcRepository<ClientEntity, Integer> {

    @Query("select * from client where username = :username")
    Mono<ClientEntity> findByUsername(String username);
    @Query("select * from client where username = :username and password = :password")
    Mono<ClientEntity> authenticate(String username, String password);


}
