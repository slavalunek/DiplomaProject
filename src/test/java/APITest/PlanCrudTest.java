package APITest;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.example.API.clients.PlansFactory;
import org.example.API.clients.PlanApiClient;
import org.example.API.dto.getAllModels.Entity;
import org.example.API.dto.getAllModels.ResponseEndpointAllGet;
import org.example.API.dto.getModels.ResponseEndpointGet;
import org.example.API.dto.postModels.PlanDetailsModel;
import org.example.API.dto.postModels.ResponseEndpointPost;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class PlanCrudTest {

    public static final String DEMO = "DEMO";
    PlanApiClient planApiClient = new PlanApiClient();
    PlansFactory plansFactory = new PlansFactory();

    public PlanCrudTest() throws ParseException {
    }

    @Test
    public void createPlanTest() {
        PlanDetailsModel bodyPlan = plansFactory.prepareBodyPlanPost();

        ResponseEndpointPost actualResponseEndpointPost = planApiClient.postPlan(bodyPlan, DEMO);
        int id = actualResponseEndpointPost.getResult().getId();
        ResponseEndpointPost expectedResponseEndpointPost = plansFactory.preparePostPlanResponse(id);
        assertThat(actualResponseEndpointPost).isEqualTo(expectedResponseEndpointPost);

        ResponseEndpointGet actualResponseEndpointGet = planApiClient.getPlan(DEMO, id);

        String created = actualResponseEndpointGet.getResult().getCreated();
        String updated = actualResponseEndpointGet.getResult().getUpdated();
        String created_at = actualResponseEndpointGet.getResult().getCreated_at();
        String updated_at = actualResponseEndpointGet.getResult().getUpdated_at();
        ResponseEndpointGet expectedResponseEndpointGet = plansFactory.prepareGetPlanResponse(id, created, updated, created_at, updated_at);

        assertThat(actualResponseEndpointGet).isEqualTo(expectedResponseEndpointGet);
    }

    @Test
    public void deletePlanTest() {
        ResponseEndpointPost actualResponseEndpointPost = plansFactory.createPlan();
        int id = actualResponseEndpointPost.getResult().getId();

        ResponseEndpointPost expectedResponseEndpointDelete = planApiClient.deletePlan(DEMO, id);

        Response deletedPlanResponse = planApiClient.getPlanResponse(DEMO, id);
        assertThat(deletedPlanResponse.statusCode()).as("Status code is incorrect in case for request for deleted plan")
                                                    .isEqualTo(404);
        assertThat(actualResponseEndpointPost).isEqualTo(expectedResponseEndpointDelete);
    }

    @Test
    public void getAllPlanTest() throws ParseException {
        ResponseEndpointAllGet expectedResponseEndpointGetAll = plansFactory.prepareAllGetPlanResponse();

        ResponseEndpointAllGet actualResponseEndpointGetAll = planApiClient.getAllProject(DEMO);
        assertThat(actualResponseEndpointGetAll).isEqualTo(expectedResponseEndpointGetAll);
    }

    @Test
    public void validateGetProjectResponseAgainstSchemaTest() {
        ResponseEndpointPost plan = plansFactory.createPlan();
        int id = plan.getResult().getId();

        Response planResponse = planApiClient.getPlanResponse(DEMO, id);
        planResponse.then().assertThat().body(matchesJsonSchemaInClasspath("plan-schema.json"));
    }

//    @Test
//    public void getAllPlanTest2() throws ParseException {
//        ResponseEndpointAllGet actualResponseEndpointGetAll = planApiClient.getAllProject(DEMO);
//        int total = actualResponseEndpointGetAll.getResult().getTotal();
//        int filtered = actualResponseEndpointGetAll.getResult().getFiltered();
//        int count = actualResponseEndpointGetAll.getResult().getCount();
//        List<Entity> entities = actualResponseEndpointGetAll.getResult().getEntities();
//        int id = entities.get(1);
//        Entity title = entities.get(2);
//        Entity description = entities.get(3);
//        Entity casesCount = entities.get(4);
//        Entity created = entities.get(5);
//        Entity updated = entities.get(6);
//        Entity createdAt = entities.get(7);
//        Entity updatedAt = entities.get(8);
//
//        ResponseEndpointAllGet expectedResponseEndpointGetAll = plansFactory.prepareAllGetPlanResponse2(total,filtered,
//                count,id,title,description,casesCount,created,updated,createdAt,updatedAt);
//        assertThat(actualResponseEndpointGetAll).isEqualTo(expectedResponseEndpointGetAll);
//    }
}
