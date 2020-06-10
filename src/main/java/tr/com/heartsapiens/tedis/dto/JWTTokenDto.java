package tr.com.heartsapiens.tedis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTTokenDto {

    @JsonProperty("id_token")
    private String idToken;
}
