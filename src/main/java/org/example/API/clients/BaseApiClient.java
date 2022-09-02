package org.example.API.clients;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.UI.utils.PropertiesLoader;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseApiClient {

    private static final String TOKEN = "token";
    private static final String BASE_URL = "base.uri";
    RequestSpecification rqSpec;

    public BaseApiClient() {
        rqSpec = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header(TOKEN, PropertiesLoader.loadProperties().getProperty(TOKEN))
                .baseUri(PropertiesLoader.loadProperties().getProperty(BASE_URL))
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
