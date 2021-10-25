package clients;

import static helpers.factories.SpecificationFactory.baseRequestSpecification;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import pojos.requests.users.PostUserRequest;

public class UsersClient {

    public static final String PATH_USERS = "/api/users";
    public static final String PATH_USERS_ID = "/api/users/{id}";

    public Response getUsersRequest() {
        return given()
            .spec(baseRequestSpecification())
            .get(PATH_USERS);
    }

    public Response getUsersRequest(int userId) {
        return given()
            .spec(baseRequestSpecification())
            .pathParam("id", userId)
            .get(PATH_USERS_ID);
    }

    public Response postUsersRequest(PostUserRequest body) {
        return given()
            .spec(baseRequestSpecification())
            .body(body)
            .post(PATH_USERS);
    }

    public Response putUsersRequest(int userId, PostUserRequest body) {
        return given()
            .spec(baseRequestSpecification())
            .pathParam("id", userId)
            .body(body)
            .post(PATH_USERS_ID);
    }

    public Response deleteUsersRequest(int userId) {
        return given()
            .spec(baseRequestSpecification())
            .pathParam("id", userId)
            .delete(PATH_USERS_ID);
    }
}
