package org.example.API.clients;

import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.example.API.dto.getAllModels.Entity;
import org.example.API.dto.getAllModels.ResponseEndpointAllGet;
import org.example.API.dto.getModels.Case;
import org.example.API.dto.getModels.ResponseEndpointGet;
import org.example.API.dto.postModels.PlanDetailsModel;
import org.example.API.dto.postModels.ResponseEndpointPost;
import org.example.API.dto.postModels.Result;

import java.text.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Log4j2
public class PlansFactory {

    Faker faker = new Faker();
    String title = faker.name().title();
    String description = faker.name().title();
    PlanApiClient planApiClient = new PlanApiClient();


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

    public ResponseEndpointAllGet prepareAllGetPlanResponse() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        String dateInString1 = "2022-08-27T08:36:37+00:00";
        String dateInString2 = "2022-08-27T08:37:24+00:00";
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = formatter.parse(dateInString1);
            date2 = formatter.parse(dateInString2);
        } catch (ParseException e) {
            log.info("The page {} was not opened because of error: {}", "Login", e.getMessage());
        }
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
                                                                                                            .created_at(date1)
                                                                                                            .updated_at(date1).build(),
                                                                                            Entity.builder()
                                                                                                  .id(28)
                                                                                                  .title("Regional Applications Orchestrator")
                                                                                                  .description("Product Tactics Assistant")
                                                                                                  .cases_count(1)
                                                                                                  .created("2022-08-27 08:37:24")
                                                                                                  .updated("2022-08-27 08:37:24")
                                                                                                  .created_at(date2)
                                                                                                  .updated_at(date2).build())).build()).build();
    }

//    public ResponseEndpointAllGet prepareAllGetPlanResponse2(int total,int filtered, int count, int id,String title,
//                                                             String description,int cases_count,String created,
//                                                             String updated,String created_at,String updated_at) throws ParseException {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
//        String dateInString1 = created_at;
//        String dateInString2 = updated_at;
//        Date date1 = null;
//        Date date2 = null;
//        try {
//            date1 = formatter.parse(dateInString1);
//            date2 = formatter.parse(dateInString2);
//        } catch (ParseException e) {
//            log.info("The page {} was not opened because of error: {}", "Login", e.getMessage());
//        }
//        return ResponseEndpointAllGet.builder()
//                                     .status(true)
//                                     .result(org.example.API.dto.getAllModels.Result.builder()
//                                                                                    .total(total)
//                                                                                    .filtered(filtered)
//                                                                                    .count(count)
//                                                                                    .entities(List.of(Entity.builder()
//                                                                                                            .id(id)
//                                                                                                            .title(title)
//                                                                                                            .description(description)
//                                                                                                            .cases_count(cases_count)
//                                                                                                            .created(created)
//                                                                                                            .updated(updated)
//                                                                                                            .created_at(date1)
//                                                                                                            .updated_at(date2).build())).build()).build();
//    }

    public ResponseEndpointPost createPlan(){
        PlanDetailsModel bodyPlan = prepareBodyPlanPost();
         ResponseEndpointPost actualResponseEndpointPost = planApiClient.postPlan(bodyPlan, "DEMO");
        return actualResponseEndpointPost;
    }
}
