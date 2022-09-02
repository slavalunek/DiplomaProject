package org.example.API.dto.getModels;

import com.google.gson.annotations.SerializedName;
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
public class Result {

    public int id;
    public String title;
    public String description;
    @SerializedName("cases_count")
    public int casesCount;
    public String created;
    public String updated;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public String updatedAt;
    @SerializedName("average_time")
    public int averageTime;
    public List<Case> cases;
}
