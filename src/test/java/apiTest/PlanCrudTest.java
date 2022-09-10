package apiTest;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.example.API.clients.PlansFactory;
import org.example.API.clients.PlanApiClient;
import org.example.API.dto.getAllModels.Entity;
import org.example.API.dto.getAllModels.ResponseEndpointAllGet;
import org.example.API.dto.getModels.ResponseEndpointGet;
import org.example.API.dto.postModels.PlanDetailsModel;
import org.example.API.dto.postModels.ResponseEndpointPost;
import org.example.UI.utils.PropertiesLoader;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class PlanCrudTest {

    public static final int PROJECTS_COUNT = 1;
    public static final String PROJECT = "project";
    private static final String PROJECT_NAME = PropertiesLoader.loadProperties().getProperty(PROJECT);
    int id;
    PlanApiClient planApiClient = new PlanApiClient();
    PlansFactory plansFactory = new PlansFactory();

    @Test
    public void createPlanTest() {
        PlanDetailsModel bodyPlan = plansFactory.prepareBodyPlanPost();

        ResponseEndpointPost actualResponseEndpointPost = planApiClient.postPlan(bodyPlan, PROJECT_NAME);
        id = actualResponseEndpointPost.getResult().getId();
        ResponseEndpointPost expectedResponseEndpointPost = plansFactory.preparePostPlanResponse(id);
        assertThat(actualResponseEndpointPost).as("The request response does not match the expected response")
                                              .isEqualTo(expectedResponseEndpointPost);

        ResponseEndpointGet actualResponseEndpointGet = planApiClient.getPlan(PROJECT_NAME, id);

        String created = actualResponseEndpointGet.getResult().getCreated();
        String updated = actualResponseEndpointGet.getResult().getUpdated();
        Date created_at = actualResponseEndpointGet.getResult().getCreatedAt();
        Date updated_at = actualResponseEndpointGet.getResult().getUpdatedAt();
        ResponseEndpointGet expectedResponseEndpointGet = plansFactory.prepareGetPlanResponse(id, created, updated, created_at, updated_at);

        assertThat(actualResponseEndpointGet).as("The request response does not match the expected response")
                                             .isEqualTo(expectedResponseEndpointGet);

        deletePlan();
    }

    @Test
    public void deletePlanTest() {
        ResponseEndpointPost actualResponseEndpointPost = plansFactory.createPlan();
        int id = actualResponseEndpointPost.getResult().getId();

        ResponseEndpointPost expectedResponseEndpointDelete = planApiClient.deletePlan(PROJECT_NAME, id);

        Response deletedPlanResponse = planApiClient.getPlanResponse(PROJECT_NAME, id);
        assertThat(deletedPlanResponse.statusCode()).as("Status code is incorrect in case for request for deleted plan")
                                                    .isEqualTo(404);
        assertThat(actualResponseEndpointPost).isEqualTo(expectedResponseEndpointDelete);
    }

    @Test
    public void validateGetProjectResponseAgainstSchemaTest() {
        createPlan();

        Response planResponse = planApiClient.getPlanResponse(PROJECT_NAME, id);
        planResponse.then().assertThat().body(matchesJsonSchemaInClasspath("plan-schema.json"));

        deletePlan();
    }

    @Test
    public void getAllPlanTest() {
        createPlan();

        ResponseEndpointGet expectedPlan = planApiClient.getPlan(PROJECT_NAME, id);

        ResponseEndpointAllGet actualResponseEndpointGetAll = planApiClient.getAllPlans(PROJECT_NAME);
        int total = actualResponseEndpointGetAll.getResult().getTotal();
        int filtered = actualResponseEndpointGetAll.getResult().getFiltered();
        int count = actualResponseEndpointGetAll.getResult().getCount();

        assertThat(total).as("The total less than one").isGreaterThanOrEqualTo(PROJECTS_COUNT);
        assertThat(filtered).as("The filtered less than one").isGreaterThanOrEqualTo(PROJECTS_COUNT);
        assertThat(count).as("The count less than one").isGreaterThanOrEqualTo(PROJECTS_COUNT);

        List<Entity> entities = actualResponseEndpointGetAll.getResult().getEntities();
        assertThat(entities).filteredOn(entity -> entity.getId() == id)
                            .isEqualTo(expectedPlan.convertResultToEntity());

        deletePlan();
    }

    public void createPlan() {
        ResponseEndpointPost actualResponseEndpointPost = plansFactory.createPlan();
        id = actualResponseEndpointPost.getResult().getId();
    }

    public void deletePlan() {
        planApiClient.deletePlan(PROJECT_NAME, id);
    }
}