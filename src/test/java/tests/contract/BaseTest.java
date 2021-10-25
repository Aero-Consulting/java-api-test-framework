package tests.contract;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.core.report.ListReportProvider;
import com.github.fge.jsonschema.core.report.LogLevel;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.qameta.allure.Step;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class BaseTest {
    private static JsonSchemaFactory jsonSchemaFactory;

    @BeforeSuite(alwaysRun = true, description = "Инициализируем валидатор JSON схем")
    public void before() {
        jsonSchemaFactory = JsonSchemaFactory.newBuilder()
                .setValidationConfiguration(
                        ValidationConfiguration.newBuilder()
                                .setDefaultVersion(SchemaVersion.DRAFTV4).freeze())
                .setReportProvider(new ListReportProvider(LogLevel.NONE, LogLevel.DEBUG))
                .freeze();

        System.out.println("this thread" + Thread.currentThread().getName());
    }

    @Step("Проверяем, что ответ соответвует схеме '{schemaPath}'")
    public void validateSchema(Response response, String schemaPath) {
        System.out.println("this thread" + Thread.currentThread().getName());
        response
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body(matchesJsonSchemaInClasspath(schemaPath).using(jsonSchemaFactory));
    }
}
