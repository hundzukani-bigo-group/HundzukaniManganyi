package za.co.finbond.assessment.hundzukani.manganyi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import za.co.finbond.assessment.hundzukani.manganyi.dto.AuthenticationRequest;
import za.co.finbond.assessment.hundzukani.manganyi.dto.ClientDetails;
import za.co.finbond.assessment.hundzukani.manganyi.service.ClientService;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ClientDetails> createClient(@Valid @RequestBody ClientDetails clientDetails) {
        return this.clientService.create(clientDetails);
    }

    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<ClientDetails> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return this.clientService.authenticate(request.getUsername(), request.getPassword());
    }

    @GetMapping(value = "/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<ClientDetails> findByUsername(@PathVariable("username") String username) {
        return this.clientService.findByUsername(username);
    }

}

