package clients;

import static helpers.factories.SpecificationFactory.baseRequestSpecification;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import pojos.requests.register.PostRegisterRequest;

public class RegisterClient {

    public static final String PATH_REGISTER = "/api/register";

    public Response postRegisterRequest(PostRegisterRequest body) {
        return given()
            .spec(baseRequestSpecification())
            .body(body)
            .post(PATH_REGISTER);
    }
}
