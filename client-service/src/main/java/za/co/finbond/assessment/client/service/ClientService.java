package za.co.finbond.assessment.client.service;

import reactor.core.publisher.Mono;
import za.co.finbond.assessment.client.dto.ClientDetails;

public interface ClientService {

    Mono<ClientDetails> create(ClientDetails clientDetails);

    Mono<ClientDetails> findByUsername(String username);


}
