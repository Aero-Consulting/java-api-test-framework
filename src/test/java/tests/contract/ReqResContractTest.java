package tests.contract;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import steps.UsersSteps;

@Feature("Контракты ReqRes")
@Story("Проверки контрактов ReqRes")
public class ReqResContractTest extends BaseTest {

    @Test(description = "Проверка контракта получения списка пользователей")
    public void checkGetListUsers() {
        UsersSteps usersSteps = new UsersSteps();
        Response response = usersSteps.sendGetUsersRequest();
        validateSchema(response, "contracts/get_list_users.json");
    }

    @Test(description = "Проверка контракта получения пользователя")
    public void checkGetSingleUser() {
        UsersSteps usersSteps = new UsersSteps();
        Response response = usersSteps.sendGetUsersIdRequest(2);
        validateSchema(response, "contracts/get_single_user.json");
    }
}

