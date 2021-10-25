package tests.integration;

import data.User;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import steps.LoginSteps;
import steps.RegisterSteps;
import steps.UsersSteps;

@Feature("Интеграционные тесты ReqRes")
@Story("Примеры")
public class ReqResIntegrationTest {

    @Test(groups = "111", description = "Создание и удаление пользователя")
    public void createAndDeleteUser() {
        UsersSteps usersSteps = new UsersSteps();
        User user = User.builder()
            .name("Vasya")
            .job("gruzchik")
            .build();
        usersSteps.createUserAndGetId(user);
        int userId = 2;
        usersSteps.deleteUser(userId);
    }

    @Test(groups = "222", description = "Регистраци и логин")
    public void registerAndLogin() {
        RegisterSteps registerSteps = new RegisterSteps();
        LoginSteps loginSteps = new LoginSteps();
        User user = User.builder()
            .email("eve.holt@reqres.in")
            .password("pistol")
            .build();
        registerSteps.registerUser(user);
        loginSteps.userCanLogin(user);
    }

    @Test(groups = "333", description = "Попытка входа без пароля")
    public void loginWithoutPassword() {
        LoginSteps loginSteps = new LoginSteps();
        User user = User.builder()
            .email("eve.holt@reqres.in")
            .build();
        loginSteps.userCannotLogin(user);
    }
}
