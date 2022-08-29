package org.example.API.clients;

import com.github.javafaker.Faker;
import org.example.API.dto.getAllModels.Entity;
import org.example.API.dto.getAllModels.ResponseEndpointAllGet;
import org.example.API.dto.getModels.Case;
import org.example.API.dto.getModels.ResponseEndpointGet;
import org.example.API.dto.postModels.PlanDetailsModel;
import org.example.API.dto.postModels.ResponseEndpointPost;
import org.example.API.dto.postModels.Result;

import java.util.Collections;
import java.util.List;

public class FactoryPlans {

    Faker faker = new Faker();
    String title = faker.name().title();
    String description = faker.name().title();

    public PlanDetailsModel prepareBodyPost() {
        return PlanDetailsModel.builder()
                               .cases(Collections.singletonList(10))
                               .title(title)
                               .description(description).build();
    }

    public ResponseEndpointPost preparePostResponse(int id) {
        return ResponseEndpointPost.builder()
                                   .status(true)
                                   .result(Result.builder()
                                                 .id(id).build()).build();
    }

    public ResponseEndpointPost prepareDeleteResponse(int id) {
        return ResponseEndpointPost.builder()
                                   .status(true)
                                   .result(Result.builder()
                                                 .id(id).build()).build();
    }

    public ResponseEndpointGet prepareGetResponse(int id, String created, String updated, String created_at, String updated_at) {
        return ResponseEndpointGet.builder()
                                  .status(true)
                                  .result(org.example.API.dto.getModels.Result.builder()
                                                                              .id(id)
                                                                              .title(title)
                                                                              .description(description)
                                                                              .cases_count(1)
                                                                              .created(created)
                                                                              .updated(updated)
                                                                              .created_at(created_at)
                                                                              .updated_at(updated_at)
                                                                              .average_time(0)
                                                                              .cases(List.of(Case.builder()
                                                                                                 .case_id(10)
                                                                                                 .assignee_id(null).build())).build()).build();
    }

    public ResponseEndpointAllGet prepareAllGetResponse() {
        return ResponseEndpointAllGet.builder()
                                     .status(true)
                                     .result(org.example.API.dto.getAllModels.Result.builder()
                                                                                    .total(2)
                                                                                    .filtered(2)
                                                                                    .count(2)
                                                                                    .entities(List.of(Entity.builder()
                                                                                                            .id(27)
                                                                                                            .title("Central Accountability Designer")
                                                                                                            .description("International Assurance Assistant")
                                                                                                            .cases_count(1)
                                                                                                            .created("2022-08-27 08:36:37")
                                                                                                            .updated("2022-08-27 08:36:37")
                                                                                                            .created_at("2022-08-27T08:36:37+00:00")
                                                                                                            .updated_at("2022-08-27T08:36:37+00:00").build(),
                                                                                            Entity.builder()
                                                                                                   .id(28)
                                                                                                   .title("Regional Applications Orchestrator")
                                                                                                   .description("Product Tactics Assistant")
                                                                                                   .cases_count(1)
                                                                                                   .created("2022-08-27 08:37:24")
                                                                                                   .updated("2022-08-27 08:37:24")
                                                                                                   .created_at("2022-08-27T08:37:24+00:00")
                                                                                                   .updated_at("2022-08-27T08:37:24+00:00").build())).build()).build();
    }
}
