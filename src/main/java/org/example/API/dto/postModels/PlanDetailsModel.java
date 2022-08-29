package org.example.API.dto.postModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Builder
public class PlanDetailsModel {

    public List<Integer> cases;
    public String title;
    public String description;
}
