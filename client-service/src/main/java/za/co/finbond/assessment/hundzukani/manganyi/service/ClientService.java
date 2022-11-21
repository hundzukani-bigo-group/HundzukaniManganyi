package za.co.finbond.assessment.hundzukani.manganyi.service;

import reactor.core.publisher.Mono;
import za.co.finbond.assessment.hundzukani.manganyi.dto.ClientDetails;

public interface ClientService {

    Mono<ClientDetails> authenticate(String username, String password);
    Mono<ClientDetails> create(ClientDetails clientDetails);
    Mono<ClientDetails> findByUsername(String username);


}
