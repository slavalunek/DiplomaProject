package org.example.API.clients;

import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.example.API.dto.getModels.Case;
import org.example.API.dto.getModels.ResponseEndpointGet;
import org.example.API.dto.postModels.PlanDetailsModel;
import org.example.API.dto.postModels.ResponseEndpointPost;
import org.example.API.dto.postModels.Result;
import org.example.UI.utils.PropertiesLoader;

import java.text.*;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Log4j2
public class PlansFactory {

    Faker faker = new Faker();
    String title = faker.name().title();
    String description = faker.name().title();
    PlanApiClient planApiClient = new PlanApiClient();
    Properties properties = PropertiesLoader.loadProperties();


    public PlansFactory() throws ParseException {
    }

    public PlanDetailsModel prepareBodyPlanPost() {
        return PlanDetailsModel.builder()
                               .cases(Collections.singletonList(10))
                               .title(title)
                               .description(description).build();
    }

    public ResponseEndpointPost preparePostPlanResponse(int id) {
        return ResponseEndpointPost.builder()
                                   .status(true)
                                   .result(Result.builder()
                                                 .id(id).build()).build();
    }

    public ResponseEndpointPost prepareDeletePlanResponse(int id) {
        return ResponseEndpointPost.builder()
                                   .status(true)
                                   .result(Result.builder()
                                                 .id(id).build()).build();
    }

    public ResponseEndpointGet prepareGetPlanResponse(int id, String created, String updated, String created_at, String updated_at) {
        return ResponseEndpointGet.builder()
                                  .status(true)
                                  .result(org.example.API.dto.getModels.Result.builder()
                                                                              .id(id)
                                                                              .title(title)
                                                                              .description(description)
                                                                              .casesCount(1)
                                                                              .created(created)
                                                                              .updated(updated)
                                                                              .createdAt(created_at)
                                                                              .updatedAt(updated_at)
                                                                              .averageTime(0)
                                                                              .cases(List.of(Case.builder()
                                                                                                 .caseId(10)
                                                                                                 .assigneeId(null).build())).build()).build();
    }

    public ResponseEndpointPost createPlan() {
        PlanDetailsModel bodyPlan = prepareBodyPlanPost();
        return planApiClient.postPlan(bodyPlan, properties.getProperty("project"));
    }
}