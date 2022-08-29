package APITest;

import io.restassured.response.Response;
import org.example.API.clients.FactoryPlans;
import org.example.API.clients.PlanApiClient;
import org.example.API.dto.getAllModels.ResponseEndpointAllGet;
import org.example.API.dto.getModels.ResponseEndpointGet;
import org.example.API.dto.postModels.PlanDetailsModel;
import org.example.API.dto.postModels.ResponseEndpointPost;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlanCrudTest {

    PlanApiClient planApiClient = new PlanApiClient();
    FactoryPlans factoryPlans = new FactoryPlans();

    @Test
    public void createPlanTest() {
        PlanDetailsModel bodyPlan = factoryPlans.prepareBodyPost();

        ResponseEndpointPost actualResponseEndpointPost = planApiClient.postPlan(bodyPlan, "DEMO");
        int id = actualResponseEndpointPost.getResult().getId();
        ResponseEndpointPost expectedResponseEndpointPost = factoryPlans.preparePostResponse(id);
        assertThat(actualResponseEndpointPost).isEqualTo(expectedResponseEndpointPost);

        ResponseEndpointGet actualResponseEndpointGet = planApiClient.getPlan("DEMO", id);

        String created = actualResponseEndpointGet.getResult().getCreated();
        String updated = actualResponseEndpointGet.getResult().getUpdated();
        String created_at = actualResponseEndpointGet.getResult().getCreated_at();
        String updated_at = actualResponseEndpointGet.getResult().getUpdated_at();
        ResponseEndpointGet expectedResponseEndpointGet = factoryPlans.prepareGetResponse(id, created, updated, created_at, updated_at);

        assertThat(actualResponseEndpointGet).isEqualTo(expectedResponseEndpointGet);
    }

    @Test
    public void deletePlanTest() {
        PlanDetailsModel bodyPlan = factoryPlans.prepareBodyPost();
        ResponseEndpointPost actualResponseEndpointPost = planApiClient.postPlan(bodyPlan, "DEMO");
        int id = actualResponseEndpointPost.getResult().getId();

        ResponseEndpointPost expectedResponseEndpointDelete = planApiClient.deletePlan("DEMO", id);

        Response deletedPlanResponse =planApiClient.getPlanResponse("DEMO", id);
        assertThat(deletedPlanResponse.statusCode()).as("Status code is incorrect in case for request for deleted plan")
                                                       .isEqualTo(404);
        assertThat(actualResponseEndpointPost).isEqualTo(expectedResponseEndpointDelete);
    }

    @Test
    public void getAllPlanTest() {
        ResponseEndpointAllGet expectedResponseEndpointGetAll = factoryPlans.prepareAllGetResponse();

        ResponseEndpointAllGet actualResponseEndpointGetAll = planApiClient.getAllProject("DEMO");
        assertThat(actualResponseEndpointGetAll).isEqualTo(expectedResponseEndpointGetAll);
    }
}
