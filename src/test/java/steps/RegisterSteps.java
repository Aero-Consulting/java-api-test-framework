package steps;

import clients.RegisterClient;
import data.User;
import helpers.factories.SpecificationFactory;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojos.requests.register.PostRegisterRequest;

public class RegisterSteps {

    private final RegisterClient registerClient = new RegisterClient();

    @Step("Выполнить запрос POST /api/register")
    public Response sendGetUsersRequest(User user) {
        PostRegisterRequest body = PostRegisterRequest.builder()
            .email(user.getEmail())
            .password(user.getPassword())
            .build();
        return registerClient.postRegisterRequest(body);
    }

    @Step("Зарегистрировать пользователя")
    public void registerUser(User user) {
        sendGetUsersRequest(user)
            .then()
            .spec(SpecificationFactory.successResponseSpecification());
    }
}
