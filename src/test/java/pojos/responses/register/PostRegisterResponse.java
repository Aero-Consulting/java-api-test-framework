package pojos.responses.register;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostRegisterResponse {

    @JsonProperty("id")
    public String id;
    @JsonProperty("token")
    public String token;
}
