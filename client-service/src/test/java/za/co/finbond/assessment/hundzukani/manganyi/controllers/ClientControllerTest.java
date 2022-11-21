package za.co.finbond.assessment.hundzukani.manganyi.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import za.co.finbond.assessment.hundzukani.manganyi.dto.ClientDetails;
import za.co.finbond.assessment.hundzukani.manganyi.repository.ClientRepository;
import za.co.finbond.assessment.hundzukani.manganyi.service.impl.ClientServiceImpl;
import za.co.finbond.assessment.hundzukani.manganyi.testdata.ClientTestData;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Import(value = {ClientServiceImpl.class})
@WebFluxTest(ClientController.class)
public class ClientControllerTest {

    public static final String XSRF_TOKEN = "XSRF-TOKEN";
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ClientRepository clientRepository;

    @Test
    @WithMockUser(username = "test", password = "test", roles = {"USER", "ADMIN"})
    void test_createClient_Success() {

        ClientDetails request = ClientTestData.getClientDetailsTestData();

        when(this.clientRepository.findByUsername(any())).thenReturn(Mono.empty());
        when(this.clientRepository.save(any())).thenReturn(Mono.just(ClientTestData.getClientTestData()));

        this.webTestClient.mutateWith(SecurityMockServerConfigurers.csrf()).post()
                .uri("/client/create")
                .contentType(MediaType.APPLICATION_JSON)
                .cookie(XSRF_TOKEN, UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ClientDetails.class)
                .value(response -> {
                    Assertions.assertNotNull(response);
                });
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = {"USER", "ADMIN"})
    void test_createClient_Given_Existing_Username_Success() {

        ClientDetails request = ClientTestData.getClientDetailsTestData();

        when(this.clientRepository.findByUsername(any())).thenReturn(Mono.just(ClientTestData.getClientTestData()));
        when(this.clientRepository.save(any())).thenReturn(Mono.just(ClientTestData.getClientTestData()));

        this.webTestClient.mutateWith(SecurityMockServerConfigurers.csrf()).post()
                .uri("/client/create")
                .contentType(MediaType.APPLICATION_JSON)
                .cookie(XSRF_TOKEN, UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = {"USER", "ADMIN"})
    void test_findByUsername_Given_Existing_Username_Success() {
        String username = "test";
        when(this.clientRepository.findByUsername(any())).thenReturn(Mono.just(ClientTestData.getClientTestData()));
        this.webTestClient.get()
                .uri("/client/username/{username}", username)
                .cookie(XSRF_TOKEN, UUID.randomUUID().toString())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ClientDetails.class)
                .value(response -> {
                    Assertions.assertNotNull(response);
                });
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = {"USER", "ADMIN"})
    void test_findByUsername_Given_Non_Existing_Username_Success() {
        String username = "test";
        when(this.clientRepository.findByUsername(any())).thenReturn(Mono.empty());
        this.webTestClient.get()
                .uri("/client/username/{username}", username)
                .cookie(XSRF_TOKEN, UUID.randomUUID().toString())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ClientDetails.class)
                .value(response -> {
                    Assertions.assertNotNull(response);
                });
    }
}
