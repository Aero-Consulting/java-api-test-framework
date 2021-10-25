package steps;

import static helpers.factories.SpecificationFactory.successCreatedResponseSpecification;
import static helpers.factories.SpecificationFactory.successDeletedResponseSpecification;
import static helpers.factories.SpecificationFactory.successResponseSpecification;

import clients.UsersClient;
import data.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojos.requests.users.PostUserRequest;
import pojos.responses.users.GetUsersIdResponse;

public class UsersSteps {

    private final UsersClient usersClient = new UsersClient();

    @Step("Выполнить запрос GET /api/users")
    public Response sendGetUsersRequest() {
        return usersClient.getUsersRequest();
    }

    @Step("Выполнить запрос GET /api/users/{userId}")
    public Response sendGetUsersIdRequest(int userId) {
        return usersClient.getUsersRequest(userId);
    }

    @Step("Выполнить запрос POST /api/users")
    public Response sendPostUserRequest(User user) {
        PostUserRequest requestBody = PostUserRequest.builder()
            .name(user.getName())
            .job(user.getJob())
            .build();
        return usersClient.postUsersRequest(requestBody);
    }

    @Step("Выполнить запрос DELETE /api/users/{userId}")
    public Response sendDeleteUserRequest(int userId) {
        return usersClient.deleteUsersRequest(userId);
    }

    @Step("Создать пользователя {user}")
    public Response createUser(User user) {
        return sendPostUserRequest(user)
            .then()
            .spec(successResponseSpecification())
            .extract()
            .response();
    }

    @Step("Создать пользователя {user} и получить id")
    public int createUserAndGetId(User user) {
        GetUsersIdResponse responseBody = sendPostUserRequest(user)
            .then()
            .spec(successCreatedResponseSpecification())
            .extract()
            .as(GetUsersIdResponse.class);
        return responseBody.getId();
    }

    @Step("Удалить пользователя с id:{userId}")
    public void deleteUser(int userId) {
        sendDeleteUserRequest(userId)
            .then()
            .spec(successDeletedResponseSpecification());
    }
}
