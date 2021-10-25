package pojos.requests.users;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostUserRequest {
    @JsonProperty("name")
    public String name;
    @JsonProperty("job")
    public String job;
}
