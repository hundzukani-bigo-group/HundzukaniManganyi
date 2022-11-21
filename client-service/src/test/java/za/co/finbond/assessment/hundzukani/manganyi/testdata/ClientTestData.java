package za.co.finbond.assessment.hundzukani.manganyi.testdata;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import za.co.finbond.assessment.hundzukani.manganyi.dto.ClientDetails;
import za.co.finbond.assessment.hundzukani.manganyi.entities.ClientEntity;

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
