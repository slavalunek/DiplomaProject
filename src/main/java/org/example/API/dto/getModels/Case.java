package org.example.API.dto.getModels;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Builder
public class Case {
    @SerializedName("case_id")
    public int caseId;
    @SerializedName("assignee_id")
    public Object assigneeId;
}
