package org.example.API.clients;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseApiClient {

    RequestSpecification rqSpec;

    public BaseApiClient() {
        rqSpec = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token", "8eee9f600e10a105a1887b3397e7278d453f3f77")
                .baseUri("https://api.qase.io")
                .log().ifValidationFails();
    }

    public Response post(String uri, Object body, Map<String, ?> parameterNameValuePairs) {
        return given().spec(rqSpec)
                      .pathParams(parameterNameValuePairs)
                      .body(body)
                      .when()
                      .post(uri)
                      .then()
                      .log().ifValidationFails()
                      .extract()
                      .response();
    }

    public Response get(String uri, Map<String, ?> parameterNameValuePairs) {
        return given().spec(rqSpec)
                      .pathParams(parameterNameValuePairs)
                      .when()
                      .get(uri)
                      .then()
                      .log().ifValidationFails()
                      .extract()
                      .response();
    }

    public Response delete(String uri, Map<String, ?> parameterNameValuePairs) {
        return given().spec(rqSpec)
                      .pathParams(parameterNameValuePairs)
                      .when()
                      .delete(uri)
                      .then()
                      .log().ifValidationFails()
                      .extract()
                      .response();
    }

    public Response getAll(String uri,Map<String, ?> pathParamsNameValuePairs, Map<String, ?> queryParamsNameValuePairs) {
        return given().spec(rqSpec)
                      .pathParams(pathParamsNameValuePairs)
                      .queryParams(queryParamsNameValuePairs)
                      .when()
                      .get(uri)
                      .then()
                      .log().ifValidationFails()
                      .extract()
                      .response();
    }
}
