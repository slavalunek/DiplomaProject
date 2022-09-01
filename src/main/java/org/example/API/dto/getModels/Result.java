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
    public int cases_count;
    public String created;
    public String updated;
    public String created_at;
    public String updated_at;
    public int average_time;
    public List<Case> cases;
}
