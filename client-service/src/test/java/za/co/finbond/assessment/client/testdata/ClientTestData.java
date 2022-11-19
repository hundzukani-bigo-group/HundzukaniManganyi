package za.co.finbond.assessment.client.testdata;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import za.co.finbond.assessment.client.dto.ClientDetails;
import za.co.finbond.assessment.client.entities.ClientEntity;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ClientTestData {

    public static ClientEntity getClientTestData() {
        return ClientEntity.builder()
                .firstName("Hundzukani")
                .lastName("Manganyi")
                .username("Hundzu")
                .password("password")
                .build();
    }

    public static ClientDetails getClientDetailsTestData() {
        return ClientDetails.builder()
                .firstName("Hundzukani")
                .lastName("Manganyi")
                .username("Hundzu")
                .password("password")
                .build();
    }
    
}
