package za.co.finbond.assessment.hundzukani.manganyi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import za.co.finbond.assessment.hundzukani.manganyi.dto.ClientDetails;
import za.co.finbond.assessment.hundzukani.manganyi.repository.ClientRepository;
import za.co.finbond.assessment.hundzukani.manganyi.service.impl.ClientServiceImpl;
import za.co.finbond.assessment.hundzukani.manganyi.testdata.ClientTestData;

public class ClientServiceTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    void before() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testCreateClient_Given_Non_Existing_Username() {
        ClientDetails clientDetails = ClientTestData.getClientDetailsTestData();
        Mockito.when(this.clientRepository.findByUsername(Mockito.any())).thenReturn(Mono.empty());
        Mockito.when(this.clientRepository.save(Mockito.any())).thenReturn(Mono.just(ClientTestData.getClientTestData()));
        Mono<ClientDetails> clientDetailsMono = this.clientService.create(clientDetails);
        StepVerifier
                .create(clientDetailsMono)
                .assertNext(response -> {
                    Assertions.assertNotNull(response);
                })
                .expectNextCount(0)
                .verifyComplete();

    }

    @Test
    public void testCreateClient_Given_Existing_Username() {
        ClientDetails clientDetails = ClientTestData.getClientDetailsTestData();
        Mockito.when(this.clientRepository.findByUsername(Mockito.any())).thenReturn(Mono.just(ClientTestData.getClientTestData()));
        Mockito.when(this.clientRepository.save(Mockito.any())).thenReturn(Mono.just(ClientTestData.getClientTestData()));

        Mono<ClientDetails> clientDetailsMono = this.clientService.create(clientDetails);

        StepVerifier
                .create(clientDetailsMono)
                .expectComplete();
    }

    @Test
    public void testAuthenticate_Given_Valid_Credentials() {
        Mockito.when(this.clientRepository.authenticate(Mockito.any(), Mockito.any())).thenReturn(Mono.just(ClientTestData.getClientTestData()));
        Mono<ClientDetails> clientDetailsMono = this.clientService.authenticate("test", "password");
        StepVerifier
                .create(clientDetailsMono)
                .assertNext(response -> {
                    Assertions.assertNotNull(response);
                })
                .expectNextCount(0)
                .verifyComplete();

    }

    @Test
    public void testAuthenticate_Given_InValid_Credentials() {
        Mockito.when(this.clientRepository.authenticate(Mockito.any(), Mockito.any())).thenReturn(Mono.empty());
        Mono<ClientDetails> clientDetailsMono = this.clientService.authenticate("test", "password");
        StepVerifier
                .create(clientDetailsMono)
                .verifyComplete();

    }

    @Test
    public void testFindByUsername_Given_Valid_Username() {
        Mockito.when(this.clientRepository.findByUsername(Mockito.any())).thenReturn(Mono.just(ClientTestData.getClientTestData()));
        Mono<ClientDetails> clientDetailsMono = this.clientService.findByUsername("84545444521212145");

        StepVerifier
                .create(clientDetailsMono)
                .assertNext(response -> {
                    Assertions.assertNotNull(response);
                })
                .expectNextCount(0)
                .verifyComplete();
    }


    @Test
    public void testFindByUsername_Given_InValid_Username() {
        Mockito.when(this.clientRepository.findByUsername(Mockito.any())).thenReturn(Mono.empty());
        Mono<ClientDetails> clientDetailsMono = this.clientService.findByUsername("84545444521212145");

        StepVerifier
                .create(clientDetailsMono)
                .verifyComplete();
    }

}
