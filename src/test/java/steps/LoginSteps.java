package steps;

import static helpers.factories.SpecificationFactory.badRequestResponseSpecification;
import static helpers.factories.SpecificationFactory.successResponseSpecification;

import clients.LoginClient;
import data.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import pojos.requests.login.PostLoginRequest;
import pojos.responses.PostErrorResponse;
import pojos.responses.login.PostLoginResponse;

public class LoginSteps {

    private final LoginClient loginClient = new LoginClient();

    @Step("Выполнить запрос POST /api/login")
    public Response sendGetUsersRequest(User user) {
        PostLoginRequest body = PostLoginRequest.builder()
            .email(user.getEmail())
            .password(user.getPassword())
            .build();
        return loginClient.postLoginRequest(body);
    }

    @Step("Пользователь может войти в систему")
    public void userCanLogin(User user) {
        PostLoginResponse responseBody = sendGetUsersRequest(user)
            .then()
            .spec(successResponseSpecification())
            .extract()
            .as(PostLoginResponse.class);
        Assert.assertNotNull(responseBody.getToken());
    }

    @Step("Пользователь не может войти в систему")
    public void userCannotLogin(User user) {
        PostErrorResponse errorBody = sendGetUsersRequest(user)
            .then()
            .spec(badRequestResponseSpecification())
            .extract()
            .as(PostErrorResponse.class);
        Assert.assertEquals(errorBody.getError(), "Missing password");
    }

}
