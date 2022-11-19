package za.co.finbond.assessment.client.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Error implements Serializable {

    private String field;

    private String rejectedValue;

    private String message;

}
