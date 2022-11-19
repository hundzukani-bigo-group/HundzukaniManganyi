package za.co.finbond.assessment.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import za.co.finbond.assessment.client.dto.ClientDetails;
import za.co.finbond.assessment.client.service.ClientService;

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


    @GetMapping(value = "/username/{username}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<ClientDetails> findByUsername(@PathVariable("username") String username) {
        return this.clientService.findByUsername(username);
    }

}

