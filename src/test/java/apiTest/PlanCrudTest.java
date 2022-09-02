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

import java.text.ParseException;
import java.util.List;
import java.util.Properties;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class PlanCrudTest {

    public static final int EXPECTED = 1;
    public static final String PROJECT = "project";
    int id;
    PlanApiClient planApiClient = new PlanApiClient();
    PlansFactory plansFactory = new PlansFactory();
    Properties properties = PropertiesLoader.loadProperties();

    public PlanCrudTest() throws ParseException {
    }

    @Test
    public void createPlanTest() {
        PlanDetailsModel bodyPlan = plansFactory.prepareBodyPlanPost();

        ResponseEndpointPost actualResponseEndpointPost = planApiClient.postPlan(bodyPlan, properties.getProperty(PROJECT));
        id = actualResponseEndpointPost.getResult().getId();
        ResponseEndpointPost expectedResponseEndpointPost = plansFactory.preparePostPlanResponse(id);
        assertThat(actualResponseEndpointPost).isEqualTo(expectedResponseEndpointPost);

        ResponseEndpointGet actualResponseEndpointGet = planApiClient.getPlan(properties.getProperty(PROJECT), id);

        String created = actualResponseEndpointGet.getResult().getCreated();
        String updated = actualResponseEndpointGet.getResult().getUpdated();
        String created_at = actualResponseEndpointGet.getResult().getCreatedAt();
        String updated_at = actualResponseEndpointGet.getResult().getUpdatedAt();
        ResponseEndpointGet expectedResponseEndpointGet = plansFactory.prepareGetPlanResponse(id, created, updated, created_at, updated_at);

        assertThat(actualResponseEndpointGet).isEqualTo(expectedResponseEndpointGet);

        deletePlan();
    }

    @Test
    public void deletePlanTest() {
        ResponseEndpointPost actualResponseEndpointPost = plansFactory.createPlan();
        int id = actualResponseEndpointPost.getResult().getId();

        ResponseEndpointPost expectedResponseEndpointDelete = planApiClient.deletePlan(properties.getProperty(PROJECT), id);

        Response deletedPlanResponse = planApiClient.getPlanResponse(properties.getProperty(PROJECT), id);
        assertThat(deletedPlanResponse.statusCode()).as("Status code is incorrect in case for request for deleted plan")
                                                    .isEqualTo(404);
        assertThat(actualResponseEndpointPost).isEqualTo(expectedResponseEndpointDelete);
    }

    @Test
    public void validateGetProjectResponseAgainstSchemaTest() {
        createPlan();

        Response planResponse = planApiClient.getPlanResponse(properties.getProperty(PROJECT), id);
        planResponse.then().assertThat().body(matchesJsonSchemaInClasspath("plan-schema.json"));

        deletePlan();
    }

    @Test
    public void getAllPlanTest() throws ParseException {
        createPlan();

        ResponseEndpointAllGet actualResponseEndpointGetAll = planApiClient.getAllPlan(properties.getProperty(PROJECT));
        int total = actualResponseEndpointGetAll.getResult().getTotal();
        int filtered = actualResponseEndpointGetAll.getResult().getFiltered();
        int count = actualResponseEndpointGetAll.getResult().getCount();

        assertThat(total).isEqualTo(EXPECTED);
        assertThat(filtered).isEqualTo(EXPECTED);
        assertThat(count).isEqualTo(EXPECTED);

        List<Entity> entities = actualResponseEndpointGetAll.getResult().getEntities();
        assertThat(entities).filteredOn(entity -> entity.getId() == id)
                .isEqualTo(entities);

        deletePlan();
    }

    public void createPlan() {
        ResponseEndpointPost actualResponseEndpointPost = plansFactory.createPlan();
        id = actualResponseEndpointPost.getResult().getId();
    }

    public void deletePlan(){
        planApiClient.deletePlan(properties.getProperty(PROJECT), id);
    }
}
