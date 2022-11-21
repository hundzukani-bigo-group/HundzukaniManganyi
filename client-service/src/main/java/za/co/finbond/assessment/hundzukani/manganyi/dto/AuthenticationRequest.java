package za.co.finbond.assessment.hundzukani.manganyi.dto;

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
public class AuthenticationRequest implements Serializable {

    @NotEmpty(message = "Username Cannot be empty or null.")
    private String username;

    @NotEmpty(message = "Password Cannot be empty or null.")
    private String password;

}
