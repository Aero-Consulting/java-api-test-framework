package helpers.factories;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.http.HttpStatus.SC_OK;

import helpers.ConfigHelper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecificationFactory {

    public static RequestSpecification baseRequestSpecification() {
        String baseUrl = ConfigHelper.getInstance().getBaseUrl();
        return new RequestSpecBuilder()
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .setRelaxedHTTPSValidation()
            .setBaseUri(baseUrl)
            .addFilter(new AllureRestAssured())
            .log(LogDetail.ALL)
            .build();
    }

    public static ResponseSpecification successResponseSpecification() {
        return new ResponseSpecBuilder()
            .expectStatusCode(SC_OK)
            .log(LogDetail.ALL)
            .build();
    }

    public static ResponseSpecification successCreatedResponseSpecification() {
        return new ResponseSpecBuilder()
            .expectStatusCode(SC_CREATED)
            .log(LogDetail.ALL)
            .build();
    }

    public static ResponseSpecification successDeletedResponseSpecification() {
        return new ResponseSpecBuilder()
            .expectStatusCode(SC_NO_CONTENT)
            .log(LogDetail.ALL)
            .build();
    }

    public static ResponseSpecification badRequestResponseSpecification() {
        return new ResponseSpecBuilder()
            .expectStatusCode(SC_BAD_REQUEST)
            .log(LogDetail.ALL)
            .build();
    }
}
