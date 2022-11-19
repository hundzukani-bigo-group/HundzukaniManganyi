package za.co.finbond.assessment.client.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client")
public class ClientEntity implements Serializable {


    @Id
    private Integer id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

}
