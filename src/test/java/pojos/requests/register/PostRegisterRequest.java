package pojos.requests.register;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostRegisterRequest {

    @JsonProperty("email")
    public String email;
    @JsonProperty("password")
    public String password;
}
