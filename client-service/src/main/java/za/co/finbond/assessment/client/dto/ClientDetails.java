package za.co.finbond.assessment.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDetails implements Serializable {

    @NotEmpty(message = "Username Cannot be empty or null.")
    private String username;

    @NotEmpty(message = "Password Cannot be empty or null.")
    private String password;

    @NotEmpty(message = "First Name Cannot be empty or null.")
    private String firstName;

    @NotEmpty(message = "Last Name Cannot be empty or null.")
    private String lastName;

}
