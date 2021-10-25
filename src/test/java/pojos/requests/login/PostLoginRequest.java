package pojos.requests.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostLoginRequest {

    @JsonProperty("email")
    public String email;
    @JsonProperty("password")
    public String password;
}
