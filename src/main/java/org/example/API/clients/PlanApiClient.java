package org.example.API.clients;

import io.restassured.response.Response;
import org.example.API.dto.getAllModels.ResponseEndpointAllGet;
import org.example.API.dto.getModels.ResponseEndpointGet;
import org.example.API.dto.postModels.PlanDetailsModel;
import org.example.API.dto.postModels.ResponseEndpointPost;

import java.util.Map;

public class PlanApiClient extends BaseApiClient {

    public static final String PLAN_URI = "/v1/plan";
    public static final String PLAN_URI_WITH_CODE = PLAN_URI + "/{planCode}";
    public static final String PLAN_URI_WITH_CODE_AND_ID = PLAN_URI + "/{planCode}" + "/{id}";
    public static final String PLAN_CODE = "planCode";
    public static final String PLAN_ID = "id";

    public ResponseEndpointPost postPlan(PlanDetailsModel planDetailsModel, String planCode) {
        Response response = post(PLAN_URI_WITH_CODE, planDetailsModel, Map.of(PLAN_CODE, planCode));
        return response.then()
                       .statusCode(200)
                       .extract()
                       .body()
                       .as(ResponseEndpointPost.class);
    }

    public ResponseEndpointGet getPlan(String planCode, int id) {
        Response response = get(PLAN_URI_WITH_CODE_AND_ID, Map.of(PLAN_CODE, planCode, PLAN_ID, id));
        return response.then()
                       .statusCode(200)
                       .extract()
                       .body()
                       .as(ResponseEndpointGet.class);
    }

    public Response getPlanResponse(String planCode, int id) {
        return get(PLAN_URI_WITH_CODE_AND_ID, Map.of(PLAN_CODE, planCode, PLAN_ID, id));
    }

    public ResponseEndpointPost deletePlan(String planCode, int id) {
        Response response = delete(PLAN_URI_WITH_CODE_AND_ID, Map.of(PLAN_CODE, planCode, PLAN_ID, id));
        return response.then()
                       .statusCode(200)
                       .extract()
                       .body()
                       .as(ResponseEndpointPost.class);
    }

    public ResponseEndpointAllGet getAllProject(String planCode) {
        Response response = getAll(PLAN_URI_WITH_CODE, Map.of(PLAN_CODE, planCode), Map.of("limit", "10", "offset", "0"));
        return response.then()
                       .statusCode(200)
                       .extract()
                       .body()
                       .as(ResponseEndpointAllGet.class);
    }
}
