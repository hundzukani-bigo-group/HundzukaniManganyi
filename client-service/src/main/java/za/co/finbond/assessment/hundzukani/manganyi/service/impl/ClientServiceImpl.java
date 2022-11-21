package za.co.finbond.assessment.hundzukani.manganyi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;
import za.co.finbond.assessment.hundzukani.manganyi.dto.ClientDetails;
import za.co.finbond.assessment.hundzukani.manganyi.entities.ClientEntity;
import za.co.finbond.assessment.hundzukani.manganyi.exception.AlreadyExistException;
import za.co.finbond.assessment.hundzukani.manganyi.repository.ClientRepository;
import za.co.finbond.assessment.hundzukani.manganyi.service.ClientService;

import java.util.function.BiConsumer;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    BiConsumer<ClientEntity, SynchronousSink<ClientDetails>> usernameBiConsumerHandler = (entity, synchronousSink) -> {
        throw new AlreadyExistException(String.format("Username [ %s ] already exist. Please provide a different username.", entity.getUsername()));
    };

    private final ClientRepository clientRepository;

    /**
     * Authenticate By Username and password
     * @param username
     * @return
     */
    @Override
    public Mono<ClientDetails> authenticate(String username, String password) {
        return this.clientRepository.authenticate(username, password)
                .map(entity -> ClientDetails.builder()
                        .username(entity.getUsername())
                        .firstName(entity.getFirstName())
                        .lastName(entity.getLastName())
                        .password(entity.getPassword())
                        .build())
                .log();
    }

    /**
     * Create Client
     * @param clientDetails
     * @return
     */
    @Override
    public Mono<ClientDetails> create(ClientDetails clientDetails) {
        return this.clientRepository.findByUsername(clientDetails.getUsername())
                .log()
                .handle(usernameBiConsumerHandler)
                .switchIfEmpty(Mono.defer(() -> {
                    return this.clientRepository.save(ClientEntity.builder()
                        .firstName(clientDetails.getFirstName())
                        .lastName(clientDetails.getLastName())
                        .username(clientDetails.getUsername())
                        .password(clientDetails.getPassword())
                .build()).map(entity -> clientDetails);
                }))
                .log();

    }

    /**
     * Find Client Details By Username
     * @param username
     * @return
     */
    @Override
    public Mono<ClientDetails> findByUsername(String username) {
        return this.clientRepository.findByUsername(username)
                .map(entity -> ClientDetails.builder()
                        .username(entity.getUsername())
                        .firstName(entity.getFirstName())
                        .lastName(entity.getLastName())
                        .password(entity.getPassword())
                        .build())
                .log();
    }

}
